package co.unicauca.edu.schedule.dao;

import co.unicauca.edu.schedule.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {

    @Query
    Usuario findByUsername(String username);
}
