package placas.java.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlacavideoService {
     @Autowired
    PlacavideoDAO PlacavideoDAO;

    public void inserirPlaca(Placavideo placa){
        PlacavideoDAO.inserirPlaca(placa);
    }

    public Placavideo mostrarPlaca(String uuid){
        return PlacavideoDAO.mostrarPlaca(uuid);
    }

    public ArrayList<Placavideo> listarPlacas(){
        return PlacavideoDAO.listarPlacas();
    }
}
