package net.bramp.jackson.lang3;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.math.Fraction;

import java.io.IOException;

/**
 * @author bramp
 */
public class FractionDeserializer extends StdDeserializer<Fraction> {

    protected FractionDeserializer() {
        super(Fraction.class);
    }

    @Override
    public Fraction deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        Fraction f;

        JsonToken cur = jp.getCurrentToken();
        if (cur == JsonToken.VALUE_NULL) {
            f = null;
        } else if (cur == JsonToken.VALUE_NUMBER_INT || cur == JsonToken.VALUE_NUMBER_FLOAT) {
            f = Fraction.getFraction( jp.getDoubleValue() );
        } else if (cur == JsonToken.VALUE_STRING) {
            String fraction = jp.getText();

            // Ambiguous as to what 0/0 is, but FFmpeg seems to think it's zero
            if (fraction.equals("0/0")) {
                f = Fraction.ZERO;
            } else {
                f = Fraction.getFraction( fraction );
            }
        } else {
            throw new JsonMappingException("Expected null,float,string but found " + cur, jp.getCurrentLocation());
        }

        return f;
    }
}
