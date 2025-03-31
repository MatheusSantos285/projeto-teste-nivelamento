package com.empresa.filemanager.ans.processor;

import com.empresa.filemanager.ans.processor.ProcessadorAnexosANS;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessadorAnexoANSIntegrationTest {

    private ProcessadorAnexosANS processor;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        processor = new ProcessadorAnexosANS();

        Path testPdf = tempDir.resolve("valid.pdf");
        try (PDDocument document = new PDDocument()) {
            document.addPage(new PDPage());
            document.save(testPdf.toFile());
        }
    }

    @Test
    void testFluxoCompletoComArquivoReal() throws Exception {
        Path testPdf = tempDir.resolve("valid.pdf");

        assertDoesNotThrow(() -> processor.processarAnexoI(testPdf.toString()));

        Path outputDir = Path.of("anexos/processados");
        assertTrue(Files.exists(outputDir.resolve("valid.csv")));
        assertTrue(Files.exists(outputDir.resolve("Teste_{Matheus_Felipe_Alves_Santos}.zip")));
    }
}