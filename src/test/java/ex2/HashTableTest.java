package ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void count() {
    }

    @Test
    void size() {
    }

    @Test
    void put_vacio() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1]", ht.toString());

    }

    @Test
    void put_sin_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1]\n" +
                " bucket[2] = [2, E2]", ht.toString());

    }

    @Test
    void put_colision_segundo() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");

        ht.put("12", "E12");

        //1 -> 01, 12, 23

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1] -> [12, E12]\n" +
                " bucket[2] = [2, E2]", ht.toString());

    }

    @Test
    void put_colision_tercero() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");

        ht.put("23", "E23");

        //1 -> 01, 12, 23

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1] -> [12, E12] -> [23, E23]\n" +
                " bucket[2] = [2, E2]", ht.toString());

    }

    @Test
    void update_no_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.put("2", "E2.2");

        //1 -> 01, 12, 23

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1] -> [12, E12] -> [23, E23]\n" +
                " bucket[2] = [2, E2.2]", ht.toString());

    }

    @Test
    void get() {
    }

    @Test
    void drop() {
    }

    @Test
    void testToString() {
    }

    @Test
    void getCollisionsForKey() {
    }

    @Test
    void testGetCollisionsForKey() {
    }
}