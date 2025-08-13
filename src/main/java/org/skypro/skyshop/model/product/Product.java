package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;
import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id;
    private final String name;

    public Product(UUID id, String name) {
        this.id = id;
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название товара отсутствует!");
        }
        this.name = name;
    }

    public String getName() {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название товара отсутствует!");
        }
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @JsonIgnore
    @Override
    public String searchTerm() {
        return name;
    }

    @JsonIgnore
    @Override
    public String getSearchTypContent() {
        return "PRODUCT";
    }

    @Override
    public String getNameSearchable() {
        return name;
    }

    @Override
    public String getStringRepresentation() {
        return Searchable.super.getStringRepresentation();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public UUID getId() {
        return id;
    }
}
