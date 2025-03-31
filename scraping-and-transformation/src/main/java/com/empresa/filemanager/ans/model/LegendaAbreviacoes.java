package com.empresa.filemanager.ans.model;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum LegendaAbreviacoes {
    OD("Odontológico"),
    AMB("Ambulatorial"),
    HCO("Hospitalar Convênio"),
    HSO("Hospitalar SUS");

    private final String descricao;

    LegendaAbreviacoes(String descricao) {
        this.descricao = descricao;
    }

    public static Map<String, String> getMap() {
        return Arrays.stream(values())
                .collect(Collectors.toMap(Enum::name, v -> v.descricao));
    }
}
