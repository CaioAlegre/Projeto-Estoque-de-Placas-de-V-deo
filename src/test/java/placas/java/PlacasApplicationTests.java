package placas.java;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import placas.java.model.Placavideo; 
// IMPORTANTE: Agora importamos o Repositório em vez do DAO
import placas.java.model.PlacavideoRepository; 

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PlacasApplicationTests {

    // Injetamos o Repositório do JPA
    @Autowired
    private PlacavideoRepository repo;

    @Test
    void contextLoads() {
    }

    @Test
    void testarBancoPlacaVideo() {
        // Cria a placa de vídeo
        Placavideo pv = new Placavideo("RTX 4070", "NVIDIA", 3500.0);
        
        // Salva a placa usando o método automático do JPA (.save em vez de .inserirPlaca)
        repo.save(pv);
        
        // Verifica se a lista não está vazia usando o método do JPA (.findAll em vez de .listarPlacas)
        assertThat(repo.findAll()).isNotEmpty();
        
        System.out.println(">>> SUCESSO: O banco de Placas de Vídeo respondeu com JPA!");
    }
}