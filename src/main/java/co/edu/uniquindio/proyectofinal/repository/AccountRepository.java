package co.edu.uniquindio.proyectofinal.repository;

import co.edu.uniquindio.proyectofinal.model.Account;
import co.edu.uniquindio.proyectofinal.model.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountRepository {
    private final String dataFilePath;
    private List<Account> accounts;

    public AccountRepository(String dataFilePath) {
        this.dataFilePath = dataFilePath;
        this.accounts = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        File file = new File(dataFilePath);
        if(!file.exists()){
            return;
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            Object obj = ois.readObject();
            if(obj instanceof List){
                this.accounts = (List<Account>) obj;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
            this.accounts = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
            this.accounts = new ArrayList<>();
        }
    }

    private void saveData(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFilePath))){
            oos.writeObject(accounts);
        } catch (FileNotFoundException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    public Optional<Account> findByOwner(Customer owner){
        if(owner == null){
            return Optional.empty();
        }
        return accounts.stream().filter(account -> owner.equals(account.getOwner())).findFirst();
    }
    public Optional<Account> findByID(String id){
        if(id == null){
            return Optional.empty();
        }
        return accounts.stream().filter(account -> id.equals(account.getAccountID())).findFirst();
    }
    public Account save(Account account){
        if(account == null){
            throw  new IllegalArgumentException("La cuenta no puede ser nula");
        }
        Optional<Account> existingAccount = findByID(account.getAccountID());
        if(existingAccount.isPresent()){
            int index = accounts.indexOf(existingAccount.get());
            accounts.set(index,account);
        }else{
            accounts.add(account);
        }
        saveData();
        return account;
    }

    public List<Account> findAll(){
        return new ArrayList<>(accounts);
    }

    public void deleteById(String id){
        if(id == null){
            return;
        }
        accounts = accounts.stream().filter(account -> !id.equals(account.getAccountID())).collect(Collectors.toList());
        saveData();
    }
    public void deleteByOwner(Account owner){
        if(owner == null){
            return;
        }
        accounts = accounts.stream().filter(account -> !owner.equals(account.getAccountID())).collect(Collectors.toList());
        saveData();
    }
}
