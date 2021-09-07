/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anpdt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class CartObj implements Serializable{
    private Map<String, Integer> items;
    public Map<String, Integer> getItems(){
        return items;
    }
    
    public void addItemToCart(String name){
        if(name == null){
            return;
        }
        if(name.trim().isEmpty()){
            return;
        }
        if(this.items == null){
            this.items = new HashMap<>();
        }
        int quantity = 1;
        if(this.items.containsKey(name)){
            quantity = this.items.get(name) + 1;
        }
        this.items.put(name, quantity);
    }
    
    public void removeItemFromCart(String name){
        if(this.items == null){
            return;
        }
        if(this.items.containsKey(name)){
            this.items.remove(name);
            if(this.items.isEmpty()){
                this.items = null;
            }
        }
    }
    
    public int getPrice(String name){
        switch(name){
            case "Servlet": return 10;
            case "Java": return 15;
            case "JS": return 50;
            case "JSP": return 8;
            case "HTML": return 1;
            case "C": return 2;
            default: return 0;
        }
    }
    
    public int getTotal(String name){
        int total = getPrice(name) * this.items.get(name);
        return total;
    }
}
