package br.com.treenchallenge.legacy;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(
            null,
            "Bem-vindo ao TreenRank!\n\n"
            + "Sistema de Ranking Semanal de Sustentabilidade.\n"
            + "Registre suas acoes, ganhe XP e TreenTokens\n"
            + "e troque por cupons de desconto!",
            "TreenRank - FIAP & SoulUp",
            JOptionPane.INFORMATION_MESSAGE
        );

        System.out.println("==========================================");
        System.out.println("  TREENRANK  - Sistema de Sustentabilidade");
        System.out.println("  Com XP, Niveis e TreenTokens!");
        System.out.println("==========================================");

        Categoria catReciclagem = new CategoriaReciclagem();
        Categoria catEnergia    = new CategoriaEnergia();

        System.out.println("\nCategorias do sistema:");
        System.out.println(catReciclagem.exibirInfo());
        System.out.println(catEnergia.exibirInfo());

        LojaCupons loja = new LojaCupons();

        Usuario usuario1 = new Usuario("Thiago Japa",     "thiagojapinha@gmail.com",    "Morumbi");
        Usuario usuario2 = new Usuario("Pantera", "pantera@gmail.com", "Barra Funda");
        Usuario usuario3 = new Usuario("Tenorio",  "tenorin@gmail.com",    "Itaquera");

        System.out.println("\n[REGISTRANDO ACOES DE " + usuario1.getNome().toUpperCase() + "]");

        usuario1.registrarAcao(new AcaoSustentavel(
            "Reciclagem de plastico", 5.0,  "kg",  "12/05/2026", catReciclagem));
        usuario1.registrarAcao(new AcaoSustentavel(
            "Reciclagem de papel",    3.5,  "kg",  "13/05/2026", catReciclagem));
        usuario1.registrarAcao(new AcaoSustentavel(
            "Economia de energia",   80.0,  "kWh", "14/05/2026", catEnergia));

        System.out.println("\n[REGISTRANDO ACOES DE " + usuario2.getNome().toUpperCase() + "]");

        usuario2.registrarAcao(new AcaoSustentavel(
            "Reciclagem de vidro",    10.0, "kg",  "11/05/2026", catReciclagem));
        usuario2.registrarAcao(new AcaoSustentavel(
            "Painel solar instalado", 150.0,"kWh", "12/05/2026", catEnergia));

        System.out.println("\n[REGISTRANDO ACOES DE " + usuario3.getNome().toUpperCase() + "]");

        usuario3.registrarAcao(new AcaoSustentavel(
            "Reciclagem de metal",    2.0,  "kg",  "10/05/2026", catReciclagem));
        usuario3.registrarAcao(new AcaoSustentavel(
            "Troca por lampadas LED", 50.0, "kWh", "14/05/2026", catEnergia));

        usuario1.exibirHistorico();
        usuario2.exibirHistorico();
        usuario3.exibirHistorico();

        usuario1.exibirPerfil();
        usuario2.exibirPerfil();
        usuario3.exibirPerfil();

        int acoesReciclagemAna = usuario1.contarAcoesPorCategoria("Reciclagem");
        System.out.println("\nAcoes de reciclagem de tenorio: " + acoesReciclagemAna);

        System.out.println("Pontos da tenorio antes do bonus: " + usuario1.getPontuacaoTotal());
        usuario1.adicionarPontos(50);
        System.out.println("Pontos da tenorio apos bonus de 50 pts: " + usuario1.getPontuacaoTotal());

        System.out.println("\n[CARTEIRAS DE TREENTOKEN]");
        usuario1.getCarteira().exibirExtrato();
        usuario2.getCarteira().exibirExtrato();
        usuario3.getCarteira().exibirExtrato();

        System.out.println("\n[DEMONSTRACAO: RESGATE DE CUPOM - " + usuario2.getNome() + "]");
        loja.exibirCatalogo();
        loja.exibirCuponsAcessiveis(usuario2.getCarteira().getSaldo());

        System.out.println("\nPantera tenta resgatar o cupom #1 (Natura - 10 TKN):");
        loja.resgatarCupom(1, usuario2.getCarteira(), usuario2.getNome());

        System.out.println("\nPantera tenta resgatar o cupom #3 (Mundo Verde - 25 TKN):");
        loja.resgatarCupom(3, usuario2.getCarteira(), usuario2.getNome());

        usuario2.getCarteira().exibirExtrato();

        // RANKING
        RankingSemanal ranking = new RankingSemanal("Semana 2 - Maio 2026");
        ranking.adicionarUsuario(usuario1);
        ranking.adicionarUsuario(usuario2);
        ranking.adicionarUsuario(usuario3);

        ranking.exibirRanking();
        ranking.exibirEstatisticas();

        System.out.println("\nMedalha 1o lugar: " + RankingSemanal.getMedalha(1));
        System.out.println("Medalha 2o lugar: " + RankingSemanal.getMedalha(2));
        System.out.println("Medalha 4o lugar: " + RankingSemanal.getMedalha(4));

        menuInterativo(catReciclagem, catEnergia, ranking, loja);

        JOptionPane.showMessageDialog(
            null,
            "Obrigado por usar o TreenRank!\nJuntos por um mundo mais sustentavel!",
            "TreenRank - Ate logo!",
            JOptionPane.INFORMATION_MESSAGE
        );

        scanner.close();
        System.out.println("\nPrograma encerrado.");
    }

     static void menuInterativo(Categoria catReciclagem, Categoria catEnergia,
                               RankingSemanal ranking, LojaCupons loja) {

        System.out.println("\n==========================================");
        System.out.println("       CADASTRO DE NOVA ACAO - TREENRANK");
        System.out.println("==========================================");

        String nomeNovo = JOptionPane.showInputDialog(
            null,
            "Digite seu nome para registrar uma acao:",
            "TreenRank - Novo Registro",
            JOptionPane.QUESTION_MESSAGE
        );

        if (nomeNovo == null || nomeNovo.trim().isEmpty()) {
            System.out.println("Cadastro cancelado.");
            return;
        }

        nomeNovo = nomeNovo.trim();
        Usuario novoUsuario = new Usuario(nomeNovo,
                nomeNovo.toLowerCase().replace(" ", "") + "@gmail.com", "Brasil");
        System.out.println("Usuario criado: " + nomeNovo);

        String[] opcoes = {"Reciclagem", "Economia de Energia"};
        int escolha = JOptionPane.showOptionDialog(
            null,
            "Qual categoria de acao voce realizou?",
            "TreenRank - Categoria",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null, opcoes, opcoes[0]
        );

        if (escolha == -1) { System.out.println("Selecao cancelada."); return; }

        Categoria categoriaSelecionada = (escolha == 0) ? catReciclagem : catEnergia;

        String[] opcoesAcao;
        String   labelQuantidade;
        String   unidadeNova;

        if (escolha == 0) {
            opcoesAcao = new String[]{
                "Reciclagem de plastico",
                "Reciclagem de papel e papelao",
                "Reciclagem de vidro",
                "Reciclagem de metal (latas, aluminio)",
                "Reciclagem de eletronicos"
            };
            labelQuantidade = "Quantos kg de material voce reciclou?\n(Ex: 2.5 para 2 kg e meio)";
            unidadeNova     = "kg";
        } else {
            opcoesAcao = new String[]{
                "Troca de lampadas por LED",
                "Uso de energia solar (painel solar)",
                "Reducao do uso de ar-condicionado",
                "Desligar aparelhos em standby",
                "Uso de aquecedor solar de agua"
            };
            labelQuantidade = "Quantos kWh voce economizou no mes?\n"
                    + "(Veja na sua conta de luz o consumo atual vs. o do mes anterior.\n"
                    + "Ex: se caiu de 200 para 150 kWh, digite 50)";
            unidadeNova = "kWh";
        }

        int escolhaAcao = JOptionPane.showOptionDialog(
            null,
            "Selecione a acao que voce realizou:",
            "TreenRank - Acao Realizada",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null, opcoesAcao, opcoesAcao[0]
        );

        if (escolhaAcao == -1) { System.out.println("Selecao de acao cancelada."); return; }

        String descricaoNova  = opcoesAcao[escolhaAcao];

        String quantidadeTexto = JOptionPane.showInputDialog(
            null, labelQuantidade, "TreenRank - Quantidade", JOptionPane.QUESTION_MESSAGE);

        if (quantidadeTexto == null || quantidadeTexto.trim().isEmpty()) {
            System.out.println("Quantidade nao informada. Cadastro cancelado.");
            return;
        }

        quantidadeTexto = quantidadeTexto.trim().replace(",", ".");
        double quantidadeNova = Double.parseDouble(quantidadeTexto);

        AcaoSustentavel novaAcao = new AcaoSustentavel(
            descricaoNova, quantidadeNova, unidadeNova, "14/05/2026", categoriaSelecionada);

        novoUsuario.registrarAcao(novaAcao);
        novoUsuario.exibirHistorico();
        novoUsuario.exibirPerfil();

        ranking.adicionarUsuario(novoUsuario);
        System.out.println("\nRanking atualizado com " + nomeNovo + ":");
        ranking.exibirRanking();

        int saldoAtual = novoUsuario.getCarteira().getSaldo();
        loja.exibirCuponsAcessiveis(saldoAtual);

        JOptionPane.showMessageDialog(
            null,
            "Parabens, " + nomeNovo + "!\n\n"
                + "Voce ganhou:\n"
                + "  " + novaAcao.getPontos()        + " Pontos de ranking\n"
                + "  " + novaAcao.getXpGerado()      + " XP\n"
                + "  " + novaAcao.getTokensGerados()  + " TreenTokens\n\n"
                + "Nivel XP atual: " + novoUsuario.getSistemaXP().getNivelAtual().getNome() + "\n"
                + "Saldo TKN    : " + saldoAtual + " TKN\n\n"
                + "Continue contribuindo para um mundo melhor!",
            "TreenRank - Ganhos!",
            JOptionPane.INFORMATION_MESSAGE
        );

        if (saldoAtual > 0) {
            String[] simNao = {"Sim, quero resgatar!", "Agora nao"};
            int respostaLoja = JOptionPane.showOptionDialog(
                null,
                "Voce tem " + saldoAtual + " TreenTokens!\nDeseja visitar a loja de cupons?",
                "TreenRank - Loja de Cupons",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, simNao, simNao[0]
            );

            if (respostaLoja == 0) {
                loja.exibirCatalogo();
                String numCupomStr = JOptionPane.showInputDialog(
                    null,
                    "Digite o numero do cupom que deseja resgatar\n"
                    + "(ou cancele para sair da loja):",
                    "TreenRank - Resgatar Cupom",
                    JOptionPane.QUESTION_MESSAGE
                );

                if (numCupomStr != null && !numCupomStr.trim().isEmpty()) {
                    try {
                        int numCupom = Integer.parseInt(numCupomStr.trim());
                        boolean sucesso = loja.resgatarCupom(
                                numCupom, novoUsuario.getCarteira(), nomeNovo);
                        if (sucesso) {
                            JOptionPane.showMessageDialog(null,
                                "Cupom resgatado com sucesso!\n"
                                + "Saldo restante: " + novoUsuario.getCarteira().getSaldo() + " TKN",
                                "TreenRank - Cupom Resgatado!",
                                JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Numero de cupom invalido.");
                    }
                }
            }
        }
    }
}
