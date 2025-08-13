package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;
import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final UUID id;
    private final String nameArticle;
    private final String textArticle;

    public Article(UUID id, String nameArticle, String textArticle) {
        this.id = id;
        this.nameArticle = nameArticle;
        this.textArticle = textArticle;
    }

    @Override
    public String toString() {
        return "Название статьи: " + nameArticle + ". Текст статьи: " + textArticle;
    }

    public String getNameArticle() {
        return nameArticle;
    }

    public String getTextArticle() {
        return textArticle;
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return toString();
    }

    @Override
    public String getNameSearchable() {
        return nameArticle;
    }

    @JsonIgnore
    @Override
    public String getSearchTypContent() {
        return "ARTICLE";
    }

    @Override
    public String getStringRepresentation() {
        return Searchable.super.getStringRepresentation();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(nameArticle, article.nameArticle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nameArticle);
    }

    @Override
    public UUID getId() {
        return id;
    }
}
