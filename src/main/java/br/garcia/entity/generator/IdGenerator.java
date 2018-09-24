package br.garcia.entity.generator;

import javax.persistence.*;

@Entity
@Table(name="id_generator")
public class IdGenerator {

    @Id
    @GeneratedValue
    private Long value;

    public Long getId() {
        return value;
    }

    public void setId(Long id) {
        this.value = id;
    }
}
