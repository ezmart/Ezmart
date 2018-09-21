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
import ezmart.model.criteria.EstablishmentCriteria;
import ezmart.model.criteria.UserCriteria;
import ezmart.model.util.SystemConstant;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Date;

public class EstablishmentService implements BaseEstablishmentService {

    @Override
    public void create(Establishment entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            entity.setUserType("emporium");
            entity.setActive(false);

            UserDAO userDao = new UserDAO();
            userDao.create(conn, entity);
            
            entity.setPlan(1);
            entity.setPlanStartDate(null);
            entity.setPlanStartDate(null);
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
            List<Establishment> emporiumList = dao.readByCriteria(conn, criteria, limit, offset);
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
        String secondEmail = (String) fields.get("secondEmail");
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
                errors.put("businessName", "*Campo sobrenome obrigatório!");
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

            //Validação de preenchimento do campo OUTRO EMAIL
            if (secondEmail == null || secondEmail.isEmpty()) {
                errors.put("secondEmail", "*Campo OUTRO EMAIL obrigatório!");
            } else {
                Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(secondEmail);
                if (!matcher.find()) {
                    errors.put("secondEmail", "*Formato inválido!");
                } else {
                    //Verifica se o SEGUNDO EMAIL já foi cadastrado
                    Map<Long, Object> criteriaSecondEmail = new HashMap<>();
                    criteriaSecondEmail.put(EstablishmentCriteria.SECOND_EMAIL_EQ, secondEmail);
                    EstablishmentService establishmentService = new EstablishmentService();
                    if (establishmentService.readByCriteria(criteriaSecondEmail, null, null).size() > 0) {
                        errors.put("secondEmail", "*E-Mail já cadastrado no sistema!");
                    }
                }
            }

            //Validação de preenchimento do campo SENHA
            if (password == null || password.isEmpty()) {
                errors.put("password", "*Campo senha obrigatório!");
            }

            //Validação de preenchimento do campo CONFIRMAÇÃO DE SENHA
            if (passwordConfirm == null || passwordConfirm.isEmpty()) {
                errors.put("passwordConfirm", "*Campo de confirmação obrigatório!");
            }

            //Validade a igualdade dos valores
            if (password != null && !password.isEmpty() && passwordConfirm != null && !passwordConfirm.isEmpty()) {
                if (!password.equals(passwordConfirm)) {
                    errors.put("passwordConfirm", "*Confirme a senha corretamente!");
                }
            }

            //Validação de preenchimento do campo RUA
            if (addressLocation == null || addressLocation.isEmpty()) {
                errors.put("addressLocation", "*Campo rua obrigatório!");
            }

            //Validação de preenchimento do campo NÚMERO
            if (numberHouse == null) {
                errors.put("numberHouse", "*Campo número obrigatório!");
            }

            //Validação de preenchimento do campo BAIRRO
            if (neighborhood == null || neighborhood.isEmpty()) {
                errors.put("neighborhood", "*Campo bairro obrigatório!");
            }

            //Validação de preenchimento do campo CIDADE
            if (cityId == null) {
                errors.put("cityId", "*Campo cidade obrigatório!");
                //flag = false;
            }
            //Validação de preenchimento do campo BAIRRO
            if (neighborhood == null || neighborhood.isEmpty()) {
                errors.put("neighborhood", "*Campo bairro obrigatório!");
            }

            //Validação de preenchimento do campo SOBRENOME
            if (zipCode == null || zipCode.isEmpty()) {
                errors.put("zipCode", "*Campo CEP obrigatório!");
            }

            //Validação de preenchimento do campo TELEFONE
            if (telephone == null || telephone.isEmpty()) {
                errors.put("telephone", "*Campo telefone obrigatório!");
            } 
//            else {
//                //Formato da expressão regular (XX)XXXXX-XXXX"
//                String regex = "^\\([1-9]{2}\\)[2-9][0-9]{3,4}\\-[0-9]{4}$";
//                Pattern VALID_TELEPHONE_REGEX = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
//                Matcher matcher = VALID_TELEPHONE_REGEX.matcher(telephone);
//                if (!matcher.find()) {
//                    errors.put("telephone", "*Formato correto: (XX)12345-6789");
//                }
//            }
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
