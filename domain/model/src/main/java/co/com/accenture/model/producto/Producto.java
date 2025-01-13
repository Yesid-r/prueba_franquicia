package co.com.accenture.model.producto;
import lombok.*;
//import lombok.NoArgsConstructor;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Producto  {

    private String id;
    private String nombre;
    private int stock;
    private String sucursalId;
    private String franquiciaId;



}
