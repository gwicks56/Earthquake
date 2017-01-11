package gwicks.com.ebayproject;

/**
 * Created by gwicks on 9/01/2017.
 */

// Earthquake object holding all the data that is parsed from the given URL
// Constructor sets all values on creation

class Earthquake {

    private String dataTime;
    private double depth;
    private double longitude;
    private double latitude;
    private String eqid;
    private double magnitude;

    public Earthquake(String dataTime, double depth, double longitude, double latitude, String eqid, double magnitude) {
        this.dataTime = dataTime;
        this.depth = depth;
        this.longitude = longitude;
        this.latitude = latitude;

        this.eqid = eqid;
        this.magnitude = magnitude;
    }

    // Getters - No setters needed as Constructor sets everything

    String getDataTime() {
        return dataTime;
    }


    String getDepth() {
        return Double.toString(depth);
    }


    String getEqid() {
        return eqid;
    }


    String getLatitude() {
        return Double.toString(latitude);
    }

    double getLatAsDouble() {
        return latitude;
    }

    double getLongAsDouble() {
        return longitude;
    }


    String getLongitude() {
        return Double.toString(longitude);
    }

    double getMagnitude() {
        return magnitude;
    }

    String getMagitudeAsString() {
        return Double.toString(magnitude);
    }


    @Override
    public String toString() {
        return
                "Time and Date: " + dataTime + '\n' +
                        "Depth: " + depth + '\n' +
                        "Longitude: " + longitude + '\n' +
                        "Latitude: " + latitude + '\n' +

                        "Eqid: " + eqid + '\n' +
                        "Magitude: " + magnitude
                ;
    }
}
