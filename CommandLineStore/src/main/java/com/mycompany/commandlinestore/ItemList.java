package com.mycompany.commandlinestore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

/**
 *
 * @author Marko
 */
public class ItemList{
    LinkedList<Item> linkedList = new LinkedList<>();
    
    void addItem(Item item)
    {
        //Dodaje proizvod u povezani popis na mjesto gdje ce prethodni element imati manji sku
        //ili zbraja kolicinu proizvoda ukoliko proizvod vec postoji.
        int size = linkedList.size();
        if(size==0)
        {
            linkedList.add(item);
        }
        else
        {
        for(int i = 0;i < size;i++)
            {
                Item compare = (Item)linkedList.get(i);
                if(item.sku<compare.sku)
                {
                 linkedList.add(i,item);
                 i=size;
                }
                if(item.sku>compare.sku && i+1==size){
                linkedList.add(item);
                }
                if(item.sku==compare.sku)
                {
                    if(item.name==compare.name && item.price==compare.price)
                    {
                        item.quantity = item.quantity+compare.quantity;
                        linkedList.set(i, item);
                        i=size;
                    }
                    else{
                    System.out.print("Error: zauzet sku.");
                    i=size;
                    }
                }
            }
        }
    }
    
    void removeItem(int sku, int quantity){
        boolean found = false;
        for(int i = 0; i<linkedList.size();i++)
        {
            Item compare = (Item)linkedList.get(i);
            if(sku == compare.sku)
            {
                if((compare.quantity - quantity)>=0)
                {
                    compare.quantity = compare.quantity - quantity;
                    linkedList.add(i, compare);
                    found = true;
                    i = linkedList.size();
                }
                if((compare.quantity - quantity)==0)
                {
                linkedList.remove(i);
                }
            }
        }
        if(found=false){
        System.out.print("Error");
        }
    }
    
    void loadList() throws FileNotFoundException, IOException, ClassNotFoundException
    {   
        //Ucitava postojecu binarnu datoteku u kojoj se nalazi povezani popis.
        try{
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("Inventory.bin"));
        linkedList = (LinkedList)input.readObject();
        }
        //Ukoliko datoteka ne postoji napraviti ce se nova prazna binarna datoteka
        catch(IOException | ClassNotFoundException e)
        {
            try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Inventory.bin"))) 
        {
            output.writeObject(null);
            System.out.print("inventory nije ucitan");
        }
        }
    }
    
    void saveList() throws FileNotFoundException, IOException
    { 
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Inventory.bin"))) 
        {
            output.writeObject(linkedList);
        }
    }
}
