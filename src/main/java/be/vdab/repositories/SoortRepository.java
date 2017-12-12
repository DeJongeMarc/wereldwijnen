package be.vdab.repositories;

import java.util.Optional;

import be.vdab.entities.Soort;
/**
 * 
 * @author marc.de.jonge
 *
 */
public class SoortRepository extends AbstractRepository {

	public Optional<Soort> read(long id) {
		return Optional.ofNullable(getEntityManager().find(Soort.class, id));
	}

}
