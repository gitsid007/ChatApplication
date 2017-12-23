/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocardgame;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author siddharth
 */
public class Deck {
    
    ArrayList<Card> deckOfcards = new ArrayList<Card>();
    
    public Deck(){
       
        // Loop for Cards from 0 to skip all colors 
        
            for(int a=1; a<5;a++)
        {
            for(int b =0; b<=12;b++)
            {
              Card c = new Card(a,b);
        deckOfcards.add(c);
            }
            }
            
            // Loop for cards from 1 to skip all colors
            
            for(int x=1; x<5;x++)
        {
            for(int y=1; y<=12;y++)
            {
              Card c = new Card(x,y);
        deckOfcards.add(c);
            }
            }
            
            // Loop for Wild and Darw +4
            
      for(int i=0; i<1;i++)
        {
            for(int j =13; j<=14;j++)
            {
              Card c = new Card(i,j);
        deckOfcards.add(c);
        deckOfcards.add(c);
        deckOfcards.add(c);
        deckOfcards.add(c);
            }
            }       
            
    }
    
    public void printDeck(){
        for(int i=0; i<=107; i++)
        {
            System.out.println(deckOfcards.get(i));
    }
        
    }
      public void shuffle() {
         Collections.shuffle(deckOfcards);
     }
}