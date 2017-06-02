package imaxct.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/***
 * Created by imaxct on 17-3-31.
 */
public class AuthFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getSession().getAttribute("user") == null){
            response.getWriter().println("failed");
        }else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {

    }
}
