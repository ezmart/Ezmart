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
        String name = (String) fields.get("name");
        String lastName = (String) fields.get("lastName");
        String email = (String) fields.get("email");
        String cpf = (String) fields.get("cpf");
        String password = (String) fields.get("password");
        String passwordConfirm = (String) fields.get("passwordConfirm");
        String addressLocation = (String) fields.get("addressLocation");
        Integer numberHouse = (Integer) fields.get("numberHouse");
        String neighborhood = (String) fields.get("neighborhood");
        Long cityId = (Long) fields.get("cityId");
        String zipCode = (String) fields.get("zipCode");
        String telephone = (String) fields.get("telephone");

        Boolean flag = true;

        if (validationType.equals((SystemConstant.VALIDATION.REGISTER.REGISTER_CONSUMER))) {

            //Validação de preenchimento do campo NOME
            if (name == null || name.isEmpty()) {
                errors.put("name", "*Campo nome obrigatório!");
                flag = false;
            }
            //Validação de preenchimento do campo SOBRENOME
            if (lastName == null || lastName.isEmpty()) {
                errors.put("lastName", "*Campo sobrenome obrigatório!");
                flag = false;
            }

            //Validação de preenchimento do campo EMAIL
            if (email == null || email.isEmpty()) {
                errors.put("email", "*Campo EMAIL obrigatório!");
                flag = false;
            } else {
                Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
                if (!matcher.find()) {
                    errors.put("email", "*Formato inválido!");
                }
            }

            //Validação de preenchimento do campo CPF
            if (cpf == null || cpf.isEmpty()) {
                errors.put("cpf", "*Campo CPF obrigatório!");
                flag = false;
            }

            //Validação de preenchimento do campo SENHA
            if (password == null || password.isEmpty()) {
                errors.put("password", "*Campo senha obrigatório!");
                flag = false;
            }

            //Validação de preenchimento do campo CONFIRMAÇÃO DE SENHA
            if (passwordConfirm == null || passwordConfirm.isEmpty()) {
                errors.put("passwordConfirm", "*Campo de confirmação obrigatório!");
                flag = false;
            }

            //Validação de preenchimento do campo RUA
            if (addressLocation == null || addressLocation.isEmpty()) {
                errors.put("addressLocation", "*Campo rua obrigatório!");
                flag = false;
            }

            //Validação de preenchimento do campo NÚMERO
            if (numberHouse == null) {
                errors.put("numberHouse", "*Campo número obrigatório!");
                flag = false;
            }

            //Validação de preenchimento do campo BAIRRO
            if (neighborhood == null || neighborhood.isEmpty()) {
                errors.put("neighborhood", "*Campo bairro obrigatório!");
                flag = false;
            }

            //Validação de preenchimento do campo CIDADE
            if (cityId == null) {
                errors.put("cityId", "*Campo cidade obrigatório!");
                flag = false;
            }

            //Validação de preenchimento do campo BAIRRO
            if (neighborhood == null || neighborhood.isEmpty()) {
                errors.put("neighborhood", "*Campo bairro obrigatório!");
                flag = false;
            }

            //Validação de preenchimento do campo SOBRENOME
            if (zipCode == null || zipCode.isEmpty()) {
                errors.put("zipCode", "*Campo CEP obrigatório!");
                flag = false;
            }

            //Validação de preenchimento do campo TELEFONE
            if (telephone == null || telephone.isEmpty()) {
                errors.put("telephone", "*Campo telefone obrigatório!");
                flag = false;
            }

            if (flag != false) {

                //Verifica se o CPF já foi cadastrado
                Map<Long, Object> criteriaCpf = new HashMap<>();
                criteriaCpf.put(ConsumerCriteria.CPF_EQ, cpf);
                if (readByCriteria(criteriaCpf, null, null).size() > 0) {
                    errors.put("cpf", "*CPF já cadastrado no sistema!");
                }

                //Verifica se o EMAIL já foi cadastrado
                Map<Long, Object> criteriaEmail = new HashMap<>();
                criteriaEmail.put(UserCriteria.EMAIL_EQ, email);
                UserService userService= new UserService();
                if (userService.readByCriteria(criteriaCpf, null, null).size() > 0) {
                    errors.put("email", "*E-Mail já existente em nosso sistema!");
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
