package model;

import java.util.List;

public class Dealer {

    private Carta carta1;
    private Carta carta2;
    
	public Dealer(Carta carta1, Carta carta2) {
		super();
		this.carta1 = carta1;
		this.carta2 = carta2;
	}

	public Carta getCarta1() {
		return carta1;
	}

	public void setCarta1(Carta carta1) {
		this.carta1 = carta1;
	}

	public Carta getCarta2() {
		return carta2;
	}

	public void setCarta2(Carta carta2) {
		this.carta2 = carta2;
	}

	@Override
	public String toString() {
		return "Dealer [carta1=" + carta1 + ", carta2=" + carta2 + "]";
	}

}
