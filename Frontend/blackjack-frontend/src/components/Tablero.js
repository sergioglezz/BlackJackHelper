import React, { useState, useEffect } from 'react';
import { obtenerEstadoJuego, pedirCarta, plantarse, iniciarJuego } from '../services/blackjackService';
import '../styles/Tablero.css';

const Tablero = () => {
  const [juego, setJuego] = useState(null);
  const [mensaje, setMensaje] = useState("");
  const [juegoTerminado, setJuegoTerminado] = useState(false);

  const cargarEstadoJuego = async () => {
    const estado = await obtenerEstadoJuego();
    setJuego(estado);
    setMensaje("");
    setJuegoTerminado(false);
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

    if (nuevoEstado.resultado==1) {
        setMensaje("Has ganado");
        setJuegoTerminado(true);
    }else if(nuevoEstado.resultado==2) {
        setMensaje("Has perdido");
        setJuegoTerminado(true);
    }else {
        setMensaje("Empate");
        setJuegoTerminado(true);}
  };

  const manejarReiniciarJuego = async () => {
    const nuevoEstado = await iniciarJuego();
    setJuego(nuevoEstado);
    setMensaje("");
    setJuegoTerminado(false);
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
        return 11; // O puedes considerar 1 según las reglas, pero generalmente es 11 al inicio
      default:
        return parseInt(primeraCarta.valor);
    }
  };
  

  useEffect(() => {
    cargarEstadoJuego();
  }, []);

  if (!juego) return <div>Cargando...</div>;

  return (
    <div>
      <h1>Blackjack</h1>
      {mensaje && <div className="mensaje">{mensaje}</div>}
      <div className='apuestas'>
        <h1>Dinero</h1>
        <h2>{juego.jugador.dinero}</h2>
        <div className="botones">
          <button className="botones_apuestas" disabled={juegoTerminado}>100</button>
          <button className="botones_apuestas" disabled={juegoTerminado}>500</button>
          <button className="botones_apuestas" disabled={juegoTerminado}>1K</button>
          <button className="botones_apuestas" disabled={juegoTerminado}>5K</button>
          <button className="botones_apuestas" disabled={juegoTerminado}>10K</button>
          <button className="botones_apuestas" disabled={juegoTerminado}>25K</button>

        </div>
        <div className="botones">
          <button disabled={juegoTerminado}>Apostar</button>
        </div>
      </div>
      <div className="tablero">
        <div>
            <h2>Dealer</h2>
            <div className="puntos-dealer">
            <h3>
                Puntos:{" "}
                {juego.cartasDealer[1].visible
                ? juego.puntosDealer // Mostrar la suma total si la segunda carta está visible
                : calcularPuntosPrimeraCarta(juego.cartasDealer)} {/* Mostrar solo la puntuación de la primera carta si la segunda está oculta */}
            </h3>
            </div>
            <div className="dealer">
                {juego.cartasDealer.map((carta, index) => (
                <img
                  key={index}
                  src={
                    index === 0 || carta.visible // Mostrar la primera carta o si es visible
                      ? `${process.env.PUBLIC_URL}/assets/cartas/${carta.valor.toLowerCase()}_${carta.palo.toLowerCase()}.png`
                      : `${process.env.PUBLIC_URL}/assets/cartas/atras.png`}
                  alt={`Carta ${index}`}
                  className={`carta ${carta.visible ? "revelar" : ""}`} // Añadir clase para animación
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
          <button onClick={manejarPedirCarta} disabled={juegoTerminado}>Pedir Carta</button>
          <button onClick={manejarPlantarse} disabled={juegoTerminado}>Plantarse</button>
          <button onClick={manejarReiniciarJuego}>Reiniciar Juego</button>
        </div>
      </div>
    </div>
  );
};

export default Tablero;