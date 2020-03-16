package net.kosto.configs;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockerServerConfig {

  @Value("${mocker.server.port}")
  private Integer port;

  /**
   * WireMock server configuration.
   * <p>
   * Possible options:
   * <li>port</li>
   * <li>httpsPort</li>
   * <li>fileSource</li>
   * <li>enableBrowserProxying</li>
   * <li>proxyVia</li>
   * <li>notifier</li>
   *
   * @return WireMock Server.
   * @see WireMockConfiguration
   */
  @Bean
  public WireMockServer wireMockServer() {
    WireMockConfiguration config = new WireMockConfiguration();
    config.port(port);
    return new WireMockServer(config);
  }
}