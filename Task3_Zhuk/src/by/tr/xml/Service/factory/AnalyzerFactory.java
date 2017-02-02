package by.tr.xml.Service.factory;

import by.tr.xml.Service.Analyzer;
import by.tr.xml.Service.impl.AnalyzerImpl;

public class AnalyzerFactory {
	private static final AnalyzerFactory instance = new AnalyzerFactory();
	private final Analyzer analyzer = new AnalyzerImpl();
	
	private AnalyzerFactory(){}

	public static AnalyzerFactory getInstance() {
		return instance;
	}

	public Analyzer getAnalyzer() {
		return analyzer;
	}
}
