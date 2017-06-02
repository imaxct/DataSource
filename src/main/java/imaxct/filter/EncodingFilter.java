package imaxct.filter;

import javax.servlet.*;
import java.io.IOException;

/***
 * Created by imaxct on 17-3-31.
 */
public class EncodingFilter implements Filter {
    private static String encoding = "UTF-8";
    public void init(FilterConfig filterConfig) throws ServletException {
        String paramEncoding = filterConfig.getInitParameter("encoding");
        if (paramEncoding != null){
            encoding = paramEncoding;
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
