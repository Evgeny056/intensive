package homework02;

import java.util.*;

public class CustomArrayList<E> implements MyLits<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private ArrayList<E> innerAl;
    private E[] elements;

    public CustomArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    private  ArrayList initInnerAlMass() {
        return innerAl = new ArrayList<E>(Arrays.asList(elements));
    }

    private void writeToArray() {
        ArrayList<E> temp = new ArrayList<>(innerAl);
            for (E el : temp) {
                if (el == null) innerAl.remove(el);
            }

        elements = (E[]) innerAl.toArray();
        innerAl = null;
    }

    public void add(E element) {
        initInnerAlMass();
        innerAl.add(element);
        writeToArray();
    }

    @Override
    public void add(int index, E element) {
        initInnerAlMass();
        innerAl.add(index, element);
        writeToArray();
    }

    @Override
    public void addAll(Collection<? extends E> c) {
        initInnerAlMass();
        innerAl.addAll(c);
        writeToArray();
    }

    @Override
    public void clear() {
        initInnerAlMass();
        innerAl.clear();
        writeToArray();
    }

    @Override
    public E get(int index) {
        initInnerAlMass();
        return innerAl.get(index);
    }

    @Override
    public boolean isEmpty() {
        initInnerAlMass();
        if (innerAl.isEmpty()) {
            return true;
        } else {
            innerAl = null;
            return false;
        }
    }

    @Override
    public void remove(int index) {
        initInnerAlMass();
        innerAl.remove(index);
        writeToArray();
    }

    @Override
    public void remove(Object o) {
        initInnerAlMass();
        innerAl.remove(o);
        writeToArray();
    }

    @Override
    public void sort(Comparator<? super E> c) {
        new SortedMerge(elements, elements.length, c);
    }

private class SortedMerge {

    private Comparator<E> c;

    private void mergeSort(Object[] elements, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Object[] l = new Object[mid];
        Object[] r = new Object[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = elements[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = elements[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);


        int i = 0, j = 0, k = 0;

        while (i < mid && j < n-mid) {
            int res = c.compare((E)l[i],(E) r[j]);

            if (res <= 0) {
                elements[k++] = l[i++];
            }
            else {
                elements[k++] = r[j++];
            }
        }
        while (i < mid) {
            elements[k++] = l[i++];
        }
        while (j < n-mid) {
            elements[k++] = r[j++];
        }
    }

    public SortedMerge(Object[] elements, int n, Comparator<? super E> c ) {
        this.c = (Comparator<E>) c;
        mergeSort(elements, n);
        }
    }
}
