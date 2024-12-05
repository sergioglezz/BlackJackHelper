package controller;

import model.Carta;
import model.Jugador;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BlackjackService {

    private List<Carta> mazo;
    private Jugador jugador;
    private List<Carta> cartasDealer;

    public BlackjackService() {
        this.jugador = new Jugador(); // Inicializa el jugador vacío
        this.cartasDealer = new ArrayList<>(); // Inicializa la lista de cartas del dealer
        inicializarMazo(); // Prepara el mazo
    }

    public void iniciarJuego() {
        if (!jugador.isApuestaRealizada()) {
            throw new IllegalStateException("Debes realizar una apuesta antes de iniciar el juego.");
        }
        jugador.getCartas().clear(); // Limpia las cartas del jugador
        barajarMazo();
        repartirCartasIniciales();
    }

    public void reiniciarJuego() {
        jugador.resetApuesta(); // Reinicia la apuesta
        iniciarJuego(); // Comienza de nuevo
    }

    private void inicializarMazo() {
        mazo = crearMazo();
    }
    
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


    private void barajarMazo() {
        Collections.shuffle(mazo);
    }

    private void repartirCartasIniciales() {
        cartasDealer = new ArrayList<>();

        // Repartimos dos cartas al jugador
        jugador.addCarta(mazo.remove(0));
        jugador.addCarta(mazo.remove(0));

        // Repartimos dos cartas al dealer (una oculta)
        Carta primeraCartaDealer = mazo.remove(0);
        Carta segundaCartaDealer = mazo.remove(0);

        cartasDealer.add(primeraCartaDealer);
        cartasDealer.add(new Carta(segundaCartaDealer.getPalo(), segundaCartaDealer.getValor(), false)); // Oculta
    }

    public Jugador getJugador() {
        return jugador;
    }

    public List<Carta> getCartasDealer() {
        return cartasDealer;
    }

    public void pedirCartaJugador() {
        if (mazo.isEmpty()) {
            throw new IllegalStateException("El mazo está vacío. No se pueden repartir más cartas.");
        }
        jugador.addCarta(mazo.remove(0));
    }

    public void pedirCartaDealer() {
        while (calcularPuntuacion(cartasDealer) < 17) {
            if (mazo.isEmpty()) {
                throw new IllegalStateException("El mazo está vacío. No se pueden repartir más cartas.");
            }
            cartasDealer.add(mazo.remove(0));
        }
    }

    public void plantarse() {
        mostrarCartaDealer();
        pedirCartaDealer();
        premioApuesta(resultado(calcularPuntuacion(getJugador().getCartas()), calcularPuntuacion(getCartasDealer())));
    }

    public byte resultado(int puntosJugador, int puntosDealer) {
        if (puntosJugador > puntosDealer || puntosDealer > 21) {
            return 1; // Ganó el jugador
        } else if (puntosJugador < puntosDealer) {
            return 2; // Ganó el dealer
        } else {
            return 3; // Empate
        }
    }
    
    public void premioApuesta(int resultado) {
    	if (resultado == 1) {
            jugador.setDinero(jugador.getDinero() + jugador.getApuesta() * 2);
    	    } else if (resultado == 3) {
            jugador.setDinero(jugador.getDinero() + jugador.getApuesta());
    	    }
    };
    
    
    public boolean verificarEstadoJuego(int puntos) {
        if (puntos > 21) {
            return true; // El jugador se pasa de 21
        }
        return false; // El jugador puede seguir jugando
    }

    public void mostrarCartaDealer() {
        if (!cartasDealer.isEmpty()) {
            cartasDealer.get(1).setVisible(true); // Revela la carta oculta
        }
    }

    public int calcularPuntuacion(List<Carta> mano) {
        int suma = 0;
        int ases = 0;

        for (Carta carta : mano) {
            String valor = carta.getValor();

            switch (valor) {
                case "T":
                case "J":
                case "Q":
                case "K":
                    suma += 10;
                    break;
                case "A":
                    ases++;
                    suma += 11; // Consideramos el As como 11 inicialmente
                    break;
                default:
                    suma += Integer.parseInt(valor);
            }
        }

        while (suma > 21 && ases > 0) {
            suma -= 10;
            ases--;
        }

        return suma;
    }
}
