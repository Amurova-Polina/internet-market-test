package ibs.amurova.pages.common;

import ibs.amurova.factory.CatalogueSection;
import lombok.Getter;

import java.util.List;

import static ibs.amurova.factory.CatalogueSection.*;

@Getter
public enum CatalogueCategory {
    BUILDING_MATERIALS("Стройматериалы", List.of(WATERPROOFING, LUMBER, CONSUMABLES)),
    HOME_AND_GARDEN("Дом и сад", List.of(HOUSEHOLD_GOODS, GARDEN_EQUIPMENT));


    private final String value;
    private final List<CatalogueSection> sectionList;

    CatalogueCategory(String value, List<CatalogueSection> subsectionList) {
        this.value = value;
        this.sectionList = subsectionList;
    }

}
