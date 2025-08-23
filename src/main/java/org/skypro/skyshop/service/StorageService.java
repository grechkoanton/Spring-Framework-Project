package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;
    private final Map<UUID, Product> availableProducts;

    public StorageService() {

        this.productStorage = new HashMap<>();
        this.articleStorage = new HashMap<>();
        createTestData();
        this.availableProducts = new HashMap<>();
        this.availableProducts.putAll(productStorage);
    }

    public Collection<Product> getProducts() {
        return productStorage.values();
    }

    public Collection<Article> getArticles() {
        return articleStorage.values();
    }

    public Collection<Searchable> getAllCollectionSearchable() {
        return Stream.concat(productStorage.values().stream(),
                        articleStorage.values().stream())
                .collect(Collectors.toList());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(availableProducts.get(id));
    }

    private void createTestData() {
        SimpleProduct apple = new SimpleProduct(UUID.randomUUID(),"Яблоки", 150);
        productStorage.put(apple.getId(), apple);
        SimpleProduct orange = new SimpleProduct(UUID.randomUUID(), "Апельсины", 300);
        productStorage.put(orange.getId(), orange);
        SimpleProduct bananas = new SimpleProduct(UUID.randomUUID(),"Бананы", 250);
        productStorage.put(bananas.getId(), bananas);
        DiscountedProduct potato = new DiscountedProduct(UUID.randomUUID(),"Картошка", 500, 10);
        productStorage.put(potato.getId(), potato);
        SimpleProduct grapefruit = new SimpleProduct(UUID.randomUUID(),"Грейпфрут", 300);
        productStorage.put(grapefruit.getId(), grapefruit);
        FixPriceProduct packageForProducts = new FixPriceProduct(UUID.randomUUID(),"Пакет для продуктов");
        productStorage.put(packageForProducts.getId(), packageForProducts);

        SimpleProduct bmvX5 = new SimpleProduct(UUID.randomUUID(),"Автомобиль BMV X5", 12_000_000);
        productStorage.put(bmvX5.getId(), bmvX5);
        DiscountedProduct toyotaLandCruiser = new DiscountedProduct(UUID.randomUUID(),"Автомобиль Toyota land Cruiser", 10_500_000, 15);
        productStorage.put(toyotaLandCruiser.getId(), toyotaLandCruiser);

        DiscountedProduct iphone14Pro = new DiscountedProduct(UUID.randomUUID(),"Iphone 14 Pro", 60_000, 10);
        productStorage.put(iphone14Pro.getId(), iphone14Pro);
        DiscountedProduct iphone15Pro = new DiscountedProduct(UUID.randomUUID(),"Iphone 15 Pro", 70_000, 10);
        productStorage.put(iphone15Pro.getId(), iphone15Pro);
        DiscountedProduct iphone15ProMax = new DiscountedProduct(UUID.randomUUID(), "Iphone 15 Pro Max", 80_000, 10);
        productStorage.put(iphone15ProMax.getId(), iphone15ProMax);
        DiscountedProduct iphone16ProMax = new DiscountedProduct(UUID.randomUUID(),"Iphone 16 Pro Max", 90_000, 10);
        productStorage.put(iphone16ProMax.getId(), iphone16ProMax);
        FixPriceProduct packageSouvenir = new FixPriceProduct(UUID.randomUUID(), "Сувенирный пакет с лейблом IPHONE");
        productStorage.put(packageSouvenir.getId(), packageSouvenir);

        Article article0 = new Article(UUID.randomUUID(),"Картошка из лукошка", "Картошка из деревни с любовью");
        articleStorage.put(article0.getId(), article0);
        Article article1 = new Article(UUID.randomUUID(), "Бананы из Африки", "Лучшие бананы в мире");
        articleStorage.put(article1.getId(), article1);
        Article article2 = new Article(UUID.randomUUID(), "Немецкий автомобиль", "БМВ лучший в своем сегменте");
        articleStorage.put(article2.getId(), article2);
        Article article3 = new Article(UUID.randomUUID(), "Японский автомобиль", "Тойота один из лучших на востоке и не только");
        articleStorage.put(article3.getId(), article3);
        Article article4 = new Article(UUID.randomUUID(), "Iphone Pro Max смартфон", "Iphone Pro Max топовая линейка в компании Apple");
        articleStorage.put(article4.getId(), article4);
    }
}
