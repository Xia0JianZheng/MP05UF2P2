package ex2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HashTableTest {


    @Test
    void put_vacio() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1]", ht.toString());

        Assertions.assertEquals(ht.count(), 1);
        Assertions.assertEquals(ht.size(), 16);
    }

    @Test
    void put_sin_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1]\n" +
                " bucket[2] = [2, E2]", ht.toString());

        Assertions.assertEquals(ht.count(), 2);
        Assertions.assertEquals(ht.size(), 16);
    }

    @Test
    void put_colision_segundo() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");

        ht.put("13", "E13");

        //1 -> 01, 12, 23

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1]\n" +
                " bucket[2] = [2, E2] -> [13, E13]", ht.toString());

        Assertions.assertEquals(ht.count(), 3);
        Assertions.assertEquals(ht.size(), 16);
    }

    @Test
    void put_colision_tercero() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");

        ht.put("2", "E2");

        ht.put("3", "E3");
        ht.put("03", "E03");
        ht.put("14", "E14");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1]\n" +
                " bucket[2] = [2, E2]\n" +
                " bucket[3] = [3, E3] -> [03, E03] -> [14, E14]", ht.toString());

        Assertions.assertEquals(ht.count(), 5);
        Assertions.assertEquals(ht.size(), 16);
    }

    @Test
    void update_no_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");

        ht.put("2", "E2.2");


        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1]\n" +
                " bucket[2] = [2, E2.2]", ht.toString());

    }

    @Test
    void update_colision_primero() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");

        ht.put("1", "E1.1");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1.1] -> [12, E12]\n" +
                " bucket[2] = [2, E2]", ht.toString());
    }

    @Test
    void update_colision_segundo() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");

        ht.put("13", "E13");
        ht.put("13", "E13.1");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1]\n" +
                " bucket[2] = [2, E2] -> [13, E13.1]", ht.toString());
    }

    @Test
    void update_colision_tercero() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("3", "E3");

        ht.put("14", "E14");
        ht.put("25", "E25");
        ht.put("25", "E25.1");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1]\n" +
                " bucket[2] = [2, E2]\n" +
                " bucket[3] = [3, E3] -> [14, E14] -> [25, E25.1]", ht.toString());
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

        Assertions.assertEquals("E1",ht.get("1"));
    }

    @Test
    public void get_colision_segundo() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("12", "E12");
        ht.put("23", "E23");

        Assertions.assertEquals("E12", ht.get("12"));
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

        assertNull(ht.get("1"));
    }

    @Test
    public void get_null_no_colision() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");

        assertNull(ht.get("12"));
    }
    @Test
    public void get_null_colision_3_item() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");

        ht.put("12", "E12");
        ht.put("23", "E23");
        assertNull(ht.get("34"));
    }

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
    }

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
    }

    @Test
    void drop_colision_segunda() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.drop("12");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1] -> [23, E23]\n" +
                " bucket[2] = [2, E2]", ht.toString());

        Assertions.assertEquals(ht.count(), 3);
        Assertions.assertEquals(ht.size(), 16);
    }

    @Test
    void drop_colision_tercera() {
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.drop("23");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1] -> [12, E12]\n" +
                " bucket[2] = [2, E2]", ht.toString());

        Assertions.assertEquals(ht.count(), 3);
        Assertions.assertEquals(ht.size(), 16);
    }

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

    @Test
    void drop_null_no_colision(){
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.drop("13");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1] -> [12, E12] -> [23, E23]\n" +
                " bucket[2] = [2, E2]", ht.toString());

        Assertions.assertEquals(ht.count(), 4);
        Assertions.assertEquals(ht.size(), 16);
    }

    @Test
    void drop_null_3_colision(){
        HashTable ht = new HashTable();
        ht.put("1", "E1");
        ht.put("2", "E2");
        ht.put("12", "E12");
        ht.put("23", "E23");

        ht.drop("34");

        Assertions.assertEquals("\n" +
                " bucket[1] = [1, E1] -> [12, E12] -> [23, E23]\n" +
                " bucket[2] = [2, E2]", ht.toString());

        Assertions.assertEquals(ht.count(), 4);
        Assertions.assertEquals(ht.size(), 16);
    }

}