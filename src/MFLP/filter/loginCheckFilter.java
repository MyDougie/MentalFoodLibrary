package MFLP.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class loginCheckFilter
 */
@WebFilter("/MFLP/controller/admin/*")
public class loginCheckFilter implements Filter {

	public loginCheckFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		httpRequest.setCharacterEncoding("UTF-8");
		boolean login = false;
		if (session != null) {
			String id = (String) session.getAttribute("id");
			String code = (String) session.getAttribute("code");
			if (id != null && code != "관리자") {
				login = true; // 세션변수가 null이 아닐경우 true로 설정.
			} else {
				System.out.println("관리자아님");
			}
		}

		if (login) {
			// 세션변수가 null이 아닐경우, 필터 체인을 거친 후, 요청한 페이지로 이동한다.
			chain.doFilter(request, response);
		} else {
			// 세션변수가 null일 경우, 로그인 폼으로 이동한다.
			request.getRequestDispatcher("/login/loginForm.jsp").forward(request, response);

		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
