package gwicks.com.ebayproject;

/**
 * Created by gwicks on 9/01/2017.
 */

class Earthquake {

    private String dataTime;
    private double depth;
    private double longitude;
    private double latitude;
    //private String source;
    private String eqid;
    private double magitude;

    public Earthquake(String dataTime, double depth, double longitude, double latitude, String eqid, double magitude) {
        this.dataTime = dataTime;
        this.depth = depth;
        this.longitude = longitude;
        this.latitude = latitude;

        this.eqid = eqid;
        this.magitude = magitude;
    }

    String getDataTime() {
        return dataTime;
    }

    void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    double getDepth() {
        return depth;
    }

    void setDepth(double depth) {
        this.depth = depth;
    }

    String getEqid() {
        return eqid;
    }

    void setEqid(String eqid) {
        this.eqid = eqid;
    }

    double getLatitude() {
        return latitude;
    }

    void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    double getLongitude() {
        return longitude;
    }

    void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    double getMagitude() {
        return magitude;
    }

    void setMagitude(double magitude) {
        this.magitude = magitude;
    }




    @Override
    public String toString() {
        return "Earthquake{" +
                "dataTime='" + dataTime + '\'' +
                ", depth=" + depth +
                ", longitude=" + longitude +
                ", latitude=" + latitude +

                ", eqid='" + eqid + '\'' +
                ", magitude=" + magitude +
                '}';
    }
}
