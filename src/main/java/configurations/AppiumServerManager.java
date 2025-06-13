package configurations;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.time.Duration;

public class AppiumServerManager {
    private AppiumDriverLocalService service;

    public void startServer(String os) {

        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withTimeout(Duration.ofSeconds(60));

        service = builder.build();
        service.start();
    }

    public void stopServer() {
        if (service != null) {
            service.stop();
        }
    }
}
