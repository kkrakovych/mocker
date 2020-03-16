package net.kosto.components;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MockerServerRunner implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(MockerServerRunner.class);

  private WireMockServer server;
  private List<String> rules;

  @Autowired
  public MockerServerRunner(WireMockServer server, List<String> rules) {
    this.server = server;
    this.rules = rules;
  }

  public WireMockServer getServer() {
    return server;
  }

  // TODO: Make sense to shutdown the server by a command - server.stop();
  @Override
  public void run(String... args) throws Exception {
    server.start();

    String url = "http://localhost:" + server.port() + "/__admin/mappings/new";
    RestTemplate restTemplate = new RestTemplate();
    for (String rule : rules) {
      ResponseEntity<String> response = restTemplate.postForEntity(url, rule, String.class);
      HttpStatus status = response.getStatusCode();
      if (HttpStatus.CREATED.equals(status)) {
        logger.info("Rule was created.\nRule: {}", rule);
      } else {
        logger.error("Rule was not created with status {}.\nRule: {}", status, rule);
      }
    }
  }
}