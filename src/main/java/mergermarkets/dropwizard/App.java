package mergermarkets.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import mergermarkets.resource.CompaniesResource;

public class App extends Application<AppConfig> {
    public void run(AppConfig appConfig, Environment environment) throws Exception {
        final CompaniesResource companiesResource = new CompaniesResource();
        final AppHealthCheck healthCheck = new AppHealthCheck();

        environment.healthChecks().register("healthcheck", healthCheck);
        environment.jersey().register(companiesResource);
    }
}
