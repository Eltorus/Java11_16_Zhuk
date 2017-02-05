package by.tr.xml.parsers.service;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import by.tr.xml.parsers.bean.WebApp;
import by.tr.xml.parsers.service.exception.ServiceException;

public class StAXParser {  
	
	public WebApp process(XMLStreamReader reader) throws ServiceException{
		WebApp webapp = null;
		StringBuffer content = null;
		ElementCreator creator = new ElementCreator();
		
		try {
			while(reader.hasNext()) {
				int type = reader.next();
				
				switch(type) {
				case XMLEvent.START_ELEMENT:
					content = new StringBuffer();
					
					if(reader.getLocalName().equals("web-app")) {
						webapp = new WebApp();
						webapp.setId(reader.getAttributeValue(1));
						webapp.setVersion(reader.getAttributeValue(2));
					}
					
					creator.startElement(reader.getLocalName());
					break;
					
				case XMLEvent.END_ELEMENT:
					creator.endElement(reader.getLocalName(), content.toString());
					break;
					
				case XMLEvent.CHARACTERS:
					content.append(reader.getText());
					break;
				}
			}
			
			webapp.setElements(creator.getWebApp().getElements());
		} catch (XMLStreamException e) {
			throw new ServiceException(e);
		}
		
		return webapp;
	}
}
