package support.js;

import javax.swing.text.html.HTML;
import java.util.Map;

public class JsUtils {
	public static String addElement(TagBean bean) {
		StringBuilder sb = new StringBuilder();
		sb.append("var ele=document.createElement('" + bean.getTag() + "');");
		for (Map.Entry<HTML.Attribute, String> attrMap : bean.getAttributeMap().entrySet()) {
			sb.append("ele." + attrMap.getKey() + "='" + attrMap.getValue() + "';");
		}
		sb.append("ele.innerHTML='" + bean.getInnerHTML() + "';");
		sb.append("document.body.appendChild(ele);");
		return sb.toString();
	}
}
