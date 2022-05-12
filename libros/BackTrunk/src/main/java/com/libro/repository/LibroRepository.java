package com.libro.repository;
import org.springframework.data.repository.CrudRepository;

//repository that extends CrudRepository
import com.libro.model.Libro;
public interface LibroRepository extends CrudRepository<Libro, Integer>
{
  
}
