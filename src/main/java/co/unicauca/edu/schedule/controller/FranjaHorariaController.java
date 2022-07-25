package co.unicauca.edu.schedule.controller;

import co.unicauca.edu.schedule.domain.model.Ambiente;
import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import co.unicauca.edu.schedule.service.IFranjaHorariaService;
import co.unicauca.edu.schedule.service.IPAAService;
import co.unicauca.edu.schedule.utils.DTOtoClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/franjahoraria")
public class FranjaHorariaController {
    @Lazy
    @Autowired
    private IFranjaHorariaService franjaService;


    @Lazy
    @Autowired
    private DTOtoClass util;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("all")
    public ResponseEntity<?> getAll(){

        List<FranjaHoraria> franjas = franjaService.findAll();
        return new ResponseEntity<>(franjas,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("create")
    public ResponseEntity<FranjaHoraria> create(@RequestBody FranjaDTO franja) throws ParseException {
        FranjaHoraria fran = franjaService.save(franja);
        if(fran == null){
            return ResponseEntity.ok(FranjaHoraria.builder().build());
        }


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(fran);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("horario/{id}")
    public ResponseEntity<?> getHorario(@PathVariable("id") String id){
        List<FranjaDTO> horario = franjaService.todoHorarioDocente(id);

        if (horario==null){
            return ResponseEntity.ok(FranjaHoraria.builder().build());
        }
        return ResponseEntity.ok(horario);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value="{id}")
    public ResponseEntity<?> getAmbienteById(@PathVariable("id") int id){
        Optional<FranjaHoraria> franja = franjaService.findById(id);

        if (franja==null){
            return ResponseEntity.ok(FranjaHoraria.builder().build());
        }
        return ResponseEntity.ok(franja);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value="/update")
    public ResponseEntity<?> update(@RequestBody FranjaHoraria franja ){

        FranjaHoraria fran = franjaService.update(franja);

        if (fran ==null){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(fran);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){

        if(franjaService.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FranjaHoraria.builder().build());
        }
        franjaService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
