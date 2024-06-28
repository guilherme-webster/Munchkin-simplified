package Munchkin.Game;

import java.util.List;

import Munchkin.Battle.Monstro;
import Munchkin.Items.Item;

/**
 * This class deals with the MasmorraView (MVC pattern)
 */
public class MasmorraView {
    /**
     * Displays the number of itens in the treasure chest and the monsters in the dungeon
     * @param itens
     * @param monstros
     */
    public void displayMasmorraDetails(List<Item> itens, List<Monstro> monstros) {
        System.out.println("Itens no Baú: " + itens.size());
        System.out.println("Monstros na Masmorra: " + monstros.size());
    }

    public void displayItemChoice(String playerName, List<Item> randomItens) {
        System.out.println(playerName + " encontrou os seguintes itens:");
        for (int i = 0; i < randomItens.size(); i++) {
            System.out.println(i + ": " + randomItens.get(i));
        }
        System.out.println("Digite 0 para escolher o primeiro item ou 1 para escolher o segundo item: ");
    }

    public void displayInvalidChoice() {
        System.out.println("Você não digitou um índice válido.");
    }

    public void displayInventoryFull() {
        System.out.println("Erro: você atingiu o limite de itens do seu inventário. Para equipar outros itens, você deve livrar-se de alguns itens do seu inventário.");
    }

    public void displayReadInputError() {
        System.out.println("Erro ao ler entrada.");
    }

    public void displayPlayerFlee(String result) {
        System.out.println(result + "\nO jogador conseguiu fugir da masmorra.");
    }

    /**
     * Displays the monster encountered and the result of the battle
     * @param playerName
     * @param monsterName
     * @param result
     * @param lostLevels
     * @param itensGained
     */
    public void displayMonsterBattleResult(String playerName, String monsterName, Resultado result, int lostLevels, List<Item> itensGained) {
        switch (result) {
            case VITORIA:
                System.out.println(playerName + " enfrenta um " + monsterName + "\nVitória!\nItens ganhos: " + itensGained);
                break;
            case DERROTA:
                System.out.println(playerName + " enfrenta um " + monsterName + "\nDerrota!\nNíveis perdidos: " + lostLevels);
                break;
            case FUGA:
                System.out.println(playerName + " enfrenta um " + monsterName + "\nFuga!\nO jogador conseguiu fugir da masmorra.");
                break;
        }
    }
}
