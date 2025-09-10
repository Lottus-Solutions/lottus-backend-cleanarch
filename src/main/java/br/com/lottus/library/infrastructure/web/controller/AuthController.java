package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.ports.in.CadastrarUsuarioUseCase;
import br.com.lottus.library.application.ports.in.LoginUseCase;
import br.com.lottus.library.infrastructure.web.command.LoginRequest;
import br.com.lottus.library.infrastructure.web.command.LoginResponse;
import br.com.lottus.library.infrastructure.web.command.RegisterUserRequest;
import br.com.lottus.library.infrastructure.web.command.RegisterUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Autenticação", description = "Endpoint para cadastro e autenticação de usuários")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;
    private final LoginUseCase loginUseCase;

    public AuthController(CadastrarUsuarioUseCase cadastrarUsuarioUseCase, LoginUseCase loginUseCase) {
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
        this.loginUseCase = loginUseCase;
    }

    @Operation(summary = "Registra um novo usuário", description = "Retorna o usuário cadastrado")
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> registrarUsuario(@RequestBody @Valid RegisterUserRequest request){
        return ResponseEntity.ok(RegisterUserResponse.of(
                cadastrarUsuarioUseCase.executar(request.toCommand())));
    }

    @Operation(summary = "Realiza login do usuário", description = "Retorna o token de sessão do usuário")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        var token = loginUseCase.execute(request.toCommand());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
