package controller;

import model.Juego;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        blackjackService.pedirCartaJugador();
        int puntosJugador = blackjackService.calcularPuntuacion(blackjackService.getJugador().getCartas());
        boolean juegoTerminado  = blackjackService.verificarEstadoJuego(puntosJugador);

        return getEstadoJuego();
        
    }


    @PostMapping("/plantarse")
    public Juego plantarse() {
    	blackjackService.plantarse();
        int puntosJugador = blackjackService.calcularPuntuacion(blackjackService.getJugador().getCartas());
        int puntosDealer = blackjackService.calcularPuntuacion(blackjackService.getCartasDealer());
        byte resultado = blackjackService.resultado(puntosJugador, puntosDealer);
        
        // Comprobamos el estado del juego
        boolean juegoTerminado = true;
        
        return getEstadoJuego();

    }


    @PostMapping("/iniciarJuego")
    public Juego iniciarJuego() {
        blackjackService.iniciarJuego();
        return new Juego(
            blackjackService.getJugador(),
            blackjackService.getCartasDealer(),
            blackjackService.calcularPuntuacion(blackjackService.getCartasDealer()),
            blackjackService.calcularPuntuacion(blackjackService.getJugador().getCartas()),
            false,
            (byte) 0
        );
    }
}
