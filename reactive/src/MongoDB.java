import com.mongodb.rx.client.*;
import org.bson.Document;
import rx.Observable;

import java.util.Arrays;


public class MongoDB {
    private static final String USERS = "users";
    private static final String PRODUCTS = "products";
    private static final String CURRENCY_VALUES = "currency_values";

    private MongoCollection<Document> usersCollection;
    private MongoCollection<Document> productsCollection;
    private MongoCollection<Document> currencyValuesCollection;

    public MongoDB() {
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase("db");
        usersCollection = database.getCollection(USERS);
        productsCollection = database.getCollection(PRODUCTS);
        currencyValuesCollection = database.getCollection(CURRENCY_VALUES);
        // clear
        usersCollection.drop().toBlocking().single();
        currencyValuesCollection.count().flatMap(count -> {
            if (count == 0) {
                return currencyValuesCollection.insertMany(Arrays.asList(
                        new CurrencyValue("RUB", 59.7).toDocument(),
                        new CurrencyValue("EUR", 0.93).toDocument(),
                        new CurrencyValue("USD", 1.00).toDocument()
                ));
            } else {
                return Observable.just(Success.SUCCESS);
            }
        }).toBlocking().single();
    }

    public Observable<User> getUsers() {
        return usersCollection.find().toObservable().map(User::new);
    }

    public Observable<Product> getProductsForUser(String email) {
        return usersCollection.find(new Document(User.EMAIL_KEY, email))
                .toObservable()
                .singleOrDefault(null)
                .filter(d -> d != null)
                .map(User::new)
                .map(User::getCurrency)
                .flatMap(currency -> currencyValuesCollection
                        .find(new Document(CurrencyValue.CURRENCY_KEY, currency))
                        .toObservable()
                        .map(CurrencyValue::new)
                        .map(CurrencyValue::getMultiplier)
                ).flatMap(multiplier -> productsCollection.find()
                        .toObservable()
                        .map(Product::new)
                        .map(product -> product.multiplyCost(multiplier)));
    }

    public Observable<Success> registerUser(User user) {
        return usersCollection.insertOne(user.toDocument());
    }

    public Observable<Success> addProduct(Product product) {
        return productsCollection.insertOne(product.toDocument());
    }
}
