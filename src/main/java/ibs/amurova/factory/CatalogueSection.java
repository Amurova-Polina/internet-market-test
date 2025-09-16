package ibs.amurova.factory;

import lombok.Getter;

import java.util.List;

@Getter
public enum CatalogueSection {
    WATERPROOFING("Гидроизоляция ", List.of("Профилированные мембраны", "Рулонная гидроизоляция", "Битумная гидроизоляция")),
    LUMBER("Пиломатериалы", List.of("Доски", "Брус")),
    CONSUMABLES("Расходные материалы для стройки", List.of("Мешки для мусора", "Коробки, пакеты, сумки")),
    HOUSEHOLD_GOODS("Хозяйственные товары", List.of("Мыло и антисептики", "Туалетная бумага, салфетки")),
    GARDEN_EQUIPMENT("Садовая техника", List.of("Газонокосилки", "Триммеры (мотокосы) садовые"));

    private final String value;
    private final List<String> units;

    CatalogueSection(String value, List<String> unit) {
        this.value = value;
        this.units = unit;
    }

    public String getUnit(String value) {
        return units.stream()
                .filter(s -> s.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Подраздела: " + value + "не существует"));
    }

}
