package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Wijn;

@Embeddable
public class Bestelbonlijn implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int aantal;
	private BigDecimal prijs;
	@ManyToOne
	@JoinColumn(name = "wijnid")
	private Wijn wijn;
	
	public int getAantal() {
		return aantal;
	}
	public BigDecimal getPrijs() {
		return prijs;
	}
	public Wijn getWijn() {
		return wijn;
	}
	
}
