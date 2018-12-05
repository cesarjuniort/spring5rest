package com.cesarjuniort.springboot.apirest.repositories;
import com.cesarjuniort.springboot.apirest.models.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends JpaRepository<Cliente,Long>{

}
