package com.mavene.expensetracker.filters;

import com.mavene.expensetracker.constants.Contants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class AuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(Contants.API_SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();
                httpServletRequest.setAttribute("userId", Integer.parseInt(claims.get("userId").toString()));
//                httpServletRequest.setAttribute("token", token);
            } catch (Exception e) {
                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid or expired token");
                return;
            }
        } else {
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization header not found");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
