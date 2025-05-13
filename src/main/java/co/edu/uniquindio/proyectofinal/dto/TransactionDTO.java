package co.edu.uniquindio.proyectofinal.dto;

import co.edu.uniquindio.proyectofinal.model.Account;

import java.time.LocalDateTime;

public record TransactionDTO(String id, LocalDateTime date, double amount) {
}
