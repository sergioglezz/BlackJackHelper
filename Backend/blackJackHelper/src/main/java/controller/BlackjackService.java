package controller;

import model.Carta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BlackjackService {

    private List<Carta> mazo;
    private List<Carta> cartasJugador;
    private List<Carta> cartasDealer;

    public BlackjackService() {
        iniciarJuego();
    }

    public void iniciarJuego() {
        mazo = crearMazo();
        Collections.shuffle(mazo); // Barajamos las cartas

        cartasJugador = new ArrayList<>();
        cartasDealer = new ArrayList<>();

        // Repartimos dos cartas al jugador
        cartasJugador.add(mazo.remove(0));
        cartasJugador.add(mazo.remove(0));

        // Repartimos dos cartas al dealer
        Carta primeraCartaDealer = mazo.remove(0);
        Carta segundaCartaDealer = mazo.remove(0);

        cartasDealer.add(primeraCartaDealer);
        cartasDealer.add(new Carta(segundaCartaDealer.getPalo(), segundaCartaDealer.getValor(), false)); // Segunda carta oculta
    }

    // Devuelve las cartas actuales del jugador
    public List<Carta> getCartasJugador() {
        return cartasJugador;
    }

    // Devuelve las cartas actuales del dealer
    public List<Carta> getCartasDealer() {
        return cartasDealer;
    }

    // Lógica para pedir una carta para el jugador
    public void pedirCartaJugador() {
        if (!mazo.isEmpty()) {
            cartasJugador.add(mazo.remove(0));
        }
    }

    // Muestra la carta oculta del dealer
    public void mostrarCartaDealer() {
        if (!cartasDealer.isEmpty()) {
            cartasDealer.get(1).setVisible(true); // Cambia la carta oculta a visible
        }
    }

    // Crea un mazo completo de 52 cartas
    private List<Carta> crearMazo() {
        String[] palos = {"corazones", "diamantes", "treboles", "picas"};
        String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};

        List<Carta> mazo = new ArrayList<>();
        for (String palo : palos) {
            for (String valor : valores) {
                mazo.add(new Carta(palo, valor, true));
            }
        }
        return mazo;
    }
}
