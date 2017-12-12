package be.vdab.repositories;

import javax.persistence.EntityManager;

import be.vdab.filters.JPAFilter;
/**
 * 
 * @author marc.de.jonge
 *
 */
abstract class AbstractRepository {
	
	EntityManager getEntityManager() {
		return JPAFilter.getEntityManager();
	}

}
