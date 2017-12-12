package be.vdab.services;

import java.util.Optional;

import javax.persistence.PersistenceException;

import be.vdab.entities.Bestelbon;
import be.vdab.repositories.BestelbonRepository;
/**
 * 
 * @author marc.de.jonge
 *
 */
public class BestelbonService extends AbstractService {

	private final BestelbonRepository bestelbonRepository = new BestelbonRepository();

	public Optional<Bestelbon> read(long id) {
		return bestelbonRepository.read(id);
	}

	public Long createBestelbon(Bestelbon bestelbon) {
		beginTransaction();
		try {
			bestelbonRepository.createBestelbon(bestelbon);
			commit();
			return bestelbon.getId();
		} catch (IllegalArgumentException | PersistenceException ex) {
			rollback();
			return null;
		}
	}

}
