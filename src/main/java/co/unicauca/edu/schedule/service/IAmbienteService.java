package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.model.Ambiente;

import java.util.List;
import java.util.Optional;

public interface IAmbienteService {

    public List<Ambiente> findAll();
    public Optional<Ambiente> findById(String id);

    public Ambiente save(Ambiente ambiente);
    public void deleteById(String id);

    public Ambiente update(Ambiente ambiente);
}
