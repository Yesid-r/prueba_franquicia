package co.com.accenture.usecase.guardarsucursal;

import co.com.accenture.model.sucursal.Sucursal;
import co.com.accenture.model.sucursal.gateways.SucursalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GuardarSucursalUseCaseTest {

    @InjectMocks
    private GuardarSucursalUseCase guardarSucursalUseCase;

    @Mock
    private SucursalRepository sucursalRepository;

    @Test
    public void guardarSucursalExitoso(){
        //Arrange
        Sucursal sucursalInput = Sucursal.builder().id("1").nombre("Sucursal 1").franquiciaId("1").build();
        Sucursal sucursalOutput = Sucursal.builder().id("1").nombre("Sucursal 1").franquiciaId("1").build();

        given(sucursalRepository.guardarSucursal(sucursalInput)).willReturn(Mono.just(sucursalOutput));
        //act
        var result = guardarSucursalUseCase.action(sucursalInput);

        //Assert
        StepVerifier.create(result)
                .expectNext(sucursalOutput)
                .verifyComplete();



    }


}