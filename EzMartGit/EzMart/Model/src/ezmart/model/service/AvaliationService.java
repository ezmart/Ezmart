package ezmart.model.service;

import ezmart.model.ConnectionManager;
import ezmart.model.base.service.BaseAvaliationService;
import ezmart.model.dao.AvaliationDAO;
import ezmart.model.entity.Avaliation;
import ezmart.model.util.SystemConstant;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvaliationService implements BaseAvaliationService {

    @Override
    public void create(Avaliation entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            AvaliationDAO dao = new AvaliationDAO();
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
    public Avaliation readById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Avaliation> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Avaliation entity) throws Exception {
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
        Long establishmentId = (Long) fields.get("establishmentId");
        Integer satisfaction = (Integer) fields.get("satisfaction");
        Integer priceProduct = (Integer) fields.get("priceProduct");
        Integer prodDiversity = (Integer) fields.get("prodDiversity");
        Integer employees = (Integer) fields.get("employees");
        Integer ambience = (Integer) fields.get("ambience");
        String commentary = (String) fields.get("commentary");

        if (validationType.equals((SystemConstant.VALIDATION.EVALUATION.REGISTER_EVALUATION))) {
            //Validação de preenchimento dos campos
            if (establishmentId == null) {
                errors.put("establishmentId", "*Selecione um mercado!");
            }
            if (satisfaction == null) {
                errors.put("satisfaction", "*Selecione um grau de satisfação!");
            }
            if (priceProduct == null) {
                errors.put("priceProduct", "*Selecione um grau de satisfação!");
            }
            if (prodDiversity == null) {
                errors.put("prodDiversity", "*Selecione um grau de satisfação!");
            }
            if (employees == null) {
                errors.put("employees", "*Selecione um grau de satisfação!");
            }
            if (ambience == null) {
                errors.put("ambience", "*Selecione um grau de satisfação!");
            }
            if (commentary == null || commentary.isEmpty()) {
                errors.put("commentary", "*Dê sua opinião em relação o mercado!");
            }
        }

        return errors;
    }

    public Avaliation findAvgAvaliation(Long establishmentId) throws Exception {

        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            AvaliationDAO dao = new AvaliationDAO();
            Avaliation avaliation = dao.findAvgAvaliation(conn, establishmentId);
            conn.commit();
            conn.close();
            return avaliation;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }
}
