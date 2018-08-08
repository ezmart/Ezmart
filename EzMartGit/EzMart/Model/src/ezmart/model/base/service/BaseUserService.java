package ezmart.model.base.service;

import ezmart.model.base.BaseCRUDService;
import ezmart.model.entity.User;
import ezmart.model.model_entity.UserModel;
import java.util.Map;

public interface BaseUserService extends BaseCRUDService<User> {

    public Map<String, String> comparePassword(Map<String, Object> fields, User entity) throws Exception;

    public void updatePasswordByUser(User entity) throws Exception;

    public void updatePasswordByEmail(UserModel entity) throws Exception;

    public void setImg(Long id, byte[] bytes) throws Exception;

    public byte[] getImg(Long id) throws Exception;

    User authenticate(String email, String password) throws Exception;
    
    public Boolean activeProfile(String email) throws Exception;
}
