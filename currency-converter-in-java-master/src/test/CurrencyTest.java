package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import currencyConverter.Currency;
import currencyConverter.MainWindow;

class CurrencyTest {

	// TESTS DONT LES VALEURS DU AMOUNT SONT NÉGATIVES
	@Test
	void testConvert_NegativeAmountNegativeExchangeRate() {
		assertEquals(Currency.convert(Double.valueOf(-1000), Double.valueOf(-2)), Double.valueOf(-1));
	}

	@Test
	void testConvert_NegativeAmountNullExchangeRate() {
		assertEquals(Currency.convert(Double.valueOf(-1000), Double.valueOf(0)), Double.valueOf(-1));
	}

	@Test
	void testConvert_NegativeAmountPostiveExchangeRate() {
		assertEquals(Currency.convert(Double.valueOf(-1000), Double.valueOf(1.5)), Double.valueOf(-1));
	}

	// TEST DONT LES VALEURS DU AMOUNT SONT DANS LE RANGE DES VALEURS ACCEPTABLES !!

	@Test
	void testConvert_WithinRangeAmountNegativeExchangeRate() {
		assertEquals(Currency.convert(Double.valueOf(5000), Double.valueOf(-2)), Double.valueOf(-1));
	}

	@Test
	void testConvert_WithinRangeAmountNullExchangeRate() {
		assertEquals(Currency.convert(Double.valueOf(5000), Double.valueOf(0)), Double.valueOf(-1));
	}

	@Test
	void testConvert_WithinRangeAmountPostiveExchangeRate() {
		assertEquals(Currency.convert(Double.valueOf(5000), Double.valueOf(1.5)), Double.valueOf(7500));
	}

	// TESTS DONT LES VALEURS DU AMOUNT SONT TROP GRANDES (>1000000)

	@Test
	void testConvert_ToLargeAmountNegativeExchangeRate() {
		assertEquals(Currency.convert(Double.valueOf(2000000), Double.valueOf(2)), Double.valueOf(-1));
	}

	@Test
	void testConvert_ToLargeAmountNullExchangeRate() {
		assertEquals(Currency.convert(Double.valueOf(2000000), Double.valueOf(0)), Double.valueOf(-1));
	}

	@Test
	void testConvert_ToLargeAmountPostiveExchangeRate() {
		assertEquals(Currency.convert(Double.valueOf(2000000), Double.valueOf(1.5)), Double.valueOf(-1));
	}

	// TEST POUR LA PARTIE 2 (BOITE BLANCHE)
	
	@Test
	void testConvert_Partie2() {
		assertEquals(Currency.convert(Double.valueOf(100), Double.valueOf(1.4)), Double.valueOf(140));
	}
}
