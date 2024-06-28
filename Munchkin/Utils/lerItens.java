package Munchkin.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Munchkin.Game.Game;
import Munchkin.Items.Item;
import Munchkin.Items.ItemType;
import Munchkin.Player.PlayerClass;
import Munchkin.Player.Raca;
/**
 * This class implements the l_Arquivo interface to initialize the itens of the game
 */
public class lerItens implements l_Arquivo{
    List<Item> itens = new ArrayList<>();

    @Override
    public void readFile(Game Jogo, String path){
        try {
            File file = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            
            NodeList nodeList = doc.getElementsByTagName("Item");
            
            for (int i = 0; i < nodeList.getLength(); i++) {

                Element itemElement = (Element) nodeList.item(i);
                
                String nome = itemElement.getElementsByTagName("nome").item(0).getTextContent();
                
                int bonusPoder = Integer.parseInt(itemElement.getElementsByTagName("bonusPoder").item(0).getTextContent());
                
                int valor = Integer.parseInt(itemElement.getElementsByTagName("valor").item(0).getTextContent());
                
                ItemType tipo = ItemType.valueOf(itemElement.getElementsByTagName("tipo").item(0).getTextContent());
                
                Boolean eGrande = Boolean.valueOf(itemElement.getElementsByTagName("itemGrande").item(0).getTextContent());
                
                NodeList classeList = itemElement.getElementsByTagName("classe");
                List<PlayerClass> classesCompativeis = new ArrayList<>();
                for (int j = 0; j < classeList.getLength(); j++) {
                    String classeName = classeList.item(j).getTextContent();
                    classesCompativeis.add(PlayerClass.valueOf(classeName));
                }

                NodeList racaList = itemElement.getElementsByTagName("raca");
                List<Raca> racasCompativeis = new ArrayList<>();
                for (int k = 0; k < racaList.getLength(); k++) {
                    String racaName = racaList.item(k).getTextContent();
                    racasCompativeis.add(Raca.valueOf(racaName));
                }
                
                Item item = new Item(nome, bonusPoder , valor, tipo, eGrande, racasCompativeis, classesCompativeis);
                itens.add(item);
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: " + path);
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();

        };
        Jogo.setDungeonInventory(itens);
    }
}
