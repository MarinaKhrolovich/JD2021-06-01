package by.ftp.projectnews.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

@WebFilter(urlPatterns = { "/*" }, initParams = {
		@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding param") })
public class EncodingFilter implements Filter {

	private String code;

	public void init(FilterConfig fConfig) throws ServletException {
		code = fConfig.getInitParameter("encoding");

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String codeRequest = request.getCharacterEncoding();

		if (code != null && !codeRequest.equalsIgnoreCase(codeRequest)) {

			request.setCharacterEncoding(code);
			response.setCharacterEncoding(code);

		}
		chain.doFilter(request, response);
	}

	public void destroy() {
		code = null;
	}

}
