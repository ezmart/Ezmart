package ezmart.model.model_entity;

import ezmart.model.base.BaseEntity;

public class EstablishmentsLocationModel extends BaseEntity {

    private String establishmentsName;
    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEstablishmentsName() {
        return establishmentsName;
    }

    public void setEstablishmentsName(String establishmentsName) {
        this.establishmentsName = establishmentsName;
    }
}
