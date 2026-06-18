package placas.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration avisa o Spring: "Leia este arquivo assim que o sistema ligar, 
// regras de configuração do projeto".
@Configuration
public class SecurityConfig {

    // @Bean avisa que este método cria um componente útil que o Spring pode usar.
    // O PasswordEncoder é o "Criptografador" de senhas.
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt:Ele pega a senha "admin123" e transforma 
        // num código maluco (ex: $2a$10$xyz...) para ninguém descobrir.
        return new BCryptPasswordEncoder();
    }

    // Aqui nós criamos os usuários que têm permissão de entrar no sistema.
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        // Como não criamos uma tabela de Usuários no banco, criamos um na memória (só no código).
        UserDetails admin = User.builder()
            .username("admin") // O login será 'admin'
            .password(encoder.encode("admin123")) // A senha será 'admin123' (já criptografada)
            .roles("ADMIN") // O cargo dele é de Administrador
            .build();
            
        // Guarda esse usuário "admin" na memória do servidor.
        return new InMemoryUserDetailsManager(admin);
    }

    // Ele dita quem pode entrar, que rotas são públicas e como é a tela de login.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) 
            
            // Aqui configuramos as REGRAS DE ACESSO das URLs:
            .authorizeHttpRequests(auth -> auth
                // .permitAll() significa "Pode entrar sem login".
                // Deixamos a tela de "/login" e a nossa API "/rest/**" abertas para qualquer um.
                .requestMatchers("/login", "/rest/**").permitAll() 
                
                // .authenticated() significa "Exige login".
                // Qualquer OUTRA rota (como o "/" ou "/placas/salvar") vai barrar o usuário
                // e mandar ele para a tela de login.
                .anyRequest().authenticated() 
            )
            
            // Aqui configuramos o FORMULÁRIO DE LOGIN visual:
            .formLogin(form -> form
                // Dizemos ao Spring: "Não use a sua tela feia padrão, use a minha tela '/login' (o arquivo login.html)".
                .loginPage("/login")
                // Se ele logar com sucesso (admin/admin123), manda ele para a página inicial "/".
                .defaultSuccessUrl("/", true)
                // Permite que qualquer pessoa acesse a tela de login para tentar logar.
                .permitAll()
            )
            
            // Aqui configuramos o botão de SAIR (LOGOUT):
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .build(); 
    }
}