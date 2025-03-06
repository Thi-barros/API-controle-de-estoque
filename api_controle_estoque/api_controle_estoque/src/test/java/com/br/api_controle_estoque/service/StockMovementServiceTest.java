package com.br.api_controle_estoque.service;

import com.br.api_controle_estoque.DTO.StockMovementRequestDto;
import com.br.api_controle_estoque.DTO.StockMovementRequestUpdateDto;
import com.br.api_controle_estoque.model.MovementType;
import com.br.api_controle_estoque.model.Product;
import com.br.api_controle_estoque.model.StockMovement;
import com.br.api_controle_estoque.model.User;
import com.br.api_controle_estoque.repository.ProductRepository;
import com.br.api_controle_estoque.repository.StockMovementRepository;
import com.br.api_controle_estoque.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StockMovementServiceTest {

    @Mock
    private StockMovementRepository stockMovementRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private StockMovementService stockMovementService;


    private Product product;
    private User user;
    private StockMovementRequestDto requestDto;

    @BeforeEach
    void setUp(){
        product = new Product();
        product.setId(1l);
        product.setName("Produto teste");
        product.setQuantity(0);

        user = new User();
        user.setId(1L);
        user.setName("Usuario de teste");

        requestDto = new StockMovementRequestDto(
                1L,
                MovementType.ENTRADA,
                10,
                "Movimentação de teste",
                1L
        );
    }

    @Test
    void testCreateStockMovement_Sucess(){
        // Mockando as chamadas do banco de dados
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(stockMovementRepository.save(any(StockMovement.class))).thenAnswer(invocation -> invocation.getArgument(0));

        StockMovement stockMovement = stockMovementService.createStockMovement(requestDto);

        // Verificações
        assertNotNull(stockMovement);
        assertEquals(product, stockMovement.getProduct());
        assertEquals(MovementType.ENTRADA, stockMovement.getMovementType());
        assertEquals(10, stockMovement.getQuantity());
        assertEquals("Movimentação de teste", stockMovement.getObservation());
        assertEquals(user, stockMovement.getUser());
        assertEquals(10, product.getQuantity());

        // Garantir que os métodos de repositório foram chamados
        verify(productRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(1L);
        verify(stockMovementRepository, times(1)).save(any(StockMovement.class));
    }

    @Test
    void testCreateStockMovement_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                stockMovementService.createStockMovement(requestDto));

        assertEquals("Produto não encontrado", exception.getMessage());
    }

    @Test
    void testCreateStockMovement_UserNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                stockMovementService.createStockMovement(requestDto));

        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    /*
    @Test
    public void testRevertStockMovement(){

        //Arrange
        Product product1 = new Product();
        product1.setQuantity(50);

        StockMovement stockMovement = new StockMovement();
        stockMovement.setMovementType(MovementType.ENTRADA);
        stockMovement.setQuantity(10);

        //Act

      asdad
    }*/

    @Test
    public void testUpdateStockMovement() {
        // Arrange
        StockMovementRequestUpdateDto updateDto = new StockMovementRequestUpdateDto
                (1L, 15, MovementType.SAIDA, "Nova Observação");

        Product product = new Product();
        product.setId(1L);
        product.setQuantity(50);

        StockMovement existingMovement = new StockMovement();
        existingMovement.setId(1L);
        existingMovement.setProduct(product);
        existingMovement.setMovementType(MovementType.ENTRADA);
        existingMovement.setQuantity(10);

        when(stockMovementRepository.findById(1L)).thenReturn(Optional.of(existingMovement));
        when(stockMovementRepository.save(any(StockMovement.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        StockMovement result = stockMovementService.updateStockMovement(updateDto);

        // Assert
        assertNotNull(result);
        assertEquals(15, result.getQuantity());
        assertEquals(MovementType.SAIDA, result.getMovementType());
        assertEquals("Nova Observação", result.getObservation());
        assertEquals(35, product.getQuantity()); // Verifica se o estoque foi atualizado corretamente
        verify(productRepository, times(1)).save(product);
        verify(stockMovementRepository, times(1)).save(any(StockMovement.class));
    }

}