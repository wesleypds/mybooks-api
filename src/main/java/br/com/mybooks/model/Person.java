package br.com.mybooks.model;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Person implements Serializable {

    private Long id;

    @NotBlank(message = "O campo Nome não pode ser vazio")
    @Size(max = 45, message = "Nome não pode conter mais de 45 caractéres")
    private String firstName;

    @NotBlank(message = "O campo Sobrenome não pode ser vazio")
    @Size(max = 100, message = "Sobrenome não pode conter mais de 100 caracteres")
    private String lastName;

    @NotNull(message = "O campo Usuário não pode ser vazio")
    @Valid
    private User user;

    @Valid
    private List<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
