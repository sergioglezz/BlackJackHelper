import React, { useState, useEffect } from 'react';
import { obtenerEstadoJuego, pedirCarta, mostrarCartaDealer, iniciarJuego } from '../services/blackjackService';
import '../styles/Tablero.css';

const Tablero = () => {
  const [juego, setJuego] = useState(null);

  // Función para obtener la ruta de imagen de una carta
  const obtenerRutaImagen = (carta) => {
    return carta.visible
      ? `${process.env.PUBLIC_URL}/assets/cartas/${carta.valor.toLowerCase()}_${carta.palo.toLowerCase()}.png`
      : `${process.env.PUBLIC_URL}/assets/cartas/atras.png`;
  };

  const cargarEstadoJuego = async () => {
    const estado = await obtenerEstadoJuego();
    setJuego(estado);
  };

  const manejarPedirCarta = async () => {
    await pedirCarta();
    await cargarEstadoJuego();
  };

  const manejarMostrarCartaDealer = async () => {
    await mostrarCartaDealer();
    await cargarEstadoJuego();
  };

  const manejarReiniciarJuego = async () => {
    await iniciarJuego();
    await cargarEstadoJuego();
  };

  useEffect(() => {
    cargarEstadoJuego();
  }, []);

  if (!juego) return <p>Cargando...</p>;

  return (
    <div>
      <h1>Blackjack</h1>
      <div className="tablero">
        <div>
            <h2>Dealer</h2>
            <div className="dealer">
            {juego.cartasDealer.map((carta, index) => (
                <img
                key={index}
                src={obtenerRutaImagen(carta)} // Usa la función aquí
                alt={carta.toString()}
                className="carta"
                />
            ))}
            </div>
        </div>
        <div>
            <h2>Jugador</h2>
            <div className="jugador">
            {juego.cartasJugador.map((carta, index) => (
                <img
                key={index}
                src={obtenerRutaImagen(carta)} // Usa la función aquí
                alt={carta.toString()}
                className="carta"
                />
            ))}
            </div>
        </div>
        <div className="botones">
          <button onClick={manejarPedirCarta}>Pedir Carta</button>
          <button onClick={manejarMostrarCartaDealer}>Mostrar Carta del Dealer</button>
          <button onClick={manejarReiniciarJuego}>Reiniciar Juego</button>
        </div>
      </div>
    </div>
  );
};

export default Tablero;
