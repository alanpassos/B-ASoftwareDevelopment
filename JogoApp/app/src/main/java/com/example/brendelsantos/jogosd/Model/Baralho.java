package com.example.brendelsantos.jogosd.Model;

/**
 * Created by Brendel Santos on 08/05/2016.
 */
public class Baralho {
    private Long id;
    private String descricao;
    private String nome;
    private TipoBaralho tipoBaralho;
    private String capa;

    private boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoBaralho getTipoBaralho() {
        return tipoBaralho;
    }

    public void setTipoBaralho(TipoBaralho tipoBaralho) {
        this.tipoBaralho = tipoBaralho;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
