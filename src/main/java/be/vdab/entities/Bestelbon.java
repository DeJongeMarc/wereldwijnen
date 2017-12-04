package be.vdab.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.enums.Bestelwijze;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;

@Entity
@Table(name = "bestelbonnen")
public class Bestelbon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDateTime besteld;
	private String naam;
	@Embedded
	private Adres adres;
	private Bestelwijze bestelwijze;
	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bonid"))
	private List<Bestelbonlijn> bestelbonlijnen;
	@Version
	private long versie;

	public Bestelbon(LocalDateTime besteld, String naam, Adres adres, Bestelwijze bestelwijze) {
		setBesteld(besteld);
		setNaam(naam);
		setAdres(adres);
		setBestelwijze(bestelwijze);
	}

	protected Bestelbon() {
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getBesteld() {
		return besteld;
	}

	public String getNaam() {
		return naam;
	}

	public Adres getAdres() {
		return adres;
	}

	public Bestelwijze getBestelwijze() {
		return bestelwijze;
	}

	public void setBesteld(LocalDateTime besteld) {
		this.besteld = besteld;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public void setBestelwijze(Bestelwijze bestelwijze) {
		this.bestelwijze = bestelwijze;
	}

}
