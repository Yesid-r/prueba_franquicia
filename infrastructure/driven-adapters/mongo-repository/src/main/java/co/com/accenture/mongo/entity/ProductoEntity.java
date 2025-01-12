package co.com.accenture.mongo.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")
@Data
public class ProductoEntity {

    @Id
    private String id;
    private String nombre;
    private int stock;
    private String sucursalId;

}
