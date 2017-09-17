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
			if (id != null && code != "������") {
				login = true; // ���Ǻ����� null�� �ƴҰ�� true�� ����.
			} else {
				System.out.println("�����ھƴ�");
			}
		}

		if (login) {
			// ���Ǻ����� null�� �ƴҰ��, ���� ü���� ��ģ ��, ��û�� �������� �̵��Ѵ�.
			chain.doFilter(request, response);
		} else {
			// ���Ǻ����� null�� ���, �α��� ������ �̵��Ѵ�.
			request.getRequestDispatcher("/login/loginForm.jsp").forward(request, response);

		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
