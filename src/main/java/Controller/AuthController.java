package Controller;

import User.UserRole;
import security.jwt.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/token")
    public String getToken() {
        Map<String, Object> claims = Map.of(
                "email", "abc@gmail.com",
                "role", UserRole.HOST.name()
        );
        return jwtService.generateToken(claims, "sub:abc", Duration.ofHours(1));
    }

    @GetMapping("/claims")
    public Claims getClaims(@RequestParam String token) {
        return jwtService.extractClaims(token);
    }
}
