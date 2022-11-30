package soccervio.back.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import soccervio.back.constants.JwtConstant;
import soccervio.back.services.UserService;
import soccervio.back.utils.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AutorisationFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String autorisation = request.getHeader(JwtConstant.AUTORIZATION);
        String token = null;
        String usernameFromToken = null;
        if(autorisation != null && autorisation.startsWith(JwtConstant.BEARER)){
            token = autorisation.substring(JwtConstant.BEARER.length());
            usernameFromToken = jwtUtil.getUsernameFromToken(token);
        }
        if(usernameFromToken != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userService.loadUserByUsername(usernameFromToken);
            if(jwtUtil.validateToken(token, userDetails))
                jwtUtil.registerAuthenticationTokenInContext(userDetails, request);
        }
        filterChain.doFilter(request, response);
    }
}
