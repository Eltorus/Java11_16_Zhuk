package by.tr.xml.parsers.bean;

import java.util.ArrayList;
import java.util.List;

public class WebApp {
	private String id;
	private String version;
	
	private final List <WebAppElement> elements = new ArrayList<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List <WebAppElement> getElements() {
		return elements;
	}
	public void addElement(WebTagName name,WebAppElement element) {
		elements.add(element);
	}
	public void setElements(List <WebAppElement> elements) {
		this.elements.addAll(elements);
	}
	
}
