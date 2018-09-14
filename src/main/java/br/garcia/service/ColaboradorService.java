package br.garcia.service;

import br.garcia.entity.Colaborador;
import br.garcia.repository.ColaboradorRepository;
import br.garcia.util.HashLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class ColaboradorService {

    @Autowired
    protected ColaboradorRepository colaboradorRepository;

    public Colaborador create(Colaborador colaborador) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        colaborador.setSenha(HashLibrary.passwordHash(colaborador.getSenha()));

        return colaboradorRepository.save(colaborador);
    }

    public List<Colaborador> getAll(){
        return colaboradorRepository.findAll();
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

    public Long auth(String email, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        List<Colaborador> res = colaboradorRepository.findByEmailAndSenha(email, HashLibrary.passwordHash(password));

        if(!res.isEmpty() && res.size() == 1){
            return res.get(0).getId();
        }

        return Long.valueOf(-1);
    }
}
