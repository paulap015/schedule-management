package co.unicauca.edu.schedule.controller;

import co.unicauca.edu.schedule.domain.model.Ambiente;
import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import co.unicauca.edu.schedule.dto.FranjaResponseDTO;
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
@CrossOrigin("http://localhost:4200")
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
        List<FranjaResponseDTO> dtoList = franjaService.listFranjaResponse(franjas);
        return new ResponseEntity<>(dtoList,HttpStatus.OK);
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

    //todo el horario de un docente
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("horario/{id}/{idPa}")
    public ResponseEntity<?> getHorario(@PathVariable("id") String id, @PathVariable("idPa") Integer idPa){
        List<FranjaDTO> horario = franjaService.todoHorarioDocente(id,idPa);

        if (horario==null){
            return ResponseEntity.ok(FranjaHoraria.builder().build());
        }
        return ResponseEntity.ok(horario);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value="{id}")
    public ResponseEntity<?> getFranjaById(@PathVariable("id") int id){
        FranjaHoraria franja = franjaService.findById(id).orElse(null);
        if (franja==null){
            //return ResponseEntity.ok(FranjaHoraria.builder().build());
            FranjaResponseDTO fran = new  FranjaResponseDTO("no existe  Franja con ese id");
            return ResponseEntity.ok(fran);
        }
        FranjaResponseDTO dto = util.classToFranjaResponse(franja);
        return ResponseEntity.ok(dto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value="/update")
    public ResponseEntity<?> update(@RequestBody FranjaDTO franja ) throws ParseException {

        FranjaHoraria fran = franjaService.update(franja);

        if (fran ==null){

            FranjaResponseDTO franj = new  FranjaResponseDTO("no existe  Franja para actualizar");
            return ResponseEntity.ok(franj);
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
