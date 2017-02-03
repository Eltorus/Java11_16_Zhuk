package by.tr.xml.Service;

public final class TagRegExpression {
	public static final String INIT_TAG_EXP = "<(\\?.+)>";
	public static final String TAG_EXP = "<(\\/?(\\w[^<>]+))>";
	public static final String CLOSETAG_EXP = "<(\\/(\\w[^<>]+))>";
	public static final String OPENTAG_EXP = "(<(\\w[^<>]+)>)";
	public static final String SELFCLOSETAG_EXP = "<((\\w[^<>]+)\\/)>";
	public static final String ATTRIBUTES_EXP = "\\s([^<>\\s]+)\\s?=\\s?\"(.+?)\"";
	public static final String TAGNAME_EXP = "<\\/?([^<>\\s/]+)";
	
	private TagRegExpression(){}
}
