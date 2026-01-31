package sg.edu.nus.iss.d13revision.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DataController {
    @GetMapping("/")
    public String healthCheck() {
        log.info("Health check endpoint called");
        return "HEALTH CHECK OK!";
    }

    @GetMapping("/version")
    public String version() {
        log.info("Version endpoint called");
        return "The actual version is 1.0.0";
    }

    @GetMapping("/nations")
    public JsonNode getRandomNations() {
        log.info("Nations endpoint called");
        var objectMapper = new ObjectMapper();
        var faker = new Faker();
        var nations = objectMapper.createArrayNode();
        for (var i = 0; i < 10; i++) {
            var nation = faker.nation();
            nations.add(objectMapper.createObjectNode()
                    .put("nationality", nation.nationality())
                    .put("capitalCity", nation.capitalCity())
                    .put("flag", nation.flag())
                    .put("language", nation.language()));
        }
        return nations;
    }

    @GetMapping("/currencies")
    public JsonNode getRandomCurrencies() {
        log.info("Currencies endpoint called");
        var objectMapper = new ObjectMapper();
        var faker = new Faker();
        var currencies = objectMapper.createArrayNode();
        for (var i = 0; i < 20; i++) {
            var money = faker.money();
            currencies.add(objectMapper.createObjectNode()
                    .put("name", money.currency())
                    .put("code", money.currencyCode()));
        }
        return currencies;

    }

}
