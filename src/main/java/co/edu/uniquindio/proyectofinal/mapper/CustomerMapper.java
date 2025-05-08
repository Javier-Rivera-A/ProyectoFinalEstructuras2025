package co.edu.uniquindio.proyectofinal.mapper;

import co.edu.uniquindio.proyectofinal.dto.CustomerDTO;
import co.edu.uniquindio.proyectofinal.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer dtoToCustomer(CustomerDTO dto);
    CustomerDTO customerToDto(Customer customer);
}
