package ibs.amurova.pages.common;

import lombok.Getter;

public enum FilterOption {
    POPULARITY("popular"),
    PRICE("price"),
    NAME("name"),
    RATING("rating"),
    RELEVANCE("relev");

    @Getter
    private final String value;

    FilterOption(String value) {
        this.value = value;
    }
}
