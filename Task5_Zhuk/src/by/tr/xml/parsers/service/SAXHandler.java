package by.tr.xml.parsers.service;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


import by.tr.xml.parsers.bean.WebApp;

public class SAXHandler extends DefaultHandler {
	private StringBuilder content;
	private WebApp webapp;
	private final ElementCreator creator = new ElementCreator();
	
	public void startElement(String uri, String localName, String qName, Attributes Attributes) {
		content = new StringBuilder();
		
		if(qName.equals("web-app")){
			webapp = new WebApp();
			webapp.setId(Attributes.getValue("id"));
			webapp.setVersion(Attributes.getValue("version"));
		}
		
		creator.startElement(qName);
	}
	
	public void characters(char[] buffer, int start, int end) {
		content.append(buffer, start, end);
	}
	
	public void endElement(String uri, String localName, String qName) {
		creator.endElement(qName, content.toString());
	}
	
	public WebApp getWebApp() {
		webapp.setElements(creator.getWebApp().getElements());
		return webapp;
	}
}
