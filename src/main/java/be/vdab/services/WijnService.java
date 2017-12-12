package be.vdab.services;

import java.util.Optional;

import javax.persistence.PersistenceException;

import be.vdab.entities.Wijn;
import be.vdab.repositories.WijnRepository;
/**
 * 
 * @author marc.de.jonge
 *
 */
public class WijnService extends AbstractService{
	
	private final WijnRepository wijnRepository = new WijnRepository();

	public Optional<Wijn> read(long id) {
		return wijnRepository.read(id);
	}

	public void incrementInBestelling(long id, int aantal) {
		try {
			beginTransaction();
			wijnRepository.read(id).ifPresent(wijn -> wijn.verhoogInBestelling(aantal));
			commit();
		} catch (PersistenceException ex) {
			rollback();
		}
	}

}
