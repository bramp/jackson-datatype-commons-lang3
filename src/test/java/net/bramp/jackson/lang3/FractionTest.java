package net.bramp.jackson.lang3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.math.Fraction;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author bramp
 */
public class FractionTest {

    ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new LangModule());
    }

    @Test
    public void read() throws IOException {
        assertEquals(null, mapper.readValue("null", Fraction.class));
        assertEquals(Fraction.getFraction(0), mapper.readValue("0", Fraction.class));
        assertEquals(Fraction.getFraction(1.0), mapper.readValue("1", Fraction.class));
        assertEquals(Fraction.getFraction(1.5), mapper.readValue("1.5", Fraction.class));
        assertEquals(Fraction.getFraction(-1.5), mapper.readValue("-1.5", Fraction.class));
        assertEquals(Fraction.getFraction(0), mapper.readValue("\"0\"", Fraction.class));
        assertEquals(Fraction.getFraction(1, 2), mapper.readValue("\"1/2\"", Fraction.class));

        assertEquals(Fraction.getFraction(30000, 1001), mapper.readValue("\"30000/1001\"", Fraction.class));
        assertEquals(Fraction.getFraction(30000, 1001), mapper.readValue("\"29 971/1001\"", Fraction.class));
        assertEquals(Fraction.getFraction(29, 971, 1001), mapper.readValue("\"29 971/1001\"", Fraction.class));

        // Special case for ffmpeg (we should consider removing it!) 0/0 is undefined
        assertEquals(Fraction.getFraction(0), mapper.readValue("\"0/0\"", Fraction.class));
    }

    @Test
    public void readArray() throws IOException {
        String s = "[null, 0, 1, 1.5, -1.5, 0, \"1/2\", \"0/0\"]";
        mapper.readValue(s, Fraction[].class);
    }

    @Test(expected = JsonMappingException.class)
    public void readInvalid() throws IOException {
        mapper.readValue("true", Fraction.class);
    }

    @Test
    public void write() throws JsonProcessingException {
        assertEquals("\"0\"", mapper.writeValueAsString(Fraction.ZERO));
        assertEquals("\"1\"", mapper.writeValueAsString(Fraction.ONE));
        assertEquals("\"1/2\"", mapper.writeValueAsString(Fraction.ONE_HALF));
        assertEquals("\"29 971/1001\"", mapper.writeValueAsString(Fraction.getFraction(30000, 1001)));
    }
}
