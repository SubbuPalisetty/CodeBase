package com.person.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO extends CrudRepository<Person, Integer>{	
	
	/*
	 * //We can add some custom methods here 
	 * 
	 * List<Person> findByFirstName(String
	 * name); List<Person> findByLastName(String name);
	 */

}
