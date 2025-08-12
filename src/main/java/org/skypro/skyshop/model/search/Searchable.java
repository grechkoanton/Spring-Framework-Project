package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {

    String searchTerm();

    String getSearchTypContent();

    String getNameSearchable();

    default String getStringRepresentation() {
        return getNameSearchable() + getSearchTypContent();
    }

    UUID getId();
}
