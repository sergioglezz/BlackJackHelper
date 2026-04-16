package model;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private Mano mano;

    public Dealer() {
        this.mano = new Mano();
    }

    public void jugar(Mazo mazo) {
        while (mano.calcularPuntos() < 17) {
            mano.agregarCarta(mazo.sacarCarta());
        }
    }

    public Mano getMano() {
        return mano;
    }
}
