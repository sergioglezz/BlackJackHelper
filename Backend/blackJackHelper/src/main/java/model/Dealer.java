package model;

import java.util.ArrayList;
import java.util.List;

public class Dealer {


    private List<Carta> cartas;
    

	public Dealer() {
		super();
        this.cartas = new ArrayList<>();
	}


	public List<Carta> getCartas() {
		return cartas;
	}

	public void addCarta(Carta carta) {
		this.cartas.add(carta);
	}
	
	
	@Override
	public String toString() {
		return "Dealer [cartas=" + cartas + "]";
	}

}
