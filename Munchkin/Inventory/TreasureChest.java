package Munchkin.Inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Munchkin.Items.Item;

/**
 * Represents the TreasureChest class
 */
public class TreasureChest extends Inventario{

    private static TreasureChest instancia;

    /**
     * TreasureChest constructor
     * @param InventoryLimit
     * @param InventoryList
     */
    private TreasureChest (int InventoryLimit, List<Item> InventoryList){
        super(InventoryLimit, InventoryList);
    }


    public static TreasureChest getInstance(int InventoryLimit, List<Item> InventoryList) {
        if (instancia == null){
            instancia = new TreasureChest(InventoryLimit, InventoryList);
        }
        return instancia;
    }


    /**
     * Different from the mother class, the itens acessed are removed from the chest inventory
     */
    @Override
    public Item getInventoryItem(String name){
        Iterator<Item> iterator = this.InventoryList.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if(item.getName().equals(name)){
                iterator.remove();
                return item;
            }
        }
        return null;
    }
    
    /**
     * Different from the mother class, only two itens are listed and they are chosen randomly
     */
    public List<Item> listRandomItems() throws Exception{
        List<Item> randomItens = new ArrayList<Item>();

        if (this.InventoryList.size() < 2) {
            throw new IllegalArgumentException("Inventário do baú precisa ter pelo menos 2 itens para listar.");
        }

        Random rand = new Random();
        int firstIdx = rand.nextInt(this.InventoryList.size());
        int secondIdx = firstIdx;
        while(firstIdx == secondIdx){
            secondIdx = rand.nextInt(this.InventoryList.size());
        }
        
        randomItens.add(this.InventoryList.get(firstIdx));
        randomItens.add(this.InventoryList.get(secondIdx));

        return randomItens;
    }

    /**
     * Get random itens from the chest and remove them
     * @param n number of random items gotten
     * @return
     */
    public List<Item> getRandomItems(int n){

        if(n > this.InventoryList.size()){
            System.out.println("Solicitando mais itens que o disponível");
            n = this.InventoryList.size();
        }

        Collections.shuffle(this.InventoryList);
        List<Item> selectedItems = new ArrayList<>();

        for(int i = 0; i < n; i++){
            selectedItems.add(this.InventoryList.get(i));
        }

        this.InventoryList.removeAll(selectedItems);

        return selectedItems;
    }
}
