package br.com.lottus.library.application.ports.out;

import java.time.Duration;

public interface TokenBlocklistPort {
    void addToBlockList(String jti, Duration ttl);
    boolean isBlocked(String jti);
}
