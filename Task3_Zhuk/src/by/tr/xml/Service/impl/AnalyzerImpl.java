package by.tr.xml.Service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import by.tr.xml.DAO.DAOFile;
import by.tr.xml.DAO.exception.DAOException;
import by.tr.xml.DAO.factory.DAOFactory;
import by.tr.xml.Service.Analyzer;
import by.tr.xml.Service.TagRegExpression;
import by.tr.xml.Service.exception.ServiceException;
import by.tr.xml.bean.NodeInfo;
import by.tr.xml.bean.NodeType;

public class AnalyzerImpl implements Analyzer {
	private final DAOFile daofile = DAOFactory.getInstance().getDaofile();
	
	private final Pattern inittagPattern = Pattern.compile(TagRegExpression.INIT_TAG_EXP);
	private final Pattern tagPattern = Pattern.compile(TagRegExpression.TAG_EXP);
	private final Pattern opentagPattern = Pattern.compile(TagRegExpression.OPENTAG_EXP);
	private final Pattern closetagPattern = Pattern.compile(TagRegExpression.CLOSETAG_EXP);
	private final Pattern selfclosetagPattern = Pattern.compile(TagRegExpression.SELFCLOSETAG_EXP);
	private final Pattern namePattern = Pattern.compile(TagRegExpression.TAGNAME_EXP);
	private final Pattern attrPattern = Pattern.compile(TagRegExpression.ATTRIBUTES_EXP);

	@Override
	public void setFile(String filename) throws ServiceException {
		try {
			daofile.setFile(filename);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public NodeInfo next() throws ServiceException {
		NodeInfo output = null;
		try {
			output = new NodeInfo();
			
			String line = daofile.getLexeme();
			if (line == null || line.equals("")) {
				return null;
			}

			Matcher initmatcher = inittagPattern.matcher(line);
			if (initmatcher.find()) {
				line = daofile.getLexeme();
			}

			Matcher tagMatcher = tagPattern.matcher(line);
			if (tagMatcher.find()) {
				
				
				Matcher matcherOT = opentagPattern.matcher(tagMatcher.group());
				Matcher matcherCT = closetagPattern.matcher(tagMatcher.group());
				Matcher matcherSCT = selfclosetagPattern.matcher(tagMatcher.group());
				Matcher matcherName = namePattern.matcher(tagMatcher.group());
				Matcher matcherAttr = attrPattern.matcher(tagMatcher.group());

				if (matcherName.find()) {
					output.setValue(matcherName.group(1));

					if (matcherCT.find()) {
						output.setType(NodeType.CLOSETAG);
					} else if (matcherSCT.find()) {
						output.setType(NodeType.SELFCLOSETAG);
					} else if (matcherOT.find()) {
						output.setType(NodeType.OPENTAG);
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
		try {
			daofile.close();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
