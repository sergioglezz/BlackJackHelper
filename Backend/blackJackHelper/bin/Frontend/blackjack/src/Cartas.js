import React from 'react';
import './Cartas.css'; // Opcional, si quieres estilos específicos para las cartas.

function Cartas({ carta }) {
  return (
    <div className="card">
      <img 
        src={carta.visible ? carta.rutaImagen : '/images/cartas/atras.png'} 
        alt={carta.visible ? `${carta.valor} de ${carta.palo}` : 'Carta Oculta'} 
      />
    </div>
  );
}

export default Cartas;
