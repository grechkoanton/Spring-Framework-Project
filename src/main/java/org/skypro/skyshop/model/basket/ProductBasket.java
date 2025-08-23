package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import java.util.*;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> products = new HashMap<>();

    public void addProductToBasket(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Product ID не может быть null");
        }
        products.put(id, products.getOrDefault(id, 0) + 1);
    }

    public Map<UUID, Integer> getAllProductsBasket() {
        return Collections.unmodifiableMap(products);
    }
}
