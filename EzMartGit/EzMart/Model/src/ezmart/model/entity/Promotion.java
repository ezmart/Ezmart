package ezmart.model.entity;

import ezmart.model.base.BaseEntity;
import java.sql.Date;

public class Promotion extends BaseEntity  {
    
    private String name;
    
    private Date startDate;
    
    private Date finalDate;
    
    private String startDateConvert;
    
    private String finalDateConvert;
    
    private Long finalDateTime;
    
    private Long currentDate;

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

    public String getStartDateConvert() {
        return startDateConvert;
    }

    public void setStartDateConvert(String startDateConvert) {
        this.startDateConvert = startDateConvert;
    }

    public String getFinalDateConvert() {
        return finalDateConvert;
    }

    public void setFinalDateConvert(String finalDateConvert) {
        this.finalDateConvert = finalDateConvert;
    }

    public Long getFinalDateTime() {
        return finalDateTime;
    }

    public void setFinalDateTime(Long finalDateTime) {
        this.finalDateTime = finalDateTime;
    }

    public Long getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Long currentDate) {
        this.currentDate = currentDate;
    }
}
