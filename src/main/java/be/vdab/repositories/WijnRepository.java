package be.vdab.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import be.vdab.entities.Wijn;

public class WijnRepository extends AbstractRepository {

	public Optional<Wijn> read(long id) {
		return Optional.ofNullable(getEntityManager().find(Wijn.class, id));
	}

	public List<Wijn> findBySoort(long soortid) {
		return Collections.unmodifiableList(getEntityManager().createNamedQuery(Wijn.FIND_BY_SOORT, Wijn.class)
				.setParameter("soortid", soortid).getResultList());
	}

}
