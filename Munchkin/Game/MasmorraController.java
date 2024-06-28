package Munchkin.Game;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Munchkin.Battle.Monstro;
import Munchkin.Items.Item;
import Munchkin.Player.Jogador;

/**
 * This class deals with the MasmorraController (MVC pattern)
 */
public class MasmorraController {
    private Masmorra model;
    private MasmorraView view;

    /**
     * MasmorraController constructor
     * @param model
     * @param view
     */
    public MasmorraController(Masmorra model, MasmorraView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Method to do the encounter of a player with a monster
     * @param player
     */
    public void openMonsterDoor(Jogador player) {
        Monstro enemy = model.getRandomMonster();
        Resultado result = model.battleMonster(player, enemy);
        int lostLevels = result == Resultado.DERROTA ? enemy.getLostLevels() : 0;
        List<Item> itensGained = result == Resultado.VITORIA ? model.getRandomItems(enemy.getTreasures()) : null;

        if (result == Resultado.VITORIA) {
            int inventoryLimit = player.getInventory().getInventoryLimit();
            int spaceAvaible = inventoryLimit - player.getInventory().getInventoryList().size();

            if (spaceAvaible <= 0) {
                view.displayInventoryFull();
            } else {
                List<Item> itensToAdd = itensGained.size() > spaceAvaible ? itensGained.subList(0, spaceAvaible) : itensGained;
                player.getInventory().getInventoryList().addAll(itensToAdd);
                view.displayMonsterBattleResult(player.getName(), enemy.getName(), result, lostLevels, itensToAdd);
            }
        } else if (result == Resultado.DERROTA) {
            int newLevel = player.getLevel() - enemy.getLostLevels();
            int actualLostLevels = player.getLevel() - 1;
            if (newLevel > 0) {
                player.setLevel(player.getLevel() - enemy.getLostLevels());
                actualLostLevels = enemy.getLostLevels();
            } else {
                player.setLevel(1);
            }
            view.displayMonsterBattleResult(player.getName(), enemy.getName(), result, actualLostLevels, null);
        } else {
            view.displayMonsterBattleResult(player.getName(), enemy.getName(), result, lostLevels, null);
        }
    }

    /**
     * Method to deal with the finding of an item by the player
     * @param player
     * @param scanner
     * @throws Exception throws exception due to the listInventory method
     */
    public void openItemDoor(Jogador player, Scanner scanner) throws Exception {
        List<Item> randomItens = model.listRandomItems();
        view.displayItemChoice(player.getName(), randomItens);

        if (scanner.hasNextLine()) {
            int choice = scanner.nextInt();
            boolean outcome;
            scanner.nextLine();
            if (choice == 0 || choice == 1) {
                Item chosenItem = randomItens.get(choice);
                model.getInventoryItem(chosenItem.getName());
                outcome = player.getInventory().addItem(chosenItem);
            } else {
                view.displayInvalidChoice();
                outcome = true;
            }
            if (!outcome) {
                view.displayInventoryFull();
            }
        } else {
            view.displayReadInputError();
        }
    }
    
    /**
     * Method to deal with the player finding items or monsters in the dungeon
     * @param player
     * @param scanner to read the player item choice
     * @throws Exception if the OpenItemDoor method falls on it's exception
     */
    public void defineRandomDoor(Jogador player, Scanner scanner) throws Exception {
        Random rand = new Random();
        int test = rand.nextInt(100);
        if (test % 2 == 0) {
            openItemDoor(player, scanner);
        } else {
            openMonsterDoor(player);
        }
    }

    public void updateView() {
        view.displayMasmorraDetails(model.getTreasureChest().getInventoryList(), model.getMonsterList());
    }
}
