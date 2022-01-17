package by.petrovich.storage.view;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspTagException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class FooterTag extends TagSupport {
	private static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	private static final String P_TAG_OPEN = "<p>";
	private static final String FOOTER_TEXT = "EPAM Java Web Development Training"
			+ " | WEB APP TASK | &copy Copyright by ";
	private static final String A_HREF_TAG_OPEN = "<a href=";
	private static final String FOOTER_TEXT_LINK = "\"https://www.linkedin.com/in/alexandr-petrovich-java-developer/\">";
	private static final String FOOTER_TEXT_NAME = "Petrovich Alexandr";
	private static final String A_TAG_CLOSE = "</a>";
	private static final String FOOTER_TEXT_YEARS = ", 2021-2022";
	private static final String P_TAG_CLOSE = "</p>";

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter jspWriter = pageContext.getOut();
			jspWriter.write(P_TAG_OPEN);
			jspWriter.write(FOOTER_TEXT);
			jspWriter.write(A_HREF_TAG_OPEN);
			jspWriter.write(FOOTER_TEXT_LINK);
			jspWriter.write(FOOTER_TEXT_NAME);
			jspWriter.write(A_TAG_CLOSE);
			jspWriter.write(FOOTER_TEXT_YEARS);
			jspWriter.write(P_TAG_CLOSE);
		} catch (IOException e) {
			logger.log(Level.ERROR, "footer tag write error", e);
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doStartTag();
	}

}
