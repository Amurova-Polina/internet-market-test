package ibs.amurova.data_provider;

import ibs.amurova.factory.CatalogueSection;
import ibs.amurova.pages.common.CatalogueCategory;
import ibs.amurova.pages.common.FilterCondition;
import ibs.amurova.pages.common.FilterOption;
import ibs.amurova.pages.common.PriceType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductTestData {
    private String city;
    private String searchWord;
    private Product product1;
    private Product product2;
    private Product product3;

    private CatalogueCategory section;
    private CatalogueSection subsection;
    private String unit;
    private FilterOption filterOption;
    private FilterCondition filterCondition;
    private PriceType priceType;

    @Getter
    @Builder
    public static class Product {
        private String articul;
        private String name;
    }
}
