package net.kosto.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MockerRulesConfig {

  private List<String> rules = new ArrayList<>();

  // TODO: Here we init rules. It does not matter from what place we will upload them.
  private void init() {
    // Please check http://wiremock.org/docs/stubbing/
    // It contains description how to create rules.
    String rule;
    // Simple rule
    rule = "{" +
        "  \"request\": {" +
        "    \"method\": \"GET\"," +
        "    \"url\": \"/some/thing\"" +
        "  }," +
        "  \"response\": {" +
        "    \"status\": 200," +
        "    \"body\": \"Hello world!\"," +
        "    \"headers\": {" +
        "      \"Content-Type\": \"text/plain\"" +
        "    }" +
        "  }" +
        "}";
    rules.add(rule);
    // Scenario rules
    rule = "{" +
        "    \"scenarioName\": \"To do list\"," +
        "    \"requiredScenarioState\": \"Started\"," +
        "    \"request\": {" +
        "        \"method\": \"GET\"," +
        "        \"url\": \"/todo/items\"" +
        "    }," +
        "    \"response\": {" +
        "        \"status\": 200," +
        "        \"body\" : \"<items><item>Buy milk</item></items>\"" +
        "    }" +
        "}";
    rules.add(rule);
    rule = "{" +
        "    \"scenarioName\": \"To do list\"," +
        "    \"requiredScenarioState\": \"Started\"," +
        "    \"newScenarioState\": \"Cancel newspaper item added\"," +
        "    \"request\": {" +
        "        \"method\": \"POST\"," +
        "        \"url\": \"/todo/items\"," +
        "        \"bodyPatterns\": [" +
        "            { \"contains\": \"Cancel newspaper subscription\" }" +
        "         ]" +
        "    }," +
        "    \"response\": {" +
        "        \"status\": 201" +
        "    }" +
        "}";
    rules.add(rule);
    rule = "{" +
        "    \"scenarioName\": \"To do list\"," +
        "    \"requiredScenarioState\": \"Cancel newspaper item added\"," +
        "    \"request\": {" +
        "        \"method\": \"GET\"," +
        "        \"url\": \"/todo/items\"" +
        "    }," +
        "    \"response\": {" +
        "        \"status\": 200," +
        "        \"body\" : \"<items><item>Buy milk</item><item>Cancel newspaper subscription</item></items>\"" +
        "    }" +
        "}";
    rules.add(rule);
  }

  @Bean
  public List<String> getRules() {
    init();
    return rules;
  }
}