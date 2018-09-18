package br.garcia.resource;

import br.garcia.entity.Tarefa;
import br.garcia.service.ColaboradorService;
import br.garcia.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.util.List;

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

        if(colaboradorId != null && !colaboradorId.equals("")){
            List<Tarefa> tarefas = tarefaService.getAllByColaborador(colaboradorId);

            if(tarefas != null && !tarefas.isEmpty()){
                return ResponseEntity.ok(tarefas);
            }

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable String id){

        if(id != null && !id.equals("")){
            Tarefa tarefa = tarefaService.getById(id);

            if(tarefa != null && !tarefa.getId().equals("")){
                return ResponseEntity.ok(tarefa);
            }

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.badRequest().build();
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
