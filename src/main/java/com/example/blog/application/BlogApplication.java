package com.example.blog.application;

import com.example.blog.application.configuration.AppConstants;
import com.example.blog.application.entity.Role;
import com.example.blog.application.repository.RoleRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


// Swagger Title and etc
//Using in Swagger Configration
/*
@OpenAPIDefinition(
		info = @Info(
				title = "Blog Application",
				version = "1.0.0",
				description = "Blogg Project for Learning",
				termsOfService = "Just For Practice",
				contact = @Contact(
						name = "Muneeb Qureshi",
						email = "Muneebhaider564@gmail.com",
						url = "https://instagram.com/muneebqureshi90?igshid=OGQ5ZDc2ODk2ZA=="
				),
				license = @License(
						name = "Licence",
						url = "Muneeb"
				)
		)
)*/

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	// Applying here Model Mapping
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Role role = new Role();
			role.setId(AppConstants.NORMAL_USER);
			role.setName("Normal_User");

			Role role1 = new Role();
			role1.setId(AppConstants.ADMIN_USER);
			role1.setName("ADMIN_User");

			List<Role> roles = List.of(role, role1);
			List<Role> result = this.roleRepository.saveAll(roles);
			result.forEach(r ->{
				System.out.println(r.getName());
			} );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println(this.passwordEncoder.encode("xyz"));
//	}
}
