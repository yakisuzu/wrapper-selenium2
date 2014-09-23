package support.js;

public class TagBean {
	private String tag;
	private String innerHTML;
	private String id;
	private String name;
	private String text;
	private String onclick;

	public TagBean(String tag) {
		this.tag = tag;
		innerHTML = "";
		id = "";
		name = "";
		text = "";
		onclick = "";
	}

	public String getTag() {
		return tag;
	}

	public String getInnerHTML() {
		return innerHTML;
	}

	public void setInnerHTML(String innerHTML) {
		this.innerHTML = innerHTML;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
}
