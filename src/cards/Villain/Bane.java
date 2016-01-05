package cards.Villain;

import core.Card;
import core.Player;

public class Bane extends Card{
    
    public Bane()
    {
        name = "Bane";
        cost = 4;
        victoryPoints = 1;
        type = "Villain";
        expansion = "1DBG";
        quantity = 2;
    }
    
    public void play(Player player) throws InterruptedException
    {
        player.power = player.power + 2;
        //player.attackDiscard();
        player.opponent.listener.output.println("ATKDISCARD");
    }

}
