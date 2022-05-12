package com.libro.service;

import java.util.List;
import java.util.Optional;

import com.libro.exception.ResourceNotFoundException;
import com.libro.model.Libro;

public interface LibroService {

	// Save Libro
	public Libro save(Libro libro);
	// getting all books record by using the method findaAll() of CrudRepository
	public List<Libro> getAllLibros();
	// saving a specific record by using the method save() of CrudRepository
	public Libro saveOrUpdate(Libro books);
	// getting a specific record by using the method findById() of CrudRepository
	public Libro getLibroById(int id);
	// deleting a specific record by using the method deleteById() of CrudRepository
	public Libro delete(int id);
	//If exist Libro
	boolean existsById(int i);
	// Update Libro
	public Libro update(Libro libro);
	// Partial Update  Libro
	public Optional<Libro> partialUpdate(Libro libro);
	// Update Or Create Libro
	public Libro UpdateOrCreate(Libro libro) throws ResourceNotFoundException;

}
