package br.garcia.resource;

import br.garcia.dto.ColaboradorAuthDto;
import br.garcia.dto.ColaboradorCreateDto;
import br.garcia.dto.ColaboradorUpdateDto;
import br.garcia.dto.ColaboradorUpdateSenhaDto;
import br.garcia.entity.Colaborador;
import br.garcia.service.ColaboradorService;
import br.garcia.util.Functions;
import br.garcia.util.HashLibrary;
import br.garcia.util.Jwt;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping(
        value = ColaboradorResource.URI_RESOURCE,
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class ColaboradorResource {

    protected static final String URI_RESOURCE = "/api/colaboradores";

    @Autowired
    private ColaboradorService colaboradorService;

    private ModelMapper mapper = new ModelMapper();

    //region no token endpoints
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid ColaboradorCreateDto colaboradorDto) {

        Colaborador colaborador = mapper.map(colaboradorDto, Colaborador.class);

        Colaborador serviceResponse = colaboradorService.create(colaborador);

        if(nonNull(serviceResponse) && !serviceResponse.getId().isEmpty()){

            JSONObject response = new JSONObject();
            response.put("id", colaborador.getId());

            return ResponseEntity
                    .created(URI.create(String.format("%s/%s", ColaboradorResource.URI_RESOURCE, serviceResponse.getId())))
                    .body(response.toString());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/auth")
    public ResponseEntity auth(@RequestBody @Valid ColaboradorAuthDto colaboradorDto) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String id = colaboradorService.auth(colaboradorDto.getEmail(), colaboradorDto.getSenha());
        String token = Jwt.create(id);

        if(nonNull(id) && !id.isEmpty() && nonNull(token) && !token.isEmpty()){
            JSONObject response = new JSONObject();
            response.put("id", id);
            response.put("token", token);

            return ResponseEntity.ok(response.toString());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    //endregion

    @RequestMapping(value = "/me")
    public ResponseEntity me(@RequestHeader HttpHeaders headers) {

        String id = Functions.getIdFromHeaders(headers);

        Colaborador colaborador = colaboradorService.getById(id);

        if (!isNull(colaborador) && !colaborador.getId().isEmpty()) {
            JSONObject response = new JSONObject();
            response.put("id", colaborador.getId());
            response.put("cpf", colaborador.getCpf());
            response.put("email", colaborador.getEmail());
            response.put("nome", colaborador.getNome());
            return ResponseEntity.ok(response.toString());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity update(@RequestHeader HttpHeaders headers, @RequestBody @Valid ColaboradorUpdateDto colaboradorDto){
        String id = Functions.getIdFromHeaders(headers);

        Colaborador colaborador = colaboradorService.getById(id);

        if(nonNull(colaborador)){

            colaborador.setEmail(colaboradorDto.getEmail());
            colaborador.setNome(colaboradorDto.getNome());
            colaborador.setCpf(colaboradorDto.getCpf());

            if(colaboradorService.update(colaborador)){
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping(value = "/updateSenha")
    public ResponseEntity updateSenha(@RequestHeader HttpHeaders headers, @RequestBody @Valid ColaboradorUpdateSenhaDto colaboradorDto) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String id = Functions.getIdFromHeaders(headers);
        Colaborador colaborador = colaboradorService.getById(id);

        if(nonNull(colaborador)){

            if(colaborador.getSenha().equals(
                    HashLibrary.passwordHash(colaboradorDto.getSenhaAntiga())
            )){

                colaborador.setSenha(colaboradorDto.getSenhaNova());

                if(colaboradorService.update(colaborador)){
                    return ResponseEntity.noContent().build();
                }
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@RequestHeader HttpHeaders headers) {

        String id = Functions.getIdFromHeaders(headers);

        if(colaboradorService.delete(id)){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
