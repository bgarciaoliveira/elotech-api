package br.garcia.resource;

import br.garcia.entity.Colaborador;
import br.garcia.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(
        value = ColaboradorResource.URI_RESOURCE,
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class ColaboradorResource {

    protected static final String URI_RESOURCE = "/api/colaboradores";

    @Autowired
    private ColaboradorService colaboradorService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Colaborador colaborador){

        Colaborador serviceResponse = colaboradorService.create(colaborador);

        if(serviceResponse == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity
                .created(URI.create(String.format("%s/%s", ColaboradorResource.URI_RESOURCE, serviceResponse.getId())))
                .body(serviceResponse);

    }

    @RequestMapping
    public ResponseEntity getAll(){
        List<Colaborador> colaboradores = colaboradorService.getAll();

        if(colaboradores.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(colaboradores);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        Colaborador colaborador = colaboradorService.getById(id);

        if(colaborador == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(colaborador);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Colaborador colaborador){

        if(!colaboradorService.update(colaborador)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(
            value="/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity delete(@PathVariable Long id){

        if(!colaboradorService.delete(id)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
