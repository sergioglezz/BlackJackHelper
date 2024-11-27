package model;

public class Carta {
    private String palo;
    private String valor;
    private boolean visible;
    private String rutaImagen;


    public Carta(String palo, String valor, boolean visible) {
        this.palo = palo;
        this.valor = valor;
        this.visible = visible;
        this.rutaImagen = "/images/cartas/" + valor.toLowerCase() + "_" + palo.toLowerCase() + ".png";
    }

    public Carta(Carta carta) {
        this.palo = carta.getPalo();
        this.valor = carta.getValor();
        this.visible = carta.isVisible();
	}

	public String getPalo() {
        return palo;
    }

    public String getValor() {
        return valor;
    }

    public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
    public String getImageName() {
        return valor + "_" + palo + ".png";
    }
	
    public String getRutaImagen() {
        return visible ? rutaImagen : "/images/cartas/carta_oculta.png";
    }

	@Override
    public String toString() {
        return valor + " de " + palo;
    }
	
}
