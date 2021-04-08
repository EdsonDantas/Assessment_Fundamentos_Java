package br.edu.infnet.models.cadastroConsultas;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import br.edu.infnet.models.produtos.Cotacao;
import br.edu.infnet.models.produtos.Produto;

public class CadastrarConsultar {

    private List<Produto> produtos;
    private List<Cotacao> cotacoes;

    public CadastrarConsultar() {

    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Cotacao> getCotacoes() {
        return cotacoes;
    }

    public void setCotacoes(List<Cotacao> cotacoes) {
        this.cotacoes = cotacoes;
    }


    
    //MÉTODOS

    // *********************CADASTROS**************************/
    // Cadastro de Produtos
    public void cadastrarProduto(List<Produto> produtos) {
        Scanner input = new Scanner(System.in);
        Produto produto = new Produto();
        // Nome
        System.out.println("Informe o nome do Produto");
        String prodNome = input.nextLine();
        produto.setNome(prodNome);
        // Tipo
        System.out.println("Informe o Tipo do Produto");
        String prodTipo = input.nextLine();
        produto.setTipo(prodTipo);
        // Código
        System.out.println("Informe um código para o Produto");
        int prodCod = input.nextInt();
        produto.setCodProduto(prodCod);
        // adiciona o novo produto
        produtos.add(produto);
    }

    // Cadastro de Cotações
    public void cadastrarCotacao(List<Cotacao> cotacoes, List<Produto> produtos) {
        // Cria uma nova cotação
        Cotacao cotacao = new Cotacao();

        Scanner input = new Scanner(System.in);
        System.out.println("//------Produtos disponíveis------//");
        for (Produto produto : produtos) {
            System.out.println("\nNome do Produto: " + produto.getNome() + "|| Código: " + produto.getCodProduto());
        }
        // pega o cógido do produto desejado
        System.out.println("\nInforme o código do Produto para cadastrar uma Cotação");
        int codProduto = Integer.parseInt(input.nextLine());

        // busca o produto e adiciona (caso exista) à lista de cotações.
        Produto buscaProduto = produtos.stream().filter(produto -> codProduto == produto.getCodProduto()).findAny()
                .orElse(null);
        if (buscaProduto != null) {
            cotacao.setProduto(buscaProduto);
            System.out.println(buscaProduto.getNome());
        } else {
            System.out.println("Produto ainda não foi cadastrado.");
        }

        // usuário insere o fornecedor
        System.out.println("Informe o nome do Fornecedor: ");
        String fornecedor = input.nextLine();
        cotacao.setFornecedor(fornecedor);

        // usuário insere o Código da Cotação
        System.out.println("Informe o código para a cotação atual: ");
        int codCotacao = input.nextInt();
        cotacao.setCodCotacao(codCotacao);

        // usuário insere quantidade de intens para a cotação
        System.out.println("Informe a Quantidade desejada do produto: ");
        int quantidade = input.nextInt();
        cotacao.setQuantidade(quantidade);

        // usuário insere a Data da Cotação (que trabalho!!!!!)
        System.out.println("Informe o ano (AAAA):");
        int anoCotacao = input.nextInt();
        System.out.println("Informe o mês (MM):");
        int mesCotacao = input.nextInt();
        System.out.println("Informe o dia (DD)");
        int diacotacao = input.nextInt();
        LocalDate data = LocalDate.of(anoCotacao, mesCotacao, diacotacao);
        cotacao.setDataCotacao(data);

        // usuário insere o valor unitário do produto
        System.out.println("Informe o valor unitário do Produto a ser cotado\nUse ',' para separar centavos.");
        double valor = input.nextDouble();
        cotacao.setValor(new BigDecimal(valor));

        // calcula valor total e coloca no setValorTotal
        BigDecimal Qntd = new BigDecimal(cotacao.getQuantidade());
        BigDecimal valorTotal = Qntd.multiply(cotacao.getValor());
        cotacao.setValorTotal(valorTotal);

        // adiciona a cotação na lista
        cotacoes.add(cotacao);

    }

    // *****************CONSULTAS***********************/

    // Consultar produtos cadastrados
    public void exibirProdutosCadastrados(List<Produto> produtos) {
        System.out.println("---------Produtos Cadastrados----------");
        for (Produto produto : produtos) {

            System.out.println("\nNome: " + produto.getNome() + " |" + "\nCódigo: " + produto.getCodProduto() + " |"
                    + "\nTipo: " + produto.getTipo() + " |");
        }
    }

    // Consultar cotações cadastrados
    public void exibirCotacoesCadastradas(List<Cotacao> cotacoes) {
        // formatar data
        DateTimeFormatter df = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy");
        
        //Exibir produtos com cotação
        System.out.println("---------Produtos Cadastrados----------");
        for (Cotacao cotacao : cotacoes) {

            System.out.println("\nCódigo: " + cotacao.getCodCotacao() + " | Nome: " + cotacao.getProduto().getNome()
                    + " | Qntd: " + cotacao.getQuantidade() + " | Fornecedor: " + cotacao.getFornecedor() + " | Data: "
                    + cotacao.getDataCotacao().format(df) + " | Valor unitário: "
                    + formatarMoeda().format(cotacao.getValor()) + " | Valor total: "
                    + formatarMoeda().format(cotacao.getValorTotal()));
        }
    }

    public void consultarCotacoesPorProduto(List<Produto> produtos, List<Cotacao> cotacoes) {

        // Preciso listar os produtos disponíveis e depois verificar se possui cotação cadastrada.

        // listar produtos
        System.out.println("********-----Produtos Cadastrados-----********\n");
        System.out.printf("%-20s %-15s %s\n", "Nome do Produto", " ", "Código");
        
        produtos.forEach(p -> {
            System.out.printf("%-20s %-15s %d\n", p.getNome(), "----------->", p.getCodProduto());
        });
        
        // Entrada do usuário (código escolhido).
        System.out.println("Informe o código do produto ao qual deseja consultar Cotações Cadastradas:\n");
        Scanner input = new Scanner(System.in);
        int codProduto = input.nextInt();

        // Gerando uma nova lista com as cotações do produto especificado.
        List<Cotacao> cotacoesExistentes = new ArrayList<>();
        for (Cotacao cotacao : cotacoes) {
            if (cotacao.getProduto().getCodProduto() == codProduto) {
                cotacoesExistentes.add(cotacao);
            }
        }

        // formatar data
        DateTimeFormatter df = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy");
        
        // Verifica se algo foi adicionado a nova lista
        if (cotacoesExistentes.size() != 0) {
            System.out.println("\nCotações para o produto escolhido\n-----------------------------------\n");
            for (Cotacao cotacao : cotacoesExistentes) {

                System.out.println("\nCódigo: " + cotacao.getCodCotacao() + " | Nome: " + cotacao.getProduto().getNome()
                        + " | Qntd: " + cotacao.getQuantidade() + " | Fornecedor: " + cotacao.getFornecedor()
                        + " | Data: " + cotacao.getDataCotacao().format(df) + " | Valor unitário: "
                        + formatarMoeda().format(cotacao.getValor()) + " | Valor total: "
                        + formatarMoeda().format(cotacao.getValorTotal()));
            }
        }else {
            System.out.println("\nNão existe Cotação para esse Produto.");
        }
    }

    public DecimalFormat formatarMoeda(){
        Locale BRAZIL = new Locale("pt", "br");
        Locale.setDefault(BRAZIL);
        DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
        DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00", REAL);
        return DINHEIRO_REAL;
    }
}
