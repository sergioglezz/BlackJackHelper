package model;

import java.util.Collections;
import java.util.Stack;

public class Mazo {
    private Stack<Carta> cartas;

    public Mazo() {
        this.cartas = new Stack<>();
        inicializarMazo();
    }

    private void inicializarMazo() {
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        String[] palos = {"corazones", "picas", "treboles", "diamantes"};
        
        for (String palo : palos) {
            for (String valor : valores) {
                cartas.push(new Carta(valor, palo, true));
            }
        }
        barajar();
    }

    public void barajar() {
        Collections.shuffle(cartas);
    }

    public Carta sacarCarta() {
        return cartas.pop();
    }
}
