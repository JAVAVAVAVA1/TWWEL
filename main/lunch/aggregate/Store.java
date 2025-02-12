package main.lunch.aggregate;

import java.io.Serializable;

public class Store implements Serializable {
    private String storeName;           //가게 상호 명
    private String storeMenu;           //가게 대표 음식
    private String storeLocation;       //가게 위치
    private int menuPrice;              //음식 가격
    private int maxPeople;              //최대 수용 인원
    private ClosedDays closedDays;      //휴무일
    private MenuTag menuTag;            //메뉴 태그 타입
    private StoreStatus storeStatus;    //가게 정보 삭제 유무

    public Store() {
    }

    public Store(String storeName, String storeMenu, String storeLocation, int menuPrice, int maxPeople, ClosedDays closedDays, MenuTag menuTag, StoreStatus storeStatus) {
        this.storeName = storeName;
        this.storeMenu = storeMenu;
        this.storeLocation = storeLocation;
        this.menuPrice = menuPrice;
        this.maxPeople = maxPeople;
        this.closedDays = closedDays;
        this.menuTag = menuTag;
        this.storeStatus = storeStatus;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreMenu() {
        return storeMenu;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public ClosedDays getClosedDays() {
        return closedDays;
    }

    public MenuTag getMenuTag() {
        return menuTag;
    }

    public StoreStatus getStoreStatus() {
        return storeStatus;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreMenu(String storeMenu) {
        this.storeMenu = storeMenu;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public void setClosedDays(ClosedDays closedDays) {
        this.closedDays = closedDays;
    }

    public void setMenuTag(MenuTag menuTag) {
        this.menuTag = menuTag;
    }

    public void setStoreStatus(StoreStatus storeStatus) {
        this.storeStatus = storeStatus;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeName='" + storeName + '\'' +
                ", storeMenu='" + storeMenu + '\'' +
                ", storeLocation='" + storeLocation + '\'' +
                ", menuPrice=" + menuPrice +
                ", maxPeople=" + maxPeople +
                ", closedDays=" + closedDays +
                ", menuTag=" + menuTag +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
