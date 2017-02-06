package by.tr.xml.parsers.view;

import java.util.Iterator;

import by.tr.xml.parsers.bean.WebApp;
import by.tr.xml.parsers.bean.WebAppElement;
import by.tr.xml.parsers.controller.Controller;
import by.tr.xml.parsers.controller.exception.ControllerException;

public class View {
	
	public static void main(String[] args) {
		String filePath = "resource/web.xml";
		Controller controller = new Controller();
		
		try {
			WebApp webApp = controller.parseWithStax(filePath);
			Iterator<WebAppElement> iter = webApp.getElements().iterator();
			
				while(iter.hasNext()) {
					System.out.println(iter.next());
				}
				System.out.println(webApp.getId());
				System.out.println(webApp.getVersion());
			} catch (ControllerException e) {
			e.printStackTrace();
		}
	}
}
