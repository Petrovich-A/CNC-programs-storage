package by.petrovich.storage.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = { "/*" }, filterName = "encodingFilter")

public class EncodingFilter implements Filter {
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final String REQUEST_ENCODING_PARAM = "encoding";
	private String encoding;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		String requestEncoding = servletRequest.getCharacterEncoding();
		if (encoding != null && !encoding.equalsIgnoreCase(requestEncoding)) {
			servletRequest.setCharacterEncoding(encoding);
			servletResponse.setCharacterEncoding(encoding);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) {
		encoding = filterConfig.getInitParameter(REQUEST_ENCODING_PARAM);
		if (encoding == null) {
			encoding = DEFAULT_ENCODING;
		}
	}

}
