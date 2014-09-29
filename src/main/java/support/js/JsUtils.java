package support.js;

import com.google.common.base.Strings;

import javax.swing.text.html.HTML;
import java.util.Map;

public class JsUtils {
	public static String addElement(TagBean... beanArray) {
		StringBuilder sb = new StringBuilder();
		for (TagBean bean : beanArray) {
			sb.append("var ele=document.createElement('" + bean.getTag() + "');");
			for (Map.Entry<HTML.Attribute, String> attrMap : bean.getAttributeMap().entrySet()) {
				sb.append("ele." + attrMap.getKey() + "='" + attrMap.getValue() + "';");
			}
			if (!Strings.isNullOrEmpty(bean.getInnerHTML())) {
				sb.append("ele.innerHTML='" + bean.getInnerHTML() + "';");
			}
			sb.append("document.body.appendChild(ele);");
		}
		return sb.toString();
	}
}
