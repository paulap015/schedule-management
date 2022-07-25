package co.unicauca.edu.schedule.controller;

import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.Usuario;
import co.unicauca.edu.schedule.dto.DocenteDTO;
import co.unicauca.edu.schedule.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("create")
    public ResponseEntity<Usuario> create(@RequestBody DocenteDTO usuario){
        Usuario user = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("all")
    public ResponseEntity<?> getAll(){

        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios,HttpStatus.OK);
    }
}
