const BASE_URL = "http://localhost:8080/blackjack";

export const obtenerEstadoJuego = async () => {
    const response = await fetch(`${BASE_URL}/estado`);
    return await response.json();
};

export const pedirCarta = async () => {
    await fetch(`${BASE_URL}/pedirCarta`, { method: "POST" });
};

export const mostrarCartaDealer = async () => {
    await fetch(`${BASE_URL}/mostrarCartaDealer`, { method: "POST" });
};

export const iniciarJuego = async () => {
    await fetch(`${BASE_URL}/iniciarJuego`, { method: "POST" });
};
