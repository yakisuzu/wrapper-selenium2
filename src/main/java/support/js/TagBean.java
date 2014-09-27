package support.js;

import javax.swing.text.html.HTML;
import java.util.HashMap;
import java.util.Map;

public class TagBean {
	private HTML.Tag tag;
	private Map<HTML.Attribute, String> attributeMap;
	private String innerHTML;

	public TagBean(HTML.Tag tag) {
		this.tag = tag;
		attributeMap = new HashMap<>();
		innerHTML = "";
	}

	public String getTag() {
		return tag.toString();
	}

	public Map<HTML.Attribute, String> getAttributeMap() {
		return attributeMap;
	}

	public void putAttributeMap(HTML.Attribute attribute, String value) {
		attributeMap.put(attribute, value);
	}

	public String getInnerHTML() {
		return innerHTML;
	}

	public void setInnerHTML(String innerHTML) {
		this.innerHTML = innerHTML;
	}
}
