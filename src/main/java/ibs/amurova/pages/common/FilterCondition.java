package ibs.amurova.pages.common;

import lombok.Getter;

@Getter
public enum FilterCondition {
    ASCENDING("По возрастанию", "asc"),
    DESCENDING("По убыванию", "desc");

    private String condition;
    private String elementAttribute;

    FilterCondition(String condition, String elementAttribute) {
        this.condition = condition;
        this.elementAttribute = elementAttribute;
    }
}
