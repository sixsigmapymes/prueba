package com.libro.serviceservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.libro.exception.ResourceNotFoundException;
import com.libro.model.Libro;
import com.libro.repository.LibroRepository;
import com.libro.service.LibroServiceImpl;

/**
 * @author Sergio Rozzenbrg
 * Test junit services
 */

@ExtendWith(MockitoExtension.class)
class LibroServiceTest {

//handles business logic
	// unit testing so mocking
	@Mock
	private LibroRepository libroRepository;

	@InjectMocks
	private LibroServiceImpl libroService;
	private Libro libro_1;
	private Libro libro_2;
	private Libro libro_3;
	private List<Libro> libroList;

	/**
	 * Init Business Objects by Tests
	 * Generator BOs by tests
	 */
	@BeforeEach
	public void setUp() {

		libroList = new ArrayList<>();
		libro_1 = new Libro(1, "RayvenYor", "Cebu Philippines", 225.22, LocalDate.now());
		libro_2 = new Libro(2, "Landup", "Ricardo CaztilloTest", 1548.45, LocalDate.now());
		libro_3 = new Libro(3, "Jane Doe", "Jane DoeDatoTest", 852.58, LocalDate.now());
		libroList.add(libro_1);
		libroList.add(libro_2);
		libroList.add(libro_3);
	}

	/**
	 * Detroyer Business Objects final Tests
	 * Destroyer BOs by tests
	 */
	@AfterEach
	public void tearDown() {
		libro_1 = libro_2 = libro_3 = null;
		libroList = null;
	}

	/**
	 * Test update or Create Libro 
	 * 
	 * @throws ResourceNotFoundException
	 */
	@Test
	void updatOrCreateLibroTest() throws ResourceNotFoundException {

		when(libroRepository.save(any())).thenReturn(libro_1);
		libroService.UpdateOrCreate(libro_1);
		verify(libroRepository, times(1)).save(any());

	}

	/**
	 *  Test partial updater Libro 
	 *  
	 * @throws ResourceNotFoundException
	 */
	@Test
	void updateLibroTest() throws ResourceNotFoundException {

		libro_3.setAutor("Autor Modificado TesT");

		libroService.partialUpdate(libro_3);

		assertTrue(!libroList.contains("Autor Modificado TesT"));

	}

	/**
	 * Test get all libros in libroList1
	 */
	@Test
	public void findAllLibrosTest() {
		libroRepository.save(libro_1);

		when(libroRepository.findAll()).thenReturn(libroList);
		List<Libro> libroList1 = libroService.getAllLibros();
		assertEquals(libroList1, libroList);
		verify(libroRepository, times(1)).save(libro_1);
		verify(libroRepository, times(1)).findAll();
	}

	/**
	 * Test finder Libro By id
	 */
	@Test
	public void findByIdLibrosTest() {
		Mockito.when(libroRepository.findById(1)).thenReturn(Optional.ofNullable(libro_1));
		assertThat(libroService.getLibroById(libro_1.getId())).isEqualTo(libro_1);
	}

	/**
	 *  Test Deleted Libro by id 
	 */
	@Test
	void deleteLibroByIdTest() {
		String autor = libro_1.getAutor();
		// Delete by Id
		libroService.delete(libro_1.getId());

		assertTrue(!libroList.contains(autor));

	}
	
	

}