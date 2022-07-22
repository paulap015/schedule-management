package co.unicauca.edu.schedule.controller;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.unicauca.edu.schedule.model.PeriodoAcademico;
import co.unicauca.edu.schedule.service.IPeriodoAcademicoService;

@RestController
@RequestMapping("/periodoacademico")
public class PeriodoAcademicoController {

    //Inyectando el servicio
    @Autowired
    private IPeriodoAcademicoService periodoAcademicoService;

    /*C*/
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<PeriodoAcademico> save(@RequestBody PeriodoAcademico activity){
        PeriodoAcademico db = periodoAcademicoService.save(activity);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(db);
    }
    /*R*/
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value = "{id}")
    public ResponseEntity<PeriodoAcademico> get(@PathVariable("id") int id){
        PeriodoAcademico db = periodoAcademicoService.findById(id).orElse(null);
        if(db == null){
            return ResponseEntity.ok(PeriodoAcademico.builder().build());
        }
        return ResponseEntity.ok(db);
    }
    /*U*/
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/update")
    public ResponseEntity<PeriodoAcademico> update(@RequestBody PeriodoAcademico activity){
        PeriodoAcademico db = periodoAcademicoService.update(activity);
        if(db == null){
            return ResponseEntity.ok(PeriodoAcademico.builder().build());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(db);
    }

    /*D*/
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value = "{id}")
    public ResponseEntity<PeriodoAcademico> delete(@PathVariable("id") int id){
        periodoAcademicoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(PeriodoAcademico.builder().build());
    }

    
}