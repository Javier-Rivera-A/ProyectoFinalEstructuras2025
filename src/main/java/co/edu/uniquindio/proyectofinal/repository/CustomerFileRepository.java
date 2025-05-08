package co.edu.uniquindio.proyectofinal.repository;

import co.edu.uniquindio.proyectofinal.model.Customer;
import co.edu.uniquindio.proyectofinal.model.Rank;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CustomerFileRepository {
    private final String dataFilePath;
    private List<Customer> customers;

    /**
     * Constructor que inicializa el repositorio con la ruta del archivo
     * @param dataFilePath Ruta del archivo donde se guardarán los clientes
     */
    public CustomerFileRepository(String dataFilePath) {
        this.dataFilePath = dataFilePath;
        this.customers = new ArrayList<>();
        loadData();
    }

    /**
     * Carga los datos del archivo
     */
    private void loadData() {
        File file = new File(dataFilePath);
        if (!file.exists()) {
            return; // Si el archivo no existe, se inicializa con una lista vacía
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                this.customers = (List<Customer>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
            this.customers = new ArrayList<>();
        }
    }

    /**
     * Guarda los datos en el archivo
     */
    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    /**
     * Busca un cliente por su ID
     * @param id ID del cliente
     * @return Optional con el cliente si existe
     */
    public Optional<Customer> findById(String id) {
        if (id == null) {
            return Optional.empty();
        }
        return customers.stream()
                .filter(customer -> id.equals(customer.getId()))
                .findFirst();
    }

    /**
     * Guarda un nuevo cliente o actualiza uno existente
     * @param customer Cliente a guardar
     * @return El cliente guardado
     */
    public Customer save(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }

        // Buscar si el cliente ya existe
        Optional<Customer> existingCustomer = findById(customer.getId());
        if (existingCustomer.isPresent()) {
            // Actualizar cliente existente
            int index = customers.indexOf(existingCustomer.get());
            customers.set(index, customer);
        } else {
            // Agregar nuevo cliente
            customers.add(customer);
        }

        saveData(); // Guardar cambios en el archivo
        return customer;
    }

    /**
     * Obtiene todos los clientes
     * @return Lista de clientes
     */
    public List<Customer> findAll() {
        return new ArrayList<>(customers); // Retorna una copia para evitar modificaciones externas
    }

    /**
     * Elimina un cliente por su ID
     * @param id ID del cliente a eliminar
     */
    public void deleteById(String id) {
        if (id == null) {
            return;
        }
        customers = customers.stream()
                .filter(customer -> !id.equals(customer.getId()))
                .collect(Collectors.toList());
        saveData(); // Guardar cambios en el archivo
    }

    /**
     * Busca clientes por rango
     * @param rank Rango de los clientes a buscar
     * @return Lista de clientes con el rango especificado
     */
    public List<Customer> findByRank(Rank rank) {
        if (rank == null) {
            return new ArrayList<>();
        }
        return customers.stream()
                .filter(customer -> rank.equals(customer.getRank()))
                .collect(Collectors.toList());
    }
}
