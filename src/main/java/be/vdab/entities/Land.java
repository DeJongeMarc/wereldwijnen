package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;
/**
 * 
 * @author marc.de.jonge
 *
 */
@Entity
@Table(name = "landen")
public class Land implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "Land.findAll";

	@Id
	private long id;
	private String naam;
	@OneToMany(mappedBy = "land")
	@OrderBy("naam")
	private Set<Soort> soorten;
	@Version
	private long versie;

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public Set<Soort> getSoorten() {
		return Collections.unmodifiableSet(soorten);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Land))
			return false;
		Land other = (Land) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}

}
