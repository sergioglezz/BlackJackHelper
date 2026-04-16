package model;

public class Juego {
    private Mazo mazo;
    private Jugador jugador;
    private Dealer dealer;
    

	public Juego() {
		super();
		this.mazo = new Mazo();
		this.jugador = new Jugador(1000);
		this.dealer = new Dealer();
	}

	public void iniciarRonda(int apuesta) {
        mazo.barajar();
        jugador.registrarApuesta(apuesta);
        jugador.getManos().get(0).agregarCarta(mazo.sacarCarta());
        jugador.getManos().get(0).agregarCarta(mazo.sacarCarta());
        dealer.getMano().agregarCarta(mazo.sacarCarta());
        dealer.getMano().agregarCarta(mazo.sacarCarta());
    }

    public void procesarAccionJugador(String accion, int indiceMano) {
        Mano mano = jugador.getManos().get(indiceMano);

        switch (accion) {
            case "pedirCarta":
                mano.agregarCarta(mazo.sacarCarta());
                break;
            case "plantarse":
                mano.setPlantada(true);
                break;
            case "separar":
                jugador.separar(indiceMano, jugador.getApuestas().get(indiceMano));
                break;
        }
    }

    /*
    public void jugarDealer() {
        dealer.jugar(mazo);
    }
    */
    
    public void resolverRonda() {
        // Dealer juega su turno hasta alcanzar al menos 17 puntos
        dealer.jugar(mazo);

        int puntosDealer = dealer.getMano().calcularPuntos();
        boolean dealerSePasa = puntosDealer > 21;

        for (int i = 0; i < jugador.getManos().size(); i++) {
            Mano manoJugador = jugador.getManos().get(i);
            int puntosJugador = manoJugador.calcularPuntos();
            int apuesta = jugador.getApuestas().get(i);

            // Caso 1: Jugador se pasa
            if (puntosJugador > 21) {
                // Jugador pierde automáticamente, apuesta no se devuelve
                continue;
            }

            // Caso 2: Dealer se pasa
            if (dealerSePasa) {
                jugador.setDinero(jugador.getDinero() + apuesta * 2); // Jugador gana el doble de la apuesta
                continue;
            }

            // Caso 3: Comparar puntos
            if (puntosJugador > puntosDealer) {
                jugador.setDinero(jugador.getDinero() + apuesta * 2); // Jugador gana
            } else if (puntosJugador == puntosDealer) {
                jugador.setDinero(jugador.getDinero() + apuesta); // Empate, apuesta devuelta
            }
            // Caso 4: Jugador tiene menos puntos que el dealer (pierde)
            // No se hace nada, la apuesta se pierde
        }
    }

    public void reiniciarJuego() {
        jugador.getManos().clear();
        dealer.getMano().getCartas().clear();;
        jugador.limpiarApuestas();
    }

	public Mazo getMazo() {
		return mazo;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public Dealer getDealer() {
		return dealer;
	}   
    
}
