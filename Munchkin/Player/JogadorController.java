package Munchkin.Player;

import java.util.ArrayList;
import java.util.List;

import Munchkin.Items.Item;

/**
 * This class deals with the JogadorController (MVC pattern)
 */
public class JogadorController {

    private Jogador model;
    private JogadorView view;

    /**
     * JogadorController constructor
     * @param model
     * @param view
     */
    public JogadorController(Jogador model, JogadorView view) {
      this.model = model;
      this.view = view;
    }

    public void setJogadorLevel(int level) {
      model.setLevel(level);
    }

    public int getJogadorLevel() {
      return model.getLevel();
    }

    /**
     * This function now deals with the player equipping new items
     * @param item
     * @param hand
     * @throws Exception
     */
    public void equipItem(Item item, String hand) {

        boolean classCompability = checkCompabilityClass(item);
        boolean raceCompability = checkCompabilityRace(item);
        // Only tries to equip item if it's compatible
        if (!classCompability || !raceCompability) {
            view.displayIncompatibleItem(model.getName(), item);
            return;
        }
        // If the player already has a big item, he can't equip another one
        if (item.getisBigItem() && hasBigItem()) {
            view.displayTooManyBigItems(model.getName());
            return;
        }

        Item previousItem = null;

        switch (item.getType()) {
        case CABECA:
            previousItem = model.getHeadItem();
            model.setHeadItem(item);
            break;
        case CORPO:
            previousItem = model.getBodyItem();
            model.setBodyItem(item);
            break;
        case MAO:
        if ("left".equalsIgnoreCase(hand)) {
            previousItem = model.getLeftItem();
            model.setLeftItem(item);
        } else {
            previousItem = model.getRightItem();
            model.setRightItem(item);
        }
            break;
        case PE:
            previousItem = model.getFootItem();
            model.setFootItem(item);
            break;
        }

        // We store the previous equipped item in the inventory, if it's not null
        if (previousItem != null) {
           model.getInventory().addItem(previousItem);
        }

        // Removes the new equipped item from the Inventory, we should only remove the item if the try to equip it was sucessful
        model.getInventory().removeItem(item.getName());
        view.displayEquipItem(model.getName(), item);

    }

    public boolean checkCompabilityClass(Item item) {
        for (PlayerClass playerClass : item.getCompatibleClasses()) {
            if (playerClass == model.getPlayerClass()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCompabilityRace(Item item) {
        for (Raca playerRace : item.getCompatibleRaces()) {
            if (playerRace == model.getRace()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasBigItem() {
        return (model.getRightItem() != null && model.getRightItem().getisBigItem()) ||
               (model.getLeftItem() != null && model.getLeftItem().getisBigItem()) ||
               (model.getFootItem() != null && model.getFootItem().getisBigItem()) ||
               (model.getHeadItem() != null && model.getHeadItem().getisBigItem()) ||
               (model.getBodyItem() != null && model.getBodyItem().getisBigItem());
    }

    /**
     * This function sells the player inventory items to increase it's level by the revenue / 1000
     * @param ItensToSell
     * @return true if the player was able to sell it's itens, and false otherwise
     */
    public boolean sellItens(List<Item> ItensToSell) {
        int totalRevenue = 0;
        for (Item item : ItensToSell) {
            totalRevenue += item.getValue();
        }

        if (totalRevenue >= 1000) {
            // if we sold at least 1000, the level should be incremented by totalRevenue / 1000 (int)
            int levelIncrease = totalRevenue / 1000;
            model.setLevel(model.getLevel() + levelIncrease);

            List<String> itensSoldNames = new ArrayList<>();
            for (Item item : ItensToSell) {
                itensSoldNames.add(item.getName());
            }
            view.displaySellItens(model.getName(), ItensToSell);
            model.getInventory().removeItens(itensSoldNames);
            return true;
        }
        // if the player does not have enough value to sell 1000 in item value, nothing happens
        view.displaySellFail(model.getName());
        return false;
    }

    /**
     * This method gets the player name, level, power, and items
     * so the View can show them to the user.
     */
    public void updateView() {
        view.displayJogadorDetails(
            model.getName(),
            model.getLevel(),
            model.playerPower(),
            model.getHeadItem(),
            model.getBodyItem(),
            model.getRightItem(),
            model.getLeftItem(),
            model.getFootItem()
        );
    }

    public void updateInventoryView() {
        view.displayJogadorInventory(
            model.getInventory()
        );
    }
}
