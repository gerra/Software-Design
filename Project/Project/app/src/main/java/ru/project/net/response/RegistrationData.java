
package ru.project.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationData {

    @SerializedName("price_max")
    @Expose
    private int priceMax;
    @SerializedName("price_min")
    @Expose
    private int priceMin;
    @SerializedName("sale_ends_at")
    @Expose
    private String saleEndsAt;
    @SerializedName("tickets_total")
    @Expose
    private int ticketsTotal;
    @SerializedName("is_registration_open")
    @Expose
    private boolean isRegistrationOpen;

    public int getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(int priceMax) {
        this.priceMax = priceMax;
    }

    public int getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(int priceMin) {
        this.priceMin = priceMin;
    }

    public String getSaleEndsAt() {
        return saleEndsAt;
    }

    public void setSaleEndsAt(String saleEndsAt) {
        this.saleEndsAt = saleEndsAt;
    }

    public int getTicketsTotal() {
        return ticketsTotal;
    }

    public void setTicketsTotal(int ticketsTotal) {
        this.ticketsTotal = ticketsTotal;
    }

    public boolean isIsRegistrationOpen() {
        return isRegistrationOpen;
    }

    public void setIsRegistrationOpen(boolean isRegistrationOpen) {
        this.isRegistrationOpen = isRegistrationOpen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationData)) return false;

        RegistrationData that = (RegistrationData) o;

        if (getPriceMax() != that.getPriceMax()) return false;
        if (getPriceMin() != that.getPriceMin()) return false;
        if (getTicketsTotal() != that.getTicketsTotal()) return false;
        if (isRegistrationOpen != that.isRegistrationOpen) return false;
        return getSaleEndsAt() != null ? getSaleEndsAt().equals(that.getSaleEndsAt()) : that.getSaleEndsAt() == null;

    }

    @Override
    public int hashCode() {
        int result = getPriceMax();
        result = 31 * result + getPriceMin();
        result = 31 * result + (getSaleEndsAt() != null ? getSaleEndsAt().hashCode() : 0);
        result = 31 * result + getTicketsTotal();
        result = 31 * result + (isRegistrationOpen ? 1 : 0);
        return result;
    }
}
