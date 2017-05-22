package solidbeans.com.handla.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class Item {

    @Id(autoincrement = true)
    private Long id;

    @ToOne(joinProperty = "itemTypeId")
    private ItemType itemType;
    private long itemTypeId;


    private int quantity;
    private String quantityType;
    private boolean checked = false;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 182764869)
    private transient ItemDao myDao;

    @Generated(hash = 2019110374)
    private transient Long itemType__resolvedKey;

    @Generated(hash = 1470900980)
    public Item() {
    }

    @Generated(hash = 2001439384)
    public Item(Long id, long itemTypeId, int quantity, String quantityType, boolean checked) {
        this.id = id;
        this.itemTypeId = itemTypeId;
        this.quantity = quantity;
        this.quantityType = quantityType;
        this.checked = checked;
    }

    public Item(ItemType itemType, int quantity, String quantityType) {
        this.itemTypeId = itemType.getId();
        this.itemType = itemType;
        this.quantity = quantity;
        this.quantityType = quantityType;
    }

    public String quantity() {
        return quantity + " " + quantityType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean getChecked() {
        return this.checked;
    }



    public long getItemTypeId() {
        return this.itemTypeId;
    }



    public void setItemTypeId(long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }



    /** To-one relationship, resolved on first access. */
    @Generated(hash = 853467355)
    public ItemType getItemType() {
        long __key = this.itemTypeId;
        if (itemType__resolvedKey == null || !itemType__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ItemTypeDao targetDao = daoSession.getItemTypeDao();
            ItemType itemTypeNew = targetDao.load(__key);
            synchronized (this) {
                itemType = itemTypeNew;
                itemType__resolvedKey = __key;
            }
        }
        return itemType;
    }



    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 466046809)
    public void setItemType(@NotNull ItemType itemType) {
        if (itemType == null) {
            throw new DaoException(
                    "To-one property 'itemTypeId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.itemType = itemType;
            itemTypeId = itemType.getId();
            itemType__resolvedKey = itemTypeId;
        }
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



    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 881068859)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getItemDao() : null;
    }

}
