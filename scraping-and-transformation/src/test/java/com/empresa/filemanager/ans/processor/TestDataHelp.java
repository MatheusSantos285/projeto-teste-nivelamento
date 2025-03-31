package com.empresa.filemanager.ans.processor;

import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.mockito.Mockito.*;

public class TestDataHelp {

    public static Table createMockTable() {
        Table table = mock(Table.class);

        RectangularTextContainer[] cells = new RectangularTextContainer[]{
                createMockCell("CODIGO"),
                createMockCell("PROCEDIMENTO"),
                createMockCell("OD"),
                createMockCell("AMB")
        };

        when(table.getRows()).thenReturn(List.of(List.of(cells)));
        return table;
    }

    private static RectangularTextContainer createMockCell(String text) {
        RectangularTextContainer cell = mock(RectangularTextContainer.class);
        when(cell.getText()).thenReturn(text);
        return cell;
    }

    public static Path createSamplePdf(Path directory) throws IOException {
        Path pdfPath = directory.resolve("sample.pdf");
        Files.copy(
                TestDataHelp.class.getResourceAsStream("/testfiles/sample.pdf"),
                pdfPath
        );
        return pdfPath;
    }
}