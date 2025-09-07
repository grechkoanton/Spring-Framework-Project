package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    BasketService basketService;

    @Test
    public void givenNonExistentProductId_whenAddProductToBasket_thenThrowNoSuchProductException() {
        // given - несуществующий ID товара
        UUID nonExistentProductId = UUID.randomUUID();
        when(storageService.getProductById(nonExistentProductId))
                .thenReturn(Optional.empty());

        // when и then - попытка добавления и проверка исключения
        assertThrows(NoSuchProductException.class, () ->
                basketService.addProductToBasket(nonExistentProductId)
        );
        // проверяем, что метод получения товара был вызван
        // и проверяем, что метод добавления в корзину не был вызван
        verify(storageService, times(1)).getProductById(nonExistentProductId);
        verifyNoInteractions(productBasket);
    }

    @Test
    public void givenExistentProductId_whenAddProductToBasket_thenCallProductBasketAddMethod() {
        // given
        UUID existentProductId = UUID.randomUUID();
        SimpleProduct product = new SimpleProduct(existentProductId, "Яблоки", 150);

        when(storageService.getProductById(existentProductId))
                .thenReturn(Optional.of(product));

        // when
        basketService.addProductToBasket(existentProductId);

        // then
        verify(productBasket, times(1)).addProductToBasket(existentProductId);
        verify(storageService, times(1)).getProductById(existentProductId);
    }

    @Test
    public void givenEmptyProductBasket_whenGetUserBasket_thenReturnEmptyUserBasket() {
        // given
        when(productBasket.getAllProductsBasket()).thenReturn(Map.of());

        // when
        UserBasket result = basketService.getUserBasket();

        // then
        assertTrue(result.getItems().isEmpty());
        assertEquals(0, result.getTotal());
        verify(productBasket, times(1)).getAllProductsBasket();
        verifyNoInteractions(storageService);
    }

    @Test
    public void givenProductBasketWithItems_whenGetUserBasket_thenReturnFilledUserBasket() {
        // given
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();

        SimpleProduct apple = new SimpleProduct(productId1, "Яблоки", 150);
        SimpleProduct orange = new SimpleProduct(productId2, "Апельсины", 200);
        int totalPriceProducts = apple.getPrice() + orange.getPrice();

        when(productBasket.getAllProductsBasket())
                .thenReturn(Map.of(productId1, 1, productId2, 1));

        when(storageService.getProductById(productId1))
                .thenReturn(Optional.of(apple));

        when(storageService.getProductById(productId2))
                .thenReturn(Optional.of(orange));

        // when
        UserBasket result = basketService.getUserBasket();

        // then
        assertEquals(2, result.getItems().size());
        assertEquals(totalPriceProducts, result.getTotal());
        verify(productBasket, times(1)).getAllProductsBasket();
        verify(storageService, times(2)).getProductById(any(UUID.class));
    }
}
