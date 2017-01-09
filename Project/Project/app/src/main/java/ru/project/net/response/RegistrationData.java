
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

}
