package com.empresa.filemanager.csv;

import technology.tabula.*;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvExtractor {
    private final SpreadsheetExtractionAlgorithm extractor;

    private CsvExtractor(Builder builder) {
        this.extractor = builder.extractor;
    }

    public List<Table> extractTablesFromPdf(String pdfPath) throws IOException {
        List<Table> tables = new ArrayList<>();
        try (PDDocument doc = PDDocument.load(new File(pdfPath))) {
            ObjectExtractor oe = new ObjectExtractor(doc);
            PageIterator pages = oe.extract();

            while (pages.hasNext()) {
                Page page = pages.next();
                tables.addAll(extractor.extract(page));
            }
        }
        return tables;
    }

    public void saveTablesToCsv(List<Table> tables, String outputPath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputPath),
                ';',
                CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {

            boolean cabecalhoEscrito = false;
            String[] modeloCabecalho = null;

            for (Table tabela : tables) {
                List<List<RectangularTextContainer>> linhas = tabela.getRows();

                for (List<RectangularTextContainer> linha : linhas) {
                    String[] valores = new String[linha.size()];

                    for (int i = 0; i < linha.size(); i++) {
                        RectangularTextContainer celula = linha.get(i);
                        valores[i] = (celula != null) ? celula.getText()
                                .replace("\n", " ")
                                .replace("\r", " ")
                                .trim()
                                .replaceAll(" +", " ")
                                : "";
                    }

                    // Verifica se é cabeçalho (texto em maiúsculas e sem valores vazios)
                    boolean isCabecalho = Arrays.stream(valores)
                            .allMatch(v -> !v.isEmpty() && v.matches(".*[A-ZÁ-Ú].*"));

                    // Se for cabeçalho e ainda não foi escrito
                    if (isCabecalho && !cabecalhoEscrito) {
                        writer.writeNext(valores);
                        modeloCabecalho = valores;
                        cabecalhoEscrito = true;
                    }
                    // Se não for cabeçalho ou se for cabeçalho mas já foi escrito
                    else if (!isCabecalho || (isCabecalho && cabecalhoEscrito && !Arrays.equals(valores, modeloCabecalho))) {
                        writer.writeNext(valores);
                    }
                }
            }
        }
    }

    private String cleanText(String text) {
        return text.replace("\n", " ")
                .replace("\r", " ")
                .trim()
                .replaceAll(" +", " ");
    }

    private boolean isHeaderRow(String[] values) {
        return Arrays.stream(values)
                .filter(v -> v.matches(".*[A-ZÁ-Ú]{3,}.*"))
                .count() >= 3;
    }

    public static class Builder {
        private SpreadsheetExtractionAlgorithm extractor = new SpreadsheetExtractionAlgorithm();

        public Builder withExtractor(SpreadsheetExtractionAlgorithm extractor) {
            this.extractor = extractor;
            return this;
        }

        public CsvExtractor build() {
            return new CsvExtractor(this);
        }
    }
}