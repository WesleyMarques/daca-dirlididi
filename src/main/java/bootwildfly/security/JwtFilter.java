package bootwildfly.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import bootwildfly.Application;
import bootwildfly.services.AuthService;

//@Component
public class JwtFilter extends GenericFilterBean {

    private String TOKEN_SESSION_KEY;
    private String USER_SESSION_KEY;
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    AuthService authService;

	@Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        final String authHeader = request.getHeader("authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            throw new ServletException("Missing or invalid Authorization header.");
//        }
//        final String token = authHeader.substring(7); // The part after "Bearer "
//        TOKEN_SESSION_KEY = token;
//        try {
//            v
//            final Claims claims = Jwts.parser().setSigningKey("secretkey")
//                    .parseClaimsJws(token).getBody();
//            request.setAttribute("claims", claims);
//            USER_SESSION_KEY = claims.getSubject();
//        } catch (final SignatureException e) {
//            throw new ServletException("Invalid token.");
//        }
        chain.doFilter(req, res);
    }
}

