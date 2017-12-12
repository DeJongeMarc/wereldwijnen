package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Wijn;
/**
 * 
 * @author marc.de.jonge
 *
 */
@Embeddable
public class Bestelbonlijn implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "wijnid")
	private Wijn wijn;
	private int aantal;
	@Column(name = "prijs")
	private BigDecimal aankoopPrijs;
	
	public Bestelbonlijn(Wijn wijn, int aantal) {
		this.wijn = wijn;
		this.aantal = aantal;
		aankoopPrijs = wijn.getPrijs();
	}
	
	protected Bestelbonlijn() {
	}

	public Wijn getWijn() {
		return wijn;
	}

	public int getAantal() {
		return aantal;
	}

	public BigDecimal getAankoopPrijs() {
		return aankoopPrijs;
	}
	
	public BigDecimal getSubTotaal() {
		return aankoopPrijs.multiply(BigDecimal.valueOf(aantal));
	}

}
