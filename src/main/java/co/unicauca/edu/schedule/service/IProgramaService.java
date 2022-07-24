package co.unicauca.edu.schedule.service;


import co.unicauca.edu.schedule.domain.model.Programa;


public interface IProgramaService {

    public Programa findByCodigo(Integer codigo);
}
