package solidbeans.com.handla.db;

import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity
public class ItemList {

    public static final String TAG = ItemList.class.getSimpleName();

    @Id(autoincrement = true)
    private Long id;

    @ToMany(referencedJoinProperty = "id")
    private List<Item> items = new ArrayList<>();

    private String name;

    private transient LinkedList<Item> sorted = new LinkedList<>();

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1841132302)
    private transient ItemListDao myDao;

    @Generated(hash = 383842011)
    public ItemList(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 70580157)
    public ItemList() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 851660729)
    public List<Item> getItems() {
        if (items == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ItemDao targetDao = daoSession.getItemDao();
            List<Item> itemsNew = targetDao._queryItemList_Items(id);
            synchronized (this) {
                if (items == null) {
                    items = itemsNew;
                }
            }
        }
        return items;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1727286264)
    public synchronized void resetItems() {
        items = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public int size() {
        return sorted.size();
    }

    public Item itemAt(int position) {
        Item item = sorted.get(position);
//        Log.d(TAG, "itemAt(" + position + "): " + item);
        return item;
    }

    public void add(Item item) {
        items.add(item);
        updateSorted();
    }

    private void updateSorted() {
        sorted.clear();
        sorted.addAll(items);
        sortSorted();
    }

    private void sortSorted() {
        Collections.sort(sorted, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                Category c1 = o1.getItemType().getCategory();
                Category c2 = o2.getItemType().getCategory();
                int ord1 = c1 == null ? 0 : c1.getOrdinal();
                int ord2 = c2 == null ? 0 : c2.getOrdinal();
                int compare = ord1 - ord2;
                if(compare != 0) {
                    return compare;
                }
                return -1;
            }
        });
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 221849985)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getItemListDao() : null;
    }
}
