package cards.Hero;

import core.Card;
import core.Player;

public class Catwoman extends Card{
    
    public Catwoman()
    {
        name = "Catwoman";
        cost = 2;
        victoryPoints = 1;
        type = "Hero";
        expansion = "1DBG";
        quantity = 3;
    }
    
    public void play(Player player)
    {
        player.power = player.power + 2;
    }
    
}
