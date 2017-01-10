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

    String getDepth() {
        return Double.toString(depth);
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

    String getLatitude() {
        return Double.toString(latitude);
    }

    void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    String getLongitude() {
        return Double.toString(longitude);
    }

    void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    Double getMagitude() {
        return magitude;
    }

    String getMagitudeAsString(){
        return Double.toString(magitude);
    }

    void setMagitude(double magitude) {
        this.magitude = magitude;
    }




    @Override
    public String toString() {
        return
                "Time and Date: " + dataTime + '\n' +
                "Depth: " + depth + '\n' +
                "Longitude: " + longitude + '\n' +
                "Latitude: " + latitude + '\n' +

                "Eqid: " + eqid + '\n' +
                "Magitude: " + magitude
                ;
    }
}
