package ezmart.model.base.service;

import ezmart.model.base.BaseCRUDService;
import ezmart.model.entity.Provider;
import java.util.List;

public interface BaseProviderService extends BaseCRUDService<Provider>{
    
    public List<Provider> findAll(Integer limit, Integer offset) throws Exception;
}
