package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.Infra.Usuario;
import med.voll.api.Infra.security.dadosAutenticacaoUser;
import med.voll.api.Infra.security.dadosTokenJWT;
import med.voll.api.Infra.security.geracaoToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class authenticationController {

    @Autowired
    public AuthenticationManager manager;

    @Autowired
    private geracaoToken tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid dadosAutenticacaoUser dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var man = manager.authenticate(token);

        var tokenJWT = tokenService.gerarToken((Usuario) man.getPrincipal());

        return ResponseEntity.ok(new dadosTokenJWT(tokenJWT));
    }




}
