package co.edu.uniquindio.proyectofinal.model;

import java.time.LocalDateTime;

public class AutomaticTransaction extends Transaction {
    private String recipientWallet,originWallet;
    private LocalDateTime scheduledDate;
    private int recurrence;

}
