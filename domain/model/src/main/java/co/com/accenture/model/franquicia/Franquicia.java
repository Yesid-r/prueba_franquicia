package co.com.accenture.model.franquicia;
import lombok.*;
//import lombok.NoArgsConstructor;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Franquicia {

    private String id;
    private String nombre;


}
