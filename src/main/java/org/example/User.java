package org.example;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class User {
    private final Integer id;
    private String nome;
    private final Scanner sc = new Scanner(System.in);

    public User(String nome){
        if (nome.isEmpty()){
            throw new NomeInvalidoException("Erro! nome vazio!");
        }else{
            this.nome = nome;
        }
        this.id = new Random().nextInt(50);
    }

    public User(){
        this.nome = "Vistante";
        this.id = 0;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return  "ID:"+this.id+"\n"+
                "Nome:"+this.nome+"\n";
    }

    public void pesquisarLiturgia() throws IOException, InterruptedException {
        System.out.print("Digite o ano:");
        String ano = sc.nextLine();
        System.out.print("Digite o número que representa o mês EX:(Setempro = 09):");
        String mes = sc.nextLine();
        System.out.print("Digite o dia:");
        String dia = sc.nextLine();

        String data = ano.concat("-"+mes+"-"+dia);

        APIExterna.requisitarLiturgia(data);
    }
}
