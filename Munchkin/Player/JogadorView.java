package Munchkin.Player;

import java.util.List;

import Munchkin.Inventory.Inventario;
import Munchkin.Items.Item;

/**
 * This class deals with the JogadorView (MVC pattern)
 */
public class JogadorView {
    public void displayJogadorDetails(String name, int level, int power, Item HeadItem, Item BodyItem, 
    Item RightItem, Item LeftItem, Item FootItem) {
        System.out.println("Nome do Jogador: " + name);
        System.out.println("Nível do Jogador: " + level);
        System.out.println("Poder do Jogador: " + power);
        System.out.println("Item da cabeça: " + HeadItem);
        System.out.println("Item do corpo: " + BodyItem);
        System.out.println("Item da mão direita: " + RightItem);
        System.out.println("Item da mão esquerda: " + LeftItem);
        System.out.println("Item do pé: " + FootItem);
    }

    public void displayJogadorInventory(Inventario inventory){
        System.out.println("Inventário do jogador:");
        System.out.println(inventory);
    }

    public void displayEquipItem(String playerName, Item item) {
        System.out.println(playerName + " equipou o item: " + item.getName());
    }

    public void displaySellItens(String playerName, List<Item> itens) {
        System.out.println(playerName + " vendeu os itens: " + itens);
    }

    public void displaySellFail(String playerName) {
        System.out.println("Nenhum item foi vendido.");
        System.out.println(playerName + " não tinha valor suficiente para vender.");
    }

    public void displayIncompatibleItem(String playerName, Item item) {
        System.out.println(playerName + " não pode equipar o item: " + item.getName() + " (incompatível).");
    }

    public void displayTooManyBigItems(String playerName) {
        System.out.println(playerName + " não pode equipar mais de um item grande.");
    }
}
