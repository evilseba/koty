package pl.seba.koty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import pl.seba.koty.domain.Kot;

/**
 * Interfejs aplikacji - klasa, którą można uruchomić dzięki metodzie main(...)
 */

public class Interfejs {

	/**
	 * Obiekt typu Scanner do wczytywania wejścia z klawiatury.
	 */
	private static Scanner sc = new Scanner(System.in);
	private static KotDAO kotDao = new KotDAO();

	/**
	 * Metoda main, pozwalająca na uruchomienie klasy jako aplikacji.
	 * 
	 * @param args
	 *            Argumenty konsoli - nieużywane
	 */
	public static void main(String[] args) {

		String wybor;
		do {
			System.out.println("**************Menu*************");
			System.out.println("* 1. Dodaj nowego kota        *");
			System.out.println("* 2. Pokaż koty               *");
			System.out.println("* x. Zakończ program          *");
			System.out.println("*******************************");

			wybor = getUserInput();
			switch (wybor.toLowerCase()) {
			case "1":
				dodajKota();
				break;
			case "2":
				pokazKoty();
				break;
			case "x":
				System.out.println("Koniec programu.");
				break;
			default:
				System.out.println("Brak opcji!");
			}
			System.out.println();
		} while (!wybor.equalsIgnoreCase("x"));

	}

	private static void dodajKota() {
		Kot kot = new Kot();
		// ----------------------------------------------------------------------------------------------

		System.out.println("Podaj imie kota :");
		kot.setImieKota(getUserInput());

		// ----------------------------------------------------------------------------------------------

		Pattern dataDoSprawdzenia = Pattern.compile("[0-9]{4}.[0-1]?[0-9].[0-3]?[0-9]");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		String dataUrodzenia;
		boolean dobraData;
		do {
			System.out.println("Podaj date urodzenia: (przykładowy format to: 2000.01.01)");
			dataUrodzenia = getUserInput();
			dobraData = dataDoSprawdzenia.matcher(dataUrodzenia).matches();
			if (dobraData) {
				try {
					kot.setDataUrodzenia(LocalDate.parse(dataUrodzenia, formatter));
				} catch (DateTimeParseException pe) {
					System.out.println("Coś jest nie tak z formatem daty!");
				}
			}
		} while (dobraData != true);

		// ----------------------------------------------------------------------------------------------

		Pattern wagaDoSprawdzenia = Pattern.compile("[0-9]+(\\.[0-9]+)?");
		String waga;
		boolean dobraWaga;
		do {
			System.out.println("Podaj wage kota: (przykładowy format to: 2.5)");
			waga = getUserInput();
			dobraWaga = wagaDoSprawdzenia.matcher(waga).matches();
			if (dobraWaga) {
				kot.setWaga(Float.parseFloat(waga));
			}
		} while (dobraWaga != true);

		// ----------------------------------------------------------------------------------------------

		System.out.println("Podaj imie opiekuna :");
		kot.setImieOpiekuna(getUserInput());

		// ----------------------------------------------------------------------------------------------
		kotDao.dodajKota(kot);
	}

	private static void pokazKoty() {
		List<Kot> koty = kotDao.wypiszKota();
		Pattern kotDoSprawdzenia = Pattern.compile("[0-9]+");
		String ktoryKot;
		if (!koty.isEmpty()) {
			do {
				for (int i = 0; i < koty.size(); i++) {
					System.out.println((i + 1) + ". " + koty.get(i).getImieKota());
				}

				System.out.println("Którego kotka wypisać z dostępnych opcji?");
				ktoryKot = getUserInput();
			} while ((kotDoSprawdzenia.matcher(ktoryKot).matches()
					&& Integer.parseInt(ktoryKot) <= koty.size()) != true);
			koty.get(Integer.parseInt(ktoryKot) - 1).przedstawSie();

		} else
			System.out.println("Brak kotów.");
	}

	/**
	 * Pomocnicza metoda pozwalająca pobrać jedną linijkę wpisaną przez
	 * użytkownika.
	 * 
	 * @return Wczytana linijka.
	 */
	public static String getUserInput() {
		return sc.nextLine();
	}

}
