package Munchkin.Game;

import java.util.List;
import java.util.Scanner;

import Munchkin.Battle.Monstro;
import Munchkin.Items.Item;
import Munchkin.Player.Jogador;
import Munchkin.Player.JogadorController;
import Munchkin.Player.JogadorView;

/**
 * This class deals with the game loop
*/
public class Game {

    private Masmorra Dungeon;
    private MasmorraController dungeonController;
    private MasmorraView dungeonView;
    private List<Jogador> PlayerList;
    private static final int WINNING_LEVEL = 10;

    public Game(Masmorra Dungeon, List<Jogador> PlayerList) {
        this.Dungeon = Dungeon;
        this.PlayerList = PlayerList;
        this.dungeonView = new MasmorraView();
        this.dungeonController = new MasmorraController(Dungeon, dungeonView);
    }

    public void setPlayerList(List<Jogador> newPlayerList){
        this.PlayerList = newPlayerList;
    }

    public Masmorra getGameDungeon(){
        return this.Dungeon;
    }

    public void setDungeonInventory(List<Item> newItemList){
        this.Dungeon.getTreasureChest().setInventoryList(newItemList);
    }

    public void setDungeonMonsters(List<Monstro> MonsterList){
        this.Dungeon.setMonsterList(MonsterList);
    }
    /**
     * This method runs the game loop
     * @throws Exception if the TreasureChest does not have enough items
     */
    public void gameLoop() throws Exception{
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        boolean initItens = false;

        for(Jogador currentPlayer : this.PlayerList){
            
            List<Item> selectedItens = this.Dungeon.getTreasureChest().getRandomItems(5);
            currentPlayer.getInventory().getInventoryList().addAll(selectedItens);

        }

        for(Jogador currentPlayer : this.PlayerList){
            JogadorView jogadorView = new JogadorView();
            JogadorController jogadorController = new JogadorController(currentPlayer, jogadorView);
                        
            initItens = false;
            jogadorController.updateView();
            while(!initItens){
                initItens = PlayerInteractions.initializePlayerItems(currentPlayer, Dungeon, scanner);
            }
            jogadorController.updateView();
            System.out.println("");
        }

        while (run) {
            for(Jogador currentPlayer : this.PlayerList){
                JogadorView jogadorView = new JogadorView();
                JogadorController jogadorController = new JogadorController(currentPlayer, jogadorView);

                jogadorController.updateView();

                boolean openDungeon = false;
                // Manages the player actions before entering the dungeon
                while (!openDungeon) {
                    openDungeon = PlayerInteractions.doPlayerAction(currentPlayer, scanner);
                }

                this.dungeonController.defineRandomDoor(currentPlayer, scanner);

                System.out.println("Você tem chance de realizar ações antes de passar o turno.");
                boolean passTurn = false;
                // Manages the player actions after he has entered the dungeon
                while (!passTurn) {
                    passTurn = PlayerInteractions.doPlayerActionAfterDungeon(currentPlayer, scanner);
                    System.out.println("");
                }
                jogadorController.updateView();
                System.out.println("");

                if (checkWinCondition(currentPlayer)) {
                    System.out.println("Parabéns, " + currentPlayer.getName() + "! Você alcançou o nível " + WINNING_LEVEL + " e venceu o jogo!");
                    run = false;
                    break;
                }
            }
        }

        scanner.close();
    }

    private boolean checkWinCondition(Jogador player) {
        return player.getLevel() >= WINNING_LEVEL;
    }

   /**
   * Puts in a single string the attributes of the game
   * @return returns this string
   */
  @Override
  public String toString(){
    String string = ("Dungeon: " + this.Dungeon.toString() + "\nPlayers: " + this.PlayerList.toString());
    return string;
  }
}
