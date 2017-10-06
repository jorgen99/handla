package solidbeans.com.handla.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileDb implements Db {

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private String filePrefix;

    private List<Category> categories;
    private List<ItemType> itemTypes;
    private List<Item> items;
    private ShoppingList currentShoppingList;

    public FileDb(String filePrefix) {
        this.filePrefix = filePrefix;
        categories = new ArrayList<>();
        itemTypes = new ArrayList<>();
        items = new ArrayList<>();
        currentShoppingList = new ShoppingList(this);

    }

    @Override
    public void defaultData() {
        categories = DbLineParser.constructCategories();
        itemTypes = DbLineParser.constructItemTypes(categories);
    }

    @Override
    public void dropAllData() {
        categories = new ArrayList<>();
        itemTypes = new ArrayList<>();
        items = new ArrayList<>();
        currentShoppingList = new ShoppingList(this);
    }

    @Override
    public Optional<Item> itemOfType(String typeName) {
        return items.stream()
                .filter(item -> item.getItemType().getName().equals(typeName))
                .findFirst();
    }

    @Override
    public List<Item> listItems() {
        return items;
    }

    @Override
    public void save(Item item) {
        Optional<Item> item1 = items.stream()
                .filter(i -> i.getItemType().getName().equals(item.getItemType().getName()))
                .findFirst();
        if (item1.isPresent()) {
            Item item2 = item1.get();
            item2.setChecked(item.isChecked());
            item2.setId(item.getId());
            item2.setItemType(item.getItemType());
            item2.setQuantity(item.getQuantity());
            item2.setQuantityType(item.getQuantityType());
        } else {
            items.add(item);
        }

    }

    @Override
    public List<ItemType> listItemTypes() {
        return itemTypes;
    }

    @Override
    public Optional<ItemType> itemTypeWithName(String name) {
        return itemTypes.stream()
                .filter(itemType -> itemType.getName().equals(name))
                .findFirst();
    }

    @Override
    public void save(ItemType itemType) {
        Optional<ItemType> existing = itemTypes.stream()
                .filter(it -> it.getName().equals(itemType.getName()))
                .findFirst();
        if (existing.isPresent()) {
            ItemType it = existing.get();
            it.setId(itemType.getId());
            it.setCategory(itemType.getCategory());
            it.setName(itemType.getName());
        } else {
            itemTypes.add(itemType);
        }
    }

    @Override
    public List<Category> listCategories() {
        return categories;
    }

    @Override
    public Optional<Category> categoryWithName(String name) {
        return categories.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    @Override
    public void save(Category category) {
        Category c = categoryWithName(category.getName())
                .orElse(category);

        c.setName(category.getName());
        c.setOrdinal(category.getOrdinal());
        c.setHexColor(category.getHexColor());

        categories.add(c);
    }

    @Override
    public ShoppingList currentShoppingList() {
        return currentShoppingList;
    }
}
