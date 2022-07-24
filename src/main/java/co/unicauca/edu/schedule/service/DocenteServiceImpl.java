package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.IDocenteRepository;
import co.unicauca.edu.schedule.domain.model.Area;
import co.unicauca.edu.schedule.domain.model.Docente;
import co.unicauca.edu.schedule.domain.model.Programa;
import co.unicauca.edu.schedule.dto.DocenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteServiceImpl implements  IDocenteService{

    @Autowired
    private IDocenteRepository docenteRepository;
    @Autowired
    private IAreaService areaService;
    @Autowired
    private IProgramaService programaService;
    @Override
    public Docente save(Docente doc) {
        System.out.println("llega doc "+doc);
        Area area= areaService.findById(doc.getAreaId().getId());
        Programa programa = programaService.findByCodigo(doc.getProgCodigo().getProgCodigo());
        if(area==null){
            System.out.println("Area no existe");
            return null;
        }
        if(programa==null){
            System.out.println("Programa no existe");
            return null;
        }
        doc.setAreaId(area);
        doc.setProgCodigo(programa);
        return docenteRepository.save(doc);
    }



    @Override
    public Docente findById(String id) {
        return docenteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Docente> findAll() {
        return docenteRepository.findAll();
    }
}
