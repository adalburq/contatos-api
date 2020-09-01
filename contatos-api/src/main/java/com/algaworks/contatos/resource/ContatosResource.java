package com.algaworks.contatos.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.contatos.model.Contato;
import com.algaworks.contatos.repository.Contatos;


@RestController
@RequestMapping("/contatos")
@CrossOrigin("*")
public class ContatosResource {
	
	@Autowired
	private Contatos contatos;
	
	@GetMapping
	public List<Contato> listar() {
		return contatos.findAll();
	}

	// @RequestBod- O Spring desserializa automaticamente o JSON em um tipo Java
	// com @RequestMapping não precisa da anotação @ResponseBody
	@PostMapping
	public Contato criar(@Valid @RequestBody Contato contato) {
		return contatos.save(contato);
	}
	
	@PutMapping
	public Contato alterar(@Valid @RequestBody Contato contato) {
		
		System.out.println("Alterar");
		return contatos.save(contato);

	}

	@DeleteMapping("/{id}") 
	//@ResponseStatus(code = HttpStatus.ACCEPTED)
	public ResponseEntity<HttpStatus> excluir(@PathVariable Long id) {
		System.out.println("Excluir " + id );
		//Optional<Contato> opt = contatos.findById(id);

		try {
			contatos.deleteById(id);
			// retorna HttpStatus.ACCEPTED = 202
			System.out.println("HttpStatus " + HttpStatus.ACCEPTED );
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			// retorna HttpStatus.BAD_REQUEST = 400
			System.out.println("HttpStatus " + HttpStatus.BAD_REQUEST );
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
	}	
	
	// DELETE POR OBJETO = OK
	// @ResponseStatus(code = HttpStatus.OK)
	// @DeleteMapping
	//public void excluir(@Valid @RequestBody Contato contato) {
		//System.out.println("Excluir");
		//contatos.delete(contato);
	//}	
	
	// PUT or PATCH
	// Nota: Seguindo os padrões da API RESTful, 
	// a ação patch HTTP é usada para atualizar os campos em vez de substituir toda a entidade. 
	// No objeto Cliente HTTP, você pode usar patch em vez de put . 
	// A implementação do aplicativo UI não varia. Na maioria das vezes, 
	// é a API do lado do servidor que pode ter PUT e PATCH disponíveis ou apenas um deles.
	
	
	
}
