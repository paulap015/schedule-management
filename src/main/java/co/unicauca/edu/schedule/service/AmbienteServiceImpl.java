package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.IAmbienteRepository;
import co.unicauca.edu.schedule.domain.model.Ambiente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmbienteServiceImpl implements IAmbienteService{
    @Autowired
    private IAmbienteRepository ambienteRepository;

    @Override
    public List<Ambiente> findAll() {
        return ambienteRepository.findAll();
    }

    @Override
    public Optional<Ambiente> findById(String id) {
        return ambienteRepository.findById(id);
    }

    @Override
    public Ambiente save(Ambiente ambiente) {
        return ambienteRepository.save(ambiente);
    }

    @Override
    public void deleteById(String id) {

        ambienteRepository.deleteById(id);
    }

    @Override
    public Ambiente update(Ambiente ambiente) {
        Optional<Ambiente> amb = findById(ambiente.getCodigo());

        if (amb.isEmpty()){
            return null;
        }
        amb.get().setCapacidad(ambiente.getCapacidad());
        amb.get().setCodigo(ambiente.getCodigo());
        amb.get().setTipoAmbiente(ambiente.getTipoAmbiente());
        amb.get().setNombre(ambiente.getNombre());
        amb.get().setUbicacion(ambiente.getUbicacion());
        //deleteById(ambiente.getCodigo());
        return ambienteRepository.save(amb.get());
    }


}
