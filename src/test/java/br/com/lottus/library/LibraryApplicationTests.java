package br.com.lottus.library;

import br.com.lottus.library.infrastructure.storage.LocalFileStorageAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class LibraryApplicationTests {

    @MockitoBean
    private LocalFileStorageAdapter localFileStorageAdapter;

    @Test
    void contextLoads() {
    }

}
