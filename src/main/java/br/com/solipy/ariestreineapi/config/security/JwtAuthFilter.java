package br.com.solipy.ariestreineapi.config.security;

import br.com.solipy.ariestreineapi.config.ConnectionFactory;
import br.com.solipy.ariestreineapi.models.auth.LoginForm;
import br.com.solipy.ariestreineapi.models.auth.UserDetail;
import br.com.solipy.ariestreineapi.services.auth.HashidService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@PropertySource(value = {"classpath:application.properties"})
public class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationManager authenticationManager;
    private HashidService hashidService;
    private static ConnectionFactory connection;

    protected JwtAuthFilter(String url, AuthenticationManager authenticationManager) {
        super(url, authenticationManager);
        setAuthenticationManager(authenticationManager);
        this.authenticationManager = authenticationManager;
        this.connection = new ConnectionFactory();
        this.hashidService = new HashidService();
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginForm loginForm = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginForm.class);

            log.info("Username: {}, Password: {}", loginForm.getUsername(), loginForm.getPassword());
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginForm.getUsername(),
                    loginForm.getPassword(),
                    new ArrayList<>()
            ));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuario", e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("TOKENNNNNNNNNN SUCESSSS");
        UserDetail userDetail = (UserDetail) authResult.getPrincipal();
        String token = generateToken(authResult);
        response.getWriter().write(token);
        response.getWriter().flush();
    }

    public String generateToken(Authentication authentication){
        UserDetail user = (UserDetail) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + connection.getJwtExpirationInMs());
        Algorithm algorithm = Algorithm.HMAC512(connection.getJwtSecret());
        log.info("TOKENNNNNNNNNN GENERATE");
        String token = JWT.create()
                .withSubject(hashidService.get(32).encode(user.getId()))
                .withClaim("name", user.getNome())
                .withClaim("roles", user.getAuthorities().stream().map(r->((GrantedAuthority) r).getAuthority()).collect(Collectors.joining(",")))
                .withClaim("userKey", hashidService.get(32).encode(user.getId()))
                .withIssuer("auth0")
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(algorithm);
        return token;
    }

    public boolean validateToken(String authToken) {
        Algorithm algorithm = Algorithm.HMAC512(connection.getJwtSecret());
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
        DecodedJWT jwt = verifier.verify(authToken);
        return true;
    }

    public static DecodedJWT decodedJWT(String authToken) {
        Algorithm algorithm = Algorithm.HMAC512(connection.getJwtSecret());
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
        DecodedJWT jwt = verifier.verify(authToken);
        return jwt;
    }

}

