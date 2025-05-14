package br.com.mybooks.model;

import java.io.Serializable;

import br.com.mybooks.enums.RoleName;
import jakarta.validation.constraints.NotNull;

public class Role implements Serializable {

    @NotNull(message = "O campo Id não pode ser vazio")
    private Long id;

    @NotNull(message = "O campo Nome não pode ser vazio")
    private RoleName name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

}
