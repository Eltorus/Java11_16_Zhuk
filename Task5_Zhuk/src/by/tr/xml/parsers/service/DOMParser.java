package by.tr.xml.parsers.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import by.tr.xml.parsers.bean.WebApp;
import by.tr.xml.parsers.bean.WebTagName;
import by.tr.xml.parsers.bean.element.DisplayName;
import by.tr.xml.parsers.bean.element.ErrorPage;
import by.tr.xml.parsers.bean.element.Filter;
import by.tr.xml.parsers.bean.element.FilterMapping;
import by.tr.xml.parsers.bean.element.InitParam;
import by.tr.xml.parsers.bean.element.Listener;
import by.tr.xml.parsers.bean.element.Servlet;
import by.tr.xml.parsers.bean.element.ServletMapping;
import by.tr.xml.parsers.bean.element.WelcomeFileList;
import by.tr.xml.parsers.service.exception.ServiceException;

public class DOMParser {
	private WebApp webApp;
	
	public WebApp parse(String filePath) throws ServiceException {
		try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = factory.newDocumentBuilder();
				Document document = db.parse(filePath);
				
				Element root = document.getDocumentElement();
				webApp = new WebApp();
				webApp.setId(root.getAttribute("id"));
				webApp.setVersion(root.getAttribute("version"));
				
				for(WebTagName tagName: WebTagName.values()) {
					NodeList children = root.getElementsByTagName(tagName.toString().toLowerCase().replace("_", "-"));
					for (int i = 0; i < children.getLength(); i++) {
						if(children!=null) {
							Element childElement = (Element) children.item(i);
							createElement(tagName,childElement);
						}
					}
				}
		}catch(ParserConfigurationException e) {
			throw new ServiceException(e);
		} catch (SAXException e) {
			throw new ServiceException(e);
		} catch (IOException e) {
			throw new ServiceException(e);
		}
		return webApp;
	}
	
	private void createElement(WebTagName name, Element childElement) {
		switch(name) {
		case DISPLAY_NAME:
			DisplayName dispname = new DisplayName();
			dispname.setContent(childElement.getTextContent());
			webApp.addElement(name,dispname);
			break;
		
		case WELCOME_FILE_LIST:
			WelcomeFileList welcomeList = new WelcomeFileList();
			
			for(Element element:getChildrenByName(childElement,"welcome-file")) {
				welcomeList.addWelcomefile(element.getTextContent());
			}
			webApp.addElement(name,welcomeList);
			break;
		
		case FILTER:
			Filter filter = new Filter();
			filter.setFilterclass(getSingleChild(childElement,"filter-class").getTextContent());
			filter.setName(getSingleChild(childElement,"filter-name").getTextContent());
			
			InitParam initparamFilter =null;
			for(Element element:getChildrenByName(childElement,"init-param")) {
				initparamFilter = new InitParam();
				initparamFilter.setParamName(getSingleChild(element,"param-name").getTextContent());
				initparamFilter.setParamValue(getSingleChild(element,"param-value").getTextContent());
				filter.addInitparam(initparamFilter);
			}
			
			webApp.addElement(name,filter);
			break;
		
		case FILTER_MAPPING:
			FilterMapping filterMapping = new FilterMapping();
			
			filterMapping.setFiltername(getSingleChild(childElement,"filter-name").getTextContent());
			filterMapping.setUrlpattern(getSingleChild(childElement,"url-pattern").getTextContent());
			filterMapping.setDispatcher(getSingleChild(childElement,"dispatcher").getTextContent());
			
			webApp.addElement(name,filterMapping);
			break;
		
		case LISTENER:
			Listener listener = new Listener();
			
			listener.setListenerclass(getSingleChild(childElement,"listener-class").getTextContent());

			webApp.addElement(name,listener);
			break;
		
		case SERVLET:
			Servlet servlet = new Servlet();
			
			servlet.setName(getSingleChild(childElement,"servlet-name").getTextContent());
			servlet.setServletclass(getSingleChild(childElement,"servlet-class").getTextContent());
			
			InitParam initparamServlet =null;
			for(Element element:getChildrenByName(childElement,"init-param")){
				initparamServlet = new InitParam();
				initparamServlet.setParamName(getSingleChild(element,"param-name").getTextContent());
				initparamServlet.setParamValue(getSingleChild(element,"param-value").getTextContent());
				servlet.addInitparam(initparamServlet);
			}
			webApp.addElement(name,servlet);
			break;
		
		case SERVLET_MAPPING:
			ServletMapping servletMapping = new ServletMapping();
			
			servletMapping.setServletname(getSingleChild(childElement,"servlet-name").getTextContent());
			servletMapping.setUrlpattern(getSingleChild(childElement,"url-pattern").getTextContent());
			
			webApp.addElement(name,servletMapping);
			break;
		
		case ERROR_PAGE:
			ErrorPage errorPage = new ErrorPage();
			
			Element element = getSingleChild(childElement,"error-code");
			if(element!=null) {
				errorPage.setCode(element.getTextContent());
			}
			
			element = getSingleChild(childElement,"exception-type");
			if(element!=null) {
				errorPage.setType(element.getTextContent());
			}
			
			errorPage.setLocation(getSingleChild(childElement,"location").getTextContent());
			webApp.addElement(name,errorPage);
			break;
		
		default:
			break;
		}
	}
	
	private Element getSingleChild(Element element, String childName) {
		NodeList nlist = element.getElementsByTagName(childName);
		Element child = (Element) nlist.item(0);
		return child;
	}
	
	private List<Element> getChildrenByName(Element element, String childName) {
		List<Element> children = new ArrayList<>();
		NodeList nlist = element.getElementsByTagName(childName);
		
		for(int j=0;j<nlist.getLength();j++) {
			children.add((Element)nlist.item(j));
		}
		return children;
	}
}