package br.garcia.service;

import br.garcia.entity.Colaborador;
import br.garcia.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {

    @Autowired
    protected ColaboradorRepository colaboradorRepository;

    public Colaborador create(Colaborador colaborador){
        return colaboradorRepository.save(colaborador);
    }

    public List<Colaborador> getAll(){
        return (List<Colaborador>) colaboradorRepository.findAll();
    }

    public Colaborador getById(Long id){
        return colaboradorRepository.findOne(id);
    }

    public Boolean update(Colaborador colaborador){

        if(colaboradorRepository.exists(colaborador.getId())){
            colaboradorRepository.save(colaborador);

            return true;
        }

        return false;
    }

    public Boolean delete(Long id){
        if(colaboradorRepository.exists(id)){
            colaboradorRepository.delete(id);

            return true;
        }

        return false;
    }
}
