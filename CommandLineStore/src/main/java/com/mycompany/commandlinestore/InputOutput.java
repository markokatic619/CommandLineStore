/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.commandlinestore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Marko
 */
public class InputOutput {
    public ItemList itemList=new ItemList();
    public ShoppingList shoppingList = new ShoppingList();
    private int part=1;
    private Scanner scanner = new Scanner(System.in);
    
    int commandInput(String input) throws IOException
    {
        String[] strings;
        strings = input.split(" ");
        String name = null;
        int sku = 0;
        int quantity=0;
        float price=0;
        
        if(part==1)
        {   
            if("ADD".equals(strings[0]))
            {
                boolean pass = true;
                try{
                sku = Integer.parseInt(strings[1]);
                }catch(NumberFormatException e){
                System.out.print("Error sku");
                pass=false;
                }catch(ArrayIndexOutOfBoundsException e)
                {
                System.out.print("Out of bounds");
                pass=false;
                }
                try{
                name = strings[2];
                }catch(NumberFormatException e){
                System.out.print("Error name");
                pass=false;
                }catch(ArrayIndexOutOfBoundsException e)
                {
                System.out.print("Out of bounds");
                pass=false;
                }
                try{
                quantity = Integer.parseInt(strings[3]);
                }catch(NumberFormatException e){
                System.out.print("Error quantity");
                pass=false;
                }catch(ArrayIndexOutOfBoundsException e)
                {
                System.out.print("Out of bounds");
                pass=false;
                }
                try{
                price = Float.parseFloat(strings[4]);
                }catch(NumberFormatException e){
                System.out.print("Error price");
                pass=false;
                }catch(ArrayIndexOutOfBoundsException e)
                {
                System.out.print("Out of bounds");
                pass=false;
                }
                if(pass){
                add(sku,name,quantity,price);
                }
                
            }
            if("END".equals(strings[0]))
            {
                end();
                part++;
            }
            
        }
        if(part==2)
        {
            if("ADD".equals(strings[0]))
            {
                boolean pass=true;
                Item addItem = new Item();
                try{
                sku = Integer.parseInt(strings[1]);
                }catch(NumberFormatException e){
                System.out.print("Error sku");
                pass=false;
                }
                try{
                quantity = Integer.parseInt(strings[2]);
                }catch(NumberFormatException e){
                System.out.print("Error quantity");
                pass=false;
                }
                if(pass)
                {
                    addItem.sku = sku;
                    addItem.quantity = quantity;
                    shoppingList.add(addItem,itemList.linkedList);
                }
            }
            if("REMOVE".equals(strings[0]))
            {   
                boolean pass=true;
                try{
                sku = Integer.parseInt(strings[1]);
                }catch(NumberFormatException e){
                System.out.print("Error sku");
                pass=false;
                }
                try{
                quantity = Integer.parseInt(strings[2]);
                }catch(NumberFormatException e){
                System.out.print("Error quantity");
                pass=false;
                }
                if(pass)
                {
                    Item removeItem=new Item();
                    removeItem.sku= sku;
                    removeItem.quantity = quantity;
                    shoppingList.remove(removeItem);
                }
                
            }
            
            if("CHECKOUT".equals(strings[0]))
            {
                itemList.linkedList=shoppingList.checkout(itemList.linkedList);
            }
            if("END2".equals(strings[0]))
            {
                end();
                part++;
            }
            
        } 
        return part;
    }
    
    
    
    
        
        
    void add(int sku,String name,int quantity, float price)
    {
        Item item = new Item();
        item.sku=sku;
        item.name=name;
        item.quantity=quantity;
        item.price=price;
        itemList.addItem(item);  
    }
    void read(){
        for(int i = 0; i<itemList.linkedList.size();i++)
        {
            Item readItem = new Item();
            readItem = itemList.linkedList.get(i);
            System.out.print(readItem.name+"\n"+readItem.quantity+"\n");
        }
    }
    void end() throws IOException{
    itemList.saveList();
    }
}
        