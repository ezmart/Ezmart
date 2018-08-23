package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.dao.EstablishmentDAO;
import ezmart.model.dao.UserDAO;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.User;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import ezmart.model.base.service.BaseEstablishmentService;
import ezmart.model.criteria.ConsumerCriteria;
import ezmart.model.criteria.EstablishmentCriteria;
import ezmart.model.criteria.UserCriteria;
import ezmart.model.util.SystemConstant;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EstablishmentService implements BaseEstablishmentService {

    @Override
    public void create(Establishment entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            entity.setUserType("emporium");
            UserDAO userDao = new UserDAO();
            userDao.create(conn, entity);

            EstablishmentDAO dao = new EstablishmentDAO();
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
    public Establishment readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EstablishmentDAO dao = new EstablishmentDAO();
            Establishment emporium = dao.readById(conn, id);
            conn.commit();
            conn.close();
            return emporium;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public List<Establishment> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EstablishmentDAO dao = new EstablishmentDAO();
            List<Establishment> emporiumList = dao.readByCriteria(conn, criteria, 0L, 0L);
            conn.commit();
            conn.close();
            return emporiumList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void update(Establishment entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UserDAO usuarioDao = new UserDAO();
            usuarioDao.update(conn, entity);
            EstablishmentDAO dao = new EstablishmentDAO();
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
            EstablishmentDAO dao = new EstablishmentDAO();
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
        String companyName = (String) fields.get("companyName");
        String businessName = (String) fields.get("businessName");
        String cnpj = (String) fields.get("cnpj");
        String email = (String) fields.get("email");
        String password = (String) fields.get("password");
        String passwordConfirm = (String) fields.get("passwordConfirm");
        String addressLocation = (String) fields.get("addressLocation");
        Integer numberHouse = (Integer) fields.get("numberHouse");
        String neighborhood = (String) fields.get("neighborhood");
        Long cityId = (Long) fields.get("cityId");
        String zipCode = (String) fields.get("zipCode");
        String telephone = (String) fields.get("telephone");

        if (validationType.equals((SystemConstant.VALIDATION.REGISTER.REGISTER_ESTABLISHMENT))) {
            //Validação de preenchimento do campo NOME FANTASIA
            if (companyName == null || companyName.isEmpty()) {
                errors.put("companyName", "*Campo nome obrigatório!");
            }

            //Validação de preenchimento do campo RAZÃO SOCIAL
            if (businessName == null || businessName.isEmpty()) {
                errors.put("lastName", "*Campo sobrenome obrigatório!");
            }

                        //Validação de preenchimento do campo CPF
            if (cnpj == null || cnpj.isEmpty()) {
                errors.put("cnpj", "*Campo CNPJ obrigatório!");
            } else {
                //Verifica se o CPF já foi cadastrado
                Map<Long, Object> criteriaCnpj = new HashMap<>();
                criteriaCnpj.put(EstablishmentCriteria.CNPJ_EQ, cnpj);
                if (readByCriteria(criteriaCnpj, null, null).size() > 0) {
                    errors.put("cnpj", "*CNPJ já cadastrado no sistema!");
                }
            }
            
            //Validação de preenchimento do campo EMAIL
            if (email == null || email.isEmpty()) {
                errors.put("email", "*Campo EMAIL obrigatório!");
            } else {
                Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
                if (!matcher.find()) {
                    errors.put("email", "*Formato inválido!");
                } else {
                    //Verifica se o EMAIL já foi cadastrado
                    Map<Long, Object> criteriaEmail = new HashMap<>();
                    criteriaEmail.put(UserCriteria.EMAIL_EQ, email);
                    UserService userService = new UserService();
                    if (userService.readByCriteria(criteriaEmail, null, null).size() > 0) {
                        errors.put("email", "*E-Mail já cadastrado no sistema!");
                    }
                }
            }
        }

        return errors;
    }

    public void readById(User user) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EstablishmentDAO dao = new EstablishmentDAO();
            dao.readById(conn, user);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    public Establishment readByUserId(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EstablishmentDAO dao = new EstablishmentDAO();
            Establishment establishment = dao.readByUserId(conn, id);
            conn.commit();
            conn.close();
            return establishment;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }
}
