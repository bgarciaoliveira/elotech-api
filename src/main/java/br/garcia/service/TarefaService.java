package br.garcia.service;

import br.garcia.entity.Tarefa;
import br.garcia.entity.TarefaStatus;
import br.garcia.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> getAllByColaborador(String colaboradorId){
        return tarefaRepository.findAllByColaborador_Id(colaboradorId);
    }

    public Tarefa getById(String id){
        return tarefaRepository.getOne(id);
    }

    public boolean updateTitulo(String id, String nome){

        if(tarefaRepository.exists(id)){
            Tarefa tarefa = tarefaRepository.findOne(id);

            if(!tarefa.getTitulo().equals(nome)){
                tarefa.setTitulo(nome);

                tarefaRepository.save(tarefa);
            }

            return true;
        }

        return false;
    }

    public boolean updateDescricao(String id, String descricao){

        if(tarefaRepository.exists(id)){
            Tarefa tarefa = tarefaRepository.findOne(id);

            if(!tarefa.getDescricao().equals(descricao)){
                tarefa.setTitulo(descricao);

                tarefaRepository.save(tarefa);
            }

            return true;
        }

        return false;
    }

    public boolean updateStatus(String id, TarefaStatus status){

        if(tarefaRepository.exists(id)){
            Tarefa tarefa = tarefaRepository.findOne(id);

            if(!tarefa.getStatus().equals(status)){
                tarefa.setStatus(status);

                tarefaRepository.save(tarefa);
            }

            return true;
        }

        return false;
    }

    public Boolean delete(String id){
        if(tarefaRepository.exists(id)){
            tarefaRepository.delete(id);

            return true;
        }

        return false;
    }
}
