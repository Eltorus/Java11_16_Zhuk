package by.tr.xml.view;

import by.tr.xml.Service.Analyzer;
import by.tr.xml.Service.exception.ServiceException;
import by.tr.xml.Service.factory.AnalyzerFactory;
import by.tr.xml.bean.NodeInfo;

public class View {
	public static void main(String[] args) {
		String filename1 = "src/resource/breakfastMenu.xml";
		String filename2 = "src/resource/notes.xml";
		AnalyzerFactory analyzerFactory = AnalyzerFactory.getInstance();
		Analyzer a = analyzerFactory.getAnalyzer();
		try {
			a.setFile(filename1);
			NodeInfo info = null;
			while ((info = a.next()) != null) {
				System.out.println(info);
			}
			System.out.println("\n");
			a.setFile(filename2);
			info = null;
			while ((info = a.next()) != null) {
				System.out.println(info);
			}
			a.close();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
	}
}
