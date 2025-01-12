package co.com.accenture.mongo.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sucursales")
@Data
public class SucursalEntity {

    private String id;
    private String nombre;
    private String franquiciaId;
}
