package ibs.amurova.data_provider;

import ibs.amurova.factory.CatalogueSection;
import ibs.amurova.pages.common.CatalogueCategory;
import ibs.amurova.pages.common.FilterCondition;
import ibs.amurova.pages.common.FilterOption;
import ibs.amurova.pages.common.PriceType;

import java.util.stream.Stream;

public class DataStorage {
    public static Stream<ProductTestData> argsProviderFactory() {
        return Stream.of(
                ProductTestData.builder()
                        .city("Москва")
                        .searchWord("Плитка")
                        .product1(
                                ProductTestData.Product.builder()
                                        .articul("207863")
                                        .name("Плитка настенная Kerabel Зоопарк, салатовая, 200х400х7,5 мм (пр-во БКСМ)")
                                        .build()
                        )
                        .product2(
                                ProductTestData.Product.builder()
                                        .name("Пленка защитная для ремонтных работ Biber (4х5 м)")
                                        .build()
                        )
                        .product3(
                                ProductTestData.Product.builder()
                                        .name("Перчатки вязаные х/б, 5 ниток с двусторонним ПВХ")
                                        .build()
                        )
                        .section(CatalogueCategory.HOME_AND_GARDEN)
                        .subsection(CatalogueSection.HOUSEHOLD_GOODS)
                        .unit(CatalogueSection.HOUSEHOLD_GOODS.getUnit("Мыло и антисептики"))
                        .filterOption(FilterOption.PRICE)
                        .filterCondition(FilterCondition.ASCENDING)
                        .priceType(PriceType.DISCOUNT)
                        .build(),

                ProductTestData.builder()
                        .city("Уфа")
                        .searchWord("Коврик для ванной")
                        .product1(
                                ProductTestData.Product.builder()
                                        .articul("220991")
                                        .name("Коврик для ванной, серый, 600х900 мм")
                                        .build()
                        )
                        .product2(
                                ProductTestData.Product.builder()
                                        .name("Тумба для ванной комнаты SKS Енисей 40, 400х500х211 мм")
                                        .build()
                        )
                        .product3(
                                ProductTestData.Product.builder()
                                        .name("Мыло жидкое (5 л)")
                                        .build()
                        )
                        .section(CatalogueCategory.BUILDING_MATERIALS)
                        .subsection(CatalogueSection.CONSUMABLES)
                        .unit(CatalogueSection.CONSUMABLES.getUnit("Коробки, пакеты, сумки"))
                        .filterOption(FilterOption.PRICE)
                        .filterCondition(FilterCondition.DESCENDING)
                        .priceType(PriceType.REGULAR)
                        .build()
        );
    }
}
