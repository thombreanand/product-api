package com.example.productapi.dto;
import jakarta.validation.constraints.Min;
public record ItemRequest(@Min(value=1, message="quantity must be at least 1") Integer quantity) {}
