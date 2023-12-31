package test;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import currencyConverter.Currency;
import currencyConverter.MainWindow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainWindowTest {
	ArrayList<Currency> currencies = new ArrayList<Currency>();
	{
		currencies.add(new Currency("US Dollar", "USD"));
		currencies.add(new Currency("Euro", "EUR"));
		currencies.add(new Currency("British Pound", "GBP"));
		currencies.add(new Currency("Swiss Franc", "CHF"));
		currencies.add(new Currency("Chinese Yuan Renminbi", "CNY"));
		currencies.add(new Currency("Japanese Yen", "JPY"));
		// USD, CAD, GBP, EUR, CHF, AUD.
		// currencies.add(new Currency("Canadian dollar", "CAD"));
		// currencies.add(new Currency("Australian dollar", "AUD"));
		for (Integer i = 0; i < currencies.size(); i++) {
			currencies.get(i).defaultValues();
		}
	}
	// NOTE IMPORTANTE : La valeur de retour -1 est retournée lorsqu'on veut
	// retourner une valeur qui ne fait pas de sens.
	// Dans notre cas, la conversion d'un montant negatif devrait nous retourner -1
	// car un montant ne peut pas être négatif.

	// expected return : -1 car le montant négatif ne fait pas de sens donc -1 est
	// la valeur numérique
	// qui nous indique que la valeur calculée ne fait pas de sens.

	// TESTS DONT LES VALEURS DU AMOUNT SONT NÉGATIVES
	@Test
	void testConvert_NegativeAmountUsdToEuro() {
		assertEquals(MainWindow.convert("US Dollar", "Euro", currencies, Double.valueOf(-1000)), Double.valueOf(-1));
	}

	@Test
	void testConvert_NegativeAmountUsdToDzd() {
		assertEquals(MainWindow.convert("US Dollar", "Algerian dinar", currencies, Double.valueOf(-1000)),
				Double.valueOf(-1));
	}

	@Test
	void testConvert_NegativeAmountDzdToUsd() {
		assertEquals(MainWindow.convert("Algerian dinar", "US Dollar", currencies, Double.valueOf(-1000)),
				Double.valueOf(-1));
	}

	@Test
	void testConvert_NegativeAmountDzdToDzd() {
		assertEquals(MainWindow.convert("Algerian dinar", "Algerian dinar", currencies, Double.valueOf(-1000)),
				Double.valueOf(-1));
	}

	@Test
	void testConvert_NegativeAmountUsdToUsd() {
		assertEquals(MainWindow.convert("US Dollar", "US Dollar", currencies, Double.valueOf(-1000)),
				Double.valueOf(-1));
	}

	// TESTS DONT LES VALEURS DU AMOUNT SONT DANS LE RANGE DES VALEURS ACCEPTABLES

	@Test
	void testConvert_WithinRangeAmountUsdToEuro() {
		assertEquals(MainWindow.convert("US Dollar", "Euro", currencies, Double.valueOf(500)), Double.valueOf(465));

	}

	@Test
	void testConvert_WithinRangeAmountUsdToDzd() {
		assertEquals(MainWindow.convert("US Dollar", "Algerian dinar", currencies, Double.valueOf(500)),
				Double.valueOf(-1));
	}

	@Test
	void testConvert_WithinRangeAmountDzdToUsd() {
		assertEquals(MainWindow.convert("Algerian dinar", "US Dollar", currencies, Double.valueOf(500)),
				Double.valueOf(-1));
	}

	@Test
	void testConvert_WithinRangeAmountDzdToDzd() {
		assertEquals(MainWindow.convert("Algerian dinar", "Algerian dinar", currencies, Double.valueOf(500)),
				Double.valueOf(-1));
	}

	@Test
	void testConvert_WithinRangeAmountUsdToUsd() {
		assertEquals(MainWindow.convert("US Dollar", "US Dollar", currencies, Double.valueOf(500)),
				Double.valueOf(500));
	}

	// TESTS DONT LES VALEURS DU AMOUNT TROP GRANDES (>1000000)

	@Test
	void testConvert_ToLargeAmountUsdToEuro() {
		assertEquals(MainWindow.convert("US Dollar", "Euro", currencies, Double.valueOf(2000000)), Double.valueOf(-1));

	}

	@Test
	void testConvert_ToLargeAmountUsdToDzd() {
		assertEquals(MainWindow.convert("US Dollar", "Algerian dinar", currencies, Double.valueOf(2000000)),
				Double.valueOf(-1));
	}

	@Test
	void testConvert_ToLargeAmountDzdToUsd() {
		assertEquals(MainWindow.convert("Algerian dinar", "US Dollar", currencies, Double.valueOf(2000000)),
				Double.valueOf(-1));
	}

	@Test
	void testConvert_ToLargeAmountDzdToDzd() {
		assertEquals(MainWindow.convert("Algerian dinar", "Algerian dinar", currencies, Double.valueOf(2000000)),
				Double.valueOf(-1));
	}

	@Test
	void testConvert_ToLargeAmountUsdToUsd() {
		assertEquals(MainWindow.convert("US Dollar", "US Dollar", currencies, Double.valueOf(2000000)),
				Double.valueOf(-1));
	}

	// TESTS POUR LA PARTIE 2 (BOITE BLANCHE)

	@Test
	void testConvert_EmptyCurrencyArray() {
		ArrayList<Currency> currencies = new ArrayList<Currency>();
		assertEquals(MainWindow.convert("US Dollar", "Euro", currencies, Double.valueOf(21)), Double.valueOf(-1));
	}

	@Test
	void testConvert_SizeOneCurrencyArrayWithNoValidCurrencyInput() {
		ArrayList<Currency> currencies = new ArrayList<Currency>();
		currencies.add(new Currency("Swiss Franc", "CHF"));
		currencies.get(0).defaultValues();
		assertEquals(MainWindow.convert("US Dollar", "Euro", currencies, Double.valueOf(42536)), Double.valueOf(-1));
	}
	

	@Test
	void testConvert_SizeOneCurrencyArrayWithOneValidCurrencyInput() {
		ArrayList<Currency> currencies = new ArrayList<Currency>();
		currencies.add(new Currency("Euro", "EUR"));
		currencies.get(0).defaultValues();
		assertEquals(MainWindow.convert("US Dollar", "Euro", currencies, Double.valueOf(12)), Double.valueOf(11.16));
	}
	
	
	@Test
	void testConvert_SizeOneCurrencyArrayWithBothValidCurrencyInput() {
		ArrayList<Currency> currencies = new ArrayList<Currency>();
		currencies.add(new Currency("Euro", "EUR"));
		currencies.add(new Currency("US Dollar", "USD"));
		currencies.get(0).defaultValues();
		currencies.get(1).defaultValues();
		assertEquals(MainWindow.convert("US Dollar", "Euro", currencies, Double.valueOf(1006)), Double.valueOf(935.58));
	}
}
