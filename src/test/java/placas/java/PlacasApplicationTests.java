package placas.java;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// IMPORTANTE: Esses nomes precisam ser IGUAIS aos arquivos que você criou
import placas.java.model.Placavideo; 
import placas.java.model.PlacavideoDAO;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PlacasApplicationTests {

    @Autowired
    private PlacavideoDAO placavideoDAO;


    @Test
    void contextLoads() {
    }

    @Test
    void testarBancoPlacaVideo() {
        // Agora o teste usa a sua classe Placavideo com modelo, marca e preço
        Placavideo pv = new Placavideo("RTX 4070", "NVIDIA", 3500.0);
        
        placavideoDAO.inserirPlaca(pv);
        
        assertThat(placavideoDAO.listarPlacas()).isNotEmpty();
        System.out.println(">>> SUCESSO: O banco de Placas de Vídeo respondeu!");
    }
}