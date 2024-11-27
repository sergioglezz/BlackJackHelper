package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Juego;

@RestController
@RequestMapping("/blackjack")
public class BlackjackController {

    private final BlackjackService blackjackService;

    public BlackjackController(BlackjackService blackjackService) {
        this.blackjackService = blackjackService;
    }

    // Endpoint para obtener el estado actual del juego
    @GetMapping("/estado")
    public Juego getEstadoJuego() {
        return new Juego(blackjackService.getCartasJugador(), blackjackService.getCartasDealer());
    }

    // Endpoint para pedir una carta para el jugador
    @PostMapping("/pedirCarta")
    public void pedirCartaJugador() {
        blackjackService.pedirCartaJugador();
    }

    // Endpoint para mostrar la carta oculta del dealer
    @PostMapping("/mostrarCartaDealer")
    public void mostrarCartaDealer() {
        blackjackService.mostrarCartaDealer();
    }

    // Endpoint para iniciar/reiniciar el juego
    @PostMapping("/iniciarJuego")
    public void iniciarJuego() {
        blackjackService.iniciarJuego();
    }
}
