/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anpdt.products;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class ProductsDTO implements Serializable{
    private String name;
    private int price;

    public ProductsDTO() {
    }

    public ProductsDTO(String name, int price) {
        this.name = name;
        this.price = price;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
