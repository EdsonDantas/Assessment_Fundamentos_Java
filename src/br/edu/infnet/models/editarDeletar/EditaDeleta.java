package br.edu.infnet.models.editarDeletar;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.edu.infnet.models.cadastroConsultas.CadastrarConsultar;
import br.edu.infnet.models.produtos.Cotacao;
import br.edu.infnet.models.produtos.Produto;

public class EditaDeleta {

    private List<Produto> produtos;
    private List<Cotacao> cotacoes;
    CadastrarConsultar consultar = new CadastrarConsultar();

    public EditaDeleta() {

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

    
    //MËTODOS
    /****************** DELETAR *********************/

    // Deletar Cotação
    public void deletarCotacao(List<Cotacao> cotacoes) {
        // formatar data
        DateTimeFormatter df = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyy");

        System.out.println("Cotações disponívei para Exclusão do Banco de Dados");
        for (Cotacao cotacao : cotacoes) {
            System.out.println("\nCódigo: " + cotacao.getCodCotacao() + " | Nome: " + cotacao.getProduto().getNome()
                    + " | Qntd: " + cotacao.getQuantidade() + " | Fornecedor: " + cotacao.getFornecedor() + " | Data: "
                    + cotacao.getDataCotacao().format(df) + " | Valor unitário: "
                    + consultar.formatarMoeda().format(cotacao.getValor()) + " | Valor total: "
                    + consultar.formatarMoeda().format(cotacao.getValorTotal()));
        }

        System.out.println("\nEntre com código da Cotação a ser Excuída:");
        Scanner input = new Scanner(System.in);
        int excluirCot = input.nextInt();

        // busca a cotação pelo input do usuário (excluiCot) -> código da cotação.
        Cotacao busCotacao = cotacoes.stream().filter(busCot -> excluirCot == busCot.getCodCotacao()).findAny()
                .orElse(null);
        // se a cotação existe, é removida da lista
        if (busCotacao != null) {
            cotacoes.remove(cotacoes.indexOf(busCotacao));
            System.out.println("*-* !Ctação Excluída com Sucesso! *-*");
        } else {
            System.out.println("======> Cotação não existe no Banco de Dados! <=======");
        }

    }

    /************************ EDITAR *************************/
    // Editar produto

    public void editarProduto(List<Produto> produtos) {
        // listar produtos
        System.out.println("Produtos disponíveis para Edição de dados\n");
        System.out.printf("%-20s %-15s %s\n", "Nome do Produto", " ", "Código");
        produtos.forEach(p -> {
            System.out.printf("%-20s %-15s %d\n", p.getNome(), "----------->", p.getCodProduto());
        });

        // input do usuário (código do produto)
        System.out.println("Informe o código do produto que deseja Editar");
        Scanner input = new Scanner(System.in);
        int codProduto = input.nextInt();

        // buscando o maldito produto

        int index = -1;
        for (Produto produto : produtos) {
            if (produto.getCodProduto() == codProduto) {
                index = produtos.indexOf(produto);
            } else {
                System.out.println("\n=====> Não existe Produto para o Código informado! <========\n");
                break;
            }
        }

        // Substituindo o produto antigo
        Produto novoProduto = produtos.get(index);

        // Alterando os dados(ou não) do novo produto
        Scanner npInput = new Scanner(System.in);
        // nome
        System.out.println("\nInforme o NOME do produto, ou pressione 'ENTER' para pular esta estapa:");
        String novoNome = npInput.nextLine();
        if (!novoNome.equals("")) { // se for vazio continua o nome antigo, do contrário seta o nome novo
            novoProduto.setNome(novoNome);
        }
        // Tipo
        System.out.println("\nInforme um TIPO para o produto, ou pressione 'ENTER' para pular esta etapa");
        String novoTipo = npInput.nextLine();
        if (!novoTipo.equals("")) {
            novoProduto.setTipo(novoTipo);
        }
        // Código
        System.out.println("\nInforme um CÓDIGO para o produto, ou pressione 'ENTER' para pular esta etapa");
        String novoCodigo = npInput.nextLine();
        if (!novoCodigo.equals("")) {
            novoProduto.setCodProduto(Integer.parseInt(novoCodigo));
        }
        produtos.set(index, novoProduto);
    }

    // Editar Cotação
    public void editarCotacao(List<Cotacao> cotacoes) {

        // listar produtos
        System.out.println("Cotações disponíveis para Edição de dados\n");
        System.out.printf("%-20s %-15s %s\n", "Nome do Produto", " ", "Código da Cotação");
        cotacoes.forEach(c -> {
            System.out.printf("%-20s %-15s %d\n", c.getProduto().getNome(), "----------->", c.getCodCotacao());
        });

        // input do usuário (código do produto)
        System.out.println("Informe o código da Cotação que deseja Editar");
        Scanner input = new Scanner(System.in);
        int codCotacao = input.nextInt();

        int index = -1;
        for (Cotacao cotacao : cotacoes) {
            if (cotacao.getCodCotacao() == codCotacao) {
                index = cotacoes.indexOf(cotacao);
            }
        }

        // Nova Cotação substituindo a anterior
        Cotacao novaCotacao = cotacoes.get(index);

        // Alterando os dados(ou não) da nova Cotação
        Scanner ncInput = new Scanner(System.in);
        System.out.println("\nInforme um CÓDIGO para a cotação, ou pressione 'ENTER' para pular esta etapa");
        String novoCodigo = ncInput.nextLine();
        if(!novoCodigo.equals("")){
            novaCotacao.setCodCotacao(Integer.parseInt(novoCodigo));
        }
        //Quantidade
        System.out.println("\nInforme uma QUANTIDADE, ou pressione 'ENTER' para pular esta etapa");
        String novaQntd = ncInput.nextLine();
        if (!novaQntd.equals("")){
            novaCotacao.setQuantidade(Integer.parseInt(novaQntd));
        }
        //Fornecedor
        System.out.println("\nInforme um novo FORNECEDOR, ou pressione 'ENTER' para pular esta etapa");
        String novoFornecedor = ncInput.nextLine();
        if (!novoFornecedor.equals("")){
            novaCotacao.setFornecedor(novoFornecedor);
        }
        //Valor Unitário do Produto
        System.out.println("\nInforme um novo VALOR UNITÁRIO, ou pressione 'ENTER' para pular esta etapa");
        String novoValor = ncInput.nextLine();
        if (!novoValor.equals("")){
            novaCotacao.setValor((new BigDecimal(novoValor.toString().replace(",","."))));
        }
        // calcula valor total e coloca no setValorTotal
        BigDecimal Qntd = new BigDecimal(novaCotacao.getQuantidade());
        BigDecimal valorTotal = Qntd.multiply(novaCotacao.getValor());
        novaCotacao.setValorTotal(valorTotal);

        cotacoes.set(index, novaCotacao);
    }

}