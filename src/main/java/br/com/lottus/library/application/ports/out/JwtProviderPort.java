package br.com.lottus.library.application.ports.out;

import java.time.Duration;

public interface JwtProviderPort {
    String generateToken(String email);
    String getEmailFromToken(String token);
    String getJTIFromToken(String token); // JWT ID
    boolean isTokenValid(String token, String email);
    Duration getExpirationTime(String token);
}
