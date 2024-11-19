package com.mavene.expensetracker.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavene.expensetracker.constants.Contants;
import com.mavene.expensetracker.dto.ResponseDto;
import com.mavene.expensetracker.utils.ApiResponse;
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
//                throw new EtAuthException("Invalid or expired token");
//                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid or expired token");
                sendErrorResponse(httpServletResponse, "Invalid or expired token");
                return;
            }
        } else {
//            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization header not found");
            sendErrorResponse(httpServletResponse, "Authorization header not found");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Helper method to construct and send a structured error response.
     */
    private void sendErrorResponse(HttpServletResponse response, String customerMessage) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");

        // Use ApiResponse to create the error response
        ResponseDto<Object> errorResponse = ApiResponse.createErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                "Forbidden",
                customerMessage
        );

        // Serialize the response using ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);

        // Write the JSON response to the output stream
        response.getWriter().write(jsonResponse);
    }
}
