package ezmart.model.dao;

import ezmart.model.base.BaseDAO;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.EstablishmentProduct;
import ezmart.model.entity.Promotion;
import ezmart.model.entity.PromotionEstablishmentProduct;
import ezmart.model.model_entity.PromotionEProductModel;
import ezmart.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PromotionEstablishmentProductDAO implements BaseDAO<PromotionEstablishmentProduct> {

    @Override
    public void create(Connection conn, PromotionEstablishmentProduct entity) throws Exception {
        String sql = "INSERT INTO public.promotionestablishmentproduct\n"
                + "(promotionestablishmentproduct_promotionid, promotionestablishmentproduct_establishmentproductid, promotionestablishmentproduct_promotionalprice)\n"
                + "VALUES(?,?,?);";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, entity.getPromotion().getId());
        statement.setLong(2, entity.getEstablishmentProduct().getId());
        statement.setDouble(3, entity.getPromotionPrice());

        statement.execute();

        statement.close();
    }

    @Override
    public PromotionEstablishmentProduct readById(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PromotionEstablishmentProduct> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Connection conn, PromotionEstablishmentProduct entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<PromotionEProductModel> findAllPromotionEstablishmentProductByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT promotionestablishmentproduct_id, promotionestablishmentproduct_promotionid, "
                + "promotionestablishmentproduct_establishmentproductid, promotionestablishmentproduct_promotionalprice "
                + "FROM promotionestablishmentproduct "
                + "WHERE 1=1";

        List<Object> paramList = new ArrayList<>();
        if (criteria != null) {

//            if (criteria.containsKey(UserCriteria.ID_NE)) {
//                Long id = (Long) criteria.get(UserCriteria.ID_NE);
//                sql += " AND usersystem.usersystem_id != ?";
//                paramList.add(id);
//            }
            //Add criterio ....
        }

        if (limit != null) {
            sql += " LIMIT ?";
            paramList.add(limit);
        }
        if (offset != null) {
            sql += " OFFSET ?";
            paramList.add(offset);
        }

        PreparedStatement statement = PreparedStatementBuilder.build(conn, sql, paramList);
        ResultSet rs = statement.executeQuery();
        List<PromotionEProductModel> promotionEProductModelList = new ArrayList<>();
        Long aux = null;
        while (rs.next()) {

            PromotionEProductModel promotionEProductModel = new PromotionEProductModel();
            promotionEProductModel.setId(rs.getLong("promotionestablishmentproduct_id"));
            promotionEProductModel.setPromotionId(rs.getLong("promotionestablishmentproduct_promotionid"));
            promotionEProductModel.setEstablishmentProductId(rs.getLong("promotionestablishmentproduct_establishmentproductid"));
            promotionEProductModel.setPromotionalPrice(rs.getDouble("promotionestablishmentproduct_promotionalprice"));

            promotionEProductModelList.add(promotionEProductModel);
        }

        rs.close();
        statement.close();
        return promotionEProductModelList;
    }

    public List<PromotionEstablishmentProduct> findAllPromotionEstablishmentProduct(Connection conn, Long id) throws Exception {
        String sql = "SELECT * FROM promotion\n"
                + "   WHERE promotion_establishmentid = ?"
                + "   ORDER BY promotion_finaldate DESC;";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();

        List<PromotionEstablishmentProduct> promotionEstablishmentProductList = new ArrayList<>();
        while (rs.next()) {
            PromotionEstablishmentProduct promotionEstablishmentProduct = new PromotionEstablishmentProduct();
            EstablishmentProduct establishmentProduct = new EstablishmentProduct();
            Establishment establishment = new Establishment();

            Promotion promotion = new Promotion();

            promotion.setId(rs.getLong("promotion_id"));
            promotion.setName(rs.getString("promotion_name"));
            promotion.setStartDate(rs.getDate("promotion_startdate"));
            promotion.setFinalDate(rs.getDate("promotion_finaldate"));
            promotion.setFinalDateTime(promotion.getFinalDate().getTime());
            promotion.setCurrentDate(new java.util.Date().getTime());
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            promotion.setStartDateConvert(sdf.format(promotion.getStartDate()));
            promotion.setFinalDateConvert(sdf.format(promotion.getFinalDate()));

            establishment.setId(id);

            establishmentProduct.setEstablishment(establishment);

            promotionEstablishmentProduct.setPromotion(promotion);
            promotionEstablishmentProduct.setEstablishmentProduct(establishmentProduct);

            promotionEstablishmentProductList.add(promotionEstablishmentProduct);
        }
        rs.close();
        statement.close();

        return promotionEstablishmentProductList;
    }

    public void savePromotion(Connection conn, PromotionEstablishmentProduct promotionEstablishmentProduct) throws Exception {
        String sql = "INSERT INTO promotion\n"
                + "(promotion_name,promotion_startdate,promotion_finaldate,promotion_establishmentid)\n"
                + "VALUES(?,?,?,?);";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, promotionEstablishmentProduct.getPromotion().getName());
        statement.setDate(2, promotionEstablishmentProduct.getPromotion().getStartDate());
        statement.setDate(3, promotionEstablishmentProduct.getPromotion().getFinalDate());
        statement.setLong(4, promotionEstablishmentProduct.getEstablishmentProduct().getEstablishment().getId());

        statement.execute();

        statement.close();

    }
}
