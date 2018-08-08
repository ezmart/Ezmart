package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.base.service.BaseConsumerService;
import ezmart.model.criteria.ConsumerCriteria;
import ezmart.model.criteria.UserCriteria;
import ezmart.model.dao.ConsumerDAO;
import ezmart.model.dao.UserDAO;
import ezmart.model.entity.Consumer;
import ezmart.model.entity.User;
import ezmart.model.util.SystemConstant;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsumerService implements BaseConsumerService {

    @Override
    public void create(Consumer entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            entity.setUserType("consumer");
            entity.setActive(false);

            UserDAO usuarioDao = new UserDAO();
            usuarioDao.create(conn, entity);
            ConsumerDAO dao = new ConsumerDAO();
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
    public Consumer readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ConsumerDAO dao = new ConsumerDAO();
            Consumer consumer = dao.readById(conn, id);
            conn.commit();
            conn.close();
            return consumer;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public List<Consumer> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ConsumerDAO dao = new ConsumerDAO();
            List<Consumer> consumerList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
            return consumerList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void update(Consumer entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UserDAO usuarioDao = new UserDAO();
            usuarioDao.update(conn, entity);
            ConsumerDAO dao = new ConsumerDAO();
            dao.update(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ConsumerDAO dao = new ConsumerDAO();
            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Map<String, String> validate(Map<String, Object> fields) throws Exception {
        Map<String, String> errors = new HashMap<>();
        String validationType = (String) fields.get("validationType");
        String email = (String) fields.get("email");
        String cpf = (String) fields.get("email");
        String password = (String) fields.get("email");

        if (validationType.equals((SystemConstant.VALIDATION.REGISTER.REGISTER_CONSUMER))) {
            if (email == null || email.isEmpty()) {
                errors.put("email", "Campo obrigatório!");
            } else {
                Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
                if (!matcher.find()) {
                    errors.put("email", "Formato inválido!");
                } else {
                    Map<Long, Object> criteria = new HashMap<>();
                    criteria.put(UserCriteria.EMAIL_EQ, email);
                    if (readByCriteria(criteria, null, null).size() > 0) {
                        errors.put("email", "E-Mail já existente em nosso sistema!");
                    }
                }
            }
            if (cpf == null || cpf.isEmpty()) {
                errors.put("email", "Campo obrigatório!");
            } else {
                Map<Long, Object> criteria = new HashMap<>();
                criteria.put(ConsumerCriteria.CPF_EQ, cpf);
                if (readByCriteria(criteria, null, null).size() > 0) {
                    
                }
            }

        }

        return errors;
    }

    @Override
    public void readById(User user) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ConsumerDAO dao = new ConsumerDAO();
            dao.readById(conn, user);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Consumer readByUserId(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ConsumerDAO dao = new ConsumerDAO();
            Consumer consumer = dao.readByUserId(conn, id);
            conn.commit();
            conn.close();
            return consumer;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }
}
