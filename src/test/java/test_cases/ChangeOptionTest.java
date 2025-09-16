package test_cases;

import base.BaseTest;
import ibs.amurova.data_provider.ProductTestData;
import ibs.amurova.pages.MainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class ChangeOptionTest extends BaseTest {

    @ParameterizedTest
    @MethodSource("ibs.amurova.data_provider.DataStorage#argsProviderFactory")
    public void changeOptionTest(ProductTestData productTestData) {

        String searchWord = productTestData.getSearchWord();
        String productArticul = productTestData.getProduct1().getArticul();
        String product1 = productTestData.getProduct1().getName();
        String product2 = productTestData.getProduct2().getName();
        String product3 = productTestData.getProduct3().getName();

        List<String> expectedTexts = List.of(product1, product2, product3);

        MainPage mainPage = new MainPage();
        mainPage
                .acceptCookiesAndCloseModals()
                .getTopMenu()
                .searchRequest(searchWord)
                .findProductByArticul(productArticul)
                .checkProductPageOpen(product1)
                .checkArticul(productArticul)
                .setColor("Графит")
                .clickInCartButton()


        ;
    }


}
