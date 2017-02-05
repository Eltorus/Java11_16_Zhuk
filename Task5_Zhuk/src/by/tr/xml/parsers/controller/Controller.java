package by.tr.xml.parsers.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.tr.xml.parsers.bean.WebApp;
import by.tr.xml.parsers.controller.exception.ControllerException;
import by.tr.xml.parsers.service.DOMParser;
import by.tr.xml.parsers.service.SAXHandler;
import by.tr.xml.parsers.service.StAXParser;
import by.tr.xml.parsers.service.exception.ServiceException;

public class Controller {
	private WebApp webApp;
	
	public WebApp parseWithDom(String filePath) throws ControllerException{
		DOMParser domParser = new DOMParser();
		try {
			webApp = domParser.parse(filePath);
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
		return webApp;
	}
	
	public WebApp parseWithSax(String filePath) throws ControllerException {
		try {
			SAXHandler saxHandler = new SAXHandler();
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(saxHandler);
			reader.parse(filePath);
			webApp = saxHandler.getWebApp();
		} catch (SAXException e) {
			throw new ControllerException(e);
		}
		catch (IOException e) {
			throw new ControllerException(e);
		}
		return webApp;
	}
	
	public WebApp parseWithStax(String filePath) throws ControllerException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			InputStream input = new FileInputStream(filePath);
			XMLStreamReader reader = factory.createXMLStreamReader(input);
			StAXParser parser = new StAXParser();
			webApp = parser.process(reader);
		} catch (FileNotFoundException e) {
			throw new ControllerException(e);
		} 
		catch (XMLStreamException e) {
			throw new ControllerException(e);
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
		return webApp;
	}
}
