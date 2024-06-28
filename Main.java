import java.util.ArrayList;
import java.util.List;

import Munchkin.Battle.Monstro;
import Munchkin.Game.Game;
import Munchkin.Game.Masmorra;
import Munchkin.Inventory.TreasureChest;
import Munchkin.Items.Item;
import Munchkin.Player.Jogador;
import Munchkin.Utils.lerItens;
import Munchkin.Utils.lerJogadores;
import Munchkin.Utils.lerMonstros;


public class Main {
    public static void main(String[] args) throws Exception {
        lerJogadores leitorDeJogadores = new lerJogadores();
        lerItens leitorDeItens = new lerItens();
        lerMonstros leitorDeMonstros = new lerMonstros();
        List<Jogador> players = new ArrayList<>();

        TreasureChest dungeonChest = TreasureChest.getInstance(100, new ArrayList<Item>());
        List<Monstro> monsterList = new ArrayList<Monstro>();
        Masmorra dungeon = new Masmorra(dungeonChest, monsterList);     
        
        Game mainLoop = new Game(dungeon, players);

        leitorDeJogadores.readFile(mainLoop, "Resources/Jogadores.xml");
        leitorDeItens.readFile(mainLoop, "Resources/Itens.xml");
        leitorDeMonstros.readFile(mainLoop, "Resources/Monstros.xml");

        mainLoop.gameLoop();
    }
}
