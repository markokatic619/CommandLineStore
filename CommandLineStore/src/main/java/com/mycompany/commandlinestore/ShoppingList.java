package com.mycompany.commandlinestore;

import java.util.LinkedList;
/**
 *
 * @author Marko
 */
public class ShoppingList {
    LinkedList shoppingList = new LinkedList();
    
    public void add(Item item,LinkedList list)
    {
        boolean found = false;
        if(shoppingList.size()>0){
        for(int i = 0;i<shoppingList.size();i++)
        {   
            Item compare = (Item)shoppingList.get(i);
            if(compare.sku == item.sku)
            {   
                found = true;
                compare.quantity = compare.quantity + item.quantity;
                for(int j = 0;j<list.size();j++)
                {
                    Item listComp = (Item)list.get(j);
                    if(item.sku == listComp.sku)
                    {
                        if(listComp.quantity<compare.quantity)
                        {
                        System.out.print("Not enough in stock");
                        }
                        else
                        {
                        shoppingList.set(i, compare);
                        
                        }
                    }   
                }
            }
        }
        if(found==false)
        {
                for(int j = 0;j<list.size();j++)
                {
                    Item listComp = (Item)list.get(j);
                    if(item.sku == listComp.sku)
                    {
                        if(listComp.quantity>item.quantity)
                        {
                            shoppingList.add(item);
                        }
                        else
                        {
                            System.out.print("Not enough in stock");
                        }
                    }
        }
        }
        }
        if(shoppingList.size()==0)
        {
            boolean found2 = false;
            for(int j = 0;j<list.size();j++)
                {
                    Item listComp = (Item)list.get(j);
                    if(item.sku == listComp.sku)
                    {
                        if(listComp.quantity>item.quantity)
                        {
                            shoppingList.add(item);
                            break;
                        }
                        else
                        {
                            System.out.print("Not enough in stock");
                        }
                    }
                }
            if(found2){System.out.print("Not in stock");}
        }
    }
    public void remove(Item item)
    {
        for(int i = 0;i<shoppingList.size();i++)
        {
            Item compare = (Item)shoppingList.get(i);
            if(item.sku==compare.sku)
            {
                if(compare.quantity-item.quantity>0)
                {
                    shoppingList.set(i, item);
                    i=shoppingList.size();
                }
                if(compare.quantity-item.quantity<=0)
                {
                    shoppingList.remove(i);
                    i=shoppingList.size();
                }
            }
        }
    }
    
    public LinkedList checkout(LinkedList list)
    {
        float price=0;
        for(int i = 0;i<shoppingList.size();i++)
        {
            Item compare = (Item)shoppingList.get(i);
            
            for(int j = 0;j<list.size();j++)
            {
                Item itemList =(Item)list.get(j);
                if(compare.sku==itemList.sku)
                {
                    if(compare.quantity==itemList.quantity)
                {
                    System.out.print("1 compare.quantity: "+compare.quantity+" "+"itemList.quantity: "+itemList.quantity);
                    System.out.print(itemList.name+" "+compare.quantity+" x "+itemList.price+" = "+ itemList.price*compare.quantity+"\n");
                    list.remove(j);
                }
                    
                    if(compare.quantity<itemList.quantity)
                    {
                        itemList.quantity = itemList.quantity - compare.quantity;
                        System.out.print(itemList.name+" "+compare.quantity+" x "+itemList.price+" = "+ itemList.price*compare.quantity+"\n");
                        price = price + itemList.price;
                        list.set(j, itemList);
                    }
                }
            } 
        }
        System.out.print("\nTotal: "+price+"\n");
        return list;
    }
}