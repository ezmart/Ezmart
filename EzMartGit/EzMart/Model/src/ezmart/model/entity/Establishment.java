package ezmart.model.entity;

import java.sql.Date;
import java.util.List;


public class Establishment extends User {

    private String cnpj;
    private String secondEmail;
    private String name;
    private String businessName;
    private Integer plan;
    private Date planStartDate;
    private Date planFinalDate;
    private List<EstablishmentProduct> productList;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public void setSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Integer getPlan() {
        return plan;
    }

    public void setPlan(Integer plan) {
        this.plan = plan;
    }

    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    public Date getPlanFinalDate() {
        return planFinalDate;
    }

    public void setPlanFinalDate(Date planFinalDate) {
        this.planFinalDate = planFinalDate;
    }

    public List<EstablishmentProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<EstablishmentProduct> productList) {
        this.productList = productList;
    }

}
