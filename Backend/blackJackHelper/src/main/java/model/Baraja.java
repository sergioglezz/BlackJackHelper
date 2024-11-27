package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {

    private List<Carta> cartas; // Lista de cartas en la baraja
    private int indiceActual;  // Índice de la carta que se va a repartir

    public Baraja() {
        this.cartas = new ArrayList<>();
        this.indiceActual = 0;
        inicializarBaraja();
        mezclar();
    }

    // Inicializa la baraja con las 52 cartas (sin comodines)
    private void inicializarBaraja() {
        String[] palos = {"Corazones", "Diamantes", "Treboles", "Picas"};
        String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};

        for (String palo : palos) {
            for (String valor : valores) {
                cartas.add(new Carta(palo, valor, true));
            }
        }
    }

    // Mezcla las cartas
    public void mezclar() {
        Collections.shuffle(cartas);
        indiceActual = 0; // Reinicia el índice para repartir desde el principio
    }

    // Reparte una carta de la baraja
    public Carta repartir() {
        if (indiceActual < cartas.size()) {
            return cartas.get(indiceActual++);
        } else {
            throw new IllegalStateException("No hay más cartas en la baraja");
        }
    }

    // Devuelve el número de cartas restantes
    public int cartasRestantes() {
        return cartas.size() - indiceActual;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Carta carta : cartas) {
            sb.append(carta).append("\n");
        }
        return sb.toString();
    }
}