package be.vdab.services;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Land;
import be.vdab.repositories.LandRepository;

public class LandService extends AbstractService {
	
	private final LandRepository landRepository = new LandRepository();

	public Optional<Land> read(long id) {
		return landRepository.read(id);
	}

	public List<Land> findAll() {
		return landRepository.findAll();
	}

}
