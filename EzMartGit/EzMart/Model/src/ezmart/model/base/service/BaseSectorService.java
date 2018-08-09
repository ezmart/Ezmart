package ezmart.model.base.service;

import ezmart.model.base.BaseCRUDService;
import ezmart.model.entity.Sector;
import java.util.List;

public interface BaseSectorService extends BaseCRUDService<Sector>{
    
    public List<Sector> findAll(Integer limit, Integer offset) throws Exception;
}
