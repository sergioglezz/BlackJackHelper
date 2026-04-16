package controller;

import model.Juego;

import org.springframework.stereotype.Service;

@Service
public class BlackjackService {

	private Juego juego;

    public BlackjackService() {
        this.juego = new Juego(); // Inicializa el objeto al crear la clase
    }

    public Juego getJuego() {
        return juego;
    }
	
	//Inicialización del Juego
	public void nuevaPartida(int apuestaInicial) {
		juego.iniciarRonda(apuestaInicial);
		}
	
	//Acciones del Jugador
	public void pedirCarta() {
		juego.procesarAccionJugador("pedirCarta", 0);
	}
	
	public void plantarse() {
		juego.procesarAccionJugador("plantarse", 0);
	}
	
	public void separar() {
		juego.procesarAccionJugador("separar", 0);
	}
	
	/*
	 * //Acciones del Dealer
	public void jugarDealer() {
		juego.jugarDealer();
	}
	*/
	
	//Resolución de la Ronda
	public void resolverRonda() {
		juego.resolverRonda();
	}
	
	//Gestión del Estado del Juego
	public void reiniciarJuego() {
		juego.reiniciarJuego();
	}
}
