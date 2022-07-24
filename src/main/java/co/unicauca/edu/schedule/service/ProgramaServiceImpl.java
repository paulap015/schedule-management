package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.IProgramaRepository;
import co.unicauca.edu.schedule.domain.model.Programa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramaServiceImpl implements IProgramaService {

    @Autowired
    public IProgramaRepository programaRepository;

    @Override
    public Programa findByCodigo(Integer codigo) {

        return programaRepository.findById(codigo).orElse(null);
    }
}
