package net.bramp.jackson.lang3;

import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;

import org.apache.commons.lang3.math.Fraction;

/**
 * Apache Commons Lang Jackson Module
 * For converting different Apache types to Json
 *
 * @author bramp
 */
public class LangModule extends SimpleModule {
    public LangModule() {
        addSerializer(Fraction.class, new FractionSerializer());
        addDeserializer(Fraction.class, new FractionDeserializer());
    }
}
