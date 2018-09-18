package br.garcia.resource;

import br.garcia.entity.Colaborador;
import br.garcia.entity.Tarefa;
import br.garcia.entity.TarefaStatus;
import br.garcia.service.ColaboradorService;
import br.garcia.service.TarefaService;
import br.garcia.util.Validator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.URI;
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

    @Autowired
    private ColaboradorService colaboradorService;

    @PostMapping
    public ResponseEntity create(@RequestBody String json){

        if(json != null && !json.equals("")){
            JSONObject jsonObj = new JSONObject(json);

            if(!jsonObj.isEmpty() && jsonObj.has("titulo") && jsonObj.has("descricao") && jsonObj.has("colaboradorId")){

                String titulo = jsonObj.getString("titulo");
                String descricao = jsonObj.getString("descricao");
                String colaboradorId = jsonObj.getString("colaboradorId");

                TarefaStatus status = TarefaStatus.TODO;

                if(jsonObj.has("status")){
                    status.valor = jsonObj.getInt("status");
                }

                if(Validator.checkTitulo(titulo) && Validator.checkDescricao(descricao) && Validator.checkStatus(status.valor)){
                    Tarefa tarefa = new Tarefa();

                    tarefa.setTitulo(titulo);
                    tarefa.setDescricao(descricao);
                    tarefa.setStatus(status);

                    Colaborador c = colaboradorService.getById(colaboradorId);

                    tarefa.setColaborador(c);

                    Tarefa serviceResponse = tarefaService.create(tarefa);

                    if(serviceResponse != null && !serviceResponse.getId().equals("")){
                        JSONObject response = new JSONObject();

                        response.put("id", tarefa.getId());

                        return ResponseEntity
                                .created(URI.create(String.format("%s/%s", TarefaResource.URI_RESOURCE, serviceResponse.getId())))
                                .body(response.toString());
                    }
                    else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/getAll/{colaboradorId}")
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

        if(json != null && !json.equals("")){
            JSONObject jsonObj = new JSONObject(json);

            if(!jsonObj.isEmpty() && jsonObj.has("id") && jsonObj.has("titulo")){
                String id = jsonObj.getString("id");
                String titulo = jsonObj.getString("titulo");
            }
        }

        return ResponseEntity.badRequest().build();
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
        if (!id.equals("") && tarefaService.delete(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
