package com.ddingmate.ddingmate.util.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Order(0)
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String TOKEN_CASE = "Bearer ";
    private static final String OTHER_CASE = "anonymous:anonymous";
    private static final String SPLIT_CASE = ":";
    private static final String VALID_LONG = "\\d+";
    private static final int INIT_NUM = 0;
    private static final int FIN_NUM = 7;
    private static final int TOKEN_LENGTH = 10;
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = parseBearerToken(request);
        User user = parseUserSpecification(token);
        AbstractAuthenticationToken authenticated = createAbstractAuthenticationToken(user, token);
        authenticated.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(token -> token.substring(INIT_NUM, FIN_NUM).equalsIgnoreCase(TOKEN_CASE))
                .map(token -> token.substring(FIN_NUM))
                .orElse(null);
    }

    private User parseUserSpecification(String token) {
        String[] split = Optional.ofNullable(token)
                .filter(subject -> subject.length() >= TOKEN_LENGTH)
                .map(tokenProvider::validateTokenAndGetSubject)
                .orElse(OTHER_CASE)
                .split(SPLIT_CASE);

        return new User(split[INIT_NUM], "", List.of(new SimpleGrantedAuthority(split[1])));
    }

    private UsernamePasswordAuthenticationToken createAbstractAuthenticationToken(User user, String token) {
        if (user.getUsername().matches(VALID_LONG)) {
            return UsernamePasswordAuthenticationToken.authenticated(Long.valueOf(user.getUsername()), token, user.getAuthorities());
        }
        return UsernamePasswordAuthenticationToken.authenticated(user.getUsername(), token, user.getAuthorities());
    }

}
