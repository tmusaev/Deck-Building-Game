package cards.Basic;

import core.Card;
import core.Player;

public class Punch extends Card{
    public Punch()
    {
        name = "Punch";
        cost = 0;
        victoryPoints = 0;
        type = "Starter";
    }
    
    public void play(Player player)
    {
        player.power = player.power + 1;
    }
    
}
