package br.com.casadocodigo.loja.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AuthorDAO;
import br.com.casadocodigo.loja.daos.BookDAO;
import br.com.casadocodigo.loja.models.Author;
import br.com.casadocodigo.loja.models.Book;

@Model
public class AdminBooksBean {

	@Inject
	private AuthorDAO authorDAO;
	private Book product = new Book();
	private List<Integer> selectedAuthorsIds = new ArrayList<>();	
	private List<Author> authors = new ArrayList<>();
	
	@Inject
	private BookDAO bookDAO;
	
	@Inject
	FacesContext facesContext;
	
	@PostConstruct
	public void loadObjects() {
		this.authors = authorDAO.list();
	}
	
	@Transactional
	public String save() {
		populateBookAuthor();
		bookDAO.save(product);
		
//		FacesContext facesContext =
//				FacesContext.getCurrentInstance();
		
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		facesContext.addMessage(null, new FacesMessage("Livro gravado com sucesso!"));
		
		return "/livros/lista?faces-redirect=true";
	}
	
	private void clearObjects() {
		this.product = new Book();
		this.selectedAuthorsIds.clear();
	}

	private void populateBookAuthor() {
		selectedAuthorsIds
			.stream()
			.map((id) -> {
				return new Author(id);
			});
	}
	
	public Book getProduct() {
		return this.product;
	}
	
	public List<Integer> getSelectedAuthorsIds() {
		return selectedAuthorsIds;
	}
	
	public void setSelectedAuthorsIds(List<Integer> selectedAuthorsIds) {
		this.selectedAuthorsIds = selectedAuthorsIds;
	}
	
	public List<Author> getAuthors() {
		return authors;
	}
}
