//package com.bistral.app.bistral_auth_service.filters;
//
//import com.bistral.app.bistral_auth_service.entity.UserEntity;
//import com.bistral.app.bistral_auth_service.exceptions.UserNotFoundException;
//import com.bistral.app.bistral_auth_service.service.interfaces.AuthService;
//import com.bistral.app.bistral_auth_service.service.interfaces.JwtService;
//import com.bistral.app.bistral_auth_service.service.interfaces.UserCrudService;
//import io.jsonwebtoken.ExpiredJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Service;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class JwtTokenAuthFilter extends OncePerRequestFilter {
//
//
//    private final JwtService jwtService;
//    private final UserCrudService userCrudService;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String token = request.getHeader("Authorization");
//        try {
//            if (token == null || !token.startsWith("Bearer ")) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//            String jwtToken = token.split("Bearer ")[1];
//            java.util.UUID userid = jwtService.getAuthContext(token);
//            UserEntity user = userCrudService.getUserById(userid);
//            if (SecurityContextHolder.getContext() == null) {
//                jwtService.isTokenValid(token, user);
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
//                        = new UsernamePasswordAuthenticationToken(user, null, null);
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//
//        } catch (UserNotFoundException userNotFoundException) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.setContentType("application/json");
//            response.getWriter().write("{\"message\": \"User not found\"}");
//            response.getWriter().flush();
//        } catch (ExpiredJwtException ex) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.setContentType("application/json");
//            response.getWriter().write("{\"message\": \"Token is expired\"}");
//            response.getWriter().flush();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw ex;
//        }
//    }
//}
