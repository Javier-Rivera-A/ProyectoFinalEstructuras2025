package co.edu.uniquindio.proyectofinal.repository;

import co.edu.uniquindio.proyectofinal.model.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionRepository {
    private final String dataFilePath;
    private List<Transaction> transactions;

    public TransactionRepository(String dataFilePath) {
        this.dataFilePath = dataFilePath;
        this.transactions = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        File file = new File(dataFilePath);
        if(!file.exists()){
            return;
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            Object obj = ois.readObject();
            if(obj instanceof List<?>){
                this.transactions = (List<Transaction>) obj;
            }
        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
            this.transactions = new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
            this.transactions = new ArrayList<>();
        }

    }
    private void saveData(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFilePath))){
            oos.writeObject(transactions);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    public Optional<Transaction> findById(String id){
        if(id == null){
            return Optional.empty();
        }
        return null;
    }
}
