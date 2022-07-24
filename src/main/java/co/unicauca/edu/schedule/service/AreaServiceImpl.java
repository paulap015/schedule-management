package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.IAreaRepository;
import co.unicauca.edu.schedule.domain.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl implements  IAreaService{
    @Autowired
    private IAreaRepository arearepository;

    @Override
    public Area findById(Integer id) {
        return arearepository.findById(id).orElse(null);
    }
}
