package by.tr.xml.parsers.bean.element;

import by.tr.xml.parsers.bean.WebAppElement;

public class FilterMapping implements WebAppElement{
	private String filtername;
	private String urlpattern;
	private String dispatcher;
	
	public String getFiltername() {
		return filtername;
	}
	public void setFiltername(String filtername) {
		this.filtername = filtername;
	}
	public String getUrlpattern() {
		return urlpattern;
	}
	public void setUrlpattern(String urlpattern) {
		this.urlpattern = urlpattern;
	}
	public String getDispatcher() {
		return dispatcher;
	}
	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}	
}
