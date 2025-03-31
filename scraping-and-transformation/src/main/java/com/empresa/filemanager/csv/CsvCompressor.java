package com.empresa.filemanager.csv;

import org.example.Teste1.CompactaçãoDeArquivos;

public class CsvCompressor {
    public static void main(String[] args) {
        String[] arquivo = {"csv/Teste_{Matheus_Felipe_Alves_Santos}.csv"};
        CompactaçãoDeArquivos.compactarArquivos(arquivo, "csv/Teste_{Matheus_Felipe_Alves_Santos}.zip");
    }
}
