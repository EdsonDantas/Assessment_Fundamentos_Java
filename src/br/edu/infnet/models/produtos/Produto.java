package br.edu.infnet.models.produtos;


public class Produto {

    private String nome;
    private String tipo;
    private int codProduto;

    public Produto(){

    }

    public Produto(String nome, String tipo, int codProduto) {
        this.nome = nome;
        this.tipo = tipo;
        this.codProduto = codProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    


    
}
