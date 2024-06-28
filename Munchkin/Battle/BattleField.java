package Munchkin.Battle;

import java.util.Random;

import Munchkin.Game.Resultado;
import Munchkin.Player.Jogador;
/**
 * Represents the battlefield class
 */
public class BattleField {
    /**
     * Manages the fight between a player and  a monster
     * @param monster
     * @param player
     * @return returns the result of the fight: {VITORIA, DERROTA, FUGA}
     */
    public static Resultado Battle(Monstro monster, Jogador player){
        if(player.playerPower() > monster.getPower()){
           return Resultado.VITORIA;
        } else{
            Random random = new Random();

            int diceResult = random.nextInt(6) + 1;

            if(diceResult >= 5){
                return Resultado.FUGA;
            } else {
                return Resultado.DERROTA;
            }
        }
    }
}
