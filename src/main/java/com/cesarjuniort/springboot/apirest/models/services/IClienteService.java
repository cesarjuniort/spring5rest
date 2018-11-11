package com.cesarjuniort.springboot.apirest.models.services;

import java.util.List;


import com.cesarjuniort.springboot.apirest.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();

}
