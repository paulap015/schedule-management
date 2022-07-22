package co.unicauca.edu.schedule.security;

import co.unicauca.edu.schedule.service.UserDetailsScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private UserDetailsScheduleService detallesService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detallesService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //los endpoint que terminen en authenticate permitirlas http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
        //perimitir todos los path
        http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
