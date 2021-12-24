package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	@GetMapping
	public List<Pessoa> buscarTodas(){
		return pessoaRepository.findAll();
	}
	
	
	@PostMapping()
	public Pessoa salvarPessoa(@RequestBody Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	
	@GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable(value = "id") String cpf)
        throws Exception {
        Pessoa pessoa = pessoaRepository.findById(cpf)
          .orElseThrow(() -> new Exception("Pessoa not found for this id :: " + cpf));
        return ResponseEntity.ok().body(pessoa);
    }
	
	
	@DeleteMapping("/{id}")
    public Map<String, Boolean> deletePessoa(@PathVariable(value = "id") String cpf)
         throws Exception {
        Pessoa pessoa = pessoaRepository.findById(cpf)
       .orElseThrow(() -> new Exception("Pessoa not present for the id :: " + cpf));
        pessoaRepository.delete(pessoa);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
	
	@PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable(value = "id") String cpf,
        @Validated @RequestBody Pessoa pessoaDetails) 
        throws  Exception {
        Pessoa pessoa = pessoaRepository.findById(cpf)
        .orElseThrow(() -> new Exception("Pessoa not found for this id :: " + cpf));
        final Pessoa updatedPessoa = pessoaRepository.save(pessoa);
        return ResponseEntity.ok(updatedPessoa);
    }
	
	
}
