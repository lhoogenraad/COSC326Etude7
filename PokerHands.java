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
    public static String[] valids = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
				     "J", "Q", "K", "A"};
    //An arraylist made out of valids array. useful for indexof() and get()
    public static ArrayList<String> validOrder = new ArrayList<>(Arrays.asList(valids));
    //An array and ArrayList containing valid suits
    public static String[] suitsarray = {"C", "D", "H", "S"};
    public static ArrayList<String> suits = new ArrayList<>(Arrays.asList(suitsarray));


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

	for(int i = 0; i < convertedHands.size(); i++){
	    String s  = convertedHands.get(i);
	    s = convertHand(s);
	    convertedHands.set(i, s);
	    if(validateHand(s)){
		System.out.println(s);
	    }else{
		System.out.println("Invalid: " + rawHands.get(i));
	    }
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
     * P.S. the commented out println statements are (of course) debugging code.
     */
    private static boolean validateHand(String hand){
	int seperators = 0;
	String sep = " ";
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
	    //System.out.println("diff seps");
	    return false;
	}
	/*Here we are sort of checking that only 4 seperators were used, in a sort of hacky way.*/
	String[] handSplit = hand.split(sep);
        if(handSplit.length != 5){
            //System.out.println("Seperators #: " + handSplit.length);
            return false;
        }
	/*Could put a try{}catch(ArrayIndexOutOfBounds) exception here in case
	  Things go very wrong, but for now i think it's unnecessary*/
	
	int[] cardValues = new int[5];
	for(int i = 0; i < handSplit.length; i++){
	    if(handSplit[i].length() > 3 ||
	       handSplit[i].length() < 2){
		return false;
	    }
	    
	    /*Getting the first and second chars of the given card into Character 
	      vars, and setting a boolean valuable which determines if given card
	      is a letter or an int*/
	    String cardval = handSplit[i].substring(0, handSplit[i].length()-1);
	    String suit = handSplit[i].substring(handSplit[i].length()-1);
	    /*Checking that cardval appears in validOrder arraylist, and that
	      suit appears in suits arraylist*/
	    if(!validOrder.contains(cardval) || !suits.contains(suit)){
		//System.out.println("not found in alists\tcardvalue: " + cardval + " suit: " + suit);
		return false;
	    }

		
	    for(int x = 0; x < handSplit.length; x++){
		if(x != i){
		    //If duplicate card is found: return false
		    if(handSplit[i].equals(handSplit[x])){
			//System.out.println("duplicate found");
			return false;
		    }
		}
	    }
	}
	return true;
    }
    

    public static String combineArray(String[] s){
	String r = "";
	for(int i = 0; i < s.length; i++){
	    r += s[i];
	}
	return r;
    }

    /** Converting values between 9-13 and 1 to their correct string values
	This code will not perform any error checking, it simply converts any cards
	with values 9-13 and 1 to the correct string equiv
	-----------------------------------Left off here-----------------------------------
    */

    public static String convertHand(String s){
	String returnString = "";
	String sep = "/";
	if(s.contains("-")){
	    sep = "-";
	}else if(s.contains(" ")){
	    sep = " ";
	}
	
	String[] split = s.split(sep);
	try{
	    for(int i = 0; i < split.length; i++){
		String cardVal = split[i].substring(0, split[i].length()-1);
		if(cardVal.equals("1")){
		    returnString += "A" + split[i].substring(split[i].length()-1) + " ";
		}
		/*Here we are converting corresponding ints to 'royal' card
		  values*/
		else if(cardVal.equals("11")){
		    returnString += "J" + split[i].substring(split[i].length()-1) + " ";
		}else if(cardVal.equals("12")){
		    returnString += "Q" + split[i].substring(split[i].length()-1) + " ";
		}else if(cardVal.equals("13")){
		    returnString += "K" + split[i].substring(split[i].length()-1) + " ";
		}else{
		    if(i < split.length-1){
			returnString += split[i] + " ";
		    }else{
			returnString += split[i];
		    }
		}
	    }
	}catch(Exception e){/*Do nothing*/}
        //System.out.println("Unsorted hand: " + returnString);
	split = sortHand(returnString.split(" "));
	returnString = "";
	for(String str:split){
	    returnString += str + " ";
	}
	//This return basically cuts the last char off of the return string, which would always
	//be the seperator.
	//System.out.println("Sorted hand: " + returnString.substring(0, returnString.length()-1));
	return returnString.substring(0,returnString.length());
    }
    
    
    /**
     * This method will sort an array of cards. it does this by calculating value
     * based on the index of the card string in the validOrder arraylist.
     *
     * The numDeadCards int var is the number of cards that have an indexOf
     * value in validOrder of -1. This sorting method uses insertion sort.
     * If indexOf == -1 then it messes up the sorting algorithm but it doesn't matter
     * because something should be invalid and sorting the string isn't an issue at that point
     */
    public static String[] sortHand(String[] s){
        try{
            for(int i = 1; i < s.length; i++){
		int j = i;
		String key = s[i];
                int value = getValue(key);
		while(j > 0 && getValue(s[j-1]) > value){
		    String swap = s[j];
		    s[j] = s[j-1];
		    s[j-1] = swap;
		    j--;
		}
		s[j] = key;
            }
        }catch(Exception e){/*Do nothing, tbh don't think i need this here but it's nice to have*/}
	sortSuits(s);
        return s;
    }

    /**
     * Will return an int representing how many times a sequence has occured
     * in a given string
     */
    public static int occursNumTimes(String s, String targ){
	if(!s.contains(targ)){
	    return 0;
	}
	int count = 0;
	int index = 0;
	String st = s;
	while(st.indexOf(targ) != -1){
	    st = st.substring(st.indexOf(targ)+1, st.length());
	    System.out.println(st);
	    count++;
	}
	return count;
    }

    /**
     * This function will sort a given array of cards based on their suits.
     * It should only sort based on suits when duplicate values are found.
     */
    public static String[] sortSuits(String[] s){
	try{
	    for(int i = 0; i < s.length; i++){
		int x = i;
		int currVal = getValue(s[i]);
		/*
		  Here we find at what index duplicates stop.
		*/
		while(x < s.length && getValue(s[x]) == currVal){
		    x++;
		}
		/*
		  This code is for sorting the duplicate values up till index x
		*/
		if(x != i){
		    for(int y = i; y < x; y++){
			//Value of suit
			int yval = suits.indexOf(s[y].substring(s[y].length()-1));
			for(int z = i+1; z < x; z++){
			    int zval = suits.indexOf(s[z].substring(s[z].length()-1));
			    if(zval < yval){
				String temp = s[z];
				s[z] = s[y];
				s[y] = temp;
			    }
			}
		    }
		}
	    }
	}catch(Exception e){/*Do nothing*/}
	return s;
    }
    
    
    /**
     * This method will return the value of a given card (with suit)
     * It will either return the value, or -1 if it is invalid
     */
    public static int getValue(String s){
	return validOrder.indexOf(s.substring(0, s.length()-1));
    }
}
