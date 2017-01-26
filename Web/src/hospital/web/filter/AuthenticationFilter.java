package hospital.web.filter;

import hospital.web.controller.UserSessionController;

import java.io.IOException;
import java.util.List;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		String requestUri = request.getRequestURI();
		String context = request.getContextPath() + "/";
		if (request.getSession().getAttribute(UserSessionController.AUTH_KEY) == null || 
				request.getSession().getAttribute(UserSessionController.AUTH_KEY).toString().isEmpty()) {
			 request.getSession().getAttribute(UserSessionController.urlList);
			String url = request.getSession().getAttribute(UserSessionController.urlList) == null ? "Garden/::home.html::"
					+ "surgery/register.html::"
					+ "dashboard/dashboard.html": request.getSession().getAttribute(UserSessionController.urlList).toString();
			String[] list = url.split("::");
			int isFilter = 0;
			for (int index = 0; index < list.length; index++) if (requestUri.endsWith(list[index])) isFilter++;
			if (request.getSession().getAttribute(UserSessionController.AUTH_KEY) == null) {}
			if (isFilter > 0) { ((HttpServletResponse) arg1).sendRedirect(context + "login.html");} 
			else {arg2.doFilter(arg0, arg1);}
		} else {
			String url = request.getSession().getAttribute(UserSessionController.urlList) == 
					null ? "Garden/" : request.getSession().getAttribute(UserSessionController.urlList).toString();
			String[] list = url.split("::");
			if (requestUri.endsWith("login.html")) {
				if (list.length < 1) {((HttpServletResponse) arg1).sendRedirect(context + "home.html");} 
				else {((HttpServletResponse) arg1).sendRedirect(context + "home.html");}
			} else {
				int isFilter = 0;
				for (int index = 0; index < list.length; index++) if (requestUri.endsWith(list[index])) isFilter++;
				if (isFilter > 0) {((HttpServletResponse) arg1).sendRedirect(context + "home.html");} 
				else {arg2.doFilter(arg0, arg1);}
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	public void setUrl(String url) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc
				.getApplication().getNavigationHandler();
		nav.performNavigation(url);
	}

}
