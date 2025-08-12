package org.skypro.skyshop.model.product;

import java.util.Objects;
import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basicPrice;
    private int discount;

    public DiscountedProduct(UUID id, String name, int basicPrice, int discount) {
        super(id, name);
        if (basicPrice <= 0) {
            throw new IllegalArgumentException("Стоимость товара отрицательная!");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Такой скидки не существует!");
        }
        this.basicPrice = basicPrice;
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return (int) basicPrice - (basicPrice * discount) / 100;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountedProduct that = (DiscountedProduct) o;
        return basicPrice == that.basicPrice && discount == that.discount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), basicPrice, discount);
    }

    @Override
    public String toString() {
        return "Название товара со скидкой: " + name + ". Стоимость: " + basicPrice + " руб." + " (" + discount + " % скидка).";
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
