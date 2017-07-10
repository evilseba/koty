package pl.seba.koty.domain;

import java.time.LocalDate;

/**
 * 
 * Klasa reprezentujaca kota
 *
 */
public class Kot {

	/**
	 * Imię kota.
	 */
	private String imieKota;

	/**
	 * Data urodzenia kota.
	 */
	private LocalDate dataUrodzenia;

	/**
	 * Waga kota.
	 */
	private Float waga;

	/**
	 * Imię opiekuna kota.
	 */
	private String imieOpiekuna;

	public String getImieKota() {
		return imieKota;
	}

	public void setImieKota(String imieKota) {
		this.imieKota = imieKota;
	}

	public LocalDate getDataUrodzenia() {
		return dataUrodzenia;
	}

	public void setDataUrodzenia(LocalDate data) {
		this.dataUrodzenia = data;
	}

	public float getWaga() {
		return waga;
	}

	public void setWaga(float waga) {
		this.waga = waga;
	}

	public String getImieOpiekuna() {
		return imieOpiekuna;
	}

	public void setImieOpiekuna(String imieOpiekuna) {
		this.imieOpiekuna = imieOpiekuna;
	}

	/**
	 * Metoda, która opisuje kota pełnym zdaniem.
	 * 
	 * @return Opis kota w postaci zdania.
	 */
	public void przedstawSie() {
		System.out.println("Imie kota: " + imieKota + " data urodzenia: " + dataUrodzenia + " waga: " + waga + " imie opiekuna: "
				+ imieOpiekuna);
	}

}
