package co.com.accenture.mongo.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "franquicias")
@Data
public class FranquiciaEntity {

    @Id
    private String id;
    private String nombre;
}
