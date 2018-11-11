package com.cesarjuniort.springboot.apirest.models.dao;
import com.cesarjuniort.springboot.apirest.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente,Long>{

}
