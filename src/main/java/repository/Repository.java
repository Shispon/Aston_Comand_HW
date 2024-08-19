package repository;

import service.serialization.ValidationException;

public interface Repository<T> {
    void sortedList();
    T search(T entity);
    void addByConsole() throws ValidationException;
    void getFromFile();
    void addByFile();
    void addByRandom(int count);
    void getAll();
}
