package co.unicauca.edu.schedule.controller;

import co.unicauca.edu.schedule.dto.UsuarioDTO;
import co.unicauca.edu.schedule.domain.AuthenticationRequest;
import co.unicauca.edu.schedule.domain.model.Usuario;
import co.unicauca.edu.schedule.security.JWTUtil;
import co.unicauca.edu.schedule.service.IUsuarioService;
import co.unicauca.edu.schedule.service.UserDetailsScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsScheduleService detallesUsuarioService;

    @Autowired
    private JWTUtil jwtUtil;

    @Lazy
    @Autowired
    private IUsuarioService clienteService;

    @PostMapping("/authenticate")
    //public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){
    public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest request){
        try {
            Usuario buscarCliente = clienteService.findByUsername(request.getUsername());

            System.out.println("Buscar clientes "+buscarCliente);
            if (buscarCliente.getId()==null){

                return new ResponseEntity<>(UsuarioDTO.builder().build(), HttpStatus.OK);
            }

            authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            UserDetails userDetails = detallesUsuarioService.loadUserByUsername(request.getUsername());

            String jwt = jwtUtil.generateToken(userDetails);
            UsuarioDTO newClienteDTO= new UsuarioDTO(userDetails.getUsername(),jwt,buscarCliente.getRol(), buscarCliente.getId());
            return new ResponseEntity<>(newClienteDTO,HttpStatus.OK);
            //return new ResponseEntity<>(new AuthenticationResponse(jwt ),HttpStatus.OK);
        }catch(BadCredentialsException e ){
            //return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            return new ResponseEntity<>(UsuarioDTO.builder().build(),HttpStatus.OK);
        }

    }
}
