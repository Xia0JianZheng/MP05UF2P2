package ex5;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class HashTableTest {
    /*
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
    }*/
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
    /*
    @Test
    void put_colision_segundo() {
        HashTable ht = new HashTable();
        ht.put("1", 1);
        ht.put("12", 12);

        ht.put("23", 23);

        //1 -> 01, 12, 23

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, 1]\n" +
                " bucket[2] = [2, 12] -> [13, 13]", ht.toString());

        Assertions.assertEquals(ht.count(), 3);
        Assertions.assertEquals(ht.size(), 16);
    }*/
    @Test
    void put_colision() {
        HashTable ht = new HashTable();

        ht.put("1", 1);
        ht.put("12", 12);
        ht.put("23", 23);

        System.out.println(ht.toString());

        Assertions.assertEquals(ht.count(), 3);
        Assertions.assertEquals(ht.size(), 64);
    }
    /*
    @Test
    void update_no_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");

        ht.put("2", "E2.2");


        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1]\n" +
                " bucket[2] = [2, E2.2]", ht.toString());

    }*/
    @Test
    void update_no_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");

        ht.put("1", "E1.1");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1.1]\n" +
                " bucket[2] = [2, E2]", ht.toString());
    }
    /*
    @Test
    void update_colision_primero() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("12", "E12");

        ht.put("1", "E1.1");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1.1] -> [12, E12], ht.toString());
    }*/
    @Test
    void update_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("12", "E12");

        ht.put("1", "E1.1");

        System.out.println(ht.toString());

        Assertions.assertEquals("E1.1", ht.get("1"));
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
    /*
    @Test
    void get_colision_primero() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        Assertions.assertEquals("E1",ht.get("1"));
    }*/
    @Test
    void get_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        Assertions.assertEquals("E12", ht.get("12"));
    }
    /*
    @Test
    void drop_no_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.drop("2");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1] -> [12, E12] -> [23, E23]", ht.toString());

        Assertions.assertEquals(ht.count(), 3);
        Assertions.assertEquals(ht.size(), 16);
    }*/

    @Test
    void drop_no_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.drop("2");

        System.out.println(ht.toString());

        Assertions.assertEquals(ht.count(), 3);
        Assertions.assertEquals(ht.size(), 64);
    }
    /*
    @Test
    void drop_colision_primero() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.drop("1");

        Assertions.assertEquals("\n" +
                " bucket[1] = [12, E12] -> [23, E23]\n" +
                " bucket[2] = [2, E2]", ht.toString());

        Assertions.assertEquals(ht.count(), 3);
        Assertions.assertEquals(ht.size(), 16);
    }*/

    @Test
    void drop_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.drop("1");

        System.out.println(ht.toString());

        Assertions.assertEquals(ht.count(), 3);
        Assertions.assertEquals(ht.size(), 64);
    }

    /*
    @Test
    void drop_null(){
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.drop("3");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1] -> [12, E12] -> [23, E23]\n" +
                " bucket[2] = [2, E2]", ht.toString());

        Assertions.assertEquals(ht.count(), 4);
        Assertions.assertEquals(ht.size(), 16);
    }
     */

    @Test
    void drop_null(){
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.drop("3");

        System.out.println(ht.toString());

        Assertions.assertEquals(ht.count(), 4);
        Assertions.assertEquals(ht.size(), 64);
    }

}