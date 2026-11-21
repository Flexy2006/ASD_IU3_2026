package ru.bmstu.lab2.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;

/** Проверяет собственный вспомогательный список и адаптацию к интерфейсу List. */
class ArrayListCustomTest {
    @Test
    void shouldStartEmpty() {
        ArrayListCustom<Integer> list = new ArrayListCustom<>();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void shouldAppendAndRead() {
        ArrayListCustom<Integer> list = new ArrayListCustom<>();
        assertTrue(list.add(7));
        assertEquals(7, list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    void shouldGrowWithoutLosingOrder() {
        ArrayListCustom<Integer> list = new ArrayListCustom<>();
        for (int index = 0; index < 1000; index++) {
            list.add(index);
        }
        assertEquals(1000, list.size());
        for (int index = 0; index < 1000; index++) {
            assertEquals(index, list.get(index));
        }
    }

    @Test
    void shouldRejectNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> new ArrayListCustom<>().get(-1));
    }

    @Test
    void shouldRejectIndexEqualToSize() {
        ArrayListCustom<Integer> list = new ArrayListCustom<>();
        list.add(7);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    void shouldRejectReadingEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> new ArrayListCustom<>().get(0));
    }

    @Test
    void shouldAllowNullInHelperList() {
        ArrayListCustom<Integer> list = new ArrayListCustom<>();
        list.add(null);
        assertNull(list.get(0));
    }

    @Test
    void shouldIterateInInsertionOrder() {
        ArrayListCustom<Integer> list = new ArrayListCustom<>();
        list.add(2);
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        assertEquals(2, iterator.next());
        assertEquals(1, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void shouldCompareUsingListContract() {
        ArrayListCustom<Integer> list = new ArrayListCustom<>();
        list.add(2);
        list.add(1);
        assertEquals(List.of(2, 1), list);
        assertEquals(list, List.of(2, 1));
    }

    @Test
    void shouldDetectModificationDuringIteration() {
        ArrayListCustom<Integer> list = new ArrayListCustom<>();
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        list.add(2);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }
}
