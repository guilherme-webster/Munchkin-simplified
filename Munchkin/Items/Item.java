package Munchkin.Items;
import java.util.List;

import Munchkin.Player.PlayerClass;
import Munchkin.Player.Raca;
/**
 * Represents the item class
 */
public class Item {
    private final String Name;
    private int PowerBonus;
    private int Value;
    private ItemType Type;
    private boolean BigItem;
    private List<PlayerClass> CompatibleClasses;
    private List<Raca> CompatibleRaces;

    /**
     * Item constructor
     * @param Name the item name
     * @param PowerBonus the bonus to the power due to the item
     * @param Value the item value
     * @param Type the item type
     * @param isBigItem
     * @param CompatibleRaces
     * @param CompatibleClasses
     */
    public Item(String Name, int PowerBonus, int Value, ItemType Type, boolean isBigItem, List<Raca> CompatibleRaces,
                List<PlayerClass> CompatibleClasses){
      this.Name = Name;
      this.PowerBonus = PowerBonus;
      this.Value = Value;
      this.Type = Type;
      this.BigItem = isBigItem;
      this.CompatibleRaces = CompatibleRaces;
      this.CompatibleClasses = CompatibleClasses;
      } 

    public String getName(){
        return this.Name;
      }

    public int getPowerBonus(){
      return this.PowerBonus;
    }

    public void setPowerBonus(int PowerBonus){
      this.PowerBonus = PowerBonus;
    }

    public int getValue(){
        return this.Value;
      }
  
    public void setValue(int Value){
      this.Value = Value;
    }

    public ItemType getType(){
        return this.Type;
      }
    
    public void setType(ItemType Type){
      this.Type = Type;
    }  

    public void setBigItem(boolean Value){
      this.BigItem = Value;
    }

    public boolean getisBigItem(){
      return this.BigItem;
    }

    public List<PlayerClass> getCompatibleClasses(){
      return this.CompatibleClasses;
    }
  
    public void setCompatibleClasses(List<PlayerClass> CompatibleClasses){
      this.CompatibleClasses = CompatibleClasses;
    }

    public List<Raca> getCompatibleRaces(){
      return this.CompatibleRaces;
    }
  
    public void setCompatibleRaces(List<Raca> CompatibleRaces){
      this.CompatibleRaces = CompatibleRaces;
    }

    /**
     * Puts in a single string the attributes of the item
     * 
     * @return returns this string
     */
    @Override
    public String toString(){
        String string = ("Nome: " + this.Name + " - Nível: " + this.getValue());
        return string;
      }
}
