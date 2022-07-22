package co.unicauca.edu.schedule.service;


import co.unicauca.edu.schedule.domain.model.Usuario;

import java.util.Optional;

public interface IUsuarioService {

    public Iterable<Usuario> findAll();
    public Optional<Usuario> findById(int id);
    public Usuario save(Usuario periodo);
    public void deleteById(int id);
    public Usuario update(Usuario save);
    public Usuario findByUsername(String username);
}
