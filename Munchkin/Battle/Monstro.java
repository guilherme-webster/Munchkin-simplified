package Munchkin.Battle;

/**
 * Represents the monster class
 */
public class Monstro {
    private final String Name;
    private int Power;
    private int Treasure;
    private int LostLevels;

    /**
     * The monster constructor
     * @param Name the monster name
     * @param Power the monster power
     * @param Treasure the monster treasure quantity
     * @param LostLevels the lost levels due to dying to this monster
     */
    public Monstro(String Name, int Power, int LostLevels, int Treasure){
        this.Name = Name;
        this.Power = Power;
        this.LostLevels = LostLevels;
        this.Treasure = Treasure;
      }

    public String getName(){
        return this.Name;
      }

    public int getPower(){
      return this.Power;
    }

    public void setPower(int Power){
      this.Power = Power;
    }

    public int getTreasures(){
        return this.Treasure;
      }
  
    public void setTreasures(int Treasures){
      this.Treasure = Treasures;
    }

    public int getLostLevels(){
        return this.LostLevels;
      }
    
    public void setLostLevels(int LostLevels){
      this.LostLevels = LostLevels;
    }

    /**
     * Puts in a single string the attributes of the monster
     * 
     * @return returns this string
     */
    @Override
    public String toString(){
        String string = ("\nName: " + this.Name + "\nPower: " + this.Power 
        + "\nTreasures: " + this.Treasure + "\nLost Levels: " + this.LostLevels);
        return string;
      }
}
