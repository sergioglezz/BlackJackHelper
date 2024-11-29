const API_URL = "http://localhost:8080/blackjack"; // Asegúrate de que sea tu URL correcta

export const obtenerEstadoJuego = async () => {
  const response = await fetch(`${API_URL}/estado`);
  if (!response.ok) throw new Error("Error al obtener el estado del juego");
  return await response.json();
};

export const pedirCarta = async () => {
  const response = await fetch(`${API_URL}/pedirCarta`, { method: "POST" });
  if (!response.ok) throw new Error("Error al pedir carta");
  return await response.json();
};

export const plantarse = async () => {
  const response = await fetch(`${API_URL}/plantarse`, { method: "POST" });
  if (!response.ok) throw new Error("Error al plantarse");
  return await response.json();
};

export const iniciarJuego = async () => {
  const response = await fetch(`${API_URL}/iniciarJuego`, { method: "POST" });
  if (!response.ok) throw new Error("Error al iniciar juego");
  return await response.json();
};

