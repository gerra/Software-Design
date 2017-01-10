
package ru.project.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Location {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("coordinates")
    @Expose
    private List<String> coordinates = null;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        if (getCountry() != null ? !getCountry().equals(location.getCountry()) : location.getCountry() != null)
            return false;
        if (getCity() != null ? !getCity().equals(location.getCity()) : location.getCity() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(location.getAddress()) : location.getAddress() != null)
            return false;
        return getCoordinates() != null ? getCoordinates().equals(location.getCoordinates()) : location.getCoordinates() == null;

    }

    @Override
    public int hashCode() {
        int result = getCountry() != null ? getCountry().hashCode() : 0;
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getCoordinates() != null ? getCoordinates().hashCode() : 0);
        return result;
    }
}
