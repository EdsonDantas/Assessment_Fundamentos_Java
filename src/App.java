import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.edu.infnet.models.Menu;
import br.edu.infnet.models.cadastroConsultas.CadastrarConsultar;
import br.edu.infnet.models.editarDeletar.EditaDeleta;
import br.edu.infnet.models.produtos.Cotacao;
import br.edu.infnet.models.produtos.Produto;

public class App {

    // onde vou guardar os produtos
    private static List<Produto> listaProdutos = new ArrayList<Produto>();

    // onde vou guardar as cotações
    private static List<Cotacao> listaCotacao = new ArrayList<Cotacao>();

    public static void main(String[] args) throws Exception {

        // Pre-cadastro de produtos para teste.
        Produto prod1 = new Produto("Alcool em Gel", "Higiene", 10);
        listaProdutos.add(prod1);
        Produto prod2 = new Produto("Mesa escritório", "Móveis", 20);
        listaProdutos.add(prod2);
        Produto prod3 = new Produto("Cadeiras giratórias", "Móveis", 30);
        listaProdutos.add(prod3);
        Produto prod4 = new Produto("Cafeteira", "Eletrodoméstico", 41);
        listaProdutos.add(prod4);
        Produto prod5 = new Produto("Máquina Bebidas", "Alimentos", 200);
        listaProdutos.add(prod5);

        // Pre-cadastro de cotações para teste.
        listaCotacao.add(new Cotacao(prod1, 111, 5, "vendendor A", LocalDate.of(2021, 4, 07), new BigDecimal(12),
                new BigDecimal(60)));
        listaCotacao.add(new Cotacao(prod2, 222, 10, "vendedor B", LocalDate.of(2021, 4, 17), new BigDecimal(120),
                new BigDecimal(1200)));
        listaCotacao.add(new Cotacao(prod4, 444, 2, "vendedor A", LocalDate.of(2021, 4, 17), new BigDecimal(80),
                new BigDecimal(160)));
        listaCotacao.add(new Cotacao(prod4, 445, 2, "vendedor C", LocalDate.of(2021, 4, 18), new BigDecimal(75),
                new BigDecimal(150)));

        try {
            Scanner input = new Scanner(System.in);
            String opcao = null;

            do {

                CadastrarConsultar cadastrar = new CadastrarConsultar();
                CadastrarConsultar consultar = new CadastrarConsultar();
                EditaDeleta deleta = new EditaDeleta();
                EditaDeleta editar = new EditaDeleta();

                Menu.opcoesMenuInicial();

                // Escolha da opção do menu
                opcao = input.next();

                // Casos que serão executados de acordo com a opção do usuário
                switch (opcao) {

                case "1": {// Castrar um novo produto
                    cadastrar.cadastrarProduto(listaProdutos);
                    cadastrar.setProdutos(listaProdutos);
                    break;// done
                }

                case "2": {// Castrar uma nova cotacão
                    cadastrar.cadastrarCotacao(listaCotacao, listaProdutos);
                    cadastrar.setCotacoes(listaCotacao);
                    break;// done
                }

                case "3": {// Exibir produtos cadastrados
                    consultar.exibirProdutosCadastrados(listaProdutos);
                    break;// done
                }

                case "4": {// Exibir cotações cadastradas
                    consultar.exibirCotacoesCadastradas(listaCotacao);
                    break;// done
                }

                case "5": {// Consultar Cotações por Produto
                    consultar.consultarCotacoesPorProduto(listaProdutos, listaCotacao);
                    break;
                }

                case "6": {// Deletar uma cotação
                    deleta.deletarCotacao(listaCotacao);
                    break;
                }

                case "7": {// Editar Produto
                    editar.editarProduto(listaProdutos);
                    break;
                }

                case "8": {// Editar Cotacao
                    editar.editarCotacao(listaCotacao);
                    break;
                }

                case "0": {// Sair do Programa
                    System.out.println("Programa Encerrado");
                    System.exit(0);
                }

                }

                System.out.println("\nDeseja encerrar o programa?\n");
                System.out.println("(0). Encerrar ---------------------");
                System.out.println("(1). Voltar ao Menu ---------------");

                int opcao2 = input.nextInt();
                if (opcao2 == 0) {
                    System.out.println("\nPrograma encerrado!!");
                    System.exit(0);
                }else{
                    continue;
                }
                
            } while (opcao != "0");

            input.close();

        } catch (NumberFormatException e) {
            System.out.println("O campo código só aceita números.");
        } catch (InputMismatchException e) {
            System.out.println("Entrada de dados inválida.");
        }

    }
}
