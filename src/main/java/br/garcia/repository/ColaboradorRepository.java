package br.garcia.repository;

import br.garcia.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    List<Colaborador> findByEmailAndSenha(String email, String senha);
}
