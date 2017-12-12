package be.vdab.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
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
	private int bestelwijze;
	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bonid"))
	private List<Bestelbonlijn> bestelbonlijnen;
	@Version
	private long versie;

	public Bestelbon(String naam, Adres adres, int bestelwijze, List<Bestelbonlijn> bestelbonlijnen) {
		besteld = LocalDateTime.now();
		this.naam = naam;
		this.adres = adres;
		this.bestelwijze = bestelwijze;
		this.bestelbonlijnen = bestelbonlijnen;
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

	public int getBestelwijze() {
		return bestelwijze;
	}
	
	public List<Bestelbonlijn> getBestelbonlijnen() {
		return Collections.unmodifiableList(bestelbonlijnen);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bestelbonlijnen == null) ? 0 : bestelbonlijnen.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Bestelbon))
			return false;
		Bestelbon other = (Bestelbon) obj;
		if (bestelbonlijnen == null) {
			if (other.bestelbonlijnen != null)
				return false;
		} else if (!bestelbonlijnen.equals(other.bestelbonlijnen))
			return false;
		return true;
	}

}
