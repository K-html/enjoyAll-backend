package org.khtml.enjoyallback.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Value("${jwt.ai-server-secret}")
    private String AI_SECRET_KEY;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (pathMatcher.match("/hc", request.getServletPath()) ||
                pathMatcher.match("/env", request.getServletPath()) ||
                pathMatcher.match("/auth/**", request.getServletPath()) ||
                pathMatcher.match("/chat", request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }

        if (pathMatcher.match("/board/ai", request.getServletPath()) ||
                pathMatcher.match("/crawleData/ai", request.getServletPath())) {
            String jwtToken = jwtTokenUtil.getTokenFromRequest(request);

            String secretKey = jwtTokenUtil.extractIdentifier(jwtToken);
            if (secretKey.equals(AI_SECRET_KEY)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(secretKey);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        }

        String jwtToken = jwtTokenUtil.getTokenFromRequest(request);

        String userId = jwtTokenUtil.extractIdentifier(jwtToken);
        if (StringUtils.hasText(jwtToken) && jwtTokenUtil.isValidateToken(jwtToken, userId)) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
