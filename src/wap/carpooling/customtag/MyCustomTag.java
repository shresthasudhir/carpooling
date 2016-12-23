package wap.carpooling.customtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyCustomTag extends SimpleTagSupport {
	String color;
	String clas;
	String text;

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		if (color != null && clas != null)
			out.write(String.format("<h1 style='color:%s' class='%s'>%s</h1>", color, clas, text));
		else
			out.write(String.format("<span>%s</span>", text));
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}
}
