package Munchkin.Game;
import java.util.Scanner;

import Munchkin.Items.Item;
import Munchkin.Items.ItemType;
import Munchkin.Player.Jogador;
import Munchkin.Player.JogadorController;
import Munchkin.Player.JogadorView;

/**
* This class deals with the player keyboard interactions
*/
public class PlayerInteractions {
    /**
    * Deals with the player actions till it enters the dungeon
    * @param player
    * @param scanner
    * @return A boolean that defines if the player entered the dungeon or not
    * @throws Exception throws exception due to the exceptions in equipItem and listInventory
    */
    public static boolean doPlayerAction(Jogador player, Scanner scanner) throws Exception{
        System.out.println("\nEscolha uma ação:");
        System.out.println("1 - Listar os itens do inventário");
        System.out.println("2 - Equipe itens do inventário");
        System.out.println("3 - Vender itens do inventário");
        System.out.println("4 - Ver o poder total que possui, seu nível e os itens equipados");
        System.out.println("5 - Passar para abrir a Masmorra.");
        System.out.println("0 - Sair do jogo");
        System.out.print("Escolha: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
    
        JogadorView jogadorView = new JogadorView();
        JogadorController jogadorController = new JogadorController(player, jogadorView);
        switch (choice) {
            case 1:
                jogadorController.updateInventoryView();
                return false;
            case 2:
                System.out.print("Índice do item a ser equipado: ");
                int index = scanner.nextInt();
                scanner.nextLine();
                Item itemToEquip = player.getInventory().getInventoryList().get(index);
                String hand = "right"; // Default to right hand
                if (itemToEquip.getType() == ItemType.MAO) {
                    System.out.print("Escolha a mão para equipar (left/right): ");
                    hand = scanner.nextLine();
                }
                jogadorController.equipItem(itemToEquip, hand);
                return false;
            case 3:
                jogadorController.sellItens(player.getInventory().getInventoryList());
                return false;
            case 4:
                jogadorController.updateView();
                return false;
            case 5:
                return true; // Player chose to enter the dungeon
            case 0:
                System.exit(0); // Player chose to end game
                return true;
            default:
                System.out.println("Escolha inválida. Por favor, digite um número entre 1 e 5.");
                return false;
        }
    }

    /**
     * Deals with the player actions after it entered the dungeon
     * @param player
     * @param scanner
     * @return A boolean that defines if the player passed his turn or not
     * @throws Exception throws exception due to the exceptions in equipItem and listInventory
     */
    public static boolean doPlayerActionAfterDungeon(Jogador player, Scanner scanner) throws Exception{
        System.out.println("\nEscolha uma ação:");
        System.out.println("1 - Listar os itens do inventário");
        System.out.println("2 - Equipe itens do inventário");
        System.out.println("3 - Vender itens do inventário");
        System.out.println("4 - Ver o poder total que possui, seu nível e os itens equipados");
        System.out.println("5 - Finalizar turno");
        System.out.println("0 - Sair do jogo");
        System.out.print("Escolha: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
    
        JogadorView jogadorView = new JogadorView();
        JogadorController jogadorController = new JogadorController(player, jogadorView);
        switch (choice) {
            case 1:
                jogadorController.updateInventoryView();
                return false;
            case 2:
                System.out.print("Índice do item a ser equipado: ");
                int index = scanner.nextInt();
                scanner.nextLine();
                Item itemToEquip = player.getInventory().getInventoryList().get(index);
                String hand = "right"; // Default to right hand
                if (itemToEquip.getType() == ItemType.MAO) {
                    System.out.print("Escolha a mão para equipar (left/right): ");
                    hand = scanner.nextLine();
                }
                jogadorController.equipItem(itemToEquip, hand);
                return false;
            case 3:
                jogadorController.sellItens(player.getInventory().getInventoryList());
                return false;
            case 4:
                jogadorController.updateView();
                return false;
            case 5:
                return true;
            case 0:
                System.exit(0); // Player chose to end game
                return true;
            default:
                System.out.println("Escolha inválida. Por favor, digite um número entre 1 e 5.");
                return false;
        }
    }

    /**
     * Adds 5 random itens to the inventory of all the players in the game
     * @param player
     * @param dungeon
     * @param scanner to deal with the player keyboard inputs
     * @return A boolean to define if the player wants to pass his turn or not
     * @throws Exception if one of the methods called falls on one of its exceptions
     */
    public static boolean initializePlayerItems(Jogador player, Masmorra dungeon,Scanner scanner) throws Exception{

        System.out.println("\nEscolha uma ação:");
        System.out.println("1 - Listar os itens do inventário");
        System.out.println("2 - Equipe itens do inventário");
        System.out.println("3 - Passar turno");
        System.out.println("0 - Sair do jogo");
        System.out.print("Escolha: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
    
        JogadorView jogadorView = new JogadorView();
        JogadorController jogadorController = new JogadorController(player, jogadorView);
        switch (choice) {
            case 1:
                jogadorController.updateInventoryView();
                return false;
            case 2:
                System.out.print("Índice do item a ser equipado: ");
                int index = scanner.nextInt();
                scanner.nextLine();
                Item itemToEquip = player.getInventory().getInventoryList().get(index);
                String hand = "right"; // Default to right hand
                if (itemToEquip.getType() == ItemType.MAO) {
                    System.out.print("Escolha a mão para equipar (left/right): ");
                    hand = scanner.nextLine();
                }
                jogadorController.equipItem(itemToEquip, hand);
                return false;
            case 3:
                return true;
            case 0:
                System.exit(0); // Player chose to end game
            default:
                System.out.println("Escolha inválida. Por favor, digite um número entre 0 e 2.");
                return false;
        }
    }
}
