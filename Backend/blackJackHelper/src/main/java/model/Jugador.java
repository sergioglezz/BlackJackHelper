package model;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

	private List<Carta> cartas;
	private int dinero;
	
	public Jugador() {
		super();
        this.cartas = new ArrayList<>();
		this.dinero = 5000;
	}
	
	public List<Carta> getCartas() {
		return cartas;
	}

	public int getDinero() {
		return dinero;
	}
	public void setDinero(int dinero) {
		this.dinero = dinero;
	}
	
	public void addCarta(Carta carta) {
		this.cartas.add(carta);
	}
	
	@Override
	public String toString() {
		return "Jugador [cartas=" + cartas + ", dinero=" + dinero + "]";
	}
	
	
}
