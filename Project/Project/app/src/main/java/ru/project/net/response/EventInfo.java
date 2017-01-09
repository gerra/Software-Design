
package ru.project.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventInfo {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("starts_at")
    @Expose
    private String startsAt;
    @SerializedName("ends_at")
    @Expose
    private String endsAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description_short")
    @Expose
    private String descriptionShort;
    @SerializedName("description_html")
    @Expose
    private String descriptionHtml;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("poster_image")
    @Expose
    private PosterImage posterImage;
    @SerializedName("ad_partner_percent")
    @Expose
    private int adPartnerPercent;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("organization")
    @Expose
    private Organization organization;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("tickets_limit")
    @Expose
    private int ticketsLimit;
    @SerializedName("ticket_types")
    @Expose
    private List<TicketType> ticketTypes = null;
    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;
    @SerializedName("widgets")
    @Expose
    private Widgets widgets;
    @SerializedName("properties")
    @Expose
    private List<String> properties = null;
    @SerializedName("moderation_status")
    @Expose
    private String moderationStatus;
    @SerializedName("access_status")
    @Expose
    private String accessStatus;
    @SerializedName("registration_data")
    @Expose
    private RegistrationData registrationData;
    @SerializedName("is_sending_free_tickets")
    @Expose
    private boolean isSendingFreeTickets;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(String startsAt) {
        this.startsAt = startsAt;
    }

    public String getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(String endsAt) {
        this.endsAt = endsAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PosterImage getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(PosterImage posterImage) {
        this.posterImage = posterImage;
    }

    public int getAdPartnerPercent() {
        return adPartnerPercent;
    }

    public void setAdPartnerPercent(int adPartnerPercent) {
        this.adPartnerPercent = adPartnerPercent;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public int getTicketsLimit() {
        return ticketsLimit;
    }

    public void setTicketsLimit(int ticketsLimit) {
        this.ticketsLimit = ticketsLimit;
    }

    public List<TicketType> getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(List<TicketType> ticketTypes) {
        this.ticketTypes = ticketTypes;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Widgets getWidgets() {
        return widgets;
    }

    public void setWidgets(Widgets widgets) {
        this.widgets = widgets;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    public String getModerationStatus() {
        return moderationStatus;
    }

    public void setModerationStatus(String moderationStatus) {
        this.moderationStatus = moderationStatus;
    }

    public String getAccessStatus() {
        return accessStatus;
    }

    public void setAccessStatus(String accessStatus) {
        this.accessStatus = accessStatus;
    }

    public RegistrationData getRegistrationData() {
        return registrationData;
    }

    public void setRegistrationData(RegistrationData registrationData) {
        this.registrationData = registrationData;
    }

    public boolean isIsSendingFreeTickets() {
        return isSendingFreeTickets;
    }

    public void setIsSendingFreeTickets(boolean isSendingFreeTickets) {
        this.isSendingFreeTickets = isSendingFreeTickets;
    }

}
