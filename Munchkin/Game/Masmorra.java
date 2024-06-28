package Munchkin.Game;

import java.util.List;
import java.util.Random;

import Munchkin.Battle.BattleField;
import Munchkin.Battle.Monstro;
import Munchkin.Inventory.TreasureChest;
import Munchkin.Items.Item;
import Munchkin.Player.Jogador;
/**
 * Represents the dungeon class
 */
public class Masmorra {
    private List<Monstro> Monsters;
    private TreasureChest TreasureChest;

    /**
     * Dungeon constructor
     * @param TreasureChest
     * @param Monsters
     */
    public Masmorra(TreasureChest TreasureChest, List<Monstro> MonsterList){
        this.TreasureChest = TreasureChest;
        this.Monsters = MonsterList;
    }

    public TreasureChest getTreasureChest(){
        return this.TreasureChest;
    }

    public void setTreasureChest(TreasureChest newTreasureChest){
        this.TreasureChest = newTreasureChest;
    }

    public void setMonsterList(List<Monstro> MonsterList){
        this.Monsters = MonsterList;
    }

    public List<Monstro> getMonsterList(){
        return this.Monsters;
    }

    public Monstro getRandomMonster() {
        Random random = new Random();
        int enemyIndex = random.nextInt(this.Monsters.size());
        return this.Monsters.get(enemyIndex);
    }

    public Resultado battleMonster(Jogador player, Monstro enemy) {
        return BattleField.Battle(enemy, player);
    }

    public List<Item> getRandomItems(int count) {
        return this.TreasureChest.getRandomItems(count);
    }

    public List<Item> listRandomItems() throws Exception {
        return this.TreasureChest.listRandomItems();
    }

    public Item getInventoryItem(String name) {
        return this.TreasureChest.getInventoryItem(name);
    }

    /**
     * Method to put the Dungeon information on a single string
     * @return this string
     */
    @Override
    public String toString(){
        String string = ("Lista de Monstros: " + this.Monsters + "\nLista de itens: " + this.TreasureChest);
        return string;
    }
}