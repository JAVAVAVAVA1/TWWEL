package main.lunch.service;

import main.lunch.aggregate.MenuTag;
import main.lunch.aggregate.Store;
import main.lunch.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;

public class StoreService {
    private final StoreRepository sr = new StoreRepository();

    public void findAllStores() {
        ArrayList<Store> findStores = sr.selectAllStores();
        System.out.println("Service에서 조회 확인: ");
        for (Store store : findStores) {
            System.out.println(store);
        }
    }

    public void findStoreByMenuTag(MenuTag menuTag) {
        ArrayList<Store> findStoreList = sr.selectStoreByMenuTag(menuTag);
        System.out.println("MenuTag가 " + menuTag + "인 가게 목록: ");
        for (Store store : findStoreList) {
            System.out.println(store);
        }
    }

    public void registerStore(Store store) {
        int lastStoreIdx = sr.selectLastStoreIdx();
        store.setStoreIdx(lastStoreIdx + 1);
        int result = sr.insertStoreInfo(store);
        if (result == 1) {
            System.out.println(store.getStoreName() + "의 등록이 완료되었습니다.");
        } else {
            System.out.println("가게 등록 실패");
        }
    }

    public Store findStoreForMod(String storeName) {
        Store selectedStore = sr.selectStoreByName(storeName);

        if (selectedStore != null) {
            Store instanceStore = new Store();
            instanceStore.setStoreName(selectedStore.getStoreName());
            instanceStore.setMenuCatalogMap(selectedStore.getMenuCatalogMap());
            instanceStore.setStoreLocation(selectedStore.getStoreLocation());
            instanceStore.setMaxPeople(selectedStore.getMaxPeople());

            instanceStore.setClosedDays(selectedStore.getClosedDays()); // 배열로 변경 예정

            instanceStore.setMenuTag(selectedStore.getMenuTag());
            instanceStore.setStoreStatus(selectedStore.getStoreStatus());
            return instanceStore;
        } else {
            System.out.println("검색된 가게가 없습니다.");
        }

        return selectedStore;
    }

    public void modifyStore(Store reformedStore) {
        int result = sr.updateStoreInfo(reformedStore);

        if (result == 1) {
            System.out.println(reformedStore.getStoreName() + " 가게의 정보를 수정했습니다.");
        } else {
            System.out.println("가게 정보 수정 실패");
        }
    }

    public void removeStore(String storeName) {
        int result = sr.deleteStoreInfo(storeName);

        if (result == 1) {
            System.out.println(storeName + "가게가 문을 닫았습니다...");
        } else {
            System.out.println("가게 삭제 실패");
        }
    }

    public void getRecommendationByStore() {
        List<Store> recommendationByStore = sr.selectAllStores();
        int randomSize = (int) (Math.random() * recommendationByStore.size());
        System.out.println(recommendationByStore.get(randomSize));
    }

    public void getRecommendationByMenuTag(MenuTag menuTag) {
        List<Store> recommendationByMenuTag =  sr.selectStoreByMenuTag(menuTag);
        int randomSize = (int) (Math.random() * recommendationByMenuTag.size());
        try {
            System.out.println("점메추: " + recommendationByMenuTag.get(randomSize));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("해당하는 메뉴 태그로 등록된 가게가 없습니다.");
        }
    }
}
