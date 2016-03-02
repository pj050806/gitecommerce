package com.packtpub.wicket.commerce;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import promocode.promoManager;

public class Checkout extends CheesrPage {
    private TextField field;
    private ShoppingCartPanel shopcartpanel = new ShoppingCartPanel("cart", getCart());
    
  public Checkout() {
    add(new FeedbackPanel("feedback"));
    Form form = new Form("form");
    add(form);
    Address address = getCart().getBillingAddress();

    form
        .add(new TextField("name", new PropertyModel(address, "name")).setRequired(true));
    form.add(new TextField("street", new PropertyModel(address,
        "street")).setRequired(true));
    form.add(new TextField("zipcode", new PropertyModel(address,
        "zipcode")).setRequired(true));
    form
        .add(new TextField("city", new PropertyModel(address, "city")).setRequired(true));
    form.add(new Link("cancel") {
      @Override
      public void onClick() {
        setResponsePage(Index.class);
      }
    });
    form.add(new Button("order") {
      @Override
      public void onSubmit() {
        Cart cart = getCart();

        // charge customersÕ credit card
        // ship cheeses to our customer
        // clean out shopping cart
        cart.getCheeses().clear();

        // return to front page
        setResponsePage(Index.class);
        System.out.println("Order submited");
      }
    });
    add(shopcartpanel);
    Form f = new Form("promo-form");
    add(f);
    field = new TextField("promo", new Model(""));
    f.add(field);
    
    f.add(new Button("promo_btn") {
        @Override
        public void onSubmit() {
            System.out.println("Method Promo Btn.");
            String promo = (String)field.getModelObject();            
            
            if(promo == null || promo.isEmpty()) {
                System.out.print("Empty Promo Field");                
            }else{
                System.out.println(promo);                
                double erg;                
                    erg = promoManager.calcsaleprice(getCart().getTotal(), promo);
                    shopcartpanel.setTotal(erg);
                                
            }
            
        }
    });
    
    
  }
}
