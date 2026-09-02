package com.example.productapi.service;
import com.example.productapi.dto.*; import com.example.productapi.entity.Product; import com.example.productapi.repository.ProductRepository; import org.junit.jupiter.api.*; import org.mockito.*; import java.util.*; import static org.junit.jupiter.api.Assertions.*; import static org.mockito.Mockito.*;
class ProductServiceTest {
 @Mock ProductRepository repo; @Mock AsyncAuditService audit; ProductService service;
 @BeforeEach void setUp(){MockitoAnnotations.openMocks(this);service=new ProductService(repo,audit);}
 @Test void createSetsAuditFields(){var r=new ProductRequest("Laptop",List.of(new ItemRequest(3)));when(repo.save(any(Product.class))).thenAnswer(i->{Product p=i.getArgument(0);p.setId(1L);return p;});var out=service.create(r,"admin");assertEquals("Laptop",out.productName());assertEquals("admin",out.createdBy());assertEquals(1,out.items().size());assertEquals(3,out.items().get(0).quantity());verify(repo).save(any(Product.class));}
 @Test void deleteMissingThrows(){when(repo.existsById(99L)).thenReturn(false);assertThrows(RuntimeException.class,()->service.delete(99L));verify(repo,never()).deleteById(anyLong());}
}
