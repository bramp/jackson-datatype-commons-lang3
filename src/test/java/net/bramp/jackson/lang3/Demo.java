package net.bramp.jackson.lang3;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.math.Fraction;

/**
 * @author bramp
 */
public class Demo {
	@JsonProperty
	Fraction fraction1 = Fraction.getFraction(1, 2);

	@JsonProperty
	Fraction fraction2 = Fraction.getFraction(30000, 1001);
}