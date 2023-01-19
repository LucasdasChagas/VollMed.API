package med.voll.api.Infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.Infra.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class geracaoToken {

    @Value("${api.security.token.secret}")
    private String secrets;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secrets);
            return JWT.create()
                    .withIssuer("Voll_med")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }
    public String getSubject(String TokenJWT){
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secrets);
            return JWT.require(algoritimo)
                    .withIssuer("Voll_med")
                    .build()
                    .verify(TokenJWT)
                    .getSubject();
        }   catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT invalido ou expirado!!");
        }
        }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}