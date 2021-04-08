package br.edu.infnet.models.produtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cotacao {
    private Produto produto;
    private int codCotacao;
    private int quantidade;
    private String fornecedor;
    private LocalDate dataCotacao;
    private BigDecimal valorProduto;
    private BigDecimal valorTotal;

    public Cotacao() {
    }

    public Cotacao(Produto produto, int codCotacao, int quantidade, String fornecedor, LocalDate dataCotacao, BigDecimal valorProduto, BigDecimal valorTotal) {
        this.produto = produto;
        this.codCotacao = codCotacao;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
        this.dataCotacao = dataCotacao;
        this.valorProduto = valorProduto;
        this. valorTotal = valorTotal;
    }

    public Produto getProduto(){
        return produto;
    }

    public void setProduto(Produto produto){
        this.produto = produto;
    }

    public int getCodCotacao() {
        return codCotacao;
    }

    public void setCodCotacao(int codCotacao) {
        this.codCotacao = codCotacao;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public LocalDate getDataCotacao() {
        return dataCotacao;
    }

    public void setDataCotacao(LocalDate dataCotacao) {
        this.dataCotacao = dataCotacao;
    }

    public BigDecimal getValor() {
        return valorProduto;
    }

    public void setValor(BigDecimal valorProduto) {
        this.valorProduto = valorProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    

}
