package placas.java.controller; 

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
public class UploadService {
    
    private final Cloudinary cloudinary;

    // Conecta no seu bucket usando a chave do application.yml
    public UploadService(@Value("${cloudinary.url}") String cloudinaryUrl) {
        this.cloudinary = new Cloudinary(cloudinaryUrl);
    }

    // Faz o envio e devolve apenas a URL da imagem
    public String fazerUpload(MultipartFile arquivo) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(arquivo.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("url").toString();
    }
}