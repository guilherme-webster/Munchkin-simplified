package Munchkin.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Munchkin.Battle.Monstro;
import Munchkin.Game.Game;
/**
 * This class implements the l_Arquivo interface to initialize the monsters of the game
 */
public class lerMonstros implements l_Arquivo{
     List<Monstro> monstros = new ArrayList<>();

    @Override
    public void readFile(Game Jogo, String path){
        try {
            File file = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            
            NodeList nodeList = doc.getElementsByTagName("Monstro");
            
            for (int i = 0; i < nodeList.getLength(); i++) {

                Element itemElement = (Element) nodeList.item(i);
                
                String nome = itemElement.getElementsByTagName("nome").item(0).getTextContent();
                
                int poder = Integer.parseInt(itemElement.getElementsByTagName("poder").item(0).getTextContent());
                
                int tesouros = Integer.parseInt(itemElement.getElementsByTagName("tesouros").item(0).getTextContent());

                int niveisPerdidos = Integer.parseInt(itemElement.getElementsByTagName("niveisPerdidos").item(0).getTextContent());
                
                Monstro monstro = new Monstro(nome, poder, niveisPerdidos, tesouros);
                monstros.add(monstro);
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: " + path);
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();

        };
        Jogo.setDungeonMonsters(monstros);
    }
}
