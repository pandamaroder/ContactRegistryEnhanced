package com.example.demo;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static com.example.demo.DataHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class DemoApplicationTests {
    // ApplicationContextInitializer appInit = new

    @Container
    static PostgreSQLContainer pgDBContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:16.1"));


   @DynamicPropertySource
   static void setProperties(DynamicPropertyRegistry registry) {
      registry.add("spring.datasource.url", pgDBContainer::getJdbcUrl);
      registry.add("spring.datasource.username", pgDBContainer::getUsername);
      registry.add("spring.datasource.password", pgDBContainer::getPassword);
   }


   @Autowired
   private ApplicationContext applicationContext;
   
   @Autowired
   private ContactService contactService;
   

   @Test
   void contextLoads() {
      assertThat(applicationContext.getBeanDefinitionNames())
              .hasSizeGreaterThan(10);

   }

   @Test
   void validateAddContactCount() {
      Contact test = prepareContact().build();
      contactService.createContact(test);
      List<Contact> allContacts = contactService.getAll();
      assertThat(allContacts)
              .isNotEmpty()
              .hasSize(4);
   }

   @Test
   void validateNewContactParameters() {
       Contact test = prepareContact().middleName("Basic").build();
      long contact = contactService.createContact(test);
      Contact contactbyId = contactService.getById(contact);

      assertThat(contactbyId)
              .usingRecursiveComparison()
              .ignoringFields("id")
              .isEqualTo(test);

   }


   @Test
   void validateDeleteContact() {
      contactService.deleteContact(1L);
      List<Contact> allContacts = contactService.getAll();
      assertThat(allContacts)
              .isNotEmpty()
              .hasSize(2);
   }

}
