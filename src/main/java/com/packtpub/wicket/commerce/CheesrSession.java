package com.packtpub.wicket.commerce;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;



/*
 * Extends WiaSession instead of WebSession to make the 
 * cheesr application work within the examples.
 */
public class CheesrSession extends WebSession {
  private Cart cart = new Cart();

  public CheesrSession(Request request) {
    super(request);
  }

  public Cart getCart() {
    return cart;
  }
}
