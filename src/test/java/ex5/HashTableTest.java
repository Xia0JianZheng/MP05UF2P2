package ex5;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class HashTableTest {


    @Test
    void put_vacio() {
        HashTable ht = new HashTable();
        ht.put("1", 1);

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, 1]", ht.toString());

        Assertions.assertEquals(ht.count(), 1);
        Assertions.assertEquals(ht.size(), 16);
    }

    @Test
    void put_sin_colision() {
        HashTable ht = new HashTable();
        ht.put("1", true);
        ht.put("2", false);

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, true]\n" +
                " bucket[2] = [2, false]", ht.toString());

        Assertions.assertEquals(ht.count(), 2);
        Assertions.assertEquals(ht.size(), 16);
    }

    @Test
    void put_colision_segundo() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("3", "E3");
        ht.put("13", "E13");

        Assertions.assertEquals(ht.count(), 4);
        Assertions.assertEquals(ht.size(), 32);
    }

    @Test
    void put_colision_tercero() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");

        ht.put("2", "E2");

        ht.put("3", "E3");
        ht.put("14", "E14");
        ht.put("25", "E25");

        Assertions.assertEquals(ht.count(), 5);
        Assertions.assertEquals(ht.size(), 64);
    }

    @Test
    void get_vacio() {
        HashTable ht = new HashTable();

        assertNull(ht.get("1"));
    }

    @Test
    void get_no_collision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");

        Assertions.assertEquals("E1", ht.get("1"));
    }

    @Test
    void get_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        Assertions.assertEquals("E12", ht.get("12"));
    }

    @Test
    void drop_no_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.drop("2");

        Assertions.assertEquals(ht.count(), 3);
        Assertions.assertEquals(ht.size(), 64);
    }

    @Test
    void drop_colision_primero() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.drop("1");

        Assertions.assertEquals(ht.count(), 3);
        Assertions.assertEquals(ht.size(), 64);
    }

    @Test
    void drop_null(){
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.drop("3");

        Assertions.assertEquals(ht.count(), 4);
        Assertions.assertEquals(ht.size(), 64);
    }

}