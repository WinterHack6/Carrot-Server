package carrot.market.model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public String checkHealth() {
        return "healthy";
    }

    @GetMapping("/test")
    public String cicdTest() {
        return "OK";
    }
}
