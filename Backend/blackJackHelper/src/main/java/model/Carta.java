package model;

public class Carta {
    private String valor; // Ejemplo: "A", "2", "3", ..., "K"
    private String palo;  // "corazones", "picas", "tréboles", "diamantes"
    private boolean visible; // Si la carta es visible o está oculta

    // Constructor
    public Carta(String valor, String palo, boolean visible) {
        this.valor = valor;
        this.palo = palo;
        this.visible = visible;
    }

    // Getters y Setters
    public int getValorNumerico() {
        if (valor.equals("A")) return 11;
        if (valor.equals("K") || valor.equals("Q") || valor.equals("J") || valor.equals("T")) return 10;
        return Integer.parseInt(valor);
    }

    public String getValor() {
        return valor;
    }

    public String getPalo() {
        return palo;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
