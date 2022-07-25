package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.IUsuarioRepository;
import co.unicauca.edu.schedule.domain.model.Area;
import co.unicauca.edu.schedule.domain.model.Programa;
import co.unicauca.edu.schedule.domain.model.Usuario;
import co.unicauca.edu.schedule.dto.DocenteDTO;
import co.unicauca.edu.schedule.utils.DTOtoClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements  IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IDocenteService docenteService;

    @Autowired
    private DTOtoClass util;
    @Autowired
    private IAreaService areaService;
    @Autowired
    private IProgramaService programaService;
    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(int id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario findById(String id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(DocenteDTO doc) {
        System.out.println("en usuario  llega el doc "+doc);
        Usuario usuario = util.dtoUsuario(doc);
        if (usuarioRepository.save(usuario)==null){
            return null;
        }
        Area area = areaService.findById(doc.getAreaId());
        Programa programa=programaService.findByCodigo(doc.getProgCodigo());
        if(doc.getRol().equalsIgnoreCase("docente")){


            docenteService.save(util.dtoDoc(doc,area,programa,usuario));
        }

        return usuario;
    }

    @Override
    public void deleteById(int id) {
        usuarioRepository.deleteById(id);
    }


    @Override
    public Usuario update(Usuario save) {
        return usuarioRepository.save(save);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
