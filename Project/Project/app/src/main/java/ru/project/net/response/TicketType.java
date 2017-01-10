
package ru.project.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TicketType {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("buy_amount_min")
    @Expose
    private int buyAmountMin;
    @SerializedName("buy_amount_max")
    @Expose
    private int buyAmountMax;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("is_promocode_locked")
    @Expose
    private boolean isPromocodeLocked;
    @SerializedName("sale_ends_at")
    @Expose
    private String saleEndsAt;
    @SerializedName("is_active")
    @Expose
    private boolean isActive;
    @SerializedName("ad_partner_profit")
    @Expose
    private int adPartnerProfit;
    @SerializedName("send_personal_links")
    @Expose
    private boolean sendPersonalLinks;
    @SerializedName("status")
    @Expose
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuyAmountMin() {
        return buyAmountMin;
    }

    public void setBuyAmountMin(int buyAmountMin) {
        this.buyAmountMin = buyAmountMin;
    }

    public int getBuyAmountMax() {
        return buyAmountMax;
    }

    public void setBuyAmountMax(int buyAmountMax) {
        this.buyAmountMax = buyAmountMax;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isIsPromocodeLocked() {
        return isPromocodeLocked;
    }

    public void setIsPromocodeLocked(boolean isPromocodeLocked) {
        this.isPromocodeLocked = isPromocodeLocked;
    }

    public String getSaleEndsAt() {
        return saleEndsAt;
    }

    public void setSaleEndsAt(String saleEndsAt) {
        this.saleEndsAt = saleEndsAt;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getAdPartnerProfit() {
        return adPartnerProfit;
    }

    public void setAdPartnerProfit(int adPartnerProfit) {
        this.adPartnerProfit = adPartnerProfit;
    }

    public boolean isSendPersonalLinks() {
        return sendPersonalLinks;
    }

    public void setSendPersonalLinks(boolean sendPersonalLinks) {
        this.sendPersonalLinks = sendPersonalLinks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketType)) return false;

        TicketType that = (TicketType) o;

        if (getId() != that.getId()) return false;
        if (getBuyAmountMin() != that.getBuyAmountMin()) return false;
        if (getBuyAmountMax() != that.getBuyAmountMax()) return false;
        if (getPrice() != that.getPrice()) return false;
        if (isPromocodeLocked != that.isPromocodeLocked) return false;
        if (isActive != that.isActive) return false;
        if (getAdPartnerProfit() != that.getAdPartnerProfit()) return false;
        if (isSendPersonalLinks() != that.isSendPersonalLinks()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        if (getSaleEndsAt() != null ? !getSaleEndsAt().equals(that.getSaleEndsAt()) : that.getSaleEndsAt() != null)
            return false;
        return getStatus() != null ? getStatus().equals(that.getStatus()) : that.getStatus() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getBuyAmountMin();
        result = 31 * result + getBuyAmountMax();
        result = 31 * result + getPrice();
        result = 31 * result + (isPromocodeLocked ? 1 : 0);
        result = 31 * result + (getSaleEndsAt() != null ? getSaleEndsAt().hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + getAdPartnerProfit();
        result = 31 * result + (isSendPersonalLinks() ? 1 : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }
}
