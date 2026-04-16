package model;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private int dinero;
    private List<Mano> manos;
    private List<Integer> apuestas;

    public Jugador(Integer dineroInicial) {
        this.dinero = dineroInicial;
        this.manos = new ArrayList<>();
        this.apuestas = new ArrayList<>();
        manos.add(new Mano()); // Una mano inicial
        apuestas.add(0);     // Apuesta inicial por defecto
    }

    public void registrarApuesta(int apuesta) {
        if (dinero < apuesta) {
            throw new IllegalArgumentException("Fondos insuficientes para esta apuesta.");
        }
        apuestas.add(apuesta);
        dinero -= apuesta;
    }
    
    public void separar(int indiceMano, int apuesta) {
        Mano mano = manos.get(indiceMano);
        if (mano.getCartas().size() == 2 && mano.getCartas().get(0).getValor().equals(mano.getCartas().get(1).getValor())) {
            Carta cartaSeparada = mano.getCartas().remove(1);
            Mano nuevaMano = new Mano();
            nuevaMano.agregarCarta(cartaSeparada);
            manos.add(nuevaMano);
            this.registrarApuesta(apuesta);
        }
    }

    public List<Mano> getManos() {
        return manos;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public List<Integer> getApuestas() {
        return apuestas;
    }
    
    public void limpiarApuestas(){
    	this.apuestas.clear();
    }
    
}

