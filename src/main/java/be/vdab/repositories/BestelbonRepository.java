package be.vdab.repositories;

import java.util.Optional;

import be.vdab.entities.Bestelbon;
/**
 * 
 * @author marc.de.jonge
 *
 */
public class BestelbonRepository extends AbstractRepository {

	public Optional<Bestelbon> read(long id) {
		return Optional.ofNullable(getEntityManager().find(Bestelbon.class, id));
	}

	public void createBestelbon(Bestelbon bestelbon) {
		getEntityManager().persist(bestelbon);
	}

}
