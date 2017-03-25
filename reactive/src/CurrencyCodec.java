import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.util.Currency;

public class CurrencyCodec implements Codec<Currency> {
    @Override
    public Currency decode(BsonReader bsonReader, DecoderContext decoderContext) {
        bsonReader.readName();
        String currencyCode = bsonReader.readString();
        return Currency.getInstance(currencyCode);
    }

    @Override
    public void encode(BsonWriter bsonWriter, Currency currency, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();
        bsonWriter.writeName("currency");
        bsonWriter.writeString(currency.getCurrencyCode());
        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<Currency> getEncoderClass() {
        return Currency.class;
    }
}
