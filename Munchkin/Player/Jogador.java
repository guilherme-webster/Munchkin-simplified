package Munchkin.Player;
import Munchkin.Inventory.Inventario;
import Munchkin.Items.Item;

/**
 * Represents the player class (Model)
 */

public class Jogador {
    private final String Name;
    private int Level;
    private Raca Race;
    private PlayerClass PlayerClass;
    private Inventario Inventory;
    protected Item HeadItem;
    protected Item BodyItem;
    protected Item RightItem;
    protected Item LeftItem;
    protected Item FootItem;

  /**
   * Player constructor
   * @param Name the player name
   * @param Level the player level
   * @param Race the player race
   * @param PlayerClass the player class
   * @param Inventory
   * @param HeadItem
   * @param BodyItem
   * @param RightItem
   * @param LeftItem
   * @param FootItem
   */  
  public Jogador(String Name, int Level, Raca Race, PlayerClass PlayerClass, 
  Inventario Inventory, Item HeadItem, Item BodyItem, Item RightItem, Item LeftItem,
  Item FootItem){
    this.Name = Name;
    this.Level = Level;
    this.Race = Race;
    this.PlayerClass = PlayerClass;
    this.Inventory = Inventory;
    this.HeadItem = HeadItem;
    this.BodyItem = BodyItem;
    this.RightItem = RightItem;
    this.LeftItem = LeftItem;
    this.FootItem = FootItem;
  }
  

  public String getName(){
    return this.Name;
  }

  public int getLevel(){
    return this.Level;
  }

  public void setLevel(int Level){
    this.Level = Level;
  }

  public Raca getRace(){
    return this.Race;
  }

  public void setRace(Raca Race){
    this.Race = Race;
  }

  public PlayerClass getPlayerClass(){
    return this.PlayerClass;
  }

  public void setPlayerClass(PlayerClass PlayerClass){
    this.PlayerClass = PlayerClass;
  }

  public Inventario getInventory(){
    return this.Inventory;
  }

  public Item getHeadItem(){
    return this.HeadItem;
  }

  public void setHeadItem(Item HeadItem) {
    this.HeadItem = HeadItem;
  }

  public Item getBodyItem(){
    return this.BodyItem;
  }

  public void setBodyItem(Item BodyItem) {
    this.BodyItem = BodyItem;
  }

  public Item getRightItem() {
    return this.RightItem;
  }

  public void setRightItem(Item RightItem) {
    this.RightItem = RightItem;
  }

  public Item getLeftItem() {
      return this.LeftItem;
  }

  public void setLeftItem(Item LeftItem) {
      this.LeftItem = LeftItem;
  }

  public Item getFootItem() {
      return this.FootItem;
  }

  public void setFootItem(Item FootItem) {
      this.FootItem = FootItem;
  }

  /**
   * Puts in a single string the attributes of the player
   * @return returns this string
   */
  @Override
  public String toString(){
    String string = ("Nome do jogador: " + this.getName() + " Poder do jogador: " + this.playerPower() + " - Nível do jogador: " + this.getLevel() + " - Item da cabeça: " + this.getHeadItem() + 
    " - Item do corpo: " + this.getBodyItem() + " - Item da mão direita: " + this.getRightItem() + " - Item da mao esquerda: " + this.getLeftItem() +
    " - Item do pé: " + this.getFootItem());
    return string;
  }

  /**
   * Checks all the items equipped, sums their power bonus with the player level
   * @return returns an int
   */
  public int playerPower(){
    int power = this.Level;

    if(this.getRightItem() != null){
      power += this.getRightItem().getPowerBonus();
    }
    if(this.getLeftItem() != null){
      power += this.getLeftItem().getPowerBonus();
    }
    if(this.getFootItem() != null){
      power += this.getFootItem().getPowerBonus();
    }
    if(this.getHeadItem() != null){
      power += this.getHeadItem().getPowerBonus();
    }
    if(this.getBodyItem() != null){
      power += this.getBodyItem().getPowerBonus();
    }

    return power;
    
  }
}
