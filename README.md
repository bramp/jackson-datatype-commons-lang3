Simple Jackson library for some datatypes in Apache Commons Lang3
=================================================================

Right now only the Fraction class is supported

Usage
-----

```xml
    <groupId>net.bramp.jackson</groupId>
    <artifactId>jackson-datatype-commons-lang3</artifactId>
    <version>1.0</version>
```

```java
	import net.bramp.jackson.lang3.LangModule
	...

    mapper = new ObjectMapper();
    mapper.registerModule(new LangModule());
```

Examples
--------

```java
class Demo {
	Fraction fraction1 = Fraction.getFraction(1, 2);
	Fraction fraction2 = Fraction.getFraction(30000, 1001);
}
```

```json
{
	fraction1: "1/2",
	fraction2: "29 971/1001"
}
```