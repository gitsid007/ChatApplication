/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocardgame;

/**
 *
 * @author siddharth
 */
public class Card {
 
    private int suit, value;
    private String[] cardSuit = {"","Red", "Blue", "Green", "Yellow"};
    private String[] cardValue = { "0", "1", "2", "3", "4", "5",
                                   "6", "7", "8", "9","Draw+2", "Reverse", "Skip", "Wild", "Draw+4"};

    public Card(int cSuit, int cValue)
    {
        suit = cSuit; 
        value = cValue;
    }

    Card(int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
public String getSuit(){
    return cardSuit[suit];
    
}

public String getValue(){
    return cardValue[value];
}
    public String toString(){
        return cardSuit[suit]+" "+cardValue[value];
    }
    
public String getImg()
    {
        String imgName = cardValue[value]+ cardSuit[suit]+".png";
        return imgName;
    }

public int getInt(String cardValue)
        {
            int cardvalue;
            switch(cardValue) {
                case "0": cardvalue=1; 
                break; 
                case "1" : cardvalue=2; 
                break;
                case "2" : cardvalue =3;
                break;
                case "3" : cardvalue =4; 
                break;
                case "4" : cardvalue =5; 
                break;
                case "5" : cardvalue =6; 
                break;
                case "6" : cardvalue =7; 
                break;
                case "7" : cardvalue =8; 
                break;
                case "8" : cardvalue =9; 
                break;
                case "9" : cardvalue =10; 
                break;
                case "Draw+2" : cardvalue =11;
                break;
                case "Reverse" : cardvalue =12; 
                break;
                case "Skip" : cardvalue =13; 
                break;
                case "Wild" : cardvalue =14; 
                break;
                case "Draw+4" : cardvalue =15; 
                break;
                default : cardvalue =0;
            }
            
            return cardvalue;
        } 
    
}