package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.ports.command.EditarUsuarioCommand;
import br.com.lottus.library.application.ports.in.BuscarUsuarioLogadoUseCase;
import br.com.lottus.library.application.ports.in.EditarUsuarioUseCase;
import br.com.lottus.library.domain.entities.Usuario;
import br.com.lottus.library.infrastructure.web.command.EditarUsuarioRequest;
import br.com.lottus.library.infrastructure.web.command.EditarUsuarioResponse;
import br.com.lottus.library.infrastructure.web.command.MeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    private final BuscarUsuarioLogadoUseCase buscarUsuarioLogadoUseCase;
    private final EditarUsuarioUseCase editarUsuarioUseCase;

    public UsuarioController(BuscarUsuarioLogadoUseCase buscarUsuarioLogadoUseCase, EditarUsuarioUseCase editarUsuarioUseCase) {
        this.buscarUsuarioLogadoUseCase = buscarUsuarioLogadoUseCase;
        this.editarUsuarioUseCase = editarUsuarioUseCase;
    }

    @Operation(summary = "Obtém os dados do usuário logado", description = "Retorna as informações do usuário com exceção da senha")
    @GetMapping("/me")
    public ResponseEntity<MeResponse> getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = buscarUsuarioLogadoUseCase.execute(email);

        return ResponseEntity.ok(MeResponse.of(usuario));
    }

    @Operation(summary = "Edita os dados do usuário", description = "Retorna o nome e email do usuário atualizados")
    @PutMapping("/{id}")
    public ResponseEntity<EditarUsuarioResponse> editarUsuario(@PathVariable Long id, @RequestBody @Valid EditarUsuarioRequest request) {
        EditarUsuarioCommand command = new EditarUsuarioCommand(id, request.nome(), request.idAvatar());

        Usuario usuarioAtualizado = editarUsuarioUseCase.execute(command);

        return ResponseEntity.ok(EditarUsuarioResponse.of(usuarioAtualizado));
    }
}