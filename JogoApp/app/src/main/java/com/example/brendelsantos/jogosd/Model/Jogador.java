package com.example.brendelsantos.jogosd.Model;

import android.graphics.Bitmap;

import java.sql.Date;
import java.util.List;

/**
 * Created by Brendel Santos on 08/05/2016.
 */
public class Jogador {
    private Long id;
    private String nome;
    private String nivel;
    private String host;
    private Long porta;
    private Long quantidadeVitoria;
    private Long quantidadeDerrota;
    private Date dataCadastro;
    private boolean ativo;

    private List<Baralho> baralhos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Long getPorta() {
        return porta;
    }

    public void setPorta(Long porta) {
        this.porta = porta;
    }

    public Long getQuantidadeVitoria() {
        return quantidadeVitoria;
    }

    public void setQuantidadeVitoria(Long quantidadeVitoria) {
        this.quantidadeVitoria = quantidadeVitoria;
    }

    public Long getQuantidadeDerrota() {
        return quantidadeDerrota;
    }

    public void setQuantidadeDerrota(Long quantidadeDerrota) {
        this.quantidadeDerrota = quantidadeDerrota;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Baralho> getBaralhos() {
        return baralhos;
    }

    public void setBaralhos(List<Baralho> baralhos) {
        this.baralhos = baralhos;
    }
}
