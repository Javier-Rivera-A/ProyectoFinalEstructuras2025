package co.edu.uniquindio.proyectofinal.dto;

import co.edu.uniquindio.proyectofinal.model.AccountType;
import co.edu.uniquindio.proyectofinal.model.Customer;

public record AccountDTO(String accountID, double balance, AccountType accountType, Customer owner) {
}
