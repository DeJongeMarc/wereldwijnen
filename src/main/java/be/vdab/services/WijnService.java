package be.vdab.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import be.vdab.entities.Wijn;
import be.vdab.repositories.WijnRepository;

public class WijnService extends AbstractService{
	
	private final WijnRepository wijnRepository = new WijnRepository();

	public Optional<Wijn> read(long id) {
		return wijnRepository.read(id);
	}

	public List<Wijn> findBySoort(long soortid) {
		return wijnRepository.findBySoort(soortid);
	}

	public void incrementInBestelling(long id, int aantal) {
		try {
			beginTransaction();
			wijnRepository.read(id).ifPresent(wijn -> wijn.incrementInBestelling(aantal));
			commit();
		} catch (PersistenceException ex) {
			rollback();
		}
	}

}
