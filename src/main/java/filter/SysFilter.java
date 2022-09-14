package filter;

import pojo.User;
import tools.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
        if (null == user){
            response.sendRedirect("/smbms/error.jsp");
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    public void destroy() {

    }
}
