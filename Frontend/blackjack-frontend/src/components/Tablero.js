import React, { useState, useEffect } from 'react';
import { obtenerEstadoJuego, pedirCarta, plantarse, iniciarJuego, apostar } from '../services/blackjackService';
import '../styles/Tablero.css';

const Tablero = () => {
  const [juego, setJuego] = useState(null);
  const [mensaje, setMensaje] = useState("");
  const [juegoTerminado, setJuegoTerminado] = useState(true); // Inicia como true (requiere apostar)
  const [apuesta, setApuesta] = useState(0);

  const cargarEstadoJuego = async () => {
    const estado = await obtenerEstadoJuego();
    setJuego(estado);
    setMensaje("");
    setJuegoTerminado(true); // Requiere apuesta para empezar
  };

  const manejarPedirCarta = async () => {
    const nuevoEstado = await pedirCarta();
    setJuego(nuevoEstado);

    if (nuevoEstado.juegoTerminado) {
      setMensaje("Te has pasado");
      setJuegoTerminado(true);
    }
  };

  const manejarPlantarse = async () => {
    const nuevoEstado = await plantarse();
    setJuego(nuevoEstado);

    if (nuevoEstado.resultado === 1) {
      setMensaje("Has ganado");
    } else if (nuevoEstado.resultado === 2) {
      setMensaje("Has perdido");
    } else {
      setMensaje("Empate");
    }
    setJuegoTerminado(true);
  };

  const manejarReiniciarJuego = async () => {
    const nuevoEstado = await iniciarJuego();
    setJuego(nuevoEstado);
    setMensaje("");
    setJuegoTerminado(true); // Vuelve a necesitar apuesta
    setApuesta(0); // Resetea la apuesta
  };

  const calcularPuntosPrimeraCarta = (cartasDealer) => {
    const primeraCarta = cartasDealer[0];
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

  const manejarApuesta = async () => {
    if (apuesta > 0) {
      try {
        const nuevoEstado = await apostar(apuesta);
        const juegoIniciado = await iniciarJuego();
        setJuego(juegoIniciado); // Inicializa el juego tras apostar
        setMensaje(`Has apostado ${apuesta}`);
        setJuegoTerminado(false); // Activa el juego
      } catch (error) {
        setMensaje("Error al realizar la apuesta");
      }
    } else {
      setMensaje("Debes seleccionar una cantidad para apostar");
    }
  };

  const añadirApuesta = (cantidad) => {
    if (juegoTerminado) setApuesta(apuesta + cantidad); // Solo permite modificar apuesta si el juego no ha comenzado
  };

  const borrarApuesta = () => {
    if (juegoTerminado) setApuesta(0); // Resetea la apuesta
  };

  useEffect(() => {
    cargarEstadoJuego();
  }, []);

  if (!juego) return <div><img height="800vh" src='assets/logo.png'></img></div>;

  return (
    <div>
      <h1>Blackjack</h1>
      {mensaje && <div className="mensaje">{mensaje}</div>}
      <div className="apuestas">
        <h1>Dinero</h1>
        <h2>{juego.jugador.dinero}</h2>
        <h1>Apuesta</h1>
        <h2>{apuesta}</h2>
        <br />
        <div className="botones">
          <button className="botones_apuestas" onClick={() => añadirApuesta(100)} disabled={!juegoTerminado}>
            <img src="/assets/ficha.png" alt="ficha" />100
          </button>
          <button className="botones_apuestas" onClick={() => añadirApuesta(500)} disabled={!juegoTerminado}>
            <img src="/assets/ficha.png" alt="ficha" />500
          </button>
          <button className="botones_apuestas" onClick={() => añadirApuesta(1000)} disabled={!juegoTerminado}>
            <img src="/assets/ficha.png" alt="ficha" />1K
          </button>
          <button className="botones_apuestas" onClick={() => añadirApuesta(5000)} disabled={!juegoTerminado}>
            <img src="/assets/ficha.png" alt="ficha" />5K
          </button>
          <button className="botones_apuestas" onClick={() => añadirApuesta(10000)} disabled={!juegoTerminado}>
            <img src="/assets/ficha.png" alt="ficha" />10K
          </button>
          <button className="botones_apuestas" onClick={() => añadirApuesta(25000)} disabled={!juegoTerminado}>
            <img src="/assets/ficha.png" alt="ficha" />25K
          </button>
        </div>
        <br />
        <div className="botones">
          <button onClick={borrarApuesta} disabled={!juegoTerminado}>
            Borrar
          </button>
          <button onClick={manejarApuesta} disabled={!juegoTerminado || apuesta === 0}>
            Apostar
          </button>
        </div>
      </div>
      <div className="tablero">
        <div>
          <h2>Dealer</h2>
          <div className="puntos-dealer">
            <h3>
              Puntos:{" "}
              {juego.cartasDealer[1].visible
                ? juego.puntosDealer
                : calcularPuntosPrimeraCarta(juego.cartasDealer)}
            </h3>
          </div>
          <div className="dealer">
            {juego.cartasDealer.map((carta, index) => (
              <img
                key={index}
                src={
                  index === 0 || carta.visible
                    ? `${process.env.PUBLIC_URL}/assets/cartas/${carta.valor.toLowerCase()}_${carta.palo.toLowerCase()}.png`
                    : `${process.env.PUBLIC_URL}/assets/cartas/atras.png`
                }
                alt={`Carta ${index}`}
                className={`carta ${carta.visible ? "revelar" : ""}`}
              />
            ))}
          </div>
        </div>
        <div>
          <h2>Jugador</h2>
          <div className="jugador">
            {juego.jugador.cartas.map((carta, index) => (
              <img
                key={index}
                src={`${process.env.PUBLIC_URL}/assets/cartas/${carta.valor.toLowerCase()}_${carta.palo.toLowerCase()}.png`}
                alt={`Carta ${index}`}
                className="carta"
              />
            ))}
          </div>
          <div className="puntos-jugador">
            <h3>Puntos: {juego.puntosJugador}</h3>
          </div>
        </div>
        <div className="botones">
          <button onClick={manejarPedirCarta} disabled={juegoTerminado}>
            Pedir Carta
          </button>
          <button onClick={manejarPlantarse} disabled={juegoTerminado}>
            Plantarse
          </button>
          <button onClick={manejarReiniciarJuego}>Reiniciar Juego</button>
        </div>
      </div>
    </div>
  );
};

export default Tablero;
