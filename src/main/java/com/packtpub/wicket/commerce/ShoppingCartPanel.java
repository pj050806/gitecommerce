package com.packtpub.wicket.commerce;

import java.io.Serializable;
import java.text.NumberFormat;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class ShoppingCartPanel extends Panel {
  private final Cart cart;  
  private Model total_model;
  
  public ShoppingCartPanel(String id, Cart cart) {
     super(id);
     this.cart = cart;
     add(new ListView("cart", new PropertyModel(this, "cart.cheeses")) { 
       @Override
       protected void populateItem(ListItem item) {
           Cheese cheese = (Cheese) item.getModelObject();
           item.add(new Label("name", cheese.getName()));
           item.add(new Label("price", "$" + cheese.getPrice()));
           
           item.add(new Link("remove", item.getModel()) {

          @Override
          public void onClick() {
            Cheese selected = (Cheese) getModelObject();
            getCart().getCheeses().remove(selected);
            total_model.setObject(getCart().getTotal());
          }
        });
       }
   });
     
    total_model = new Model(getCart().getTotal());
    //total_model.setObject(getCart().getTotal());
    add(new Label("total", total_model));
  }

  private Cart getCart() {
     return cart;
  }
  public void setTotal(double val) {      
      total_model.setObject(val);      
  }
  public void setTotal(String val) {      
      total_model.setObject(val);      
  }
}