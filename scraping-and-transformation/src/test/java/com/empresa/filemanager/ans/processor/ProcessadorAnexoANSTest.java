package com.empresa.filemanager.ans.processor;

import com.empresa.filemanager.ans.processor.ProcessadorAnexosANS;
import com.empresa.filemanager.compression.FileCompressor;
import com.empresa.filemanager.csv.CsvExtractor;
import com.empresa.filemanager.download.PdfDownloader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessadorAnexoANSTest {

    @Mock
    private PdfDownloader pdfDownloader;
    @Mock private CsvExtractor csvExtractor;
    @Mock private FileCompressor fileCompressor;

    @InjectMocks
    private ProcessadorAnexosANS processor;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        when(pdfDownloader.downloadFiles(any(), any())).thenReturn(new String[]{"test.pdf"});

        Table mockTable = mock(Table.class);

        List<RectangularTextContainer> linha1 = List.of(
                mock(RectangularTextContainer.class),
                mock(RectangularTextContainer.class)
        );

        List<RectangularTextContainer> linha2 = List.of(
                mock(RectangularTextContainer.class),
                mock(RectangularTextContainer.class)
        );

        when(mockTable.getRows()).thenReturn(List.of(linha1, linha2));

        when(csvExtractor.extractTablesFromPdf(anyString())).thenReturn(List.of(mockTable));
    }

    @Test
    void testProcessarAnexoIComSucesso() throws Exception {
        Path testFile = tempDir.resolve("test.pdf");
        Files.copy(
                getClass().getResourceAsStream("/testfiles/sample.pdf"),
                testFile
        );

        assertDoesNotThrow(() -> processor.processarAnexoI(testFile.toString()));

        verify(csvExtractor).extractTablesFromPdf(testFile.toString());
        verify(csvExtractor).saveTablesToCsv(anyList(), anyString());
        verify(fileCompressor).compressFiles(any(), anyString());
    }
}