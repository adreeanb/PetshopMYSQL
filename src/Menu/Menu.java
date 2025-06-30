package Menu;
import java.sql.SQLException;
import java.util.Scanner;
import DAO.CachorroDAO;
import DAO.ClienteDAO;
import DAO.GatoDAO;
import DAO.ServicoDAO;
import Model.*;
import DAO.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Menu {

    public static void registrarCliente() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome do cliente:");
        String nome = scanner.nextLine();

        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();

        System.out.print("Telefone do cliente: ");
        String telefone = scanner.nextLine();

        System.out.print("Email do cliente: ");
        String email = scanner.nextLine();

        Cliente cliente1 = new Cliente();

        cliente1.setNome(nome);
        cliente1.setTelefone(telefone);
        cliente1.setCpf(cpf);
        cliente1.setEmail(email);
        new ClienteDAO().cadastrarCliente(cliente1);
    }

    public static void registrarAnimal() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome do animal:");
        String nome = scanner.nextLine();

        System.out.print("Espécie do animal: ");
        String especie = scanner.nextLine();

        System.out.print("Idade do animal: ");
        int idade = scanner.nextInt();

        System.out.print("Peso: ");
        double peso = scanner.nextDouble();
        scanner.nextLine(); // consome o Enter pendente

        System.out.println("O animal é um gato ou cachorro?");
        String tipo_animal = scanner.nextLine().toLowerCase();

        if (tipo_animal.equals("gato")) {

            System.out.println("Qual a raça do gato?");
            String raca = scanner.nextLine();

            System.out.println("O gato gosta de arranhar? (Respostas válidas: Sim/Não)");
            String gostaArranhar = scanner.nextLine();

            // Verifica a resposta antes de continuar
            if (!gostaArranhar.equalsIgnoreCase("Sim") && !gostaArranhar.equalsIgnoreCase("Não")) {
                System.out.println("Resposta inválida. Tente novamente com Sim ou Não.");
                return;
            }

            boolean gostaArranharBoolean = gostaArranhar.equalsIgnoreCase("Sim");

            Gato gato = new Gato();
            gato.setNome(nome);
            gato.setEspecie(especie);
            gato.setIdade(idade);
            gato.setPeso(peso);
            gato.setRaca(raca);
            gato.setGostaDeArranhar(gostaArranharBoolean);

            // Depois na tabela GATO
            new GatoDAO().cadastrarGato(gato);


        }else if(tipo_animal.equals("cachorro")){
            System.out.println("Qual a raça do cachorro?");
            String raca = scanner.nextLine();
            System.out.println("O cachorro tem carteira de vacinação? (Respostas válidas: Sim/Não)");
            String temCarteira = scanner.nextLine();

            if (!temCarteira.equalsIgnoreCase("Sim") && !temCarteira.equalsIgnoreCase("Não")) {
                System.out.println("Resposta inválida. Tente novamente com Sim ou Não.");
                return;
            }

            boolean temCarteiraBoolean = temCarteira.equalsIgnoreCase("Sim");

            Cachorro cachorro = new Cachorro();
            cachorro.setNome(nome);
            cachorro.setEspecie(especie);
            cachorro.setIdade(idade);
            cachorro.setPeso(peso);
            cachorro.setRaca(raca);
            cachorro.setCarteiraVacinacao(temCarteiraBoolean);

            // Depois na tabela cachorro
            new CachorroDAO().cadastrarCachorro(cachorro);
        } else{
            System.out.println("Resposta Inválida. Tente novamente!");
        }
    }

    public static void registrarServico() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Descrição do serviço:");
        String descricao = scanner.nextLine();

        System.out.print("Valor do serviço: ");
        double valor = scanner.nextDouble();

        System.out.print("Duração estimada (em minutos): ");
        int duracaoEstimadaMin = scanner.nextInt();
        scanner.nextLine();

        Servico servico = new Servico(descricao, valor, duracaoEstimadaMin);

        new ServicoDAO().cadastrarServico(servico);
    }

    public static void registrarAtendimento() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do cliente: ");
        int idCliente = scanner.nextInt();

        System.out.print("Digite o ID do serviço: ");
        int idServico = scanner.nextInt();

        System.out.print("Digite o ID do animal: ");
        int idAnimal = scanner.nextInt();
        scanner.nextLine(); // consumir o \n

        System.out.print("Digite a data do atendimento (YYYY-MM-DD): ");
        String dataStr = scanner.nextLine();

        LocalDate data;
        try {
            data = LocalDate.parse(dataStr);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido!");
            return;
        }

        // Cria objetos com só o ID
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);

        Servico servico = new Servico();
        servico.setId(idServico);

        Animal animal = new Animal() {};  // Animal é abstrato, então criar classe anônima vazia para teste
        animal.setId(idAnimal);

        Atendimento atendimento = new Atendimento(cliente, servico, animal, data);

        new AtendimentoDAO().cadastrarAtendimento(atendimento);
    }

    public static void consultarAtendimentosPorCliente() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o CPF do cliente: (Formato : xxx.xxx.xxx-xx ");
        String cpfCliente = scanner.nextLine();

        new AtendimentoDAO().listarAtendimentosPorCpf(cpfCliente);

    }

    public static void consultarAtendimentosPorData() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a data (YYYY-MM-DD): ");
        String dataStr = scanner.nextLine();

        try {
            LocalDate data = LocalDate.parse(dataStr);
            new AtendimentoDAO().listarAtendimentosPorData(data);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido!");
        }
    }

    public static void consultarClientePorCpf() throws SQLException{
            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite o CPF do cliente: (Formato: xxx.xxx.xxx-xx)");
            String cpf = scanner.nextLine();

            Cliente cliente = new ClienteDAO().buscarPorCpf(cpf);

            if (cliente != null) {
                System.out.println("\n===== DADOS DO CLIENTE =====");
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("CPF: " + cliente.getCpf());
                System.out.println("Telefone: " + cliente.getTelefone());
                System.out.println("Email: " + cliente.getEmail());
            } else {
                System.out.println("Cliente não encontrado.");
            }
    }

    public static void consultarAnimalPorNome() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do animal: ");
        String nome = scanner.nextLine();

        Animal animal = new AnimalDAO().buscarPorNome(nome);

        if (animal != null) {
            System.out.println("\n===== DADOS DO ANIMAL =====");
            System.out.println("ID: " + animal.getId());
            System.out.println("Nome: " + animal.getNome());
            System.out.println("Espécie: " + animal.getEspecie());
            System.out.println("Idade: " + animal.getIdade());
            System.out.println("Peso: " + animal.getPeso());

            if (animal instanceof Gato gato) {
                System.out.println("Raça: " + gato.getRaca());
                System.out.println("Gosta de arranhar: " + (gato.getGostaDeArranhar() ? "Sim" : "Não"));
            } else if (animal instanceof Cachorro cachorro) {
                System.out.println("Raça: " + cachorro.getRaca());
                System.out.println("Carteira de vacinação: " + (cachorro.getCarteiraVacinacao() ? "Sim" : "Não"));
            }
        } else {
            System.out.println("Animal não encontrado com esse nome.");
        }
    }

    public static void consultarServicoPorDescricao() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a descrição do serviço: ");
        String descricao = scanner.nextLine();

        Servico servico = new ServicoDAO().buscarPorDescricao(descricao);

        if (servico != null) {
            System.out.println("\n===== DADOS DO SERVIÇO =====");
            System.out.println("ID: " + servico.getId());
            System.out.println("Descrição: " + servico.getDescricao());
            System.out.println("Valor: R$ " + servico.getValor());
            System.out.println("Duração estimada: " + servico.getDuracaoEstimadaMin() + " min");
        } else {
            System.out.println("Serviço não encontrado.");
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception ex) {
            System.out.println("Não foi possível limpar a tela.");
        }
    }

    public static void pause() {
        System.out.println("\nPressione Enter para continuar...");
        new Scanner(System.in).nextLine();
    }
}

