package br.com.solipy.ariestreineapi.config.security;

import br.com.solipy.ariestreineapi.config.ConnectionFactory;
import br.com.solipy.ariestreineapi.models.auth.UserRequest;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@Log4j2
public class JwtValidationFilter extends BasicAuthenticationFilter {

    private ConnectionFactory connection;
    private final String headerAuthorization = "Authorization";

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.connection = new ConnectionFactory();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(headerAuthorization);
        if(Objects.isNull(token)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        request.setAttribute("userRequest", getUserRequest(token));
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(connection.getJwtSecret());
        String user = JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();

        if (user == null) {
            return null;
        }
        DecodedJWT decodedJWT = JwtAuthFilter.decodedJWT(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(claims.get("roles").asString()));
        return new UsernamePasswordAuthenticationToken(user,null, authorities);
    }

    private UserRequest getUserRequest(String token) {
        DecodedJWT decodedJWT = JwtAuthFilter.decodedJWT(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        UserRequest userRequest = new UserRequest
                (
                        claims.get("name").asString(),
                        Objects.nonNull(claims.get("roles")) ? claims.get("roles").asString() : "",
                        claims.get("userKey").asString()
                );

        return userRequest;
    }



}

