package co.unicauca.edu.schedule.security;

import co.unicauca.edu.schedule.service.UserDetailsScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil  jwtUtil;

    @Autowired
    private UserDetailsScheduleService detallesUsuarioService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader !=null && authHeader.startsWith(("Bearer"))){
            String jwt = authHeader.substring(7);
            String username = jwtUtil.extractUsername(jwt);

            if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = detallesUsuarioService.loadUserByUsername(username);

                if(jwtUtil.validateToken(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request,response);

    }
}
