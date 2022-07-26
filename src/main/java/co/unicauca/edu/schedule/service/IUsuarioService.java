package co.unicauca.edu.schedule.service;


import co.unicauca.edu.schedule.domain.model.Usuario;
import co.unicauca.edu.schedule.dto.DocenteDTO;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    public List<Usuario> findAll();
    public Optional<Usuario> findById(int id);

    public Usuario findById(String id);
    public Usuario save(DocenteDTO usuario);
    public void deleteById(int id);
    public Usuario update(Usuario save);
    public Optional<Usuario> findByUsername(String username);
}
