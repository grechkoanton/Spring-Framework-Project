package org.skypro.skyshop.controller;

import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.error.ShopError;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> generateExceptionHandler (NoSuchProductException e) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404))
                .body(new ShopError("Продукт не найден <ShopError> с HTTP кодом 404", e.getMessage()));
    }
}
