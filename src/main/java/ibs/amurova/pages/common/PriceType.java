package ibs.amurova.pages.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PriceType {
    DISCOUNT(".//div[contains(@class, 'goods_card_price_discount_value')]//span[@data-price]"),
    REGULAR(".//div[contains(@class, 'goods_card_price_value')]//span[@data-price]");

    private final String xpath;
}
