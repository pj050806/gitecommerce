/*
 * Copyright 2016 jordanpa.
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
package promocode;

import com.pentasys.moneypattern.Money;
import com.pentasys.moneypattern.differentCurrencyException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jordanpa
 */
public class promoManager {
    private static final List<String> promocodes = Arrays.asList("cheese","123","abc");    
    
    public static boolean promocodevalid(String s) {
        return promocodes.contains(s);
    }
    
    public static String calcsaleprice(double price, String promo) throws differentCurrencyException {
        Money Price = new Money(price);
        int erg = 0;
        
        if(promo.equals(promocodes.get(0))) {            
                //erg = price-0.99;
                erg = Price.sub(new Money(99));
                
        } else if(promo.equals(promocodes.get(1))) {
            //erg = price*0.85;            
            erg = Price.mul(new Money(85));
            Money temp = new Money(erg);
            erg = temp.div(new Money(100));
            
        } else if(promo.equals(promocodes.get(2))) {
            //erg = price/2;
            erg = Price.div(new Money(2));
            
        } else {
            erg = Price.getAmount();
        }
        Money Erg = new Money(erg);
        System.out.println(Erg.printAmount());
        return Erg.printAmount();
    }
}
