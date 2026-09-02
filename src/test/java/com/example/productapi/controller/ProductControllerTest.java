package com.example.productapi.controller;
import com.example.productapi.service.ProductService; import org.junit.jupiter.api.*; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import org.springframework.boot.test.mock.mockito.MockBean; import org.springframework.security.test.context.support.WithMockUser; import org.springframework.http.MediaType; import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*; import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(ProductController.class) class ProductControllerTest {
 @Autowired MockMvc mvc; @MockBean ProductService service;
 @Test @WithMockUser(roles="USER") void validationWorks() throws Exception {mvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content("{\"productName\":\"\",\"items\":[]}" )).andExpect(status().isBadRequest());}
 @Test @WithMockUser(roles="USER") void userCannotCreate() throws Exception {mvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content("{\"productName\":\"Laptop\"}" )).andExpect(status().isForbidden());}
}
