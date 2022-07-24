package co.unicauca.edu.schedule.controller;

import co.unicauca.edu.schedule.domain.model.Competencia;
import co.unicauca.edu.schedule.service.ICompetenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("competencia")
public class CompetenciaControllar {

    @Autowired
    private ICompetenciaService competenciaService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("all")
    public ResponseEntity<?> getAll(){

        List<Competencia> competencias = competenciaService.findAll();
        return new ResponseEntity<>(competencias,HttpStatus.OK);
    }
}
