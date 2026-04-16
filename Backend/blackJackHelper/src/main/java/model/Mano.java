package model;

import java.util.ArrayList;
import java.util.List;

public class Mano {
    private List<Carta> cartas;
    private boolean plantada;
    private boolean pasada;

    public Mano() {
        this.cartas = new ArrayList<>();
        this.plantada = false;
        this.pasada = false;
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
        if (calcularPuntos() > 21) {
            pasada = true;
        }
    }

    public int calcularPuntos() {
        int puntos = 0;
        int ases = 0;

        for (Carta carta : cartas) {
            puntos += carta.getValorNumerico();
            if (carta.getValor().equals("A")) ases++;
        }

        // Ajustar el valor del As si los puntos exceden 21
        while (puntos > 21 && ases > 0) {
            puntos -= 10;
            ases--;
        }
        return puntos;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public boolean isPlantada() {
        return plantada;
    }

    public void setPlantada(boolean plantada) {
        this.plantada = plantada;
    }

    public boolean isPasada() {
        return pasada;
    }
}
