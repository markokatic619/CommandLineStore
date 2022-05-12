package com.mycompany.commandlinestore;

import java.io.Serializable;

/**
 *
 * @author Marko
 */
public class Item implements Serializable{
    int sku;
    String name;
    int quantity;
    float price;
}
