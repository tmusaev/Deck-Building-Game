package core;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Player {
    public PlayerDeck deck;
    public PlayerHand hand;
    public PlayerInPlay inPlay;
    public PlayerDiscardPile discardPile;
    public int power;
    public Listener listener;
    Table table;
    ArrayList<ArrayList<String>> gameState = new ArrayList();
    ArrayList<ArrayList<String>> oppState = new ArrayList();
    public Player opponent;
    
    public Player(Table t, Socket s)
    {
        power = 0;
        deck = new PlayerDeck();
        hand = new PlayerHand(deck);
        inPlay = new PlayerInPlay();
        discardPile = new PlayerDiscardPile();
        table = t;
        gameState = updateGameState();
        listener = new Listener(this, s, gameState);
    }
    
    public void setOpponent(Player o) throws IOException
    {
        opponent = o;
        oppState = updateOppState();
        listener.setOppState(oppState);
    }
    
    public void startListening() throws IOException
    {
        //listener.writeOpp();
        listener.start();
    }
    
    public synchronized ArrayList<ArrayList<String>> updateGameState()
    {
        ArrayList<ArrayList<String>> g = new ArrayList();
        g.add(hand.getString());
        g.add(inPlay.getString());
        g.add(discardPile.getString());
        g.add(deck.getString());
        g.add(table.mainDeck.getString());
        g.add(table.lineUp.getString());
        g.add(table.kickStack.getString());
        g.add(table.weaknessStack.getString());
        g.add(table.superVillainStack.getString());
        //g.add(opponent.hand.getString());
        //g.add(opponent.inPlay.getString());
        //g.add(opponent.discardPile.getString());
        return g;
    }
    
    public ArrayList<ArrayList<String>> updateOppState()
    {
        ArrayList<ArrayList<String>> g = new ArrayList();
        g.add(opponent.hand.getString());
        g.add(opponent.inPlay.getString());
        g.add(opponent.discardPile.getString());
        g.add(opponent.deck.getString());
        ArrayList<String> pow = new ArrayList();
        pow.add(Integer.toString(opponent.power));
        g.add(pow);
        return g;
    }
    
    public int getpower()
    {
        return power;
    }
    
    public void drawCard()
    {
        if(deck.count() == 0)
        {
            ArrayList<Card> d = discardPile.getZone();
            deck.refill(d);
            discardPile.removeAll();
        }
        
        if(deck.count() > 0)
        {
            Card c = deck.giveTopCard();
            hand.addCard(c);
        }
    }
    
    public void playCard(int id) throws IOException, InterruptedException
    {
        if(id <= hand.count()-1)
        {
            Card c = hand.getCard(id);
            inPlay.addCard(c);
            c.play(this);
            Thread.sleep(500);
            listener.update();
            Game.updateOpponent();
        }
    }
    
    public boolean affordCard(Card c)
    {
        if(power >= c.getcost())
            return true;
        else
            return false;
    }
    public void buyCard(Card c)
    {
        power = power - c.getcost();
        gainCard(c);
    }
    
    public void buyLineUp(int index) throws IOException
    {
        Card c = table.lineUp.buyCard(index);
        gainCard(c);
        Game.updateOpponent();
    }
    
    public void buyKick() throws IOException
    {
        Card c = table.kickStack.getCard();
        gainCard(c);
        Game.updateOpponent();
    }
    
    public void buySuperVillain() throws IOException
    {
        Card c = table.superVillainStack.getCard();
        gainCard(c);
        Game.updateOpponent();
    }       
    
    public void gainCard(Card c)
    {
        discardPile.addCard(c);
    }
    
    public void endTurn() throws IOException
    {
        power = 0;
        ArrayList<Card> h = hand.getZone();
        for(Card c:h)
            discardPile.addCard(c);
        hand.removeAll();
        
        ArrayList<Card> p = inPlay.getZone();
        for(Card c:p)
            discardPile.addCard(c);
        inPlay.removeAll();
        
        for(int i = 0; i < 5; i++)
            drawCard();
        
        table.lineUp.fill(table.mainDeck);
        Game.updateOpponent();
        Game.nextPlayer();
    }
   
    public void batSig(int index) throws IOException
    {
        Card c = discardPile.peek(index);
        if(c.type.equals("Hero"))
        {
            hand.addCard(c);
            discardPile.remove(index);
                            //gameState = player.updateGameState();
                            //output.println("UPDATE");
                            //outputStream.writeObject(gameState);
                            //outputStream.flush();
                            //output.println("POWER "+player.power);
                            //Game.updateOpponent();
            Game.updateOpponent();
            listener.update();
            
        }
    }
    
    public void discard(int index) throws IOException, InterruptedException
    {
        Card c = hand.peek(index);
        if(c instanceof Defendable)
        {
            ((Defendable)c).defense(this);
        }
        hand.remove(index);
        discardPile.addCard(c);
        listener.update();
        opponent.listener.writeOpp();
    }
    
}