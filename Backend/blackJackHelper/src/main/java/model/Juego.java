package model;

import java.util.List;

public class Juego {
    private List<Carta> cartasJugador;
    private List<Carta> cartasDealer;
    private boolean dealerVisible;
    
    
	public Juego(List<Carta> cartasJugador, List<Carta> cartasDealer) {
		super();
		this.cartasJugador = cartasJugador;
		this.cartasDealer = cartasDealer;
	}

	public List<Carta> getCartasJugador() {
		return cartasJugador;
	}
	
	public void setCartasJugador(List<Carta> cartasJugador) {
		this.cartasJugador = cartasJugador;
	}
	
	public List<Carta> getCartasDealer() {
		return cartasDealer;
	}
	
	public void setCartasDealer(List<Carta> cartasDealer) {
		this.cartasDealer = cartasDealer;
	}
	
	public boolean isDealerVisible() {
		return dealerVisible;
	}
	
	public void setDealerVisible(boolean dealerVisible) {
		this.dealerVisible = dealerVisible;
	}

    
}
