package com.cesarjuniort.springboot.apirest.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.validation.FieldError;

import com.cesarjuniort.springboot.apirest.models.entity.Cliente;
import com.cesarjuniort.springboot.apirest.models.services.IClienteService;
// import com.cesarjuniort.springboot.apirest.models.entity.services.ClienteServiceImpl;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAll();
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("errMsg", e.getMessage()); // TODO: refactor to a user friendly message - exception details
													// should not propagate to the final user.
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cliente == null) {
			response.put("errMsg", "Record not found.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		
		Cliente createdCliente = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			/*  Using the Classic Java < 8			
			List<String> errors = new ArrayList<>();
			for(FieldError err: result.getFieldErrors()) {
				errors.add( err.getField() +": "+   err.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); */
			
			// new approach, with Lamdas and Streams.
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> err.getField() +": "+   err.getDefaultMessage())
					.collect(Collectors.toList());
			
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			createdCliente = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("message", "Unable to create the Cliente record.");
			response.put("errMsg", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("cliente", createdCliente);
		response.put("message", "Record created successfully.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Cliente curr = null;
		try {
			curr = clienteService.findById(id);
			curr.setApellido(cliente.getApellido());
			curr.setEmail(cliente.getEmail());
			curr.setNombre(cliente.getNombre());
			curr.setLastModified(new Date()); // just modifying on update.
			response.put("cliente", clienteService.save(curr));
			response.put("message", "Record updated successfully.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("message", "Unable to create the Cliente record.");
			response.put("errMsg", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/clientes/{id}")
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?>  delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			clienteService.delete(id);
			response.put("message", "Record deleted successfully.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("message", "Unable to create the Cliente record");
			response.put("errMsg", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
