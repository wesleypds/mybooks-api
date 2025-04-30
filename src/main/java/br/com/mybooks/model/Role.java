package br.com.mybooks.model;

import java.io.Serializable;

import br.com.mybooks.enums.RoleName;

public class Role implements Serializable {

    private Long id;

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
