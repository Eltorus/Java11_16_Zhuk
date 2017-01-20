package by.tr.xml.bean;

import java.util.HashMap;
import java.util.Map;

public class NodeInfo {
	private NodeType type;
	private Map<String, String> attributes = new HashMap<>();
	private String value;

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttribute(String key, String value) {
		this.attributes.put(key, value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		String result = "Type:" + " " + this.type.toString() + "," + " " + "value:" + " " + value;
		if (!attributes.isEmpty()) {
			for (Map.Entry<String, String> entry : attributes.entrySet()) {
				result = result + "," + " " + "attribute_name:" + " " + entry.getKey();
				result = result + "," + " " + "attribute_value:" + " " + entry.getValue();
			}
		}
		return result;
	}
}
