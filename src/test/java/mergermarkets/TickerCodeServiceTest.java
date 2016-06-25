package mergermarkets;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class TickerCodeServiceTest {

    @Test
    public void canGetCompanyDetailsForTickerCode() {
        TickerCodeService tickerCodeService = new TickerCodeService();
        String companyName = tickerCodeService.getCompanyName("GOOG");

        assertThat(companyName, is("Google Inc"));
    }

}