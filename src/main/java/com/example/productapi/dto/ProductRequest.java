package com.example.productapi.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
public record ProductRequest(@NotBlank @Size(max=255) String productName, @Valid List<ItemRequest> items) {}
