package main.lunch.repository;

import main.lunch.aggregate.StoreStatus;
import main.lunch.aggregate.ClosedDays;
import main.lunch.aggregate.MenuTag;
import main.lunch.aggregate.Store;
import main.lunch.stream.MyObjectOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StoreRepository {
    private final ArrayList<Store> storeList = new ArrayList<>();
    private final File file = new File("main/lunch/db/storeDB.dat");

    public StoreRepository() {
        if (!file.exists()) {
            ArrayList<Store> defaultStores = new ArrayList<>();

            HashMap<String, Integer> defaultCatalog1 = new HashMap<>();
            HashSet<ClosedDays> defaultClosedDays1 = new HashSet<>();
            defaultCatalog1.put("떡라면",4500);
            defaultCatalog1.put("라볶이",5500);
            defaultClosedDays1.add(ClosedDays.TUE);
            defaultStores.add(new Store("포마토", defaultCatalog1, "횡단보도 건넌 바로 앞", 6, defaultClosedDays1, MenuTag.SNACK, StoreStatus.OPEN, 1));

            HashMap<String, Integer> defaultCatalog2 = new HashMap<>();
            HashSet<ClosedDays> defaultClosedDays2 = new HashSet<>();
            defaultCatalog2.put("햄치즈스페셜",6000);
            defaultCatalog2.put("햄치즈",5000);
            defaultClosedDays2.add(ClosedDays.SUN);
            defaultStores.add(new Store("이삭토스트", defaultCatalog2, "시장 초입", 4, defaultClosedDays2, MenuTag.SANDWICH, StoreStatus.OPEN,2));

            HashMap<String, Integer> defaultCatalog3 = new HashMap<>();
            HashSet<ClosedDays> defaultClosedDays3 = new HashSet<>();
            defaultCatalog3.put("프레쉬버거",8400);
            defaultClosedDays3.clear();
            defaultClosedDays3.add(ClosedDays.SUN);
            defaultStores.add(new Store("버거리", defaultCatalog3, "3번 출구 앞", 8, defaultClosedDays3, MenuTag.BURGER, StoreStatus.OPEN,3));

            saveStores(defaultStores);
        }

        loadStores();
    }

    private void loadStores() {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(file)
                    )
            );
            while (true) {
                storeList.add((Store) (ois.readObject()));
            }
        } catch (EOFException e) {
            System.out.println("Loading Stores Complete!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveStores(ArrayList<Store> defaultStores) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(file)
                    )
            );
            for (Store store : defaultStores) {
                oos.writeObject(store);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public ArrayList<Store> selectAllStores() {
        return this.storeList;
    }

    public ArrayList<Store> selectStoreByMenuTag(MenuTag menuTag) {
        ArrayList<Store> returnStoreList = new ArrayList<>();
        for (Store store : storeList) {
            if (store.getMenuTag().equals(menuTag)) {
                returnStoreList.add(store);
            }
        }
        return returnStoreList;
    }

    public Store selectStoreByName(String name) {
        Store returnStore = null;
        for (Store store : storeList) {
            if (store.getStoreName().equals(name)) {
                returnStore = store;
            }
        }
        return returnStore;
    }

    public int insertStoreInfo(Store store) {
        MyObjectOutput moo = null;

        try {
            moo = new MyObjectOutput(
                    new BufferedOutputStream(
                            new FileOutputStream(file)
                    )
            );
            moo.writeObject(store);
            storeList.add(store);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (moo != null) {
                    moo.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return 1;
    }

    public int updateStoreInfo(Store reformedStore) {
        for (int i = 0; i < storeList.size(); i++) {
            if (storeList.get(i).getStoreName().equals(reformedStore.getStoreName())) {
                storeList.set(i, reformedStore);
                saveStores(storeList);

                return 1;
            }
        }
        return 0;
    }

    public int deleteStoreInfo(String removeStoreName) {
        int result = 0;

        for (Store store : storeList) {
            if (store.getStoreName().equals(removeStoreName)) {
                store.setStoreStatus(StoreStatus.CLOSED);
                result = 1;
                saveStores(storeList);
            }
        }
        return result;
    }
}
