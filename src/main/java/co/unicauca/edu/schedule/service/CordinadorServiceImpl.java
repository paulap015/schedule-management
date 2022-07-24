package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.ICordinadorRepository;
import co.unicauca.edu.schedule.domain.model.Cordinador;
import co.unicauca.edu.schedule.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CordinadorServiceImpl implements ICordinadorService{
    @Autowired
    private ICordinadorRepository cordinadorRepository;
    @Override
    public Cordinador save(Cordinador cordinador) {
        return cordinadorRepository.save(cordinador);
    }


}
