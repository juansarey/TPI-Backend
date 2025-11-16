package tpi.transporte.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpMethod;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter; 


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${seguridad.desactivada:false}")
    private boolean seguridadDesactivada;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        if (seguridadDesactivada) {
            return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(ex -> ex.anyExchange().permitAll())
                .build();
        }

        http
    .csrf(ServerHttpSecurity.CsrfSpec::disable)
    .authorizeExchange(exchange -> exchange
        // 1. OPERACIONES – Solicitudes
        .pathMatchers(HttpMethod.POST, "/operaciones/solicitudes").hasAnyRole("cliente","administrador")
        .pathMatchers(HttpMethod.GET, "/operaciones/solicitudes/cliente/*").hasAnyRole("cliente","administrador")
        .pathMatchers(HttpMethod.GET, "/operaciones/solicitudes/*/costo-final").hasAnyRole("cliente","administrador")
        .pathMatchers(HttpMethod.GET, "/operaciones/solicitudes/*").hasAnyRole("cliente","administrador")
        .pathMatchers("/operaciones/solicitudes").hasRole("administrador") // listar todas sólo admin
        .pathMatchers(HttpMethod.PUT, "/operaciones/solicitudes/*").hasRole("administrador")
        .pathMatchers(HttpMethod.DELETE, "/operaciones/solicitudes/*").hasRole("administrador")

        // 2. OPERACIONES – Tramos
        .pathMatchers(HttpMethod.POST, "/operaciones/tramos").hasRole("administrador")         // crear tramos
        .pathMatchers(HttpMethod.DELETE, "/operaciones/tramos/*").hasRole("administrador")     // eliminar tramos
        .pathMatchers("/operaciones/tramos/ruta/*").hasRole("administrador")                   // listar por ruta
        .pathMatchers("/operaciones/tramos/*").hasRole("administrador")                        // obtener por id

        .pathMatchers("/operaciones/tramos/camion/*").hasRole("transportista")         // ver tramos asignados
        .pathMatchers(HttpMethod.POST, "/operaciones/tramos/*/iniciar").hasRole("transportista")
        .pathMatchers(HttpMethod.POST, "/operaciones/tramos/*/finalizar").hasRole("transportista")

        // 3. OPERACIONES – Catálogos (estados/tipos de tramo) y test
        .pathMatchers("/operaciones/estados-tramo/**").permitAll()
        .pathMatchers("/operaciones/tipos-tramo/**").permitAll()
        .pathMatchers("/operaciones/test").permitAll()

        // 4. MAESTROS – ABM de maestros (sólo admin)
        .pathMatchers("/maestro/camiones/**").hasRole("administrador")
        .pathMatchers("/maestro/clientes/**").hasRole("administrador")
        .pathMatchers("/maestro/contenedores/**").hasRole("administrador")
        .pathMatchers("/maestro/estados-contenedor/**").hasRole("administrador")
        .pathMatchers("/maestro/tarifas/**").hasRole("administrador")
        .pathMatchers("/maestro/depositos/**").hasRole("administrador")
        .pathMatchers("/maestro/test").permitAll()

        // cualquier otro intercambio requiere autenticación
        .anyExchange().authenticated()
    )
    .oauth2ResourceServer(oauth2 -> oauth2
        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
    );


        return http.build();
    }


    private Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        ReactiveJwtAuthenticationConverter converter = new ReactiveJwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<String> roles = extractRealmRoles(jwt);
            List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
            return Flux.fromIterable(authorities);
        });
        return converter;
    }

    private List<String> extractRealmRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess == null || !realmAccess.containsKey("roles")) {
            return Collections.emptyList();
        }
        return (List<String>) realmAccess.get("roles");
    }
    
}
