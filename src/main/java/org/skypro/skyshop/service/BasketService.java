package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException(id + "ID  товара не может быть null");
        }
        storageService.getProductById(id)
                .ifPresentOrElse(
                        product -> productBasket.addProductToBasket(id),
                        () -> {
                            throw new IllegalArgumentException("Товар с ID " + id + " не найден");
                        }
                );
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketItems = productBasket.getAllProductsBasket();
        List<BasketItem> items = basketItems.entrySet().stream()
                .map(entry -> storageService.getProductById(entry.getKey())
                        .map(product -> new BasketItem(product, entry.getValue()))
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new UserBasket(items);
    }
}
