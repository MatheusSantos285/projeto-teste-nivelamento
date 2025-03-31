package com.empresa.filemanager.ans.processor;

import com.empresa.filemanager.ans.model.LegendaAbreviacoes;
import com.empresa.filemanager.compression.FileCompressor;
import com.empresa.filemanager.compression.ZipStrategy;
import com.empresa.filemanager.csv.CsvAbbreviationHandler;
import com.empresa.filemanager.csv.CsvCleaner;
import com.empresa.filemanager.csv.CsvExtractor;
import com.empresa.filemanager.download.PdfDownloader;
import com.empresa.filemanager.exception.ProcessingException;
import technology.tabula.Table;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ProcessadorAnexosANS {
    public static void main(String[] args) {
        try {
            new ProcessadorAnexosANS().processar();
            System.out.println("Processo concluído com sucesso!");
        } catch (ProcessingException e) {
            System.err.println("Erro no processamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void processar() throws ProcessingException {
        String[] arquivosBaixados = baixarAnexos();

        compactarAnexos(arquivosBaixados);

        processarAnexoI(arquivosBaixados[0]);
    }

    protected String[] baixarAnexos() throws ProcessingException {
        try {
            Path dirBaixados = Paths.get("anexos/baixados");
            if (!Files.exists(dirBaixados)) {
                Files.createDirectories(dirBaixados);
                System.out.println("Diretório criado: " + dirBaixados.toAbsolutePath());
            }

            PdfDownloader downloader = new PdfDownloader();

            String[] arquivosBaixados = downloader.downloadFiles(new String[]{
                    "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf",
                    "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025.pdf"
            }, "anexos/baixados");

            if (arquivosBaixados == null || arquivosBaixados.length == 0) {
                throw new ProcessingException("Nenhum arquivo foi baixado com sucesso", null);
            }

            System.out.println("Quantidade de arquivos baixados: " + arquivosBaixados.length);
            for (String arquivo : arquivosBaixados) {
                System.out.println("Arquivo baixado: " + arquivo);
            }

            return arquivosBaixados;
        } catch (IOException e) {
            throw new ProcessingException("Falha ao baixar anexos", e);
        }
    }

    private void compactarAnexos(String[] arquivos) throws ProcessingException {
        try {
            Path dirCompactados = Paths.get("anexos/compactados");
            if (!Files.exists(dirCompactados)) {
                Files.createDirectories(dirCompactados);
                System.out.println("Diretório criado: " + dirCompactados.toAbsolutePath());
            }

            FileCompressor compressor = new FileCompressor(new ZipStrategy());
            compressor.compressFiles(arquivos, "anexos/compactados/anexos.zip");
            System.out.println("Arquivos compactados com sucesso em: anexos/compactados/anexos.zip");
        } catch (IOException e) {
            throw new ProcessingException("Falha ao compactar anexos: " + e.getMessage(), e);
        }
    }

    public void processarAnexoI(String arquivoAnexoI) throws ProcessingException {
        try {
            Path dirProcessados = Paths.get("anexos/processados").toAbsolutePath();

            Files.createDirectories(dirProcessados);
            System.out.println("Diretório de processamento: " + dirProcessados);

            if (!Files.isWritable(dirProcessados)) {
                throw new ProcessingException("Sem permissão de escrita no diretório: " + dirProcessados, null);
            }

            String nomeBase = Paths.get(arquivoAnexoI).getFileName().toString()
                    .replace(".pdf", "");

            Path csvPath = dirProcessados.resolve(nomeBase + ".csv");
            Path csvProcessadoPath = dirProcessados.resolve(nomeBase + "_processado.csv");
            Path csvFinalPath = dirProcessados.resolve(nomeBase + "_final.csv");

            System.out.println("Extraindo tabelas do PDF...");
            CsvExtractor extractor = new CsvExtractor.Builder().build();
            List<Table> tables = extractor.extractTablesFromPdf(arquivoAnexoI);

            System.out.println("Salvando dados em: " + csvPath);
            try {
                extractor.saveTablesToCsv(tables, csvPath.toString());
            } catch (IOException e) {
                throw new ProcessingException("Falha ao salvar CSV: " + csvPath, e);
            }

            System.out.println("Processando abreviações...");
            Map<String, String> abreviacoes = Map.of(
                    "\"OD\"", "\"Seg. Odontológica\"",
                    "\"AMB\"", "\"Seg. Ambulatorial\"",
                    "OD", "Seg. Odontológica",
                    "AMB", "Seg. Ambulatorial"
            );

            new CsvAbbreviationHandler(abreviacoes).processFile(
                    csvPath.toString(),
                    csvProcessadoPath.toString()
            );

            System.out.println("Limpando CSV...");
            new CsvCleaner().cleanCsvFile(
                    csvProcessadoPath.toString(),
                    csvFinalPath.toString()
            );

            System.out.println("Compactando CSV...");
            String nomePersonalizado = "Teste_{Matheus_Felipe_Alves_Santos}";
            Path zipPath = dirProcessados.resolve(nomePersonalizado + ".zip");

            if (Files.exists(zipPath)) {
                Files.delete(zipPath);
            }

            new FileCompressor(new ZipStrategy()).compressFiles(
                    new String[]{csvFinalPath.toString()},
                    zipPath.toString()
            );

            System.out.println("Processamento concluído! Arquivo final: " + zipPath);

        } catch (IOException e) {
            throw new ProcessingException("Falha ao processar Anexo I: " + e.getMessage(), e);
        }
    }
}
