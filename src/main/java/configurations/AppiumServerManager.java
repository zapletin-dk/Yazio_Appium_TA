package configurations;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class AppiumServerManager {
    private static final ThreadLocal<Map<Integer, AppiumDriverLocalService>> services =
            ThreadLocal.withInitial(HashMap::new);
    private AppiumDriverLocalService service;

    public int startServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingAnyFreePort()
                .withTimeout(Duration.ofSeconds(60));

        AppiumDriverLocalService service = builder.build();
        service.start();

        int actualPort = service.getUrl().getPort();
        services.get().put(actualPort, service);

        return actualPort;
    }

    public void stopServer(int port) {
        Map<Integer, AppiumDriverLocalService> localServices = services.get();
        AppiumDriverLocalService service = localServices.get(port);
        if (service != null && service.isRunning()) {
            service.stop();
            localServices.remove(port);
        }
    }

    public void stopAllServers() {
        Map<Integer, AppiumDriverLocalService> localServices = services.get();
        localServices.values().forEach(s -> {
            if (s != null && s.isRunning()) {
                s.stop();
            }
        });
        localServices.clear();
    }
}

