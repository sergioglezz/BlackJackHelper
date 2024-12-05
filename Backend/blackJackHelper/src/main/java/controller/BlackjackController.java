package controller;

import model.Juego;
import model.Jugador;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackjack")
public class BlackjackController {

    private final BlackjackService blackjackService;

    public BlackjackController(BlackjackService blackjackService) {
        this.blackjackService = blackjackService;
    }

    @GetMapping("/estado")
    public Juego getEstadoJuego() {
        int puntosJugador = blackjackService.calcularPuntuacion(blackjackService.getJugador().getCartas());
        int puntosDealer = blackjackService.calcularPuntuacion(blackjackService.getCartasDealer());
        byte resultado = blackjackService.resultado(puntosJugador, puntosDealer);

        boolean juegoTerminado = blackjackService.verificarEstadoJuego(puntosJugador);

        return new Juego(
            blackjackService.getJugador(),
            blackjackService.getCartasDealer(),
            puntosDealer,
            puntosJugador,
            juegoTerminado,
            resultado
        );
    }

    @PostMapping("/pedirCarta")
    public Juego pedirCartaJugador() {
        if (!blackjackService.getJugador().isApuestaRealizada()) {
            throw new IllegalStateException("Debes realizar una apuesta antes de pedir una carta.");
        }
        blackjackService.pedirCartaJugador();
        return getEstadoJuego();
    }

    @PostMapping("/plantarse")
    public Juego plantarse() {
        if (!blackjackService.getJugador().isApuestaRealizada()) {
            throw new IllegalStateException("Debes realizar una apuesta antes de plantarte.");
        }
        blackjackService.plantarse();
        return getEstadoJuego();
    }

    @PostMapping("/apostar")
    public Juego apostar(@RequestBody Map<String, Integer> body) {
        int cantidad = body.get("cantidad");
        Jugador jugador = blackjackService.getJugador();

        if (jugador.getDinero() >= cantidad) {
            jugador.setDinero(jugador.getDinero() - cantidad);
            jugador.setApuesta(cantidad);
            return getEstadoJuego();
        } else {
            throw new IllegalArgumentException("No tienes suficiente dinero para esta apuesta.");
        }
    }


    @PostMapping("/iniciarJuego")
    public Juego iniciarJuego() {
        try {
            blackjackService.iniciarJuego();
            return getEstadoJuego();
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}

