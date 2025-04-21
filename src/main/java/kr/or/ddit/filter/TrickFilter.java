package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class TrickFilter implements Filter {

	private String name;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		name = filterConfig.getInitParameter("name");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
//		req.getParameter("what"); 
		HttpServletRequest filteredReq = new HttpServletRequestWrapper(req) {
			@Override
			public String getParameter(String parameterName) {
				if(parameterName.equals(name)) {
					return "P101000001";
				}else {
					//파라미터 값이 what이 아닐때 건들지 않음
					return super.getParameter(parameterName);
					
				}
			}
		};
		
		String prodId = filteredReq.getParameter(name);
		
		chain.doFilter(filteredReq, response);
	}

}
