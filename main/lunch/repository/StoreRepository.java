package main.lunch.repository;

import main.lunch.aggregate.StoreStatus;
import main.lunch.aggregate.ClosedDays;
import main.lunch.aggregate.MenuTag;
import main.lunch.aggregate.Store;
import main.lunch.stream.MyObjectOutput;

import java.io.*;
import java.util.ArrayList;

public class StoreRepository {
    private final ArrayList<Store> storeList = new ArrayList<>();
    private final File file = new File(
            "main/lunch/db/storeDB.dat");

    public StoreRepository() {
        if (!file.exists()) {
            ArrayList<Store> defaultStores = new ArrayList<>();
            defaultStores.add(new Store("포마토", "떡라면", "횡단보도 건넌 바로 앞", 6000, 6, ClosedDays.TUE, MenuTag.SNACK, StoreStatus.OPEN));
            defaultStores.add(new Store("이삭토스트", "햄치즈토스트", "시장 초입", 7000, 4, ClosedDays.SUN, MenuTag.SANDWICH, StoreStatus.OPEN));
            defaultStores.add(new Store("버거리", "프레쉬버거", "3번 출구 앞", 8400, 8, ClosedDays.SUN, MenuTag.BURGER, StoreStatus.OPEN));

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
