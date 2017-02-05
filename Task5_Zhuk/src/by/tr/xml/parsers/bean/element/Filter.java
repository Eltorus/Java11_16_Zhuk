package by.tr.xml.parsers.bean.element;

import java.util.ArrayList;
import java.util.List;

import by.tr.xml.parsers.bean.WebAppElement;

public class Filter implements WebAppElement{
	private String name;
	private String filterclass;
	private final List<InitParam> initparam = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilterclass() {
		return filterclass;
	}
	public void setFilterclass(String filterclass) {
		this.filterclass = filterclass;
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
