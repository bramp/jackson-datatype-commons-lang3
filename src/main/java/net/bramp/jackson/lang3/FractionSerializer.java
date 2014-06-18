package net.bramp.jackson.lang3;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.commons.lang3.math.Fraction;

import java.io.IOException;

/**
 * @author bramp
 */
public class FractionSerializer extends StdSerializer<Fraction> {

    protected FractionSerializer() {
        super(Fraction.class);
    }

    @Override
    public void serialize(Fraction value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        if (value == null) {
            jgen.writeNull();
        } else {
            /*
            String s;
            if (value.getNumerator() == 0) {
                s = "0";
            } else {
                s = value.toString(); // value.toProperString()
            }
            */
            String s = value.toProperString();
            jgen.writeString( s );
        }
    }
}
