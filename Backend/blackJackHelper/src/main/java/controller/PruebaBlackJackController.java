package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import model.Baraja;
import model.Carta;
import model.Dealer;
import model.Jugador;

@Controller
public class PruebaBlackJackController {
    
    @GetMapping("/BJ/iniciar")
    public String iniciar(Model model) {
        Baraja baraja = new Baraja();

        Jugador jugador1 = new Jugador(2000);
        
        // Cartas del jugador
        Carta jugadorCarta1 = baraja.repartir();
        Carta jugadorCarta2 = baraja.repartir();
        Carta jugadorCarta3 = baraja.repartir();


        jugador1.addCarta(jugadorCarta1);
        jugador1.addCarta(jugadorCarta2);

        
        // Dealer como modelo
        Dealer dealer = new Dealer();
        
        // Cartas del dealer
        Carta dealerCarta1 = baraja.repartir();
        Carta dealerCarta2 = baraja.repartir();
        dealerCarta2.setVisible(false);
        
        dealer.addCarta(dealerCarta1);
        dealer.addCarta(dealerCarta2);
        
        // Añadir datos al modelo
        model.addAttribute("cartasJugador", List.of(jugadorCarta1, jugadorCarta2));
        model.addAttribute("dealerCarta1", dealerCarta1);
        model.addAttribute("dealerCarta2", dealerCarta2);

        return "blackjack.html"; // Renderiza el archivo blackjack.html
    }
}
