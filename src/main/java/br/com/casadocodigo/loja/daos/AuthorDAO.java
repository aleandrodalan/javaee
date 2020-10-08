package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Author;

public class AuthorDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Author> list() {
		return manager.createQuery("select * from AUTHOR", Author.class).getResultList();
	}
}	
