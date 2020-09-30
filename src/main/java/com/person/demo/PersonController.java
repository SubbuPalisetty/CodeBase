package com.person.demo;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@Autowired
	PersonDAO personDAO;

	@RequestMapping("/getPerson/{id}")
	public Person showPerson(@PathVariable int id) {
		Person person = personDAO.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Person ID '" + id + "' Doesn't exist"));
		return person;
	}
	
	@RequestMapping("/getAddress/{id}")
	public Set<Address> showAddress(@PathVariable int id) {
		Person person = personDAO.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Person ID '" + id + "' Doesn't exist"));
		return person.getAddresses();
	}

	@RequestMapping(value = "/addPerson", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Person add(@RequestBody Person person) {

		/*
		 * Person newPerson = new Person(); newPerson.setPid(person.getPid());
		 * newPerson.setFirstName(person.getFirstName());
		 * newPerson.setLastName(person.getLastName()); Set<Address> addressSet =
		 * person.getAddresses(); newPerson.setAddresses(addressSet);
		 */
		try {
			personDAO.save(person);
		} catch (Exception exception) {
			throw new CustomException("Error in Saving the record :" + exception.getLocalizedMessage());
		}

		return person;
	}

	@RequestMapping(value = "/deletePerson/{pid}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Person delete(@PathVariable int id) {

		Person person = personDAO.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Person ID '" + id + "' Doesn't exist"));
		if (person != null)
			try {
				personDAO.deleteById(id);
			} catch (Exception e) {
				throw new CustomException("Error in Saving the record :" + e.getLocalizedMessage());
			}
		return person;
	}

	@RequestMapping(value = "/updatePerson", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Person updatePerson(@RequestBody Person p) {

		Person person = personDAO.findById(p.getPid())
				.orElseThrow(() -> new RecordNotFoundException("Person ID '" + p.getPid() + "' Doesn't exist"));
		if (person != null) {
			person.setFirstName(p.getFirstName());
			person.setLastName(p.getLastName());
		}
		Set<Address> originalSet = person.getAddresses();
		Set<Address> updatedSet = p.getAddresses();

		originalSet.forEach((Address OriginalAddress) -> {
			updatedSet.forEach((Address updatedAddress) -> {
				if (OriginalAddress.getAid() == updatedAddress.getAid()) {
					OriginalAddress.setCity(updatedAddress.getCity());
					OriginalAddress.setState(updatedAddress.getState());
					OriginalAddress.setStreet(updatedAddress.getStreet());
					OriginalAddress.setPostalCode(updatedAddress.getPostalCode());
				}
			});
		});

		person.setAddresses(originalSet);
		try {
			personDAO.save(person);
		} catch (Exception e) {			
			throw new CustomException("Error in Saving the record :" + e.getLocalizedMessage());
		}
		return person;
	}

	@RequestMapping(value = "/addAddress/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Person addAddress(@PathVariable int id, @RequestBody Address address) {
		Person person = personDAO.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Person ID '" + id + "' Doesn't exist"));

			Set<Address> addressSet = person.getAddresses();
			addressSet.add(address);
			person.setAddresses(addressSet);
	
		/*
		 * Set<Address> set = new HashSet<Address>(); addressSet.forEach((Address
		 * address)->{ Address newAddress = new Address(address.getAid(),
		 * address.getStreet(),address.getCity(),address.getState(),
		 * address.getPostalCode()); set.add(newAddress); }); person.setAddresses(set);
		 */

		try {
			personDAO.save(person);
		} catch (Exception e) {
			throw new CustomException("Error in Saving the record :" + e.getLocalizedMessage());
		}
		return person;
	}

	@RequestMapping(value = "/updateAddress/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Person updateAddress(@PathVariable int id, @RequestBody Address updatedAddress) {
		System.out.println("id = " + id);
		Person person = personDAO.findById(id).orElseThrow(() -> new RecordNotFoundException("Person ID '" + id + "' Doesn't exist"));
		
			Set<Address> originalSet = person.getAddresses();
			originalSet.forEach((Address OriginalAddress) -> {
				if (OriginalAddress.getAid() == updatedAddress.getAid()) {
					OriginalAddress.setCity(updatedAddress.getCity());
					OriginalAddress.setState(updatedAddress.getState());
					OriginalAddress.setStreet(updatedAddress.getStreet());
					OriginalAddress.setPostalCode(updatedAddress.getPostalCode());
				}
			});
			person.setAddresses(originalSet);
			try {
				personDAO.save(person);
			} catch (Exception e) {
				throw new CustomException("Error in Saving the record :" + e.getLocalizedMessage());
			}
			return person;		
	}

	@RequestMapping(value = "/deleteAddress/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Person deleteAddress(@PathVariable int id, @RequestBody Address address) {
		System.out.println("id = " + id);
		Person person = personDAO.findById(id).orElseThrow(() -> new RecordNotFoundException("Person ID '" + id + "' Doesn't exist"));
		
			Set<Address> addressSet = person.getAddresses();
			addressSet.forEach((Address dbAddress) -> {
				if (dbAddress.getAid() == address.getAid()) {
					addressSet.remove(dbAddress);
				}
			});
			person.setAddresses(addressSet);
			try {
				personDAO.save(person);
			} catch (Exception e) {
				throw new CustomException("Error in Saving the record :" + e.getLocalizedMessage());
			}
			return person;		
	}

	@RequestMapping(value = "/personCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String showCount() {

		try {
			List<Person> list = (List<Person>) personDAO.findAll();
			return "Total Number of persons : " + list.size();
		} catch (Exception e) {
			throw new CustomException("Error in Saving the record :" + e.getLocalizedMessage());
		}				
	}

	@RequestMapping(value = "/listPersons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> listAllPersons() {	
			
			List<Person> list = (List<Person>) personDAO.findAll();
			if(list.size()==0) {
				throw new CustomException("No Records Found" );
			}
			return list;			
	}

}
