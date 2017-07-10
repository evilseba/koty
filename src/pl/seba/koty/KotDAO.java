package pl.seba.koty;

import java.util.ArrayList;
import java.util.List;

import pl.seba.koty.domain.Kot;

public class KotDAO {

	private List<Kot> listaKotow = new ArrayList<Kot>();
	
	public void dodajKota(Kot kot) {
		listaKotow.add(kot);
		System.out.println("Dodano nowego kota.");
	}
	
	public List<Kot> wypiszKota() {
		return listaKotow;
		
	}
}
