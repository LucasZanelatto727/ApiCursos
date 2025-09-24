package com.senac.aulaapijovemprogramador2.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.senac.aulaapijovemprogramador2.dto.LoginRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${spring.minhacriptografia}")
    private String secret;

    private String emissor = "Lucas";

    @Value("${spring.sessao}")
    private Long tempo;

    public String gerarToken(LoginRequestDto login) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer(emissor)
                    .withSubject(login.email())
                    .withExpiresAt(this.gerarDataExpiracao())
                    .sign(algorithm);
            return token;

        } catch (Exception e) {
            return null;
        }

    }

    private Instant gerarDataExpiracao() {
        var dataAtual = LocalDateTime.now();
        var dataFutura = dataAtual.plusMinutes(tempo);
        return dataFutura.toInstant(ZoneOffset.of("-03:00"));
    }


}
