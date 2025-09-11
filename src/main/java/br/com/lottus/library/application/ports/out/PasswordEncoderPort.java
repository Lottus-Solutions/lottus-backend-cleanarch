package br.com.lottus.library.application.ports.out;

public interface PasswordEncoderPort {
    String encode(String password);
    boolean matches(String rawPassword, String encodedPassword);
}
