package org.skypro.skyshop.error;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException(String message) {
        super(message);
    }
}
