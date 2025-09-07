package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test
    public void givenEmptyStorage_whenSearch_thenReturnEmptyList() {
        //given - настраиваем Мок, чтобы вернуть пустой список
        when(storageService.getAllCollectionSearchable())
                .thenReturn(Collections.emptyList());

        //when - вызываем метод поиска
        Collection<SearchResult> result = searchService.search("Яблоки");

        //then - проверяем, что результат пустой
        assertTrue(result.isEmpty());
        verify(storageService, times(1)).getAllCollectionSearchable();
    }

    @Test
    public void givenProductsInStorage_whenSearch_thenReturnNotSuitableProduct() {
        // given
        SimpleProduct product = new SimpleProduct(UUID.randomUUID(), "Апельсины", 200);
        Article article = new Article(UUID.randomUUID(), "Бананы из Африки", "Лучшие бананы в мире");
        // Мок для storageService
        when(storageService.getAllCollectionSearchable())
                .thenReturn(Arrays.asList(product, article));

        //when
        Collection<SearchResult> result = searchService.search("Яблоки");

        //then
        assertTrue(result.isEmpty());
        verify(storageService, times(1)).getAllCollectionSearchable();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Яблоки", "Статья", "Апельсины"})
    public void givenProductsInStorage_whenSearch_thenReturnRealProduct(String searchQuery) {
        // given
        SimpleProduct apples = new SimpleProduct(UUID.randomUUID(), "Яблоки", 150);
        Article appleArticle = new Article(UUID.randomUUID(), "Статья о яблоках", "Все о яблоках");
        SimpleProduct oranges = new SimpleProduct(UUID.randomUUID(), "Апельсины", 200);
        // Мок для storageService
        when(storageService.getAllCollectionSearchable())
                .thenReturn(Arrays.asList(apples, appleArticle, oranges));

        // when
        Collection<SearchResult> result = searchService.search(searchQuery);

        // then
        assertFalse(result.isEmpty());
        verify(storageService, times(1)).getAllCollectionSearchable();
    }
}
