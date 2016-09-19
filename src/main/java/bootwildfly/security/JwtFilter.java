package bootwildfly.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import bootwildfly.Application;
import bootwildfly.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.google.common.base.Strings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

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
        HttpServletRequest request = (HttpServletRequest) req;

       // if (authService.isAuth(request.getSession())) {
            if (request.getRequestURI().matches("^/api/[A-Z,a-z,0-9]*")) {
//            log.error("ASJDAJSDJASDASDJASDJAJ+++++++++++++++++++++++++++++++");
//            log.error(request.getHeader("authorization"));
//            final String authHeader = request.getHeader("authorization");
//            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                throw new ServletException("Missing or invalid Authorization header.");
//            }
//
//            final String token = authHeader.substring(7); // The part after "Bearer "
//            TOKEN_SESSION_KEY = token;
//            try {
//                final Claims claims = Jwts.parser().setSigningKey("secretkey")
//                        .parseClaimsJws(token).getBody();
//                request.setAttribute("claims", claims);
//                log.info(claims.toString());
//                USER_SESSION_KEY = claims.getSubject();
//            }
//            catch (final SignatureException e) {
//                log.info("AEAWEAW Invalid Token");
//                throw new ServletException("Invalid token.");
//            }
//            addSessionContextToLogging();
            }
       // }

        chain.doFilter(req, res);
    }
//
//    private void addSessionContextToLogging() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String tokenValue = "EMPTY";
//        if (authentication != null && !Strings.isNullOrEmpty(authentication.getDetails().toString())) {
//            MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("SHA-1");
//            tokenValue = encoder.encodePassword(authentication.getDetails().toString(), "not_so_random_salt");
//        }
//        MDC.put(TOKEN_SESSION_KEY, tokenValue);
//
//        String userValue = "EMPTY";
//        if (authentication != null && !Strings.isNullOrEmpty(authentication.getPrincipal().toString())) {
//            userValue = authentication.getPrincipal().toString();
//        }
//        MDC.put(USER_SESSION_KEY, userValue);
//    }
}

