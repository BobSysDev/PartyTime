import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.collection.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest
{
  private ArrayList<String> list;

  @BeforeEach public void setup(){
    list = new ArrayList<>();
  }

  @Test public void zeroInsertOne(){
    assertDoesNotThrow(() -> list.add("thing"));
    assertEquals("{thing}", list.toString());
  }

  @Test public void zeroInsertNull(){
    assertDoesNotThrow(() -> list.add(null));
    assertEquals("{null}", list.toString());
  }

  @Test public void oneInsertOne(){
    list.add("thing");
    assertDoesNotThrow(() -> list.add("thing2"));
    assertEquals("{thing, thing2}", list.toString());
  }

  @Test public void oneInsertNull(){
    list.add("thing");
    assertDoesNotThrow(() -> list.add(null));
    assertEquals("{thing, null}", list.toString());
  }

  @Test public void manyInsertOne(){
    list.add("thing");
    list.add("thing2");
    list.add("thing3");
    list.add("thing4");
    list.add("thing5");
    assertDoesNotThrow(() -> list.add("thing6"));
    assertEquals("{thing, thing2, thing3, thing4, thing5, thing6}", list.toString());
  }

  @Test public void oneInsertMany(){
    list.add("thing");

    assertDoesNotThrow(() -> {
      for(int i = 2; i<6; i++)
      {
        list.add("thing"+i);
      }
    });

    assertEquals("{thing, thing2, thing3, thing4, thing5}", list.toString());
  }

  @Test public void manyInsertMiddle(){
    list.add("thing");
    list.add("thing2");
    list.add("thing3");
    list.add("thing4");
    list.add("thing5");
    assertDoesNotThrow(() -> list.add(2, "thing6"));
    assertEquals("{thing, thing2, thing6, thing3, thing4, thing5}", list.toString());
  }

  @Test public void oneInsertInvalid(){
    list.add("thing");
    Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "thing2"));
    assertEquals("Index:-1", exception.getMessage());
  }

  @Test public void zeroRemoveOne(){
    Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    assertEquals("index:0", exception.getMessage());
  }

  @Test public void oneRemoveOne(){
    list.add("thing");
    assertDoesNotThrow(() -> list.remove(0));
    assertEquals("{}", list.toString());
  }

  @Test public void oneRemoveInvalid(){
    list.add("thing");
    Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    assertEquals("index:1", exception.getMessage());
  }

  @Test public void manyRemoveOne(){
    list.add("thing");
    list.add("thing2");
    list.add("thing3");
    list.add("thing4");
    list.add("thing5");
    assertDoesNotThrow(() -> list.remove(2));
    assertEquals("{thing, thing2, thing4, thing5}", list.toString());
  }

  @Test public void manyRemoveInvalid(){
    list.add("thing");
    list.add("thing2");
    list.add("thing3");
    list.add("thing4");
    list.add("thing5");
    Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
    assertEquals("index:5", exception.getMessage());
  }

  @Test public void manyRemoveOneByOne(){
    list.add("thing");
    list.add("thing2");
    list.add("thing3");
    list.add("thing4");
    list.add("thing5");
    assertDoesNotThrow(() -> list.remove(0));
    assertDoesNotThrow(() -> list.remove(0));
    assertDoesNotThrow(() -> list.remove(0));
    assertDoesNotThrow(() -> list.remove(0));
    assertDoesNotThrow(() -> list.remove(0));
    assertEquals("{}",list.toString());
    Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    assertEquals("index:0", exception.getMessage());
  }


  @Test public void zeroGetOne(){
    Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    assertEquals("index:0", exception.getMessage());
  }

  @Test public void oneGetOne(){
    list.add("thing");
    assertEquals("thing", list.get(0));
  }

  @Test public void oneGetInvalid(){
    list.add("thing");
    Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    assertEquals("index:1", exception.getMessage());
  }

  @Test public void manyGetOne(){
    list.add("thing");
    list.add("thing2");
    list.add("thing3");
    list.add("thing4");
    list.add("thing5");
    assertEquals("thing", list.get(0));
  }

  @Test public void zeroIndexOfOne(){
    assertEquals(-1, list.indexOf("thing"));
  }

  @Test public void oneIndexOfOne(){
    list.add("thing");
    assertEquals(0, list.indexOf("thing"));
  }

  @Test public void oneIndexOfNull(){
    list.add(null);
    Throwable exception = assertThrows(NullPointerException.class, () -> list.indexOf(null));
    assertEquals("Cannot invoke \"Object.equals(Object)\" because \"element\" is null", exception.getMessage());
  }

  @Test public void oneIndexOfInvalid(){
    list.add("thing");
    assertEquals(-1, list.indexOf("thing2"));
  }

  @Test public void manyIndexOfOne(){
    list.add("thing");
    list.add("thing2");
    list.add("thing3");
    list.add("thing4");
    list.add("thing5");
    assertEquals(0, list.indexOf("thing"));
  }

  @Test public void emptyIsEmpty(){
    assertTrue(list.isEmpty());
  }

  @Test public void oneIsEmpty(){
    list.add("thing");
    assertFalse(list.isEmpty());
  }

  @Test public void manyIsEmpty()
  {
    list.add("thing");
    list.add("thing2");
    list.add("thing3");
    list.add("thing4");
    list.add("thing5");
    assertFalse(list.isEmpty());
  }

  @Test public void emptyIsFull(){
    assertFalse(list.isFull());
  }

  @Test public void oneIsFull(){
    list.add("thing");
    assertFalse(list.isFull());
  }

  @Test public void manyIsFull(){
    list.add("thing");
    list.add("thing2");
    list.add("thing3");
    list.add("thing4");
    list.add("thing5");
    assertFalse(list.isFull());
  }

  @Test public void zeroSize(){
    assertEquals(0, list.size());
  }

  @Test public void oneSize(){
    list.add("thing");
    assertEquals(1, list.size());
  }

  @Test public void manySize(){
    list.add("thing");
    list.add("thing2");
    list.add("thing3");
    list.add("thing4");
    list.add("thing5");
    assertEquals(5, list.size());
  }

  @Test public void zeroSet(){
    Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "thing"));
    assertEquals("index:0", exception.getMessage());
  }

  @Test public void oneSetOne(){
    list.add("thing");
    assertDoesNotThrow(() -> list.set(0, "thing2"));
    assertEquals("{thing2}", list.toString());
  }

  @Test public void oneSetInvalid(){
    list.add("thing");
    Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, "thing2"));
    assertEquals("index:1", exception.getMessage());
  }

  @Test public void manySetOne(){
    list.add("thing");
    list.add("thing2");
    list.add("thing3");
    list.add("thing4");
    list.add("thing5");
    assertDoesNotThrow(() -> list.set(2, "thing6"));
    assertEquals("{thing, thing2, thing6, thing4, thing5}", list.toString());
  }

  @Test public void manySetInvalid(){
    list.add("thing");
    list.add("thing2");
    list.add("thing3");
    list.add("thing4");
    list.add("thing5");
    Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, "thing6"));
    assertEquals("index:5", exception.getMessage());
  }
}