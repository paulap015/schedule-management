package co.unicauca.edu.schedule.service;


import co.unicauca.edu.schedule.domain.model.FranjaHoraria;

import java.util.List;
import java.util.Optional;

public interface IFranjaHorariaService {

    public List<FranjaHoraria> findAll();
    public Optional<FranjaHoraria> findById(int id);

    public FranjaHoraria save(FranjaHoraria franja);
    public void deleteById(int id);

    public FranjaHoraria update(FranjaHoraria franja);
}
