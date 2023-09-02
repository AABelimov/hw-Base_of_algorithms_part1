package pro.sky.hwbaseofalgorithms.stringlist;

import pro.sky.hwbaseofalgorithms.exception.ItemNotFoundException;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] list;
    private int size = 0;

    public StringListImpl(int capacity) {
        list = new String[capacity];
    }

    public StringListImpl() {
        this(5);
    }

    private void validateItem(String item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String add(String item) {
        validateItem(item);
        if (size >= list.length) {
            list = Arrays.copyOf(list, list.length + 5);
        }

        list[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateItem(item);
        if (index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == size) {
            return add(item);
        } else {
            return addNotToEnd(index, item);
        }
    }

    private String addNotToEnd(int index, String item) {
        if (list.length == size) {
            list = Arrays.copyOf(list, list.length + 5);
        }

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        size++;

        return list[index] = item;
    }

    @Override
    public String set(int index, String item) {
        validateItem(item);
        if (index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == size) {
            return add(item);
        } else {
            return list[index] = item;
        }
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                count++;
                removeItem(i);
            }
        }

        if (count == 0) {
            throw new ItemNotFoundException("Item can't be removed because it was not found");
        }
        return item;
    }

    private void removeItem(int i) {
        for (int j = i; j < size; j++) {
            list[j] = list[j + 1];
        }
        list[size] = null;
        size--;
    }

    @Override
    public String remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }

        String item = list[index];
        removeItem(index);
        return item;
    }

    @Override
    public boolean contains(String item) {
        validateItem(item);
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        validateItem(item);
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        validateItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return list[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullPointerException();
        }

        if (this == otherList) {
            return true;
        }

        if (size != otherList.size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!list[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        list = new String[list.length];
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(list, size);
    }
}
