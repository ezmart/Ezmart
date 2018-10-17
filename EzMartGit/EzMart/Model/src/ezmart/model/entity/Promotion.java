package ezmart.model.entity;

import ezmart.model.base.BaseEntity;
import java.sql.Date;

public class Promotion extends BaseEntity  {
    
    private String name;
    
    private Date startDate;
    
    private Date finalDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
}
