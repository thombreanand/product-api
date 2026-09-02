package com.example.productapi.controller;
import com.example.productapi.dto.*; import com.example.productapi.service.ProductService; import jakarta.validation.Valid; import org.springframework.data.domain.*; import org.springframework.data.web.PageableDefault; import org.springframework.http.*; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*; import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@RestController @RequestMapping("/api/v1/products") @SecurityRequirement(name="bearerAuth") public class ProductController {
 private final ProductService s; public ProductController(ProductService s){this.s=s;}
 @GetMapping Page<ProductResponse> getAll(@PageableDefault(size=20,sort="id",direction=Sort.Direction.ASC) Pageable p){return s.findAll(p);}
 @GetMapping("/{id}") ProductResponse get(@PathVariable Long id){return s.find(id);}
 @GetMapping("/{id}/items") java.util.List<ItemResponse> items(@PathVariable Long id){return s.items(id);}
 @PostMapping @PreAuthorize("hasRole('ADMIN')") ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest r,Authentication a){return ResponseEntity.status(HttpStatus.CREATED).body(s.create(r,a.getName()));}
 @PutMapping("/{id}") @PreAuthorize("hasRole('ADMIN')") ProductResponse update(@PathVariable Long id,@Valid @RequestBody ProductRequest r,Authentication a){return s.update(id,r,a.getName());}
 @DeleteMapping("/{id}") @PreAuthorize("hasRole('ADMIN')") ResponseEntity<Void> delete(@PathVariable Long id){s.delete(id);return ResponseEntity.noContent().build();}
}
