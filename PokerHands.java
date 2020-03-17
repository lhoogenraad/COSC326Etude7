import java.util.*;

/**
 * Solution to Poker Hands Etude from COSC326,
 * University of Otago.
 *
 * @author Leon Hoogenraad
 */

public class PokerHands{
    //An array list of strings representing the hands the user enters
    public static ArrayList<String> hands = new ArrayList<>();
    //I'll probably redo how valid chars are held, but for now it's fine.
    //Also the order of this array represents the value of each card.
    public static Character[] valids = {'2', '3', '4', '5', '6', '7', '8', '9', 'J',
			    'Q', 'K', 'A'};
    //An arraylist made out of valids array. useful for indexof() and get()
    public static ArrayList<Character> validOrder = new ArrayList<>(Arrays.asList(valids));
}
