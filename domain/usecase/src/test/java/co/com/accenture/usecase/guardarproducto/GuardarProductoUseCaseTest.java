package co.com.accenture.usecase.guardarproducto;

import co.com.accenture.model.producto.Producto;
import co.com.accenture.model.producto.gateways.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GuardarProductoUseCaseTest {

    @InjectMocks
    private GuardarProductoUseCase guardarProductoUseCase;

    @Mock
    private ProductoRepository productoRepository;

    @Test
    void guardarProducto() {
        //Arrange
        Producto productoInput = Producto.builder().nombre("Producto 1").sucursalId("1").build();
        Producto productoOutput = Producto.builder().id("1").nombre("Producto 1").sucursalId("1").build();

        //act
        given(productoRepository.guardarProducto(any(Producto.class))).willReturn(Mono.just(productoOutput));

        //assert
        var result = guardarProductoUseCase.action(productoInput);
//        assertAll(
//                () -> assertNotNull(result),
//                () -> assertEquals(productoOutput, result.block())
//        );
        StepVerifier.create(result).expectNext(productoOutput).verifyComplete();
    }
}