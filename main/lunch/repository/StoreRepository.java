package main.lunch.repository;

import main.lunch.aggregate.*;
import main.lunch.stream.MyObjectOutput;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class StoreRepository {
    private final List<Store> storeList = new ArrayList<>();
    private final File file = new File("main/lunch/db/storeDB.dat");
    private final List<History> historyList = new LinkedList<>();
    private final File historyFile = new File("main/lunch/db/historyDB.dat");

    public StoreRepository() {
        if (!file.exists()) {
            List<Store> defaultStores = new ArrayList<>();
            insertDefaultStores((ArrayList<Store>) defaultStores);
            saveStores((ArrayList<Store>) defaultStores);
        }
        if (!historyFile.exists()) {
            List<History> defaultHistory = new LinkedList<>();
            insertDefaultHistory((LinkedList<History>) defaultHistory);
            saveHistory((LinkedList<History>) defaultHistory);
        }

        loadStores();
        loadHistory();
    }

    private void loadHistory() {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(historyFile)
                    )
            );
            while (true) {
                historyList.add((History) (ois.readObject()));
            }
        } catch (EOFException e) {
            System.out.println("Loading History Complete!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveHistory(LinkedList<History> defaultHistory) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(historyFile)
                    )
            );
            for (History history : defaultHistory) {
                oos.writeObject(history);
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

    private void insertDefaultHistory(LinkedList<History> defaultHistory) {
        HashMap<String, Integer> defaultCatalog1 = new HashMap<>();
        HashSet<ClosedDays> defaultClosedDays1 = new HashSet<>();
        defaultCatalog1.put("떡라면", 4500);
        defaultCatalog1.put("라볶이", 5500);
        defaultClosedDays1.add(ClosedDays.TUE);
        defaultHistory.add(new History(LocalDate.of(25, 1, 1),
                new Store("포마토", defaultCatalog1, "횡단보도 건넌 바로 앞", 6, defaultClosedDays1, MenuTag.SNACK, StoreStatus.OPEN, 1)
        ));
    }

    private void insertDefaultStores(ArrayList<Store> defaultStores) {
        HashMap<String, Integer> defaultCatalog1 = new HashMap<>();
        HashSet<ClosedDays> defaultClosedDays1 = new HashSet<>();
        defaultCatalog1.put("떡라면", 4500);
        defaultCatalog1.put("라볶이", 5500);
        defaultClosedDays1.add(ClosedDays.TUE);
        defaultStores.add(new Store("포마토", defaultCatalog1, "횡단보도 건넌 바로 앞", 6, defaultClosedDays1, MenuTag.SNACK, StoreStatus.OPEN, 1));

        HashMap<String, Integer> defaultCatalog2 = new HashMap<>();
        HashSet<ClosedDays> defaultClosedDays2 = new HashSet<>();
        defaultCatalog2.put("햄치즈스페셜", 6000);
        defaultCatalog2.put("햄치즈", 5000);
        defaultClosedDays2.add(ClosedDays.SUN);
        defaultStores.add(new Store("이삭토스트", defaultCatalog2, "시장 초입", 4, defaultClosedDays2, MenuTag.SANDWICH, StoreStatus.OPEN, 2));

        HashMap<String, Integer> defaultCatalog3 = new HashMap<>();
        HashSet<ClosedDays> defaultClosedDays3 = new HashSet<>();
        defaultCatalog3.put("프레쉬버거", 8400);
        defaultClosedDays3.add(ClosedDays.SUN);
        defaultStores.add(new Store("버거리", defaultCatalog3, "3번 출구 앞", 8, defaultClosedDays3, MenuTag.BURGER, StoreStatus.OPEN, 3));
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
        ArrayList<Store> returnStoreList = new ArrayList<>();
        for (Store store : storeList) {
            if (store.getStoreStatus() != StoreStatus.CLOSED) {
                returnStoreList.add(store);
            }
        }
        return returnStoreList;
    }

    public ArrayList<Store> selectStoreByMenuTag(MenuTag menuTag) {
        ArrayList<Store> returnStoreList = new ArrayList<>();
        for (Store store : storeList) {
            if (store.getMenuTag().equals(menuTag) && store.getStoreStatus() != StoreStatus.CLOSED) {
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

    public ArrayList<Store> selectStoreByMaxPeople(int people) {
        ArrayList<Store> returnStoreList = new ArrayList<>();
        for (Store store : storeList) {
            if (store.getMaxPeople() >= people && store.getStoreStatus() != StoreStatus.CLOSED) {
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
            if (storeList.get(i).getStoreIdx() == reformedStore.getStoreIdx()) {
                storeList.set(i, reformedStore);
                saveStores((ArrayList<Store>) storeList);

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
                saveStores((ArrayList<Store>) storeList);
            }
        }
        return result;
    }

    public int selectLastStoreIdx() {
        Store lastStore = storeList.get(storeList.size() - 1);
        return lastStore.getStoreIdx();
    }
}
