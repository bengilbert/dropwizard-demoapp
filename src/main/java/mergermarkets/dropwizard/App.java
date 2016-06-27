package mergermarkets.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import mergermarkets.resource.CompaniesResource;
import mergermarkets.service.news.NewsService;
import mergermarkets.service.sentiment.SentimentService;
import mergermarkets.service.stockprice.StockPriceService;
import mergermarkets.service.tickercode.TickerCodeService;

public class App extends Application<AppConfig> {

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        super.initialize(bootstrap);
        bootstrap.addBundle(new AssetsBundle("/webapp", "/ui", "index.html"));
    }

    public void run(AppConfig appConfig, Environment environment) throws Exception {

        final TickerCodeService tickerCodeService = new TickerCodeService(appConfig.getTickerCodeMongoDb());
        final StockPriceService stockPriceService = new StockPriceService((appConfig.getStockPriceUrl()));
        final SentimentService sentimentService = new SentimentService();
        final NewsService newsService = new NewsService(sentimentService);
        final CompaniesResource companiesResource = new CompaniesResource(tickerCodeService, stockPriceService, newsService);
        final AppHealthCheck healthCheck = new AppHealthCheck();

        environment.healthChecks().register("healthcheck", healthCheck);
        environment.jersey().register(companiesResource);
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
