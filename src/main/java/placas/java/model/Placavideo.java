package placas.java.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Placavideo {

    private String uuid;
    private String modelo;
    private String marca;
    private double preco;

    // Construtores
    public Placavideo() {}

    public Placavideo(String modelo, String marca, double preco) {
        this.modelo = modelo;
        this.marca = marca;
        this.preco = preco;
    }


    public static Placavideo converter(Map<String, Object> registro) {
        Placavideo p = new Placavideo();
        
        // No Postgres, o ID vem como objeto UUID, precisamos do .toString()
        if (registro.get("id") != null) {
            p.setUuid(registro.get("id").toString());
        }
        
        p.setModelo((String) registro.get("modelo"));
        p.setMarca((String) registro.get("marca"));
        
        // No Postgres, o preço vem como BigDecimal. Esse cast garante que não dê Erro 500
        if (registro.get("preco") != null) {
            p.setPreco(((Number) registro.get("preco")).doubleValue());
        }
        
        return p;
    }

    public static ArrayList<Placavideo> converterTodos(List<Map<String, Object>> registros) {
        ArrayList<Placavideo> lista = new ArrayList<>();
        for (Map<String, Object> registro : registros) {
            lista.add(converter(registro));
        }
        return lista;
    }

    // --- GETTERS E SETTERS (Obrigatórios para o Thymeleaf) ---

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

}