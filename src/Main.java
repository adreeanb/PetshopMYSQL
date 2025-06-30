import java.sql.SQLException;
import java.util.Scanner;
import Menu.Menu;
import DAO.AtendimentoDAO;
import static Menu.Menu.clearScreen;
import static Menu.Menu.pause;

public class Main {
    public static void main(String[] args) throws SQLException {
        final Scanner scanner = new Scanner(System.in);
        int opcao;

        while (true) {
            clearScreen();
            System.out.println("=============================================");
            System.out.println("================ BEM-VINDO ===================");
            System.out.println("= Escolha uma opção:                        =");
            System.out.println("=-------------------------------------------=");
            System.out.println("= 1 - Registrar Cliente                     =");
            System.out.println("= 2 - Registrar Animal                      =");
            System.out.println("= 3 - Registrar Serviço                     =");
            System.out.println("= 4 - Registrar Atendimento                 =");
            System.out.println("= 5 - Consultar Todos os Atendimentos       =");
            System.out.println("= 6 - Consultar Atendimentos por Cliente    =");
            System.out.println("= 7 - Consultar Atendimentos por Data       =");
            System.out.println("= 8 - Consultar Cliente por CPF             =");
            System.out.println("= 9 - Consultar Animal por Nome             =");
            System.out.println("=10 - Consultar Serviço por Descrição       =");
            System.out.println("=11 - Sair                                  =");
            System.out.println("=============================================");
            System.out.print("Digite a opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> Menu.registrarCliente();
                    case 2 -> Menu.registrarAnimal();
                    case 3 -> Menu.registrarServico();
                    case 4 -> Menu.registrarAtendimento();
                    case 5 -> new AtendimentoDAO().listarTodosAtendimentos();
                    case 6 -> Menu.consultarAtendimentosPorCliente();
                    case 7 -> Menu.consultarAtendimentosPorData();
                    case 8 -> Menu.consultarClientePorCpf();
                    case 9 -> Menu.consultarAnimalPorNome();
                    case 10 -> Menu.consultarServicoPorDescricao();
                    case 11 -> {
                        System.out.println("\nEncerrando o programa... Até logo!");
                        return;
                    }
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }

                if (opcao != 11) pause();

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
                pause();
            }
        }
    }
}
