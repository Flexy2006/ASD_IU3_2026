package ru.bmstu.lab2.list;

import java.util.AbstractList;
import java.util.Objects;

/**
 * Простой массив-список для результатов 3A и 3C: поддерживает чтение и добавление в конец.
 * AbstractList даёт только адаптер интерфейса List (итератор, equals), хранение реализовано здесь.
 */
public class ArrayListCustom<T> extends AbstractList<T> {
    private static final int INITIAL_CAPACITY = 16;

    private Object[] elements = new Object[INITIAL_CAPACITY];
    private int size;

    /** Создаёт пустой список. */
    public ArrayListCustom() {
    }

    /** Возвращает элемент за O(1). */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    /** Возвращает размер за O(1). */
    @Override
    public int size() {
        return size;
    }

    /** Добавляет элемент в конец за O(1) амортизированно. */
    @Override
    public boolean add(T item) {
        if (size == elements.length) {
            Object[] expanded = new Object[elements.length * 2];
            System.arraycopy(elements, 0, expanded, 0, size);
            elements = expanded;
        }
        elements[size++] = item;
        modCount++;
        return true;
    }
}
