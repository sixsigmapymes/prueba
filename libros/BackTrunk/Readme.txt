# examen20220511
Examen sondeo 20220511
##############################################################
### JDK
Version : 11
### Mavem 
Version : 3.8
##  H2 
Version : 2.1.212 (2022-04-09) ,Instalado
###  Springboot
Version : 	2.3.3.RELEASE
##  IDE
IntelletJ 
Version : Educativa
#############################################################
## CMD MAVEN
------------------
--Generar el jar
------------------
>   mvn clean install
------------------
Ejecutar spring boot
-------------------
>   mvnw spring-boot:run
--------------------

#############################################################
      Back   =================>   Back End
#############################################################
# H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2
# Datasource
spring.datasource.url=jdbc:h2:file:./target/BaseDatos/prueba;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
server.port=8082  
*************************************************************
    Apis Rest URL
-------------------------------------------------------------

// Carga 15 Business Objects BOs en la Base de datos H2

http://localhost:8082/api/libro/init
.......................................................
// Visualiza el listados de BOs cargados en la Base H2

http://localhost:8082/api/libro/list

LISTADO
--------
{"id":1,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"},
{"id":2,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"},
{"id":3,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"},
{"id":4,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"},
{"id":5,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"},
{"id":6,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"},
{"id":7,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"},
{"id":8,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"},
{"id":9,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"},
{"id":10,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"},
{"id":11,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"},
{"id":12,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"},
{"id":13,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"},
{"id":14,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"},
{"id":15,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"}

................................................................................

// Busqudad de Libros por id

http://localhost:8082/api/libro/findById/10

Libro para la id=10
--------------------

{"id":1,"titulo":"Titulo","autor":"AutorPrueba","precio":1156.4608,"fechaLanzamiento":"2022-05-11"}

*******************************************************************************
Test Unitario  codigo
-------------------------------------------------------------------------------

package com.libro.serviceservice;


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
-------------------------------------------------------------------------------
   EJECUCION DE LOS TEST JUNIT DE LOS SERVICES
-------------------------------------------------------------------------------
Test set: com.libro.LibroApplicationTests
-------------------------------------------------------------------------------
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 5.637 s - in com.libro.LibroApplicationTests
-------------------------------------------------------------------------------
Test set: com.libro.serviceservice.LibroServiceTest
-------------------------------------------------------------------------------
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.302 s - in com.libro.serviceservice.LibroServiceTest
-------------------------------------------------------------------------------

@Autor: Rozenberg Sergio 20220512
