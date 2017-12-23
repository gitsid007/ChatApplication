/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wargame;


public class Card {
    private int Facevalue;
        private  int suit;
        private final String[] cardValue = {"Hearts","Spades","Clubs","Diamonds"};
        private final String[] cardSuit = {"Ace", "2", "3", "4", "5","6", "7", "8", "9", "10", "JACK", "QUEEN", "KING"};
        
        
        public Card(int cSuit, int cValue){
            
            this.suit = cSuit ;
            this.Facevalue =cValue;
        }
        
        public String getSuit(){
            
            return cardSuit [suit];
        }
        
        public int getValue(){
            
            return Facevalue+1;    
        }
        
        public String getImage() {
 
            String imgName = cardSuit[suit]+cardValue[Facevalue] +".png";
            return imgName;    
        }
  
        public String toString(){
            
        return cardValue[Facevalue] + " " +cardSuit[suit]  ;  
        }

    public int getInt(String cardSuit)
        {
            int cardvalue;
            switch(cardSuit) {
                case "Ace": cardvalue=1; 
                break; 
                case "2" : cardvalue=2; 
                break;
                case "3" : cardvalue =3;
                break;
                case "4" : cardvalue =4; 
                break;
                case "5" : cardvalue =5; 
                break;
                case "6" : cardvalue =6; 
                break;
                case "7" : cardvalue =7; 
                break;
                case "8" : cardvalue =8; 
                break;
                case "9" : cardvalue =9; 
                break;
                case "10" : cardvalue =10; 
                break;
                case "JACK" : cardvalue =11;
                break;
                case "QUEEN" : cardvalue =12; 
                break;
                case "KING" : cardvalue =13; 
                break;
                default : cardvalue =0;
            }
            
            return cardvalue;
        }       
}
