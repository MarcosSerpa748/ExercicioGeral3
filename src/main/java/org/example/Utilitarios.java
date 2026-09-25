package org.example;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Utilitarios{
    private static final Scanner sc = new Scanner(System.in);

    public static Optional<User> gerarUser(){

        System.out.print("Digite seu nome:");
        String nome = sc.nextLine();

        try {

            return Optional.of(new User(nome));

        }catch (NomeInvalidoException e){

            System.out.println(e.getMensagem()+" Gerando usuário padrão...");
            return Optional.empty();
        }
    }

    public static User gerarUserPadrao(){
        return new User();
    }

    public static void exibirCorpoProjeto() throws IOException, InterruptedException {
        User user = Utilitarios.gerarUser().orElseGet(() -> Utilitarios.gerarUserPadrao());
        String escolha;
        do {
            System.out.println(
                    "Boas vindas, "+user.getNome()+"!\n"+
                            "Digite 1 para selecionar uma data de liturgia.\n"+
                            "Digite 2 para ver seu dados.\n"+
                            "Digite 0 para finalizar o sistema.");
            escolha = sc.nextLine();

            switch (escolha){
                case "1" -> user.pesquisarLiturgia();
                case "2" -> System.out.println(user);
                case "0" -> System.out.println("Finalizando o sistema...");
                default -> System.out.println("Tecla não inserida nas opções de funcionalidade.");
            }
        }while (!escolha.equalsIgnoreCase("0"));
    }
}
