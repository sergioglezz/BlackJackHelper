import axios from 'axios';

const API_URL = 'http://localhost:8080/blackjack'; // Cambia esta URL según tu configuración

export const nuevaPartida = async (apuestaInicial) => {
  try {
    await axios.post(`${API_URL}/nuevaPartida`, null, { params: { apuestaInicial } });
  } catch (error) {
    console.error('Error al iniciar nueva partida:', error);
  }
};

export const pedirCarta = async () => {
  try {
    await axios.post(`${API_URL}/pedirCarta`);
  } catch (error) {
    console.error('Error al pedir carta:', error);
  }
};

export const plantarse = async () => {
  try {
    await axios.post(`${API_URL}/plantarse`);
  } catch (error) {
    console.error('Error al plantarse:', error);
  }
};

export const separar = async () => {
  try {
    await axios.post(`${API_URL}/separar`);
  } catch (error) {
    console.error('Error al separar cartas:', error);
  }
};

export const resolverRonda = async () => {
  try {
    await axios.post(`${API_URL}/resolverRonda`);
  } catch (error) {
    console.error('Error al resolver la ronda:', error);
  }
};

export const reiniciarJuego = async () => {
  try {
    await axios.post(`${API_URL}/reiniciarJuego`);
  } catch (error) {
    console.error('Error al reiniciar el juego:', error);
  }
};

export const obtenerEstadoJuego = async () => {
  try {
    const response = await axios.get(`${API_URL}/estadoJuego`);
    return response.data; // Devuelve el estado del juego al frontend
  } catch (error) {
    console.error('Error al obtener el estado del juego:', error);
  }
};
