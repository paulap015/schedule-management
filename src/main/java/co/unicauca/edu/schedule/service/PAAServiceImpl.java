package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.IPAARepository;
import co.unicauca.edu.schedule.domain.model.Ambiente;
import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademico;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import co.unicauca.edu.schedule.utils.DTOtoClass;
import co.unicauca.edu.schedule.utils.Validar;
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
    @Lazy
    @Autowired
    private Validar validar;

    @Override
    public PeriodoAcademicoAmbiente save(FranjaDTO franjaDTO,FranjaHoraria fran) {
        //util.dtoPAA(franjaDTO,newFran);
        Ambiente ambiente = ambienteService.findById(franjaDTO.getAmbienteCod()).orElse(null);
        PeriodoAcademico pa = paService.findById(franjaDTO.getPaId()).orElse(null);
        if (ambiente == null || pa ==null){
            System.out.println("No existe ambiente o periodo academico");
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
    public boolean delete(PeriodoAcademicoAmbiente paa) {

        paaRepository.delete(paa);
        return true;
    }


    @Override
    public List<PeriodoAcademicoAmbiente> findAll() {
        return paaRepository.findAll();
    }

    @Override
    public List<PeriodoAcademicoAmbiente> findAllByPa(Integer pa) {
        return paaRepository.findByPa(pa);
    }

    @Override
    public PeriodoAcademicoAmbiente update(FranjaDTO franjaDTO,FranjaHoraria fran) {
        PeriodoAcademicoAmbiente paa = paaRepository.findByHor(franjaDTO.getIdHorario());
        Ambiente ambiente = ambienteService.findById(franjaDTO.getAmbienteCod()).orElse(null);
        PeriodoAcademico paActual = paa.getPaId();
        PeriodoAcademico paDTO = paService.findById(franjaDTO.getPaId()).orElse(null);

        if(ambiente == null || paDTO==null){
            return null;
        }
        if (validar.periodoAcademicoEsDiferente(paDTO,paActual)){
            paa.setPaId(paDTO);
        }
        paa.setHor(fran);
        paa.setAmbienteCod(ambiente);

        return paaRepository.save(paa);
    }

    @Override
    public PeriodoAcademicoAmbiente findByHor(Integer id) {
        return paaRepository.findByHor(id);
    }


}
