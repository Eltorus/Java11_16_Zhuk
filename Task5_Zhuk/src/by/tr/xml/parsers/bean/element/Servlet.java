package by.tr.xml.parsers.bean.element;

import java.util.ArrayList;
import java.util.List;

import by.tr.xml.parsers.bean.WebAppElement;

public class Servlet implements WebAppElement{
	private String name;
	private String servletclass;
	private final List<InitParam> initparam = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getServletclass() {
		return servletclass;
	}
	public void setServletclass(String servletclass) {
		this.servletclass = servletclass;
	}
	public List<InitParam> getInitparam() {
		return initparam;
	}
	public void addInitparam(InitParam initparam) {
		this.initparam.add(initparam);
	}
	public void setInitparam(List<InitParam> initparam){
		this.initparam.addAll(initparam);
	}
	
}
