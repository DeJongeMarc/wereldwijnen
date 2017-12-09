package be.vdab.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import be.vdab.entities.Soort;

public class SoortRepository extends AbstractRepository {

	public Optional<Soort> read(long id) {
		return Optional.ofNullable(getEntityManager().find(Soort.class, id));
	}

	public List<Soort> findByLand(long landid) {
		return Collections.unmodifiableList(getEntityManager().createNamedQuery(Soort.FIND_BY_LAND, Soort.class)
				.setParameter("landid", landid).getResultList());
	}

}
