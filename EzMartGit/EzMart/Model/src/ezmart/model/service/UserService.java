package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.base.service.BaseUserService;
import ezmart.model.criteria.UserCriteria;
import ezmart.model.dao.UserDAO;
import ezmart.model.entity.User;
import ezmart.model.model_entity.UserModel;
import ezmart.model.util.SystemConstant;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService implements BaseUserService {

    @Override
    public void create(User entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UserDAO dao = new UserDAO();
            dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public User readById(Long id) throws Exception {
                Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UserDAO dao = new UserDAO();
            User user = dao.readById(conn, id);
            conn.commit();
            conn.close();
            return user;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }}

    @Override
    public List<User> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UserDAO dao = new UserDAO();
            List<User> userList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
            return userList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void update(User entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> validate(Map<String, Object> fields) throws Exception {
        Map<String, String> errors = new HashMap<>();
        String validationType = (String) fields.get("validationType");
        String email = (String) fields.get("email");

        if (validationType.equals((SystemConstant.VALIDATION.USER.AUTHENTICATION))) {
            if (email == null || email.isEmpty()) {
                errors.put("email", "Campo obrigatório!");
            } else {
                Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
                if (!matcher.find()) {
                    errors.put("email", "Formato inválido!");
                }
            }
            String password = (String) fields.get("password");
            if (password == null || password.isEmpty()) {
                errors.put("password", "Campo obrigatório!");
            } else {
                int length = password.length();
                if (length < 6 || length > 20) {
                    errors.put("password", "Tamanho inválido!");
                }
            }
            
            
            
        } else if (validationType.equals(SystemConstant.VALIDATION.USER.RECOVERY_PASSWORD)) {
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(UserCriteria.EMAIL_EQ, email);

            if (email == null || email.isEmpty()) {
                errors.put("email", "Campo obrigatório!");
            } else if (readByCriteria(criteria, null, null).isEmpty()) {
                errors.put("email", "E-Mail não encontrado!");
            } else {
                Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
                if (!matcher.find()) {
                    errors.put("email", "E-Mail inválido!");
                }
            }
        }
        return errors;
    }

    @Override
    public User authenticate(String email, String password) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UserDAO dao = new UserDAO();
            User usuario = dao.authenticate(conn, email, password);
            conn.commit();
            conn.close();
            return usuario;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Map<String, String> comparePassword(Map<String, Object> fields, User entity) throws Exception {
        Map<String, String> errors = new HashMap<>();

        String currentPassword = (String) fields.get("currentPassword");
        String newPassword = (String) fields.get("newPassword");
        String confirmNewPassword = (String) fields.get("confirmNewPassword");

        Boolean testPassword = comparePassword(entity, currentPassword);
        if (!testPassword) {
            errors.put("currentPassword", "Senha atual não confere!");
        }

        if (currentPassword == null || currentPassword.isEmpty()) {
            errors.put("currentPassword", "Campo obrigatório!");
        } else if (newPassword == null || newPassword.isEmpty()) {
            errors.put("newPassword", "Campo obrigatório!");
        } else if (confirmNewPassword == null || confirmNewPassword.isEmpty()) {
            errors.put("confirmNewPassword", "Campo obrigatório!");
        } else if (!newPassword.equals(confirmNewPassword)) {
            errors.put("confirmNewPassword", "Senha não confere!");
        } else {
            int length = newPassword.length();
            if (length < 5 || length > 100) {
                errors.put("newPassword", "Tamanho inválido! (Minimo 6 caracteres)");
            }
        }

        return errors;
    }

    public Boolean comparePassword(User entity, String pass) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UserDAO dao = new UserDAO();
            Boolean test = dao.comparePassword(entity, pass, conn);
            conn.commit();
            conn.close();
            return test;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void updatePasswordByUser(User entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UserDAO dao = new UserDAO();
            dao.updatePasswordByUser(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void updatePasswordByEmail(UserModel entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UserDAO dao = new UserDAO();
            dao.updatePasswordByEmail(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }
    
    @Override
    public void setImg(Long id, byte[] bytes) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UserDAO dao = new UserDAO();
            dao.setImg(conn, id, bytes);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public byte[] getImg(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UserDAO dao = new UserDAO();
            byte[] bytes = dao.getImg(conn, id);
            conn.commit();
            conn.close();
            return bytes;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Boolean activeProfile(String email) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            Boolean response = false;
            UserDAO dao = new UserDAO();
            response = dao.activeUser(conn, email);
            conn.commit();
            conn.close();
            return response;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }
}
