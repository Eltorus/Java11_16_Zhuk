package by.tr.xml.parsers.bean.element;

import java.util.ArrayList;
import java.util.List;

import by.tr.xml.parsers.bean.WebAppElement;

public class WelcomeFileList implements WebAppElement{
	private List<String> welcomefiles = new ArrayList<>();

	public List<String> getWelcomefiles() {
		return welcomefiles;
	}

	public void addWelcomefile(String welcomefile) {
		this.welcomefiles.add(welcomefile);
	}
	public void setInitparam(List<String> welcomefiles){
		this.welcomefiles.addAll(welcomefiles);
	}
}
