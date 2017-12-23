/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wargame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Deck {
    ArrayList<Card> DeckofCards = new ArrayList<>();   
    ArrayList<Card> Player1 = new ArrayList<>();
    ArrayList<Card> Player2 = new ArrayList<>();
    
    public Deck(){
     for (int i = 0; i <2; i++){
           
           for (int j = 0; j <13; j++) {
               
               Card c;
               c = new Card(j, i);
               Player1.add(c);   
           }
     }
     for(int i=2; i<4; i++){
         
         for(int j=0; j<13; j++){
             
             Card c1;
             c1 = new Card(j,i);
             Player2.add(c1);
         }
     }
     
     
    }
    public void printDeck(){  
    System.out.println(Player1.get(0));
    System.out.println(Player2.get(1));
   } 
    
   public void shuffle(){
     
       Collections.shuffle(DeckofCards);
       Collections.shuffle(Player1);
       Collections.shuffle(Player2);
       
   }   
    
}
