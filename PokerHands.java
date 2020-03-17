import java.util.*;

/**
 * Solution to Poker Hands Etude from COSC326,
 * University of Otago.
 *
 * @author Leon Hoogenraad
 */

public class PokerHands{
    //An array list of strings representing the hands the user enters, not converted to upper case
    public static ArrayList<String> rawHands = new ArrayList<>();
    //This array list will contain all of the strings from rawHands ArrayList, but
    //converted to upper case
    public static ArrayList<String> convertedHands = new ArrayList<>();
    //I'll probably redo how valid chars are held, but for now it's fine.
    //Also the order of this array represents the value of each card.
    public static Character[] valids = {'2', '3', '4', '5', '6', '7', '8', '9', 'J',
					'Q', 'K', 'A'};
    //An arraylist made out of valids array. useful for indexof() and get()
    public static ArrayList<Character> validOrder = new ArrayList<>(Arrays.asList(valids));
    //An array and ArrayList containing valid suits
    public static Character[] suitsarray = {'H', 'D', 'S', 'C'};
    public static ArrayList<Character> suits = new ArrayList<>(Arrays.asList(suitsarray));


    public static void main(String[] args){
	Scanner sc = new Scanner(System.in);
	/*While user is entering data, read in data into rawHands
	  ArrayList, and add a toUpperCase() version of the string to the
	  convertedHands ArrayList
	*/
	while(sc.hasNextLine()){
	    String in = sc.nextLine();
	    rawHands.add(in);
	    convertedHands.add(in.toUpperCase());
	}
    }

    /**
     * This method validates a given poker hand.
     * It achieves this by first checking that the length of the given string 
     * is == to 14, as this is the only length a valid poker hand can be.
     *
     * Then it checks that only one seperator is used, and then checks that only 4 seperators
     * have been used by using String.split(sep). 
     * I always feel hacky and yuck using String.split() to check how many times a sequence appears
     * in a string, but if we're splitting by a one char seperator then it should work every time 8).
     *
     * It then calls validate
     */
    private static boolean validateHand(String hand){
	int seperators = 0;
	String sep = "";
	if(hand.contains("/")){
	    seperators++;
	    sep = "/";
	}
	if(hand.contains("-")){
	    seperators++;
	    sep = "-";
	}
	if(hand.contains(" ")){
	    seperators++;
	    sep = " ";
	}
	/*Only one seperator should be used, so if seperators is not 1,
	  the hand must be invalid.*/
	if(seperators != 1){
	    return false;
	}
	/*Here we are sort of checking that only 4 seperators were used, in a sort of hacky way.*/
	String[] handSplit = hand.split(sep);
	if(handSplit.length != 5){
	    return false;
	}
	/*Could put a try{}catch(ArrayIndexOutOfBounds) exception here in case
	  Things go very wrong, but for now i think it's unnecessary*/
	
	int[] cardValues = new int[5];
	for(int i = 0; i < handSplit.length; i++){
	    /*If, by some act of god, one of the cards given is not 2 chars long return false.*/
	    if(handSplit[i].length() != 3){
		return false;
	    }
	    
	    /*Getting the first and second chars of the given card into Character 
	      vars, and setting a boolean valuable which determines if given card
	      is a letter or an int*/
	    Character c1 = handSplit.charAt(0);
	    Character c2 = handSplit.charAt(1);
	    for(int x = 0; x < handSplit.length; x++){
		if(x != i){
		    //If duplicate card is found: return false
		    if(handSplit[i].equals(handSplit[x])){
			return false;
		    }
		}
	    }
	    //Checking that each card before this card is lower val than this card
	    //Yeah this is very bad code but i think it'll work!
	    for(int i = 0; i < i-1; i++){
		if()
	    }
	}
    }
}
