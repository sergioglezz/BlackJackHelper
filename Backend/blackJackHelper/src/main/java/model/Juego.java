package model;

import java.util.List;

public class Juego {
    private Jugador jugador; // Objeto Jugador
    private List<Carta> cartasDealer;
    private int puntosDealer;
    private int puntosJugador;
    private boolean juegoTerminado;
    private byte resultado;

    public Juego(Jugador jugador, List<Carta> cartasDealer, int puntosDealer,int puntosJugador, boolean juegoTerminado, byte resultado) {
        this.jugador = jugador;
        this.cartasDealer = cartasDealer;
        this.puntosDealer = puntosDealer;
        this.setPuntosJugador(puntosJugador);
        this.juegoTerminado = juegoTerminado;
        this.resultado = resultado;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public List<Carta> getCartasDealer() {
        return cartasDealer;
    }

    public int getPuntosDealer() {
        return puntosDealer;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public byte getResultado() {
        return resultado;
    }

    public void setResultado(byte resultado) {
        this.resultado = resultado;
    }

	public int getPuntosJugador() {
		return puntosJugador;
	}

	public void setPuntosJugador(int puntosJugador) {
		this.puntosJugador = puntosJugador;
	}
}

