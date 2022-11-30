package soccervio.back.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import soccervio.back.constants.JwtConstant;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil implements Serializable {

    @Value("${jwt.secretKey}")
    private String secretKey;

    public String generateKey(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        addRolesClaim(userDetails, claims);
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstant.JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    private void addRolesClaim(UserDetails userDetails, Map<String, Object> claims){
        if(userDetails == null || userDetails.getAuthorities() == null || userDetails.getAuthorities().isEmpty())
            return;

        String roles = "";
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities)
            roles += grantedAuthority.getAuthority() + ",";
        if(!roles.isEmpty())
            roles = roles.substring(0, roles.length() - 1);
        claims.put("roles", "[" + roles + "]");
    }

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claimsTFunction.apply(claims);
    }

    public boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        Date dateExpiration = getClaimFromToken(token, Claims::getExpiration);
        boolean isExpired = dateExpiration.before(new Date());
        return (username.equals(userDetails.getUsername()) && !isExpired);
    }

    public void registerAuthenticationTokenInContext(UserDetails userDetails, HttpServletRequest request){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
