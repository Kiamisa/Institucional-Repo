package br.uema.IntelligentAssistent.config;

import br.uema.IntelligentAssistent.auth.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    /**
     * Este bean define a cadeia de filtros de segurança, que é o núcleo da configuração de segurança.
     */
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        http
                // Configuração do CORS usando o bean 'corsConfigurationSource'
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                //Desabilitando o Cross-Site Request Forgery
                .csrf(csrf -> csrf.disable())
                // Define a gestão de sessão como STATELESS, essencial para APIs REST com JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //Definindo as regras de autorização para fazer as requisições
                .authorizeHttpRequests(authorize -> authorize
                // Permissao para o endpoint de login
                         .requestMatchers("/auth/**").permitAll()
                        //IMPORTANTE: Caso seja necessário criar um usuario
                                .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                        //Todas requisicoes exigirao autenticacao
                         .anyRequest().authenticated()
                );
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Este bean define a configuração do CORS (Cross-Origin Resource Sharing).
     * É essencial para permitir que seu frontend Vue.js (rodando em uma porta diferente)
     * possa fazer requisições para este backend.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //Definição da origem permitida (URL Vue.js)
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        //Definição dos métodos HTTP permitidos
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        //Definição dos cabeçalhos permitidos
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        //Permitir envio de credenciais (cookies, cabeçalhos de autorização, etc.)
        configuration.setAllowCredentials(true);
        //Definição do tempo máximo de cache da configuração CORS(1 Hora)
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //Aplicação da configuração de CORS para todos os ENDPOINTS da aplicação
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Este bean cria uma instância do BCryptPasswordEncoder, que é o algoritmo
     * recomendado para fazer o hash de senhas. Ele será injetado nos serviços.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
