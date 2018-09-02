package ezmart.model.entity;

import ezmart.model.base.BaseEntity;

public class Provider extends BaseEntity{
    
    private String cnpj;
    
    private String name;
    
    private String businessName;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
    
}
