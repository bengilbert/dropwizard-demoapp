package mergermarkets.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import mergermarkets.resource.CompaniesResource;
import mergermarkets.service.NewsService;
import mergermarkets.service.StockPriceService;
import mergermarkets.service.TickerCodeService;

public class App extends Application<AppConfig> {
    public void run(AppConfig appConfig, Environment environment) throws Exception {

        final TickerCodeService tickerCodeService = new TickerCodeService(appConfig.getTickerCodeMongoDb());
        final StockPriceService stockPriceService = new StockPriceService((appConfig.getStockPriceUrl()));
        final NewsService newsService = new NewsService();
        final CompaniesResource companiesResource = new CompaniesResource(tickerCodeService, stockPriceService, newsService);
        final AppHealthCheck healthCheck = new AppHealthCheck();

        environment.healthChecks().register("healthcheck", healthCheck);
        environment.jersey().register(companiesResource);
    }
}
