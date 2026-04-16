package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import model.Juego;

@RestController
@RequestMapping("/blackjack")
public class BlackjackController {

    @Autowired
    private BlackjackService blackjackService;

    // Endpoint para iniciar una nueva partida
    @PostMapping("/nuevaPartida")
    public void nuevaPartida(@RequestParam int apuestaInicial) {
        blackjackService.nuevaPartida(apuestaInicial);
    }

    // Endpoint para que el jugador pida carta
    @PostMapping("/pedirCarta")
    public void pedirCarta() {
        blackjackService.pedirCarta();
    }

    // Endpoint para que el jugador se plante
    @PostMapping("/plantarse")
    public void plantarse() {
        blackjackService.plantarse();
    }

    // Endpoint para que el jugador separe cartas
    @PostMapping("/separar")
    public void separar() {
        blackjackService.separar();
    }

    /*
    // Endpoint para que el dealer juegue su turno
    @PostMapping("/jugarDealer")
    public void jugarDealer() {
        blackjackService.jugarDealer();
    }
    */

    // Endpoint para resolver la ronda
    @PostMapping("/resolverRonda")
    public void resolverRonda() {
        blackjackService.resolverRonda();
    }

    // Endpoint para reiniciar el juego (manteniendo el dinero del jugador)
    @PostMapping("/reiniciarJuego")
    public void reiniciarJuego() {
        blackjackService.reiniciarJuego();
    }

    // Endpoint para obtener el estado del juego (jugador, dealer, etc.)
    @GetMapping("/estadoJuego")
    public Juego obtenerEstadoJuego() {
        return blackjackService.getJuego();
    }
}
