package core;

public class Table {
    MainDeck mainDeck = new MainDeck();
    LineUp lineUp = new LineUp(mainDeck);
    KickStack kickStack = new KickStack();
    WeaknessStack weaknessStack = new WeaknessStack();
    SuperVillainStack superVillainStack = new SuperVillainStack();
}
