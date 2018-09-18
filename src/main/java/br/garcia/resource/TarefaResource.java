package br.garcia.resource;

import br.garcia.service.ColaboradorService;
import br.garcia.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Resource;
import javax.xml.ws.Response;

@RestController
@RequestMapping(
        value = TarefaResource.URI_RESOURCE,
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class TarefaResource {
    protected static final String URI_RESOURCE = "/api/tarefas";

    @Autowired
    private TarefaService tarefaService;

    @RequestMapping(value = "/{colaboradorId}")
    public ResponseEntity getAll(@PathVariable String colaboradorId){
        throw new NotImplementedException();
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable String id){
        throw new NotImplementedException();
    }

    @PutMapping(value = "/updateTitulo")
    public ResponseEntity updateTitulo(@RequestBody String json){
        throw new NotImplementedException();
    }

    @PutMapping(value = "/updateDescricao")
    public ResponseEntity updateDescricao(@RequestBody String json){
        throw new NotImplementedException();
    }

    @PutMapping(value = "/updateStatus")
    public ResponseEntity updateStatus(@RequestBody String json){
        throw new NotImplementedException();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable String id){
        throw new NotImplementedException();
    }
}
