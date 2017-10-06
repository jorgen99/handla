package solidbeans.com.handla.db;

import java.util.List;
import java.util.Optional;

public interface Db {

    void defaultData();

    void dropAllData();

    Optional<Item> itemOfType(String typeName);
    List<Item> listItems();
    void save(Item item);

    List<ItemType> listItemTypes();
    Optional<ItemType> itemTypeWithName(String name);
    void save(ItemType itemType);

    List<Category> listCategories();
    Optional<Category> categoryWithName(String name);
    void save(Category category);

    ShoppingList currentShoppingList();

}
