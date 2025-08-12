package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product{
    private static final int FIX_PRICE = 5;

    public FixPriceProduct(UUID id, String name) {
        super(id, name);
    }

    @Override
    public int getPrice() {
        if (FIX_PRICE < 0) {
            throw new IllegalArgumentException("Стоимость товара отрицательная!");
        }
        return FIX_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "Название товара с фиксированной ценой: " + name + ". Фиксированная цена: " + FIX_PRICE + " руб.";
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

