package br.com.lottus.library.application.ports.in;

public interface LoginUseCase {
    String execute(String email, String senha);
}
