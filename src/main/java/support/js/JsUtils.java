package support.js;

public class JsUtils {
	public static String addElement(TagBean bean) {
		StringBuilder sb = new StringBuilder();
		sb.append("var ele = document.createElement('" + bean.getTag() + "');");
		sb.append("ele.innerHTML = '" + bean.getInnerHTML() + "';");
		sb.append("ele.id = '" + bean.getId() + "';");
		sb.append("ele.name = '" + bean.getName() + "';");
		sb.append("ele.text = '" + bean.getText() + "';");
		sb.append("ele.onclick = '" + bean.getOnclick() + "';");
		sb.append("document.body.appendChild(ele);");
		return sb.toString();
	}
}
