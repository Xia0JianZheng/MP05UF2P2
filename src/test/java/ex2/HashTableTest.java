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
    void update_colision_primero() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.put("1", "E1.1");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1.1] -> [12, E12] -> [23, E23]\n" +
                " bucket[2] = [2, E2]", ht.toString());
    }

    @Test
    void update_colision_segundo() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.put("12", "E12.1");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1] -> [12, E12.1] -> [23, E23]\n" +
                " bucket[2] = [2, E2]", ht.toString());
    }

    @Test
    void update_colision_tercero() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.put("23", "E23.1");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1] -> [12, E12] -> [23, E23.1]\n" +
                " bucket[2] = [2, E2]", ht.toString());
    }

    @Test
    void get_vacio() {
        HashTable ht = new HashTable();

        assertNull(ht.get("1"));
    }

    @Test
    void get_colision_primero() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        Assertions.assertEquals("E12",ht.get("12"));
    }

    @Test
    public void get_colision_segundo() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("12", "E12");
        ht.put("23", "E23");
        ht.put("34", "E34");

        Assertions.assertEquals("E23", ht.get("23"));
    }

    @Test
    public void get_colision_tercero() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("12", "E12");
        ht.put("23", "E23");
        ht.put("34", "E34");

        Assertions.assertEquals("E34", ht.get("34"));
    }
    @Test
    public void get_null_empty_bucket() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("12", "E12");
        ht.put("23", "E23");
        ht.put("34", "E34");

        assertNull(ht.get("2"));
    }

    @Test
    public void get_null_no_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("3", "E3");
        ht.put("5", "E5");

        assertNull(ht.get("2"));
    }
    @Test
    public void get_null_colision_3_item() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("23", "E23");
        ht.put("34", "E34");
        ht.put("45", "E45");
        assertNull(ht.get("12"));
    }

    @Test
    void drop_no_colision() {
            HashTable ht = new HashTable();
            ht.put("1", "E1");
            ht.put("2", "E2");
            ht.put("12", "E12");
            ht.put("23", "E23");
            ht.put("34", "E34");

            ht.drop("2");

            assertNull(ht.get("2"));
            assertEquals("E1", ht.get("1"));
            assertEquals("E12", ht.get("12"));
            assertEquals("E23", ht.get("23"));
             assertEquals("E34", ht.get("34"));
    }

    @Test
    void drop_colision_primero() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");
        ht.put("34", "E34");

        ht.drop("12");

        assertNull(ht.get("12"));
        assertEquals("E1", ht.get("1"));
        assertEquals("E2", ht.get("2"));
        assertEquals("E23", ht.get("23"));
        assertEquals("E34", ht.get("34"));
    }

    @Test
    void drop_colision_segunda() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");
        ht.put("34", "E34");

        ht.drop("23");

        assertNull(ht.get("23"));
        assertEquals("E1", ht.get("1"));
        assertEquals("E2", ht.get("2"));
        assertEquals("E12", ht.get("12"));
        assertEquals("E34", ht.get("34"));
    }

    @Test
    void drop_colision_tercera() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");
        ht.put("34", "E34");

        ht.drop("34");

        assertNull(ht.get("34"));
        assertEquals("E1", ht.get("1"));
        assertEquals("E2", ht.get("2"));
        assertEquals("E12", ht.get("12"));
        assertEquals("E23", ht.get("23"));
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