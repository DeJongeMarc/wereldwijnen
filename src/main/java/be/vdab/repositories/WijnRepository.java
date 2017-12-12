package be.vdab.repositories;

import java.util.Optional;

import be.vdab.entities.Wijn;
/**
 * 
 * @author marc.de.jonge
 *
 */
public class WijnRepository extends AbstractRepository {

	public Optional<Wijn> read(long id) {
		return Optional.ofNullable(getEntityManager().find(Wijn.class, id));
	}

}
