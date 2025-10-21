package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.LogoutUseCase;
import br.com.lottus.library.application.ports.out.JwtProviderPort;
import br.com.lottus.library.application.ports.out.TokenBlocklistPort;

public class LogoutUseCaseImpl implements LogoutUseCase {
    private final JwtProviderPort jwtProvider;
    private final TokenBlocklistPort tokenBlocklist;

    public LogoutUseCaseImpl(JwtProviderPort jwtProvider, TokenBlocklistPort tokenBlocklist) {
        this.jwtProvider = jwtProvider;
        this.tokenBlocklist = tokenBlocklist;
    }

    @Override
    public void execute(String token) {
        String jit = jwtProvider.getJTIFromToken(token);
        tokenBlocklist.addToBlockList(jit, jwtProvider.getExpirationTime(token));
    }
}
