import org.bson.Document;

/**
 * Created by german on 24.03.17.
 */
public class Product {
    private String title;
    private String description;
    private double price;

    public Product(Document document) {
        this(document.getString("title"),
                document.getString("description"),
                document.getDouble("price"));
    }

    public Product(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Document toDocument() {
        Document document = new Document();
        document.put("title", title);
        document.put("description", description);
        document.put("price", price);
        return document;
    }

    public Product multiplyCost(double multiplier) {
        price *= multiplier;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
