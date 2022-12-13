package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.binding.Contact;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository repo;

	@Override
	public String saveContact(Contact contact) {

		contact = repo.save(contact);
		if (contact.getContactId() != null) {
			return "Contact Saved";

		} else {
			return "Contact Not Saved";

		}

	}

	@Override
	public List<Contact> getAllContacts() {

		return repo.findAll();
	}

	@Override
	public Contact getContactById(Integer contactId) {

		Optional<Contact> findbyId = repo.findById(contactId);

		if (findbyId.isPresent()) {
			return findbyId.get();

		}

		return null;
	}

	@Override
	public String updateContact(Contact contact) {

		if (repo.existsById(contact.getContactId())) {

			repo.save(contact);
			return "contact Updated ";

		} else {
			return "contact not updated";

		}

	}

	@Override
	public String deleteContact(Integer contactId) {
		if(repo.existsById(contactId)) {
		repo.deleteById(contactId);
		return "Contact Deleted ";
		
		}else {
			return "No record found";
		}
		
		
	}

}
