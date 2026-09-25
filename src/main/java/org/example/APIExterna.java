package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.example.models.LiturgiaDiaria;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIExterna {

    private static final String URL = "https://api-liturgia-diaria.vercel.app/?date=";
    private static final Gson gson = new Gson();

    public static void requisitarLiturgia(String data) throws IOException, InterruptedException {

        HttpClient cliente = HttpClient
                .newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest requisicao = HttpRequest
                .newBuilder()
                .uri(URI.create(URL+data))
                .GET()
                .build();

        HttpResponse<String> resposta = cliente.send(requisicao,HttpResponse.BodyHandlers.ofString());

        try {
            LiturgiaDiaria liturgia = gson.fromJson(resposta.body(),LiturgiaDiaria.class);
            exibirLiturgia(liturgia);
        }catch (JsonSyntaxException e){
            System.out.println("ERRO!!! Data inválida!");
        }
    }

    private static void exibirLiturgia(LiturgiaDiaria liturgia){
        System.out.println(
                "Título:"+liturgia.today().entry_title()+"\n"+
                "Cor:"+liturgia.today().color()+"\n"+
                "Data:"+liturgia.today().date()+"\n");

        System.out.println("*****LEITURA INICIAL*****");
        System.out.println(
                "Chamada:"+liturgia.today().readings().first_reading().footer()+"\n"+
                        "Resposta da chamada:"+liturgia.today().readings().first_reading().footer_response()+"\n"+
                        "Cabeça:"+liturgia.today().readings().first_reading().head()+"\n"+
                        "Título:"+liturgia.today().readings().first_reading().title()+"\n"+
                        "Texto:"+liturgia.today().readings().first_reading().text()+"\n");

        System.out.println("*****EVANGELHO*****");
        System.out.println(
                "Chamada"+liturgia.today().readings().gospel().footer()+"\n"+
                        "Resposta da chamada:"+liturgia.today().readings().gospel().footer_response()+"\n"+
                        "Cabeça:"+liturgia.today().readings().gospel().head()+"\n"+
                        "Resposta da cabeça:"+liturgia.today().readings().gospel().head_response()+"\n"+
                        "Título da cabeça:"+liturgia.today().readings().gospel().head_title()+"\n"+
                        "Texto:"+liturgia.today().readings().gospel().text()+"\n"+
                        "Título:"+liturgia.today().readings().gospel().title()+"\n");

        System.out.println("*****SALMOS*****");
        System.out.println(
                "Título:"+liturgia.today().readings().psalm().title()+"\n");

        liturgia.today().readings().psalm().content_psalm().forEach(System.out::println);
        System.out.println("Resposta:"+liturgia.today().readings().psalm().response()+"\n");

        System.out.println("*****SEGUNDA LEITURA*****");
        try {
            System.out.println(
                    "Chamada:"+liturgia.today().readings().second_reading().footer()+"\n"+
                            "Resposta da chamada:"+liturgia.today().readings().second_reading().footer_response()+"\n"+
                            "Cabeça:"+liturgia.today().readings().second_reading().head()+"\n"+
                            "Texto:"+liturgia.today().readings().second_reading().text()+"\n");
        }catch (NullPointerException e){
            System.out.println("Hoje não é proclamado uma segunda leitura, pois não é domingo e nem dia de solenidade.\n");
        }
    }
}
