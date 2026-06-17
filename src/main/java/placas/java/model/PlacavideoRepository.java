package placas.java.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PlacavideoRepository extends JpaRepository<Placavideo, UUID> {
    // O JPA já fornece findAll(), save(), findById(), deleteById() automaticamente.
}