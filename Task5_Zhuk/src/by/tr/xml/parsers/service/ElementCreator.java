package by.tr.xml.parsers.service;

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

public class ElementCreator {
	private WebApp webapp;
	private DisplayName displayName;
	private ErrorPage errorPage;
	private FilterMapping filterMapping;
	private Filter filter;
	private InitParam initParam;
	private Listener listener;
	private Servlet servlet;
	private ServletMapping servletMapping;
	private WelcomeFileList welcomeList;
	
	public void startElement(String localName){
		WebTagName currentTag = WebTagName.valueOf(localName.toUpperCase().replace("-","_"));
		switch(currentTag){
		case WEB_APP:
			webapp = new WebApp();
			break;
			
		case DISPLAY_NAME:
			displayName = new DisplayName();
			break;
		
		case WELCOME_FILE_LIST:
			welcomeList = new WelcomeFileList();
			break;
		
		case FILTER_MAPPING:
			filterMapping = new FilterMapping();
			break;
		
		case INIT_PARAM:
			initParam = new InitParam();
			break;
		
		case FILTER:
			filter = new Filter();
			break;
		
		case LISTENER:
			listener = new Listener();
			break;
		
		case SERVLET:
			servlet = new Servlet();
			break;
		
		case SERVLET_MAPPING:
			servletMapping = new ServletMapping();
			break;
			
		case ERROR_PAGE:
			errorPage = new ErrorPage();
			break;
			
		default:
			break;
		}
	}
	
	public void endElement(String elementName, String content) {
		WebTagName currentTag = WebTagName.valueOf(elementName.toUpperCase().replace("-","_"));
		switch(currentTag){
		case DISPLAY_NAME:
			displayName.setContent(content.toString());
			webapp.addElement(currentTag, displayName);
			displayName = null;
			break;
		
		case WELCOME_FILE:
			welcomeList.addWelcomefile(content.toString());
			break;
		
		case WELCOME_FILE_LIST:
			webapp.addElement(currentTag, welcomeList);
			welcomeList = null;
			break;
		
		case FILTER_NAME:
			if(filter != null) {
				filter.setName(content.toString());
			}
			else {
				filterMapping.setFiltername(content.toString());
			}
			break;
		
		case FILTER_CLASS:
			filter.setFilterclass(content.toString());
			break;
		
		case PARAM_NAME:
			initParam.setParamName(content.toString());
			break;
		
		case PARAM_VALUE:
			initParam.setParamValue(content.toString());
			break;
		
		case INIT_PARAM:
			if(filter != null) {
				filter.addInitparam(initParam);
			}
			else {
				servlet.addInitparam(initParam);
			}
			initParam = null;
			break;
		
		case FILTER:
			webapp.addElement(currentTag, filter);
			filter = null;
			break;
		
		case URL_PATTERN:
			if(servletMapping != null) {
				servletMapping.setUrlpattern(content.toString());
			}
			else {
				filterMapping.setUrlpattern(content.toString());
			}
			break;
		
		case DISPATCHER:
			filterMapping.setDispatcher(content.toString());
			break;
		
		case LISTENER_CLASS:
			listener.setListenerclass(content.toString());
			break;
		
		case LISTENER:
			webapp.addElement(currentTag, listener);
			listener = null;
			break;
		
		case SERVLET_NAME:
			if(servletMapping != null){
				servletMapping.setServletname(content.toString());
			}
			else{
				servlet.setName(content.toString());
			}
			break;
		
		case FILTER_MAPPING:
			webapp.addElement(currentTag, filterMapping);
			filterMapping = null;
			break;
		
		case SERVLET:
			webapp.addElement(currentTag, servlet);
			servlet = null;
			break;
		
		case SERVLET_CLASS:
			servlet.setServletclass(content.toString());
			break;
		
		case SERVLET_MAPPING:
			webapp.addElement(currentTag, servletMapping);
			servletMapping = null;
			break;
		
		case EXCEPTION_TYPE:
			errorPage.setType(content.toString());
			break;
			
		case LOCATION:
			errorPage.setLocation(content.toString());
			break;
			
		case ERROR_CODE:
			errorPage.setCode(content.toString());
			break;
			
		case ERROR_PAGE:
			webapp.addElement(currentTag, errorPage);
			errorPage = null;
			break;
			
		default:
			break;
		}
	}
	
	public WebApp getWebApp(){
		return webapp;
	}

}
