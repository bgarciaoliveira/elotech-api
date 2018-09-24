package br.garcia.resource;

import br.garcia.dto.TarefaCreateDto;
import br.garcia.dto.TarefaUpdateDto;
import br.garcia.entity.Tarefa;
import br.garcia.service.TarefaService;
import br.garcia.util.Functions;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping(
        value = TarefaResource.URI_RESOURCE,
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class TarefaResource {
    protected static final String URI_RESOURCE = "/api/tarefas";

    @Autowired
    private TarefaService tarefaService;

    private ModelMapper mapper = new ModelMapper();

    @PostMapping
    public ResponseEntity create(@RequestHeader HttpHeaders headers, @RequestBody @Valid TarefaCreateDto tarefaDto){

        String id = Functions.getIdFromHeaders(headers);

        Tarefa tarefa = mapper.map(tarefaDto, Tarefa.class);

        if(nonNull(tarefa)){

            tarefa.setColaboradorId(id);

            long codigo = tarefaService.getLastCodigoByColaborador(id) + 1;

            tarefa.setCodigo(codigo);

            Tarefa serviceResponse = tarefaService.create(tarefa);

            if(nonNull(serviceResponse) && !serviceResponse.getId().isEmpty()){
                JSONObject response = new JSONObject();
                response.put("id", tarefa.getId());
                return ResponseEntity
                        .created(URI.create(String.format("%s/%s", TarefaResource.URI_RESOURCE, serviceResponse.getId())))
                        .body(response.toString());
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping
    public ResponseEntity getAll(@RequestHeader HttpHeaders headers) {

        String id = Functions.getIdFromHeaders(headers);

        List<Tarefa> tarefas = tarefaService.getAllByColaborador(id);

        if (nonNull(tarefas) && !tarefas.isEmpty()) {
            return ResponseEntity.ok(tarefas);
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity getById(@RequestHeader HttpHeaders headers, @PathVariable @NotNull String id){
        String colaboradorId = Functions.getIdFromHeaders(headers);
        Tarefa tarefa = tarefaService.getById(id);

        if(colaboradorId.equals(tarefa.getColaboradorId())){
            if(!tarefa.getId().isEmpty()) {
                //colaborador pode obter apenas as tarefas em que ele é dono
                return ResponseEntity.ok(tarefa);
            }

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @RequestMapping(value = "/getEstatisticas")
    public ResponseEntity getEstatisticas(@RequestHeader HttpHeaders headers){
        String colaboradorId = Functions.getIdFromHeaders(headers);

        List<Tarefa> tarefas = tarefaService.getAllByColaborador(colaboradorId);

        JSONObject response = new JSONObject();

        for(byte i = 1; i <= 5; i++){
            final byte finalI = i;
            response.put("c" + i, tarefas.stream().filter(tarefa -> tarefa.getStatus() == finalI).count());
        }

        return ResponseEntity.ok(response.toString());
    }

    @PutMapping
    public ResponseEntity update(@RequestHeader HttpHeaders headers, @RequestBody @Valid TarefaUpdateDto tarefaDto){

        String colaboradorId = Functions.getIdFromHeaders(headers);

        Tarefa storedTarefa = tarefaService.getById(tarefaDto.getId());

        if(colaboradorId.equals(storedTarefa.getColaboradorId())){
            //colaborador pode alterar apenas as tarefas em que ele é dono

            storedTarefa.setTitulo(tarefaDto.getTitulo());
            storedTarefa.setDescricao(tarefaDto.getDescricao());
            storedTarefa.setStatus(tarefaDto.getStatus());

            if(tarefaService.update(storedTarefa)){
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@RequestHeader HttpHeaders headers, @PathVariable @NotNull String id){

        String colaboradorId = Functions.getIdFromHeaders(headers);
        Tarefa tarefa = tarefaService.getById(id);

        if(colaboradorId.equals(tarefa.getColaboradorId())){
            //colaborador pode deletar apenas as tarefas em que ele é dono
            if(tarefaService.delete(id)){
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
