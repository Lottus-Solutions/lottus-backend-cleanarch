package br.com.lottus.library.application.ports.out;

import org.springframework.web.multipart.MultipartFile;

public interface FileStoragePort {
    String save(MultipartFile file);
}
