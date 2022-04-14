package by.petrovich.storage.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class XssRequestWrapper extends HttpServletRequestWrapper {
	private static final String TAG_REGEX = "[<>]";

	public XssRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		String[] encodedValues;
		if (values == null) {
			return null;
		} else {
			int counter = values.length;
			encodedValues = new String[counter];
			for (int i = 0; i < counter; i++) {
				encodedValues[i] = this.stripXss(values[i]);
			}
		}
		return encodedValues;
	}

	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		return value == null ? null : this.stripXss(value);
	}

	public String getHeader(String name) {
		String value = super.getHeader(name);
		return this.stripXss(value);
	}

	private String stripXss(String value) {
		if (value == null) {
			return null;
		}
		return value.replaceAll(TAG_REGEX, "");
	}

}
