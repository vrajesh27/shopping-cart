package com.shopping.cart.management.tax;

import java.util.HashMap;
import java.util.Map;

public class TaxSlab {

    public enum LocationCode
    {
        IND,
        AUS,
    };

    private static Map<LocationCode, Double> taxSlabRatesPerLocation = new HashMap<LocationCode, Double>() {{
        put(LocationCode.IND, 12.5);
        put(LocationCode.AUS, 13.5);
    }};

    public static double getCartSalesValue(double totalPriceOfCartItems, LocationCode cartLocationCode) {
        return taxSlabRatesPerLocation.get(cartLocationCode) * totalPriceOfCartItems / 100;
    }
}
