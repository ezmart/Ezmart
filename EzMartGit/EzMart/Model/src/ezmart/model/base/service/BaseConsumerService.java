package ezmart.model.base.service;

import ezmart.model.base.BaseCRUDService;
import ezmart.model.entity.Consumer;
import ezmart.model.entity.User;

public interface BaseConsumerService extends BaseCRUDService<Consumer> {

    public void readById(User user) throws Exception;

    public Consumer readByUserId(Long id) throws Exception;

}
