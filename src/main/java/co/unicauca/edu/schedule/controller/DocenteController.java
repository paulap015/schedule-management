package co.unicauca.edu.schedule.controller;

import co.unicauca.edu.schedule.domain.model.Docente;
import co.unicauca.edu.schedule.service.IDocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("docente")
public class DocenteController {

    @Autowired
    private IDocenteService docenteService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("all")
    public ResponseEntity<?> getAll(){
        List<Docente> docentes = docenteService.findAllAvailable();//docenteService.findAll();
        return new ResponseEntity<>(docentes,HttpStatus.OK);
    }
}
