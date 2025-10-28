package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.exceptions.FormatoArquivoInvalidoException;
import br.com.lottus.library.application.ports.command.EnfileirarProcessamentoArquivoCommand;
import br.com.lottus.library.application.ports.in.EnfileirarProcessamentoArquivoUseCase;
import br.com.lottus.library.infrastructure.web.dto.UploadResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Tag(name = "Upload", description = "Endpoint para o upload de arquivos")
@RestController
@RequestMapping("/upload")
public class UploadController {

    private final EnfileirarProcessamentoArquivoUseCase useCase;
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".csv", ".xlsx");

    public UploadController(EnfileirarProcessamentoArquivoUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<UploadResponse> uploadArquivo(
            @RequestParam("arquivo") MultipartFile arquivo,
            @RequestParam("finalidade") String finalidade,
            @RequestHeader("Authorization") String authorizationHeader) {

        validarFormatoArquivo(arquivo.getOriginalFilename());
        String token = extrairToken(authorizationHeader);

        EnfileirarProcessamentoArquivoCommand command = new EnfileirarProcessamentoArquivoCommand(
                arquivo,
                finalidade,
                token
        );

        useCase.executar(command);

        UploadResponse response = new UploadResponse("Upload recebido e processamento enfileirado com sucesso.");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    private void validarFormatoArquivo(String filename) {
        if (filename == null || filename.lastIndexOf('.') == -1) {
            throw new FormatoArquivoInvalidoException("Formato de arquivo inválido. Use CSV ou XLSX.");
        }
        String extension = filename.substring(filename.lastIndexOf('.')).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new FormatoArquivoInvalidoException("Formato de arquivo inválido. Use CSV ou XLSX.");
        }
    }

    private String extrairToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        throw new SecurityException("Token JWT ausente ou inválido.");
    }
}
