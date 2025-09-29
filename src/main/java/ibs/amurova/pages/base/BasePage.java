package ibs.amurova.pages.base;

import ibs.amurova.pages.common.Header;
import ibs.amurova.pages.common.TopMenu;
import lombok.Getter;

@Getter
public abstract class BasePage {
    private final TopMenu topMenu = new TopMenu();
    private final Header header = new Header();

}
