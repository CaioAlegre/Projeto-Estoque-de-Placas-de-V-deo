package placas.java.model;

import java.util.ArrayList;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct;

@Repository
public class PlacavideoDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirPlaca(Placavideo placa) {
        String sql = "INSERT INTO placavideo(modelo, marca, preco) VALUES (?,?,?)";
        jdbc.update(sql, placa.getModelo(), placa.getMarca(), placa.getPreco());
        //Object[] obj = new Object[3];
        // primeiro ?
        //obj[0] = placa.getModelo();
        // segundo ?
        //obj[1] = placa.getMarca();
        // terceiro ?
        //obj[2] = placa.getPreco();

        //jdbc.update(sql, obj);
    }

    public Placavideo mostrarPlaca(String uuid) {
        String sql = "SELECT * FROM placavideo WHERE id = ?::uuid";
        return Placavideo.converter(jdbc.queryForMap(sql, new Object[]{uuid}));    
    }

    public ArrayList<Placavideo> listarPlacas() {
        String sql = "SELECT * FROM public.placavideo";
        return Placavideo.converterTodos(jdbc.queryForList(sql));
    }
}
