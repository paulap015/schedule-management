package co.unicauca.edu.schedule.dao;

import co.unicauca.edu.schedule.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {

    @Query
    Optional<Usuario> findByUsername(String username);

    @Query
    Usuario findById(String id);
}
