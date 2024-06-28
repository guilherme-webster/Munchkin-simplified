package Munchkin.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Munchkin.Game.Game;
import Munchkin.Inventory.Inventario;
import Munchkin.Items.Item;
import Munchkin.Player.Jogador;
import Munchkin.Player.PlayerClass;
import Munchkin.Player.Raca;

/**
 * This class implements the l_Arquivo interface to initialize the players of the game
 */
public class lerJogadores implements l_Arquivo{
    List<Jogador> jogadores = new ArrayList<>();

    @Override
    public void readFile(Game Jogo, String path){
        try {
            File file = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            
            NodeList nodeList = doc.getElementsByTagName("Jogador");
            
            for (int i = 0; i < nodeList.getLength(); i++) {

                Element jogadorElement = (Element) nodeList.item(i);
                
                String nome = jogadorElement.getElementsByTagName("nome").item(0).getTextContent();
                
                int nivel = Integer.parseInt(jogadorElement.getElementsByTagName("nivel").item(0).getTextContent());
                
                Raca raca = Raca.valueOf(jogadorElement.getElementsByTagName("raca").item(0).getTextContent());
                
                PlayerClass classe = PlayerClass.valueOf(jogadorElement.getElementsByTagName("classe").item(0).getTextContent());
                
                Jogador jogador = new Jogador(nome, nivel , raca , classe , new Inventario(50, new ArrayList<Item>()), null, null, null, null, null);
                jogadores.add(jogador);
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: " + path);
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();

        };
        Jogo.setPlayerList(jogadores);
    }
}
