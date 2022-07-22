package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.IUsuarioRepository;
import co.unicauca.edu.schedule.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsScheduleService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username);

        if(user ==null){
            throw new UsernameNotFoundException(username);
        }
        UserDetails usuario = User.withUsername(user.getUsername())
                .password("{noop}"+user.getPassword())
                .authorities(getAuthorities(user)).build();
        return usuario;


    }
    public Collection<? extends GrantedAuthority> getAuthorities(Usuario cli) {
        String rol = cli.getRol();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(rol.toUpperCase()));

        return authorities;
    }


}

