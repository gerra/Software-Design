import com.google.gson.annotations.SerializedName;
import org.bson.Document;

public class User {
    public static final String EMAIL_KEY = "email";

    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("currency")
    private String currency;

    public User(Document document) {
        this(document.getString("name"),
                document.getString("email"),
                document.getString("currency"));
    }

    public User(String name, String email, String currency) {
        this.name = name;
        this.email = email;
        this.currency = currency;
    }

    public Document toDocument() {
        Document document = new Document();
        document.put("name", name);
        document.put("email", email);
        document.put("currency", currency);
        return document;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCurrency() {
        return currency;
    }
}
