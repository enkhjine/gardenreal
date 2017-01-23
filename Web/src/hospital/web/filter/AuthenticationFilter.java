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
					+ "company/list.html::"
					+ "company/register.html::"
					+ "role/list.html::"
					+ "role/register.html::"
					+ "user/list.html::"
					+ "user/register.html::"
					+ "calendar/calendar.html::"
					+ "suborganization/list.html::"
					+ "suborganization/register.html::"
					+ "employee/list.html::"
					+ "employee/register.html::"
					+ "treatment/list.html::"
					+ "treatment/register.html::"
					+ "xray/list.html::"
					+ "customer/list.html::"
					+ "customer/register.html::"
					+ "customer/request.html::"
					+ "doctor/list.html::"
					+ "doctor/register.html::"
					+ "doctor/register1.html::"
					+ "doctor/register2.html::"
					+ "doctor/register4.html::"
					+ "doctor/register5.html::"
					+ "doctor/register-liver-gall.html::"
					+ "doctor/newOrder.html::"
					+ "cash/list.html::"
					+ "cash/register.html::"
					+ "xray/request.html::"
					+ "report/report.html::"
					+ "medicine/list.html::"
					+ "medicine/register.html::"
					+ "diagnose/grouplist.html::"
					+ "exmination/list.html::"
					+ "exmination/register.html::"
					+ "diagnose/list.html::"
					+ "examinationgroup/list.html::"
					+ "conditionalprescription/list.html::"
					+ "examination/request.html::"
					+ "examination/list.html::"
					+ "examination/register.html::"
					+ "examination/drequest.html::"
					+ "treatment/request.html::"
					+ "surgery/request.html::"
					+ "surgery/list.html::"
					+ "/test.html::"
					+ "xray/module.html::"
					+ "xray/xrayds.html::"
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
