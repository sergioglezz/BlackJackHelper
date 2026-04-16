import React, { useState, useEffect } from "react";
import { nuevaPartida, pedirCarta, plantarse, separar, resolverRonda, reiniciarJuego, obtenerEstadoJuego } from "../services/blackjackService.js";
import "../styles/Tablero.css";

const Tablero = () => {
  const [juego, setJuego] = useState(null);
  const [mensaje, setMensaje] = useState("");
  const [juegoTerminado, setJuegoTerminado] = useState(true); // Indica si el juego está terminado o no
  const [apuesta, setApuesta] = useState(0);

  const cargarEstadoJuego = async () => {
    const estado = await obtenerEstadoJuego();
    setJuego(estado);
    setMensaje("");
  };

  const manejarNuevaPartida = async () => {
    if (apuesta > 0) {
      try {
        await nuevaPartida(apuesta);
        const estado = await obtenerEstadoJuego();
        setJuego(estado);
        setJuegoTerminado(false);
        setMensaje("");
      } catch (error) {
        setMensaje("Error al iniciar la partida");
      }
    } else {
      setMensaje("Debes realizar una apuesta para comenzar");
    }
  };

  const manejarPedirCarta = async () => {
    await pedirCarta();
    const estado = await obtenerEstadoJuego();
    setJuego(estado);
    if (estado.juegoTerminado) {
      setMensaje("Te has pasado");
      setJuegoTerminado(true);
    }
  };

  const manejarPlantarse = async () => {
    await plantarse();
    const estado = await obtenerEstadoJuego();
    setJuego(estado);
    setJuegoTerminado(true);

    if (estado.resultado === 1) {
      setMensaje("Has ganado");
    } else if (estado.resultado === 2) {
      setMensaje("Has perdido");
    } else {
      setMensaje("Empate");
    }
  };

  const manejarSeparar = async () => {
    await separar();
    const estado = await obtenerEstadoJuego();
    setJuego(estado);
  };

  const manejarResolverRonda = async () => {
    await resolverRonda();
    const estado = await obtenerEstadoJuego();
    setJuego(estado);
    setJuegoTerminado(true);
  };

  const manejarReiniciarJuego = async () => {
    await reiniciarJuego();
    const estado = await obtenerEstadoJuego();
    setJuego(estado);
    setMensaje("");
    setApuesta(0);
    setJuegoTerminado(true);
  };

  const añadirApuesta = (cantidad) => {
    if (juegoTerminado) setApuesta(apuesta + cantidad);
  };

  const borrarApuesta = () => {
    if (juegoTerminado) setApuesta(0);
  };

  const calcularPuntosPrimeraCarta = (cartasDealer) => {
    const primeraCarta = cartasDealer?.[0];
    if (!primeraCarta) return 0;

    switch (primeraCarta.valor) {
      case "T":
      case "J":
      case "Q":
      case "K":
        return 10;
      case "A":
        return 11;
      default:
        return parseInt(primeraCarta.valor);
    }
  };

  useEffect(() => {
    cargarEstadoJuego();
  }, []);

  if (!juego) return <div><img height="800vh" src='assets/logo.png'></img></div>;

  return (
    <div>
      <h1>Blackjack</h1>
      {mensaje && <div className="mensaje">{mensaje}</div>}
      
      <div className="tablero">
        <div>
          <h2>Dealer</h2>
          {juego.cartasDealer?.length > 0 && (
            <>
              <h3>Puntos: {juego.cartasDealer[1]?.visible ? juego.puntosDealer : calcularPuntosPrimeraCarta(juego.cartasDealer)}</h3>
              <div className="dealer">
                {juego.cartasDealer.map((carta, index) => (
                  <img className="carta"
                    key={index}
                    src={carta.visible
                      ? `${process.env.PUBLIC_URL}/assets/cartas/${carta.valor.toLowerCase()}_${carta.palo.toLowerCase()}.png`
                      : `${process.env.PUBLIC_URL}/assets/cartas/atras.png`}
                    alt={`Carta ${index}`}
                  />
                ))}
              </div>
            </>
          )}
        </div>
        <div>
          <h2>Jugador</h2>
          {juego.jugador.cartas?.length > 0 && (
            <>
              <div className="jugador">
                {juego.jugador.cartas.map((carta, index) => (
                  <img className="carta"
                    key={index}
                    src={`${process.env.PUBLIC_URL}/assets/cartas/${carta.valor.toLowerCase()}_${carta.palo.toLowerCase()}.png`}
                    alt={`Carta ${index}`}
                  />
                ))}
              </div>
              <h3>Puntos: {juego.puntosJugador}</h3>
            </>
          )}
        </div>
        <div className="botones_acciones">
          <button onClick={manejarPedirCarta} disabled={juegoTerminado}>Pedir Carta</button>
          <button onClick={manejarPlantarse} disabled={juegoTerminado}>Plantarse</button>
          <button onClick={manejarSeparar} disabled={juegoTerminado}>Separar</button>
          <button onClick={manejarResolverRonda} disabled={!juegoTerminado}>Resolver Ronda</button>
        </div>
      </div>
    </div>
  );
};

export default Tablero;
