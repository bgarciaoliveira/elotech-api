package br.garcia.resource;

import br.garcia.entity.Colaborador;
import br.garcia.service.ColaboradorService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@RestController
@RequestMapping(
        value = ColaboradorResource.URI_RESOURCE,
        produces = {MediaType.APPLICATION_JSON_VALUE}
)

@CrossOrigin
public class ColaboradorResource {

    protected static final String URI_RESOURCE = "/api/colaboradores";

    @Autowired
    private ColaboradorService colaboradorService;

    @PostMapping
    public ResponseEntity create(@RequestBody Colaborador colaborador) throws UnsupportedEncodingException, NoSuchAlgorithmException {

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

    @PutMapping
    public ResponseEntity update(@RequestBody Colaborador colaborador){

        if(!colaboradorService.update(colaborador)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        if(!colaboradorService.delete(id)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/auth")
    public ResponseEntity auth(@RequestBody String json) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        JSONObject jsonObject = new JSONObject(json);

        String email = jsonObject.get("email").toString().trim();
        String senha = jsonObject.get("senha").toString();

        Long id = colaboradorService.auth(email, senha);

        if(id == -1){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        JSONObject response = new JSONObject();
        response.put("id", id);

        return ResponseEntity.ok(response.toString());
    }

}
