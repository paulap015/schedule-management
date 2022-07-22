package co.unicauca.edu.schedule.controller;

import co.unicauca.edu.schedule.model.Ambiente;
import co.unicauca.edu.schedule.service.IAmbienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ambiente")
public class AmbienteController {

    @Autowired
    private IAmbienteService ambienteService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("all")
    public ResponseEntity<?> getAll(){

        List<Ambiente> ambientes = ambienteService.findAll();
        return new ResponseEntity<>(ambientes,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("create")
    public ResponseEntity<Ambiente> create(@RequestBody Ambiente ambiente){
        Ambiente amb = ambienteService.save(ambiente);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(amb);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value="{id}")
    public ResponseEntity<?> getAmbienteById(@PathVariable("id") String id){
        Optional<Ambiente> amb = ambienteService.findById(id);

        if (amb==null){
            return ResponseEntity.ok(Ambiente.builder().build());
        }
        return ResponseEntity.ok(amb);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value="/update")
    public ResponseEntity<?> update(@RequestBody Ambiente ambiente ){

        Ambiente amb = ambienteService.update(ambiente);

        if (amb ==null){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(amb);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){

        if(ambienteService.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Ambiente.builder().build());
        }
        ambienteService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
