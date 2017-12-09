package be.vdab.services;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Soort;
import be.vdab.repositories.SoortRepository;

public class SoortService extends AbstractService {

	private final SoortRepository soortRepository = new SoortRepository();

	public Optional<Soort> read(long id) {
		return soortRepository.read(id);
	}

	public List<Soort> findByLand(long landid) {
		return soortRepository.findByLand(landid);
	}

}
