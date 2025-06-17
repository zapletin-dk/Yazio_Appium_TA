package configurations;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class AppiumServerManager {
    private AppiumDriverLocalService service;
    private static final Map<String, AppiumDriverLocalService> services = new HashMap<>();

    public void startServer(String os, String port) {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(Integer.parseInt(port))
                .withTimeout(Duration.ofSeconds(60));

        service = builder.build();
        service.start();
        services.put(port, service);
    }

    public void stopServer(String port) {
        AppiumDriverLocalService currentService = services.get(port);
        if (currentService != null && currentService.isRunning()) {
        currentService.stop();
        services.remove(port);
        }
    }
    public void stopAllServers() {
        services.values().forEach(service -> {
            if (service != null && service.isRunning()) {
                service.stop();
            }
        });
        services.clear();
    }


}
