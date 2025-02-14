package main.lunch.aggregate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class History implements Serializable {
    private LocalDate date;      // 갔던 날짜
    private Store store;    // 갔던 가게

    public History() {
    }

    public History(LocalDate date, Store store) {
        this.date = date;
        this.store = store;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "History{" +
                "date=" + date +
                ", store=" + store +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(date, history.date) && Objects.equals(store, history.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, store);
    }
}
