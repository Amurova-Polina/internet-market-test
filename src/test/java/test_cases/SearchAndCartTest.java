package test_cases;

import base.BaseTest;
import ibs.amurova.data_provider.ProductTestData;
import ibs.amurova.pages.MainPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class SearchAndCartTest extends BaseTest {

    @Tag("1")
    @ParameterizedTest
    @MethodSource("ibs.amurova.data_provider.DataStorage#argsProviderFactory")
    public void searchAndCartTest(ProductTestData productTestData) {

        List<String> expectedTexts = List.of(
                productTestData.getProduct1().getName(),
                productTestData.getProduct2().getName(),
                productTestData.getProduct3().getName()
        );

        MainPage mainPage = new MainPage();
        mainPage
                .acceptCookiesAndCloseModals()
                .getHeader()
                .chooseCity(productTestData.getCity(), MainPage.class)
                .acceptCookiesAndCloseModals()
                .getTopMenu()
                .goToCatalogue()
                .clickCatalogueSection(productTestData.getSection())
                .clickSubsubsection(productTestData.getSubsection(), productTestData.getUnit())
                .setFilter(productTestData.getFilterOption(), productTestData.getFilterCondition())
                .checkPriceFilterResults(productTestData.getFilterCondition(), productTestData.getPriceType())
                .getTopMenu()
                .searchRequest(productTestData.getSearchWord())
                .findProductByArticul(productTestData.getProduct1().getArticul())
                .checkProductPageOpen(productTestData.getProduct1().getName())
                .checkArticul(productTestData.getProduct1().getArticul())
                .clickInCartButton()
                .checkRedCounterIcon("1")
                .getTopMenu()
                .searchRequest(productTestData.getProduct1().getName())
                .assertResultIsSingle()
                .getTopMenu()
                .searchRequest(productTestData.getProduct2().getName())
                .clickProductByName(productTestData.getProduct2().getName())
                .checkProductPageOpen(productTestData.getProduct2().getName())
                .clickInCartButton()
                .checkRedCounterIcon("2")
                .getTopMenu()
                .goToCart()
                .checkAmountInCart(2)
                .addItemFromSection(productTestData.getProduct3().getName(), "Вам может понадобиться")
                .checkAmountInCart(3)
                .checkItemsName(expectedTexts)
                .checkSummNaturalPerson()
                .checkSummLegalEntity()
                .clearCart()
                .checkCartIsEmpty();
    }
}