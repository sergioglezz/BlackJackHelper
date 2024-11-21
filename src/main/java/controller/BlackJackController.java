package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import model.Baraja;
import model.Carta;
import model.Dealer;

@Controller
public class BlackJackController {
    
    @GetMapping("/BJ/iniciar")
    public String iniciar(Model model) {
        Baraja baraja = new Baraja();

        // Cartas del jugador
        Carta jugadorCarta1 = baraja.repartir();
        Carta jugadorCarta2 = baraja.repartir();

        // Cartas del dealer
        Carta dealerCarta1 = baraja.repartir();
        Carta dealerCarta2 = baraja.repartir();
        dealerCarta2.setVisible(false);

        // Dealer como modelo
        Dealer dealer = new Dealer(dealerCarta1, dealerCarta2);

        // Añadir datos al modelo
        model.addAttribute("cartasJugador", List.of(jugadorCarta1, jugadorCarta2));
        model.addAttribute("dealerCarta1", dealerCarta1);
        model.addAttribute("dealerCarta2", dealerCarta2);

        return "blackjack.html"; // Renderiza el archivo blackjack.html
    }
}
