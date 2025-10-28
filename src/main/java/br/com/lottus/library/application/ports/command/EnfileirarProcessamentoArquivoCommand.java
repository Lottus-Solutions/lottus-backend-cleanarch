package br.com.lottus.library.application.ports.command;

import org.springframework.web.multipart.MultipartFile;

public record EnfileirarProcessamentoArquivoCommand(
        MultipartFile arquivo,
        String finalidade,
        String token
) {
}
