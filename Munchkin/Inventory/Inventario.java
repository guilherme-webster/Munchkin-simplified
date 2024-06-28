package Munchkin.Inventory;

import java.util.Iterator;
import java.util.List;

import Munchkin.Items.Item;

/**
 * Represents the Inventario class
 */
public class Inventario {
    protected int InventoryLimit;
    protected List<Item> InventoryList;

    /**
     * Inventario constructor
     * @param InventoryLimit
     * @param InventoryList List of the items in the inventory
     */
    public Inventario(int InventoryLimit, List<Item> InventoryList){
        this.InventoryLimit = InventoryLimit;
        this.InventoryList = InventoryList;
    }

    public int getInventoryLimit(){
        return this.InventoryLimit;
    }

    public void setInventoryLimit(int InventoryLimit){
        this.InventoryLimit = InventoryLimit;
    }

    public List<Item> getInventoryList(){
        return this.InventoryList;
    }

    public void setInventoryList(List<Item> newInventory){
        this.InventoryList = newInventory;
    }

    /**
     * Adds an Item to the inventory and checks if the inventory limit is not yet reached
     * @param Item
     * @return returns a true if it was possible to add the item, false otherwise.
     */
    public boolean addItem(Item Item){
        if(this.InventoryList.size() < this.InventoryLimit){
            this.InventoryList.add(Item);
            return true;
        }
        return false;
    }

    /**
     * Remomve item from the inventory by their name
     * @param name is the item name
     */
    public void removeItem(String name){
        Iterator<Item> iterator = this.InventoryList.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if(item.getName().equals(name)){
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Remove items from the inventory by their names
     * @param nomes list of items names
     */
    public void removeItens(List<String> nomes){
        Iterator<Item> iterator = this.InventoryList.iterator();
        while(iterator.hasNext()){
            Item item = iterator.next();
            if(nomes.contains(item.getName())){
                iterator.remove();
            };
        }
    }


    /**
     * Gets an inventory item looking for it's name.
     * @param name
     * @return returns the item if it was found, null otherwise.
     */
    public Item getInventoryItem(String name){
        for(Item item : this.InventoryList){
            if(item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }

    /**
     * Puts all the attributes of the Inventario in a string
     * @return returns this string
     */
    public String toString(){
        String string = "";
        for(Item item : this.InventoryList){
            string += item.toString() + "\n";
        }
        return string;
    }

    /**
     * Prints the inventory list.
     */
    public void listInventory() throws Exception{ // throws due to the listInventory overrided for TreasureChest
        for(Item item : this.InventoryList){
            System.out.println(item.toString());
        }
    }
}
