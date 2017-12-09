package be.vdab.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Land;

public class LandRepository extends AbstractRepository {
	
	public Optional<Land> read(long id) {
		return Optional.ofNullable(getEntityManager().find(Land.class, id));
	}

	public List<Land> findAll() {
		return getEntityManager().createNamedQuery(Land.FIND_ALL, Land.class).getResultList();
	}

}
