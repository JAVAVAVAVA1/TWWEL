package main.lunch.aggregate;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class Store implements Serializable {
    private String storeName;           //가게 상호 명
    private Map<String,Integer> menuCatalogMap;           //가게 메뉴 및 가격
    private String storeLocation;       //가게 위치
    private int maxPeople;              //최대 수용 인원
    private Set<ClosedDays> closedDays;      //휴무일
    private MenuTag menuTag;            //메뉴 태그 타입
    private StoreStatus storeStatus;    //가게 정보 삭제 유무
    private int storeIdx;               //가게 이름 수정용 인덱스

    public Store() {
    }

    public Store(String storeName, Map<String, Integer> menuCatalogMap, String storeLocation,  int maxPeople, Set<ClosedDays> closedDays, MenuTag menuTag, StoreStatus storeStatus, int storeIdx) {
        this.storeName = storeName;
        this.menuCatalogMap = menuCatalogMap;
        this.storeLocation = storeLocation;
        this.maxPeople = maxPeople;
        this.closedDays = closedDays;
        this.menuTag = menuTag;
        this.storeStatus = storeStatus;
        this.storeIdx = storeIdx;
    }

    public int getStoreIdx() {
        return storeIdx;
    }

    public void setStoreIdx(int storeIdx) {
        this.storeIdx = storeIdx;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setMenuCatalogMap(Map<String, Integer> menuCatalogMap) {
        this.menuCatalogMap = menuCatalogMap;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }


    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public void setClosedDays(Set<ClosedDays> closedDays) {
        this.closedDays = closedDays;
    }

    public void setMenuTag(MenuTag menuTag) {
        this.menuTag = menuTag;
    }

    public void setStoreStatus(StoreStatus storeStatus) {
        this.storeStatus = storeStatus;
    }

    public String getStoreName() {
        return storeName;
    }

    public Map<String, Integer> getMenuCatalogMap() {
        return menuCatalogMap;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public Set<ClosedDays> getClosedDays() {
        return closedDays;
    }

    public MenuTag getMenuTag() {
        return menuTag;
    }

    public StoreStatus getStoreStatus() {
        return storeStatus;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeName='" + storeName + '\'' +
                ", menuCatalogMap=" + menuCatalogMap +
                ", storeLocation='" + storeLocation + '\'' +
                ", maxPeople=" + maxPeople +
                ", closedDays=" + closedDays +
                ", menuTag=" + menuTag +
                ", storeStatus=" + storeStatus +
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
