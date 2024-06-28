package Munchkin.Utils;

import Munchkin.Game.Game;

/**
 * Interface to read the files that will initialize the players, itens and monsters
 */
public interface l_Arquivo {
    public void readFile(Game Jogo, String path);
}
