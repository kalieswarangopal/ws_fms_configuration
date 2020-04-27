package com.fms.configuration;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.fms.configuration.controller.RoleController;
import com.fms.configuration.entity.Role;
import com.fms.configuration.repository.AnswerRepository;
import com.fms.configuration.repository.QuestionRepository;
import com.fms.configuration.repository.RoleRepository;
import com.fms.configuration.service.RoleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = RoleController.class)
@Import(RoleService.class)
public class RoleControllerTest {

	@Autowired
	WebTestClient webTestClient;

	@MockBean
	RoleRepository roleRepository;
	
	@MockBean
	QuestionRepository questionRepo;
	
	@MockBean
	AnswerRepository answerRepo;

	@Test
	public void testCreateRole() {

		Role role = new Role();
		role.setFirstName("First");
		role.setLastName("Last");
		role.setRole("PMO");

		Mockito.when(roleRepository.save(role)).thenReturn(Mono.just(role));

		webTestClient.post().uri("/role").header(HttpHeaders.ACCEPT, "application/json")
				.body(BodyInserters.fromObject(role)).exchange().expectStatus().isCreated();

		Mockito.verify(roleRepository, times(1)).save(role);
	}
	
	@Test
	public void testGetRoles() {

		Role role = new Role();
		role.setFirstName("First");
		role.setLastName("Last");
		role.setRole("PMO");
		List<Role> roleList = new ArrayList<>();
		roleList.add(role);
		Flux<Role> roleFlux = Flux.fromIterable(roleList);

		Mockito.when(roleRepository.findAll()).thenReturn(roleFlux);

		webTestClient.get().uri("/role").header(HttpHeaders.ACCEPT, "application/json")
				.exchange().expectStatus().isOk();

		Mockito.verify(roleRepository, times(1)).findAll();
	}

}
