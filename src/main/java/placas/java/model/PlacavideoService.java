package placas.java.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

// @Service avisa ao Spring: "Esta classe contém a lógica de negócios da aplicação".
// É ela que o Controller vai chamar quando o usuário clicar em "Salvar" lá na tela HTML.
@Service
public class PlacavideoService {

    // @Autowired é a "injeção de dependência" do Spring.
    // Ele diz: "Spring, pegue aquele PlacavideoRepository (que faz a mágica do banco) e coloque aqui dentro para eu usar".
    @Autowired
    private PlacavideoRepository repo;

    // Método para salvar ou atualizar uma placa.
    public Placavideo salvar(Placavideo placa) {
        
        // --- REGRA DE NEGÓCIO ---
        // Aqui vemos POR QUE o Service existe. Antes de jogar para o banco de dados,
        // nós verificamos se o usuário digitou um preço inválido (negativo).
        // Se ele digitou -50, o sistema corrige para 0 automaticamente.
        if(placa.getPreco() < 0) {
            placa.setPreco(0);
        }
        
        // Depois de validar tudo, passamos a bola para o Repository:
        // "Ei repo, agora que os dados estão certos, salva lá no PostgreSQL pra mim!"
        return repo.save(placa);
    }

    // Método para buscar UMA placa específica pelo ID dela.
    public Placavideo buscarPorId(UUID id) {
        // repo.findById(id) vai no banco procurar. 
        // O '.orElse(null)' significa: "Se você achar a placa com esse ID, me devolva ela. 
        // Se não achar nada, me devolva 'null' (nulo/vazio) em vez de dar um erro na tela".
        return repo.findById(id).orElse(null);
    }

    // Método para listar todas as placas.
    public List<Placavideo> listarPlacas() {
        // Simplesmente pede ao banco: "Me traga uma lista com tudo o que tem na tabela placavideo".
        return repo.findAll();
    }

    // Método para excluir uma placa.
    public void deletar(UUID id) {
        // Pede ao banco: "Delete a linha que tem este ID específico".
        repo.deleteById(id);
    }
}