package br.com.casadocodigo.loja.managedbean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.BookDAO;
import br.com.casadocodigo.loja.models.Book;

@Model
public class AdminBooksBean {

	private Book product = new Book();
	
	@Inject
	private BookDAO bookDAO;
	
	@Transactional
	public void save() {
		bookDAO.save(product);
	}
	
	public Book getProduct() {
		return this.product;
	}
}
