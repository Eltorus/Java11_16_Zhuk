package by.tr.xml.parsers.bean.element;

import by.tr.xml.parsers.bean.WebAppElement;

public class ErrorPage implements WebAppElement{
	private String type;
	private String code;
	private String location;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
