package br.garcia.service;

import br.garcia.entity.Tarefa;
import br.garcia.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa create(Tarefa tarefa)  {
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> getAllByColaborador(String colaboradorId){
        return tarefaRepository.findAllByColaboradorId(colaboradorId);
    }

    public Tarefa getById(String id){
        return tarefaRepository.getOne(id);
    }

    public boolean update(Tarefa tarefa){

        if(tarefaRepository.exists(tarefa.getId())){
            tarefaRepository.save(tarefa);
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
