package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.IPAARepository;
import co.unicauca.edu.schedule.domain.model.Ambiente;
import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademico;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import co.unicauca.edu.schedule.utils.DTOtoClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PAAServiceImpl implements  IPAAService{

    @Autowired
    private IPAARepository paaRepository;

    @Lazy
    @Autowired
    private IFranjaHorariaService franjaService;

    @Autowired
    private IAmbienteService ambienteService;
    @Autowired
    private IPeriodoAcademicoService paService;

    @Autowired
    private DTOtoClass util;
    @Override
    public PeriodoAcademicoAmbiente save(FranjaDTO franjaDTO,FranjaHoraria fran) {
        //util.dtoPAA(franjaDTO,newFran);
        Ambiente ambiente = ambienteService.findById(franjaDTO.getAmbienteCod()).orElse(null);
        PeriodoAcademico pa = paService.findById(franjaDTO.getPaId()).orElse(null);
        if (ambiente == null || pa ==null){
            return null;
        }
        PeriodoAcademicoAmbiente paa = new PeriodoAcademicoAmbiente();
        paa.setIdPaa(franjaDTO.getPaaId());
        paa.setHor(fran);
        paa.setAmbienteCod(ambiente);
        paa.setPaId(pa);
        return paaRepository.save(paa);
    }

    @Override
    public List<PeriodoAcademicoAmbiente> findAll() {
        return paaRepository.findAll();
    }
}
