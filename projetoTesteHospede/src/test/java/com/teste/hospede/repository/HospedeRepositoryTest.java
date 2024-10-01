package com.teste.hospede.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.teste.hospede.entity.Hospede;

@DataJpaTest
class HospedeRepositoryTest {
	
	@Autowired
	private HospedeRepository hospedeRepository;
	
	@DisplayName("Testando o Save")
    @Test
	void testSalvarRepository() {
		
		//Given / Arrange
		Hospede hospede1 =new Hospede(null,"Luis Fernando",
				                           "luis@gmail.com",
				                           "(00)0000-0000");
		//When / Act
		Hospede saveHospede = hospedeRepository.save(hospede1);
		
		//Then /Assert
		assertNotNull(saveHospede);
		assertTrue(saveHospede.getId()>0);
		
	}
	@DisplayName("Testando GET para todos Hospedes")
	@Test
	void testGetAllRepository() {
		
		// Given / Arrange
		Hospede hospede1 =new Hospede(null, "Luis Fernando",
				"luis@gmail.com",
				"(00)0000-0000");
		
		Hospede hospede2 =new Hospede(null,"Hector fernando",
				"hector@gmail.com",
				"(00)0000-0000");
		
		hospedeRepository.save(hospede1);
		hospedeRepository.save(hospede2);
		
		//When /Act
		List<Hospede> hospedeList = hospedeRepository.findAll();
		
		//Then / Assert
		assertNotNull(hospedeList);
		assertEquals(2, hospedeList.size());
		
	}
	
	@DisplayName("Testando GET ById")
	@Test
	void testGetById() {
		
		// Given / Arrange
		Hospede hospede1 =new Hospede(null,"Luis fernando",
				"luis@gmail.com",
				"(00)0000-0000");
		
		
		hospedeRepository.save(hospede1);
		
		//When /Act
		Hospede saveHospede = hospedeRepository.findById(hospede1.getId()).get();
		
		//Then / Assert
		assertNotNull(saveHospede);
		assertEquals(hospede1.getId(), saveHospede.getId());
		

}
	@DisplayName("Testando Update")
	@Test
	void testUpdateHospede() {
		
		// Given / Arrange
		Hospede hospede1 =new Hospede(null,"Alonso Fernando",
				"alonso@gmail.com",
				"(00)0000-0000");
		
		
		hospedeRepository.save(hospede1);
		
		//When /Act
		Hospede saveHospede = hospedeRepository.findById(hospede1.getId()).get();
		hospede1.setNome("Leonardo");
		hospede1.setEmail("leonardo@gmail.com");
		
		Hospede updateHospede = hospedeRepository.save(saveHospede);
		
		//Then / Assert
		assertNotNull(updateHospede);
		assertEquals("Leonardo",updateHospede.getNome());
		assertEquals("leonardo@gmail.com",updateHospede.getEmail());
   }
	
	@DisplayName("Testando o Delete")
	@Test
	void testDeleteHospede() {
		
		// Given / Arrange
		Hospede hospede1 =new Hospede(null,"Alonso Fernando",
				"alonso@gmail.com",
				"(00)0000-0000");
		
		
		hospedeRepository.save(hospede1);
		
		//When /Act
		hospedeRepository.deleteById(hospede1.getId());
		
		Optional<Hospede> hospedeOptional = hospedeRepository.findById(hospede1.getId());
		
		//Then / Assert
		assertTrue(hospedeOptional.isEmpty());
	}
}

