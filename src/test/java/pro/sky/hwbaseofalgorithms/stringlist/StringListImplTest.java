package pro.sky.hwbaseofalgorithms.stringlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.hwbaseofalgorithms.exception.ItemNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {
    StringList out = new StringListImpl();

    @BeforeEach
    public void beforeEach() {
        out.add("Test1");
        out.add("Test2");
        out.add("Test3");
    }

    @AfterEach
    public void afterEach() {
        out.clear();
    }

    @Test
    public void shouldReturnExceptionWhenAddedItemIsNull() {
        assertThrows(IllegalArgumentException.class, () -> out.add(null));
        assertThrows(IllegalArgumentException.class, () -> out.add(1, null));
        assertThrows(IllegalArgumentException.class, () -> out.set(1, null));
        assertThrows(IllegalArgumentException.class, () -> out.remove(null));
        assertThrows(IllegalArgumentException.class, () -> out.contains(null));
        assertThrows(IllegalArgumentException.class, () -> out.indexOf(null));
        assertThrows(IllegalArgumentException.class, () -> out.lastIndexOf(null));
    }

    @Test
    public void shouldReturnExceptionWhenIndexOutOfBound() {
        assertThrows(IndexOutOfBoundsException.class, () -> out.add(4, "Test4"));
        assertThrows(IndexOutOfBoundsException.class, () -> out.set(4, "Test4"));
        assertThrows(IndexOutOfBoundsException.class, () -> out.remove(4));
        assertThrows(IndexOutOfBoundsException.class, () -> out.get(4));
    }

    @Test
    public void shouldReturnSize() {
        assertEquals(3, out.size());
    }


    @Test
    public void shouldReturnFalseWhenListNotEmpty() {
        assertFalse(out.isEmpty());
    }

    @Test
    public void shouldReturnTrueWhenListEmpty() {
        out.clear();
        assertTrue(out.isEmpty());
    }

    @Test
    public void shouldReturnItemWhenItemIsCorrectAndShouldReturnSizeOfListAfterAdd() {
        assertEquals("Test4", out.add("Test4"));
        assertEquals("Test5", out.add("Test5"));
        assertEquals("Test6", out.add("Test6"));
        assertEquals(6, out.size());
    }

    @Test
    public void shouldReturnItemWhenItemIsCorrectAndShouldReturnSizeOfListAfterAddWithIndex() {
        assertEquals("Test4", out.add(3, "Test4"));
        assertEquals("Test5", out.add(4, "Test5"));
        assertEquals("Test6", out.add(5, "Test6"));
        assertEquals("Test7", out.add(3, "Test7"));
        assertEquals(7, out.size());
    }

    @Test
    public void shouldReturnItemWhenItemIsCorrectAndShouldReturnSizeOfListAfterSet() {
        assertEquals("Test4", out.set(3, "Test4"));
        assertEquals("Test5", out.set(4, "Test5"));
        assertEquals("Test6", out.set(5, "Test6"));
        assertEquals("Test7", out.set(3, "Test7"));
        assertEquals(6, out.size());
    }

    @Test
    public void shouldReturnItemWhenItemExistAndShouldReturnSize() {
        assertEquals("Test1", out.remove("Test1"));
        assertEquals(2, out.size());
    }

    @Test
    public void shouldReturnExceptionWhenItemNotExist() {
        assertThrows(ItemNotFoundException.class, () -> out.remove("Test11"));
    }

    @Test
    public void shouldReturnItemWhenIndexNotNotOutOfBoundAndShouldReturnSize() {
        assertEquals("Test1", out.remove(0));
        assertEquals(2, out.size());
    }

    @Test
    public void shouldReturnTrueWhenItemContainsInList() {
        assertTrue(out.contains("Test1"));
        assertTrue(out.contains("Test3"));
    }

    @Test
    public void shouldReturnFalseWhenItemNotContainsInList() {
        assertFalse(out.contains("Test4"));
    }

    @Test
    public void shouldReturnIndexOfItemOrNegativeNumberIfItemNotExist() {
        out.add("Test1");
        out.add("Test2");

        assertEquals(0, out.indexOf("Test1"));
        assertEquals(1, out.indexOf("Test2"));
        assertEquals(2, out.indexOf("Test3"));
        assertEquals(-1, out.indexOf("Test4"));
        assertEquals(-1, out.indexOf("Test"));
    }

    @Test
    public void shouldReturnIndexOfItemOrNegativeNumberIfItemNotExistWhenReverseSearch() {
        out.add("Test1");
        out.add("Test2");

        assertEquals(3, out.lastIndexOf("Test1"));
        assertEquals(4, out.lastIndexOf("Test2"));
        assertEquals(2, out.lastIndexOf("Test3"));
        assertEquals(-1, out.lastIndexOf("Test4"));
        assertEquals(-1, out.lastIndexOf("Test"));
    }

    @Test
    public void shouldReturnItemWhenIndexIsCorrect() {
        assertEquals("Test1", out.get(0));
        assertEquals("Test3", out.get(2));
    }

    @Test
    public void shouldReturnExceptionWhenIllegalIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> out.get(4));
    }

    @Test
    public void shouldReturnTrueWhenListsEqual() {
        StringList expected = new StringListImpl();
        expected.add("Test1");
        expected.add("Test2");
        expected.add("Test3");

        assertTrue(out.equals(expected));
    }

    @Test
    public void shouldReturnFalseWhenListsEqual() {
        StringList expected1 = new StringListImpl();
        StringList expected2 = new StringListImpl();
        expected1.add("Test1");
        expected1.add("Test2");
        expected2.add("Test1");
        expected2.add("Test2");
        expected2.add("Test4");

        assertFalse(out.equals(expected1));
        assertFalse(out.equals(expected2));
    }

    @Test
    public void shouldReturnArray() {
        String[] expected = {"Test1", "Test2", "Test3"};

        assertArrayEquals(expected, out.toArray());
    }
}