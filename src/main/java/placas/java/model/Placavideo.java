package placas.java.model;

import jakarta.persistence.*;
import java.util.UUID;

// @Entity diz ao Spring: "Esta classe não é uma classe comum. Ela representa uma Tabela na base de dados."
@Entity
// @Table diz qual é o nome exato da tabela lá no PostgreSQL. 
// Se ela não existir, o sistema vai criá-la para si automaticamente.
@Table(name = "placavideo")
public class Placavideo {

    // @Id Chave Primária
    @Id
    // @GeneratedValue diz: "Não me peça para digitar o ID. Gere um automaticamente para mim."
    // O 'strategy = GenerationType.UUID' escolhe o formato UUID (ex: 123e4567-e89b-12d3...).
    @GeneratedValue(strategy = GenerationType.UUID)
    // @Column aqui diz que esta coluna 'id' não pode ser atualizada depois de criada e não pode ser nula.
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // @Column(nullable = false) significa: "É obrigatório ter um modelo". 
    // Equivale ao 'NOT NULL' do SQL. Se tentar guardar na base de dados sem modelo, o Java bloqueia e dá erro.
    @Column(nullable = false)
    private String modelo;

    // Como não tem @Column aqui, o Spring assume que a coluna se chama 'marca' 
    // e que ela aceita ficar vazia (pode ser nula se o utilizador não preencher).
    private String marca;

    // Mesma regra do modelo: o preço é obrigatório.
    @Column(nullable = false)
    private double preco;

    // O JPA OBRIGA a ter um construtor vazio. 
    // Ele usa isto nos bastidores para "montar" a placa de vídeo quando faz o SELECT na base de dados.
    public Placavideo() {}

    // Este construtor serve para o programador criar uma placa nova no código mais facilmente.
    // Exemplo: Placavideo pv = new Placavideo("RTX 4060", "NVIDIA", 2500.0);
    public Placavideo(String modelo, String marca, double preco) {
        this.modelo = modelo;
        this.marca = marca;
        this.preco = preco;
    }

    // --- GETTERS E SETTERS ---
    // Eles são obrigatórios para que o Thymeleaf (o ecrã HTML) consiga ler e alterar os dados.

    // Lê o ID da placa
    public UUID getId() { return id; }
    // Define o ID da placa (usado maioritariamente pelo próprio JPA)
    public void setId(UUID id) { this.id = id; }

    // Lê o modelo (ex: "RTX 4060") para mostrar na tabela HTML
    public String getModelo() { return modelo; }
    // Grava o modelo quando o utilizador escreve no formulário
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
}