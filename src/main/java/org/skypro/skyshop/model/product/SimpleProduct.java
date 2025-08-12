package org.skypro.skyshop.model.product;

import java.util.Objects;
import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(UUID id, String name, int price) {
        super(id, name);
        if (price <= 0) {
            throw new IllegalArgumentException("Стоимость товара отрицательная!");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimpleProduct that = (SimpleProduct) o;
        return price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price);
    }

    @Override
    public String toString() {
        return "Название товара: " + name + ". Стоимость: " + price + " руб.";
    }

    @Override
    public String searchTerm() {
        return super.searchTerm();
    }

    @Override
    public String getSearchTypContent() {
        return super.getSearchTypContent();
    }

    @Override
    public String getNameSearchable() {
        return super.getNameSearchable();
    }

    @Override
    public String getStringRepresentation() {
        return super.getStringRepresentation();
    }
}
