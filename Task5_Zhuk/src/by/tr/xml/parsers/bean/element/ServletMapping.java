package by.tr.xml.parsers.bean.element;

import by.tr.xml.parsers.bean.WebAppElement;

public class ServletMapping implements WebAppElement{
	private String servletname;
	private String urlpattern;
	
	public String getServletname() {
		return servletname;
	}
	public void setServletname(String servletname) {
		this.servletname = servletname;
	}
	public String getUrlpattern() {
		return urlpattern;
	}
	public void setUrlpattern(String urlpattern) {
		this.urlpattern = urlpattern;
	}
	
}
