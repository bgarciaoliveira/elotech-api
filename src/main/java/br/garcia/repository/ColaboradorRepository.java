package br.garcia.repository;

import br.garcia.entity.Colaborador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends CrudRepository<Colaborador, Long> {

}
