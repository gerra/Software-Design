import org.bson.Document;

/**
 * Created by german on 24.03.17.
 */
public class CurrencyValue {
    public static final String CURRENCY_KEY = "currency";

    private String currency;
    private double multiplier;

    public CurrencyValue(Document document) {
        currency = document.getString("currency");
        multiplier = document.getDouble("multiplier");
    }

    public CurrencyValue(String currency, double multiplier) {
        this.currency = currency;
        this.multiplier = multiplier;
    }

    public Document toDocument() {
        Document document = new Document();
        document.put("currency", currency);
        document.put("multiplier", multiplier);
        return document;
    }

    public String getCurrency() {
        return currency;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
