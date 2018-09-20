package br.garcia.repository;

import br.garcia.entity.Colaborador;
import br.garcia.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, String> {

    List<Tarefa> findAllByColaboradorId(String colaboradorId);


}
