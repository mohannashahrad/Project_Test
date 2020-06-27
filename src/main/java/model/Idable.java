package model;

public interface Idable<T> {

    public int getId();
    public T getById(int id);

}
