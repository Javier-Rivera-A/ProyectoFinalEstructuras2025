package co.edu.uniquindio.proyectofinal.service;

import co.edu.uniquindio.proyectofinal.dto.CustomerDTO;
import co.edu.uniquindio.proyectofinal.mapper.CustomerMapper;
import co.edu.uniquindio.proyectofinal.model.Customer;
import co.edu.uniquindio.proyectofinal.model.Rank;
import co.edu.uniquindio.proyectofinal.repository.CustomerFileRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerService {
    private final CustomerFileRepository customerRepository;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    public CustomerService() {
        this.customerRepository = new CustomerFileRepository("src/main/resources/Persistence/customers.txt");
    }

    /**
     * Crea un nuevo cliente en el sistema
     * @param customerDTO Datos del cliente a crear
     * @return DTO del cliente creado
     */
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        // Validaciones básicas
        if (customerDTO == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }

        if (customerDTO.id() == null || customerDTO.id().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del cliente no puede estar vacío");
        }

        if (customerRepository.findById(customerDTO.id()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un cliente con el ID: " + customerDTO.id());
        }

        // Convertir DTO a entidad y establecer valores iniciales
        Customer customer = customerMapper.dtoToCustomer(customerDTO);
        customer.setRank(Rank.BRONZE); // Por defecto, un cliente nuevo es Bronce
        customer.setTotalPoints(0);     // Inicia con 0 puntos

        // Guardar en el repositorio
        Customer savedCustomer = customerRepository.save(customer);

        // Convertir de vuelta a DTO y retornar
        return customerMapper.customerToDto(savedCustomer);
    }

    /**
     * Obtiene un cliente por su ID
     * @param id ID del cliente
     * @return DTO del cliente encontrado o null si no existe
     */
    public CustomerDTO findCustomerById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }

        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.map(customerMapper::customerToDto).orElse(null);
    }

    /**
     * Obtiene todos los clientes del sistema
     * @return Lista de DTOs de clientes
     */
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::customerToDto)
                .collect(Collectors.toList());
    }

    /**
     * Actualiza la información de un cliente existente
     * @param customerDTO Datos actualizados del cliente
     * @return DTO del cliente actualizado o null si no existe
     */
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        if (customerDTO == null || customerDTO.id() == null) {
            throw new IllegalArgumentException("El cliente o su ID no pueden ser nulos");
        }

        // Verificar si el cliente existe
        Optional<Customer> existingCustomerOpt = customerRepository.findById(customerDTO.id());
        if (existingCustomerOpt.isEmpty()) {
            return null; // Cliente no encontrado
        }

        Customer existingCustomer = existingCustomerOpt.get();
        Customer updatedCustomer = customerMapper.dtoToCustomer(customerDTO);

        // Conservar los valores que no deben cambiarse al actualizar
        updatedCustomer.setTotalPoints(existingCustomer.getTotalPoints());
        updatedCustomer.setRank(existingCustomer.getRank());
        updatedCustomer.setWallets(existingCustomer.getWallets());
        updatedCustomer.setAccounts(existingCustomer.getAccounts());
        updatedCustomer.setTransactions(existingCustomer.getTransactions());

        // Guardar cambios
        Customer savedCustomer = customerRepository.save(updatedCustomer);
        return customerMapper.customerToDto(savedCustomer);
    }

    /**
     * Elimina un cliente del sistema
     * @param id ID del cliente a eliminar
     * @return true si se eliminó correctamente, false si no existía
     */
    public boolean deleteCustomer(String id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }

        if (customerRepository.findById(id).isEmpty()) {
            return false; // Cliente no encontrado
        }

        customerRepository.deleteById(id);
        return true;
    }

    /**
     * Busca clientes por nombre o email
     * @param searchTerm Término de búsqueda
     * @return Lista de DTOs de clientes que coinciden con la búsqueda
     */
    public List<CustomerDTO> searchCustomers(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllCustomers();
        }

        String term = searchTerm.toLowerCase();
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .filter(customer ->
                        customer.getName().toLowerCase().contains(term) ||
                                customer.getEmail().toLowerCase().contains(term))
                .map(customerMapper::customerToDto)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los clientes de un determinado rango
     * @param rank Rango de los clientes a buscar
     * @return Lista de DTOs de clientes con el rango especificado
     */
    public List<CustomerDTO> getCustomersByRank(Rank rank) {
        if (rank == null) {
            throw new IllegalArgumentException("El rango no puede ser nulo");
        }

        List<Customer> customers = customerRepository.findByRank(rank);
        return customers.stream()
                .map(customerMapper::customerToDto)
                .collect(Collectors.toList());
    }
}