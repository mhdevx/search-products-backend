package product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;

/**
* Esta clase implementa los tests de integración/unitarios 
* los cuales se ejecutarán al construir el proyecto 
*
* @author  Manuel Hernández
* @version 1.0
* @since   2020-10-08 
*/
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {
	@Autowired
	private MockMvc mockMvc;
	
	/**
	* comprobar si el método de prueba/salud responde. 
	*/
	@Test
	public void helloReturnGretting() throws Exception {
		this.mockMvc.perform(get("/product/v1/")).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.saludo").value("hola"));
	}

	/**
	* comprobar si la búsqueda por 1 responde un objeto que tiene por identificador numérico el mismo valor. 
	*/	
	@Test
	public void searchByOneAndReturnIdentifierOne() throws Exception {
//		System.out.println(get("/product/v1/search/").toString());
		this.mockMvc.perform(get("/product/v1/search/1"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].identifier").value("1"));
	}
	
	/**
	* comprobar si al buscar por número es retornado sólo un producto. 
	*/
	@Test
	public void searchByNumberAndReturnOneResult() throws Exception {
		this.mockMvc.perform(get("/product/v1/search/1"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}
	
	
	/**
	* comprobar si al buscar con un parámetro vacío retorna código 404 
	*/
	@Test
	public void searchByEmptyTerm() throws Exception {
		this.mockMvc.perform(get("/product/v1/search/"))
				.andDo(print()).andExpect(status().isNotFound());
	}

	/**
	* comprobar si al buscar con palíndromo y obtener resultado este contiene descuento. 
	*/
	@Test
	public void searchByPalindromeTermAndGetDiscount() throws Exception {
		this.mockMvc.perform(get("/product/v1/search/22"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].discountApplied").value("0.5"));
	}


}
