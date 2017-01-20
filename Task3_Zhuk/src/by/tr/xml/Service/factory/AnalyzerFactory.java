package by.tr.xml.Service.factory;

import by.tr.xml.Service.Analyzer;
import by.tr.xml.Service.impl.AnalyzerImpl;

public class AnalyzerFactory {
	private static final Analyzer instance = new AnalyzerImpl();

	public static Analyzer getInstance() {
		return instance;
	}
}
