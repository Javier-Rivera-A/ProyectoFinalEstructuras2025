package co.edu.uniquindio.proyectofinal.mapper;

import co.edu.uniquindio.proyectofinal.dto.AccountDTO;
import co.edu.uniquindio.proyectofinal.dto.CustomerDTO;
import co.edu.uniquindio.proyectofinal.model.Account;
import co.edu.uniquindio.proyectofinal.model.Customer;
import org.mapstruct.factory.Mappers;

public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    Account dtoToAccount(AccountDTO dto);
    AccountDTO accountToDto(Account account);

}
