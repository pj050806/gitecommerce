/*
 * Copyright 2016 Patrick.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pentasys.moneypattern;
import java.util.function.DoubleToLongFunction;
/**
 *
 * @author Patrick
 */
public class Money {

	private Integer amount;
	private String currency;
	
	private boolean samecurrency(Money compareObject){
		return compareObject.getCurrency().equalsIgnoreCase(currency);		
	}
	
	private static int doubleAmountToInt(double f){		
		String s = Double.toString(f);
		String[] ss = s.split("\\.");
		s = ss[0] + ss[1];
		return Integer.valueOf(s);		
	}

	public Money(int amount, String currency) {
		super();
		this.amount = amount;
		this.currency = currency;
	}
	
	public Money(int amount) {
		this(amount,"EUR");
	}
	
	public Money(double f){
		super();
		this.amount=doubleAmountToInt(f);
		this.currency = "EUR";		
	}
	
	public Integer getAmount() {
		return this.amount;
	}
	
	public String getCurrency() {
		return this.currency;
	}
	
	public boolean equals(Money compareObject){
            return (compareObject.getAmount() == this.amount) && samecurrency(compareObject);
	}
	
	public String printAmount(){
		String s;
		s=this.amount.toString();
		if(s.length() == 1)
			s = "0.0"+s;
		else if(s.length() == 2)
			s= "0."+s;
		else if(s.length() >= 3)
			s = s.substring(0, s.length()-2) +"."+ s.substring(s.length()-2);
		return s;
	}	
	
	public void add(Money m) throws differentCurrencyException{	
		if(this.samecurrency(m))
			this.amount += m.amount;
		else
			throw new differentCurrencyException("Currency is different");
	}
	
	public int sub(Money m) throws differentCurrencyException{	
		if(this.samecurrency(m))
			return this.amount - m.amount;
		else
			throw new differentCurrencyException("Currency is different");
	}

	public int mul(Money m) throws differentCurrencyException{	
		if(this.samecurrency(m))
			return this.amount * m.amount;
		else
			throw new differentCurrencyException("Currency is different");
	}
	
	public int div(Money m) throws differentCurrencyException{	
		if(this.samecurrency(m))
			return this.amount / m.amount;
		else
			throw new differentCurrencyException("Currency is different");
	}
	
}