package co.com.accenture.model.sucursal;
import lombok.*;
//import lombok.NoArgsConstructor;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {

    private String id;
    private String nombre;
    private String franquiciaId;
}
