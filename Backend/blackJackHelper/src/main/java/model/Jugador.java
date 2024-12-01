package model;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private List<Carta> cartas;
    private int dinero;
    private int apuesta;
    private boolean apuestaRealizada;

    public Jugador() {
        this.cartas = new ArrayList<>();
        this.dinero = 5000; // Dinero inicial
        this.apuesta = 0;
        this.apuestaRealizada = false;
    }

    public void addCarta(Carta carta) {
        this.cartas.add(carta);
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

    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
        this.apuestaRealizada = true; // Se marca como realizada al establecer una apuesta
    }

    public boolean isApuestaRealizada() {
        return apuestaRealizada;
    }

    public void resetApuesta() {
        this.apuesta = 0;
        this.apuestaRealizada = false; // Se reinicia cuando termina el juego
    }
}
