package mergermarkets.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import mergermarkets.TickerCodeService;
import mergermarkets.resource.CompaniesResource;

public class App extends Application<AppConfig> {
    public void run(AppConfig appConfig, Environment environment) throws Exception {

        final TickerCodeService tickerCodeService = new TickerCodeService(appConfig.getTickerCodeMongoDb());
        final CompaniesResource companiesResource = new CompaniesResource(tickerCodeService);
        final AppHealthCheck healthCheck = new AppHealthCheck();

        environment.healthChecks().register("healthcheck", healthCheck);
        environment.jersey().register(companiesResource);
    }
}
