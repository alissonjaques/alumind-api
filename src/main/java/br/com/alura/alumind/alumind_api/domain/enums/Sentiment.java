package br.com.alura.alumind.alumind_api.domain.enums;

public enum Sentiment {
    POSITIVO("POSITIVO"),
    NEGATIVO("NEGATIVO"),
    INCONCLUSIVO("INCONCLUSIVO");

    private final String descricao;

    private Sentiment(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
