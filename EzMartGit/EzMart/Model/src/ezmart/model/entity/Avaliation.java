package ezmart.model.entity;

import ezmart.model.base.BaseEntity;
import java.sql.Date;

public class Avaliation extends BaseEntity {
    
    private Integer satisfaction;
    private Integer productPrice;
    private Integer diversity;
    private Integer employees;
    private Integer ambience;
    private String commentary;
    private Date dateAvaliation;
    private Double satisfactionConvert;
    private Double productPriceConvert;
    private Double diversityConvert;
    private Double employeesConvert;
    private Double ambienceConvert;
    
    private Consumer consumer;
    private Establishment establishment;

    public Integer getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(Integer satisfaction) {
        this.satisfaction = satisfaction;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getDiversity() {
        return diversity;
    }

    public void setDiversity(Integer diversity) {
        this.diversity = diversity;
    }

    public Integer getEmployees() {
        return employees;
    }

    public void setEmployees(Integer employees) {
        this.employees = employees;
    }

    public Integer getAmbience() {
        return ambience;
    }

    public void setAmbience(Integer ambience) {
        this.ambience = ambience;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Date getDateAvaliation() {
        return dateAvaliation;
    }

    public void setDateAvaliation(Date dateAvaliation) {
        this.dateAvaliation = dateAvaliation;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public Double getSatisfactionConvert() {
        return satisfactionConvert;
    }

    public void setSatisfactionConvert(Double satisfactionConvert) {
        this.satisfactionConvert = satisfactionConvert;
    }

    public Double getProductPriceConvert() {
        return productPriceConvert;
    }

    public void setProductPriceConvert(Double productPriceConvert) {
        this.productPriceConvert = productPriceConvert;
    }

    public Double getDiversityConvert() {
        return diversityConvert;
    }

    public void setDiversityConvert(Double diversityConvert) {
        this.diversityConvert = diversityConvert;
    }

    public Double getEmployeesConvert() {
        return employeesConvert;
    }

    public void setEmployeesConvert(Double employeesConvert) {
        this.employeesConvert = employeesConvert;
    }

    public Double getAmbienceConvert() {
        return ambienceConvert;
    }

    public void setAmbienceConvert(Double ambienceConvert) {
        this.ambienceConvert = ambienceConvert;
    }
}
