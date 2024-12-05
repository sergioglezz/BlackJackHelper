import React, { useState, useEffect } from "react";
import { obtenerEstadoJuego, pedirCarta, plantarse, iniciarJuego, apostar } from "../services/blackjackService";
import "../styles/Tablero.css";

const Tablero = () => {
  const [juego, setJuego] = useState(null);
  const [mensaje, setMensaje] = useState("");
  const [juegoTerminado, setJuegoTerminado] = useState(true); // Juego no iniciado
  const [apuesta, setApuesta] = useState(0);


  const cargarEstadoJuego = async () => {
      const estado = await obtenerEstadoJuego();
      setJuego(estado);
      setMensaje("");
      setJuegoTerminado(true); // Se requiere apuesta para iniciar
    };
    
  useEffect(() => {
    if (mensaje) {
      const timer = setTimeout(() => {
        setMensaje("");
        // Vaciar cartas y detener el juego
        setJuego((prevJuego) => ({
          ...prevJuego,
          cartasDealer: [],
          jugador: { ...prevJuego.jugador, cartas: [] },
        }));
        setApuesta(0);
        setJuegoTerminado(true); // Marcar el juego como terminado
      }, 3000); // 3 segundos para que desaparezca el mensaje
  
      return () => clearTimeout(timer);
    }
  }, [mensaje]);
  
  
  
  

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

  const manejarApuesta = async () => {
    if (apuesta > 0) {
      try {
        await apostar(apuesta);
        const juegoIniciado = await iniciarJuego();
        setJuego(juegoIniciado);
        setJuegoTerminado(false); // Activar el juego
      } catch (error) {
        setMensaje("Error al realizar la apuesta");
      }
    } else {
      setMensaje("Debes seleccionar una cantidad para apostar");
    }
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
      <div className="apuestas">
        <h1>Dinero</h1>
        <h2>{juego.jugador.dinero}</h2>
        <h1>Apuesta</h1>
        <h2>{apuesta}</h2>
        <div className="botones">
          {[100, 500, 1000, 5000, 10000, 25000].map((cantidad) => (
            <button
              key={cantidad}
              className="botones_apuestas"
              onClick={() => añadirApuesta(cantidad)}
              disabled={!juegoTerminado}
            >
              <img src="/assets/ficha.png" alt="ficha" />
              {cantidad}
            </button>
          ))}
        </div>
        <div className="botones">
          <button onClick={borrarApuesta} disabled={!juegoTerminado}>Borrar</button>
          <button onClick={manejarApuesta} disabled={!juegoTerminado || apuesta === 0}>Apostar</button>
        </div>
      </div>
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
        </div>
      </div>
    </div>
  );
};

export default Tablero;
