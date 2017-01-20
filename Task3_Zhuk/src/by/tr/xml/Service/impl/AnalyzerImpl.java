package by.tr.xml.Service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import by.tr.xml.DAO.DAOFile;
import by.tr.xml.DAO.exception.DAOException;
import by.tr.xml.DAO.factory.DAOFactory;
import by.tr.xml.Service.Analyzer;
import by.tr.xml.Service.exception.ServiceException;
import by.tr.xml.bean.NodeInfo;
import by.tr.xml.bean.NodeType;

public class AnalyzerImpl implements Analyzer {
	private DAOFile daofile;

	@Override
	public void setFile(String filename) throws ServiceException {
		daofile = DAOFactory.getInstance().getDaofile();
		try {
			daofile.setFile(filename);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public NodeInfo next() throws ServiceException {
		daofile = DAOFactory.getInstance().getDaofile();
		NodeInfo output = new NodeInfo();
		try {
			String line = daofile.getLexeme();
			if (line == null)
				return null;

			String inittag = "<(\\?.*)>";
			String tag = "<(\\/?(\\w.*?))>";
			String closeTag = "<(\\/(\\w.*?))>";
			String openTag = "(<(\\w.*?)>)";
			String selfcloseTag = "<((\\w.*?)\\/)>";
			String attributes = " (\\w*)\\s?=\\s?\"(\\w*)\"";
			String tagName = "<\\/?([^<>\\s/]*)";

			Pattern inittagPattern = Pattern.compile(inittag);
			Matcher initmatcher = inittagPattern.matcher(line);
			if (initmatcher.find())
				line = daofile.getLexeme();

			Pattern tagPattern = Pattern.compile(tag);
			Matcher matcher = tagPattern.matcher(line);
			if (matcher.find()) {
				Pattern opentagPattern = Pattern.compile(openTag);
				Matcher matcherOT = opentagPattern.matcher(matcher.group());
				Pattern closetagPattern = Pattern.compile(closeTag);
				Matcher matcherCT = closetagPattern.matcher(matcher.group());
				Pattern selfclosetagPattern = Pattern.compile(selfcloseTag);
				Matcher matcherSCT = selfclosetagPattern.matcher(matcher.group());

				Pattern namePattern = Pattern.compile(tagName);
				Matcher matcherName = namePattern.matcher(matcher.group());
				Pattern attrPattern = Pattern.compile(attributes);
				Matcher matcherAttr = attrPattern.matcher(matcher.group());

				if (matcherName.find()) {
					output.setValue(matcherName.group(1));

					if (matcherCT.find()) {
						output.setType(NodeType.CLOSE_TAG);
					}

					else if (matcherSCT.find()) {
						output.setType(NodeType.SELFCLOSE_TAG);
					}

					else if (matcherOT.find()) {
						output.setType(NodeType.OPEN_TAG);
					}

					while (matcherAttr.find()) {
						output.setAttribute(matcherAttr.group(1), matcherAttr.group(2));
					}
				}
			} else {
				output.setType(NodeType.CONTENT);
				output.setValue(line);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return output;
	}

	@Override
	public void close() throws ServiceException {
		daofile = DAOFactory.getInstance().getDaofile();
		try {
			daofile.close();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
