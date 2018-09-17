package br.garcia.resource;

import br.garcia.entity.Colaborador;
import br.garcia.service.ColaboradorService;
import br.garcia.util.Jwt;
import br.garcia.util.Validator;
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
public class ColaboradorResource {

    protected static final String URI_RESOURCE = "/api/colaboradores";

    @Autowired
    private ColaboradorService colaboradorService;

    //region no required token endpoints
    @PostMapping
    public ResponseEntity create(@RequestBody String json) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        JSONObject jsonObj = new JSONObject(json);

        // validação campos obrigatórios
        if (!jsonObj.isEmpty() && jsonObj.has("cpf") && jsonObj.has("email") && jsonObj.has("senha") && jsonObj.has("nome")) {

            String cpf = jsonObj.getString("cpf");
            String email = jsonObj.getString("email");
            String senha = jsonObj.getString("senha");
            String nome = jsonObj.getString("nome");

            if (Validator.checkCpf(cpf) && Validator.checkEmail(email) && Validator.checkNome(nome) && Validator.checkSenha(senha)) {
                Colaborador colaborador = new Colaborador();

                colaborador.setCpf(cpf);
                colaborador.setEmail(email);
                colaborador.setSenha(senha);
                colaborador.setNome(nome);

                Colaborador serviceResponse = colaboradorService.create(colaborador);

                if (serviceResponse != null && !serviceResponse.getId().equals("")) {
                    JSONObject response = new JSONObject();

                    response.put("id", colaborador.getId());

                    return ResponseEntity
                            .created(URI.create(String.format("%s/%s", ColaboradorResource.URI_RESOURCE, serviceResponse.getId())))
                            .body(response.toString());
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/auth")
    public ResponseEntity auth(@RequestBody String json) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        JSONObject jsonObject = new JSONObject(json);

        if (!jsonObject.isEmpty() && jsonObject.has("email") && jsonObject.has("senha")) {

            String email = jsonObject.get("email").toString().trim();
            String senha = jsonObject.get("senha").toString();

            if (Validator.checkEmail(email) && Validator.checkSenha(senha)) {
                String id = colaboradorService.auth(email, senha);
                String token = Jwt.create(id);

                System.out.println(id);
                System.out.println(token);

                if (!id.equals("") && !token.equals("")) {
                    JSONObject response = new JSONObject();
                    response.put("id", id);
                    response.put("token", token);

                    return ResponseEntity.ok(response.toString());

                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }
            }
        }

        return ResponseEntity.badRequest().build();
    }

    //endregion

    @RequestMapping
    @Deprecated
    public ResponseEntity getAll() {
        List<Colaborador> colaboradores = colaboradorService.getAll();

        if (!colaboradores.isEmpty()) {
            return ResponseEntity.ok(colaboradores);
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Colaborador colaborador = colaboradorService.getById(id);

        if (colaborador != null) {
            return ResponseEntity.ok(colaborador);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/updateNome")
    public ResponseEntity updateNome(@RequestBody String json) {
        JSONObject jsonObj = new JSONObject(json);

        if (!jsonObj.isEmpty() && jsonObj.has("id") && jsonObj.has("nome")) {
            String id = jsonObj.getString("id");
            String nome = jsonObj.getString("nome");

            if (!id.equals("") && Validator.checkNome(nome)) {
                boolean serviceResponse = colaboradorService.updateNome(id, nome);

                if (serviceResponse) {
                    return ResponseEntity.noContent().build();
                }
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/updateEmail")
    public ResponseEntity updateEmail(@RequestBody String json) {
        JSONObject jsonObj = new JSONObject();

        if (!jsonObj.isEmpty() && jsonObj.has("id") && jsonObj.has("email")) {

            String id = jsonObj.getString("id");
            String email = jsonObj.getString("email");

            if (!id.equals("") && Validator.checkEmail(email)) {
                boolean serviceResponse = colaboradorService.updateEmail(id, email);

                if (serviceResponse) {
                    return ResponseEntity.noContent().build();
                }
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {

        if (!id.equals("") && colaboradorService.delete(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


}
