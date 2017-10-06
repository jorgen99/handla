package solidbeans.com.handla.db;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import solidbeans.com.handla.db.Item;
import solidbeans.com.handla.db.ShoppingList;

class RandomStuff {
    static void randomQuantity(Item newItem) {
        String type = randomQuantityType();
        newItem.setQuantityType(type);
        int quantity = new Random().nextInt(9) + 1;
        if (type.equals("g")) {
            quantity *= 100;
        }
        newItem.setQuantity(quantity);
    }

    private static String randomQuantityType() {
        List<String> types = Arrays.asList("st", "g", "kg", "paket", "burk", "påse");
        return types.get(new Random().nextInt(types.size()));
    }
}
