package solidbeans.com.handla.db;

import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.stream.Collectors;

import solidbeans.com.handla.view.list.ListCategory;
import solidbeans.com.handla.view.list.ListItem;
import solidbeans.com.handla.view.list.ListObject;

import static solidbeans.com.handla.db.Category.UNCATEGORIZED;

public class ShoppingList {

    @SuppressWarnings("unused")
    private static final String TAG = ShoppingList.class.getSimpleName();

    private final Db db;
    private LinkedList<ListObject> sorted = new LinkedList<>();

    ShoppingList(Db db) {
        this.db = db;
    }

    public void addItemWithName(String itemName) {
        Item item = itemFrom(itemName);
        db.save(item);
        updateSorted();
    }

    public ListObject itemAt(int position) {
        return sorted.get(position);
    }

    public int size() {
        return sorted.size();
    }

    private Item itemFrom(String typeName) {
        return db.itemOfType(typeName)
                .orElseGet(() -> createItem(typeName));
    }

    @NonNull
    private Item createItem(String typeName) {
        ItemType it = db.itemTypeWithName(typeName)
                .orElseGet(() -> createItemType(typeName));
        Item newItem = new Item();
        newItem.setItemType(it);
        newItem.setChecked(false);
        RandomStuff.randomQuantity(newItem); // TODO: Remove after editing an Item is possible
        return newItem;
    }

    @NonNull
    private ItemType createItemType(String typeName) {
        ItemType newItemType = new ItemType();
        newItemType.setName(typeName);
        newItemType.setCategory(UNCATEGORIZED);
        db.save(newItemType);
        return newItemType;
    }

    public void toggleItemAt(int position) {
        ListItem item = (ListItem) sorted.get(position);
        String itemTypeName = item.getItemType().getName();
        Item dbItem = db.itemOfType(itemTypeName)
                .orElseGet(() -> {
                    throw new RuntimeException("No No itemtype of type: " + itemTypeName);
                });
        dbItem.toggle();
        db.save(dbItem);
        updateSorted();
    }

    private void updateSorted() {
        sorted.clear();
        LinkedList<ListItem> sortedItems = db.listItems().stream()
                .sorted(this::itemComparator)
                .map(ListItem::new)
                .collect(Collectors.toCollection(LinkedList::new));
        sorted = insertCategories(sortedItems);
    }

    private int itemComparator(Item item1, Item item2) {
        Category c1 = item1.getItemType().getCategory();
        Category c2 = item2.getItemType().getCategory();
        int ord1 = ordinal(item1, c1);
        int ord2 = ordinal(item2, c2);
        int compare = ord1 - ord2;
        if (compare != 0) {
            return compare;
        }
        //If the same category sort on ItemType name
        return item1.getItemType().getName().compareTo(item2.getItemType().getName());
    }

    private int ordinal(Item item, Category category) {
        int ord1;
        if (category == null) ord1 = 0;
        else if (item.isChecked()) ord1 = 99999;
        else ord1 = category.getOrdinal();
        return ord1;
    }


    private <E> LinkedList<E> tail(LinkedList<E> list) {
        list.removeFirst();
        return list;
    }

    private LinkedList<ListObject> insertCategories(LinkedList<ListItem> sortedItems) {
        return insertCategories(new LinkedList<>(), sortedItems);
    }

    private LinkedList<ListObject> insertCategories(LinkedList<ListObject> result,
                                                    LinkedList<ListItem> rest) {
        if(rest.isEmpty()) {
            return result;
        }

        ListItem currentItem = rest.getFirst();
        if(result.isEmpty()) {
            return addCategoryForItem(currentItem, result, rest);
        }

        ListObject last = result.getLast();
        if(last instanceof ListCategory) {
            return addItem(currentItem, result, rest);
        }

        ListItem lastItem = (ListItem) last;
        String lastCategory = lastItem.listCategory().getName();
        String currentCategory = currentItem.listCategory().getName();
        if(lastCategory.equals(currentCategory)) {
            return addItem(currentItem, result, rest);
        }

        return addCategoryForItem(currentItem, result, rest);
    }

    private LinkedList<ListObject> addItem(ListItem currentItem, LinkedList<ListObject> result, LinkedList<ListItem> rest) {
        result.add(currentItem);
        return insertCategories(result, tail(rest));
    }

    private LinkedList<ListObject> addCategoryForItem(ListItem currentItem, LinkedList<ListObject> result, LinkedList<ListItem> rest) {
        result.add(currentItem.listCategory());
        return insertCategories(result, rest);
    }
}
