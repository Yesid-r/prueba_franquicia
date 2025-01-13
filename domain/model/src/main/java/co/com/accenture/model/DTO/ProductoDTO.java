package co.com.accenture.model.DTO;

import co.com.accenture.model.producto.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private String nombre;
    private int stock;
    private String sucursalNombre;


    public static Producto fromProducto(String nombre, int stock, String sucursalNombre) {
        return Producto.builder()
                .nombre(nombre)
                .stock(stock)
                .build();
    }

}
