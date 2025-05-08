package co.edu.uniquindio.proyectofinal.dto;

import co.edu.uniquindio.proyectofinal.model.Rank;

public record CustomerDTO(String name, String id, String email, String phone, Rank rank, int totalPoints) {
}
