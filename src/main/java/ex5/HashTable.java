package ex5;

// Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
// Modified by Fernando Porrino Serrano for academic purposes.

import java.util.ArrayList;

/**
 * Implementació d'una taula de hash sense col·lisions.
 * Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
 */
public class HashTable {
    private int SIZE = 16;
    private int ITEMS = 0;
    private HashEntry[] entries = new HashEntry[SIZE];

    // nuevo tamaño para hashentry
    int newSize = SIZE;
    // nuevo items para hashentry
    int newItems = 0;
    //nuevo hashentry
    HashEntry[] newEntries;
    //el tamaño del hashentry actual
    private int currentSize = SIZE;
    // detectar colision
    private boolean collision;

    public int count(){
        //return this.ITEMS;
        return this.ITEMS + this.newItems;

    }

    public int size(){
        //return this.SIZE;
        return this.currentSize;
    }

    /**
     * Permet afegir un nou element a la taula.
     * @param key La clau de l'element a afegir.
     * @param value El propi element que es vol afegir.
     */
    public void put(String key, /*String value*/Object value) {
        /*
        int hash = getHash(key);
        final HashEntry hashEntry = new HashEntry(key, value);

        if(entries[hash] == null) {
            entries[hash] = hashEntry;
        }
        else {
            //put

            HashEntry temp = entries[hash];
            while(temp.next != null)
                temp = temp.next;

            temp.next = hashEntry;
            hashEntry.prev = temp;


            //put_update_no_colision
            HashEntry temp = entries[hash];
            while(temp != null) {
                if(temp.key.equals(key)) {
                    temp.value = value; // Actualitza el valor si ja existeix la clau
                    return;
                }
                if (temp.next == null) {
                    temp.next = hashEntry;
                    hashEntry.prev = temp;
                    break;
                }
                temp = temp.next;
            }
        }
        //count
        ITEMS++;*/
        int hash = getHash(key);
        final HashEntry hashEntry = new HashEntry(key, value);

        if (entries[hash] == null) {
            entries[hash] = hashEntry;
            collision = false;
        } else {
            // comprobar el key para actualizar el valor
            HashEntry current = entries[hash];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; // Update existing key's value
                    return;
                }
                current = current.next;
            }

            // añadir nueva entrada a la nueva array
            newSize = currentSize * 2;
            currentSize = newSize;
            newEntries = new HashEntry[newSize];
            collision = true;
            for (int i = 0; i < SIZE; i++) {
                HashEntry entry = entries[i];
                while (entry != null) {
                    int newHash = getHash(entry.key);
                    HashEntry newEntry = new HashEntry(entry.key, entry.value);
                    if (newEntries[newHash] == null) {
                        newEntries[newHash] = newEntry;
                    } else {
                        // llama recursiva si occurre una collision en la array
                        SIZE = newSize;
                        entries = newEntries;
                        put(entry.key, entry.value);
                        return;
                    }
                    entry = entry.next;
                }
            }

            int newHash = getHash(key);
            if (newEntries[newHash] == null) {
                newEntries[newHash] = hashEntry;
            } else {

                SIZE = newSize;
                entries = newEntries;
                put(key, value);
                return;
            }
            SIZE = newSize;
            entries = newEntries;
        }
        ITEMS++;
    }

    /**
     * Permet recuperar un element dins la taula.
     * @param key La clau de l'element a trobar.
     * @return El propi element que es busca (null si no s'ha trobat).
     */
    public Object get(String key) {
        /*get
        int hash = getHash(key);
        if(entries[hash] != null) {
            HashEntry temp = entries[hash];
            while( !temp.key.equals(key))
                temp = temp.next;
            return temp.value;
        }
        return null;

        //get_null
        int hash = getHash(key);
        HashEntry temp = entries[hash];
        while (temp != null && !temp.key.equals(key)) {
            temp = temp.next;
        }
        if (temp != null) {
            return temp.value;
        } else {
            return null;
        }*/
        int hash = getHash(key);
        if(collision){
            HashEntry temp = newEntries[hash];
            while (temp != null && !temp.key.equals(key)) {
                temp = temp.next;
            }
            return (temp == null ? null : temp.value);
        }else{
            HashEntry temp = entries[hash];
            while (temp != null && !temp.key.equals(key)) {
                temp = temp.next;
            }
            return (temp == null ? null : temp.value);
        }

    }

    /**
     * Permet esborrar un element dins de la taula.
     * @param key La clau de l'element a trobar.
     */
    public void drop(String key) {
        /* drop
        int hash = getHash(key);
        if(entries[hash] != null) {

            HashEntry temp = entries[hash];
            while( !temp.key.equals(key))
                temp = temp.next;

            if(temp.prev == null) entries[hash] = null;             //esborrar element únic (no col·lissió)
            else{
                if(temp.next != null) temp.next.prev = temp.prev;   //esborrem temp, per tant actualitzem l'anterior al següent
                temp.prev.next = temp.next;                         //esborrem temp, per tant actualitzem el següent de l'anterior
            }
        }
        // drop_collision
        int hash = getHash(key);
        if (entries[hash] != null) {
            HashEntry temp = entries[hash];
            while (temp != null && !temp.key.equals(key)) {
                temp = temp.next;
            }
            if (temp != null) {
                if (temp.prev == null) {
                    entries[hash] = temp.next;
                    if (temp.next != null) {
                        temp.next.prev = null;
                    }
                } else {
                    temp.prev.next = temp.next;
                    if (temp.next != null) {
                        temp.next.prev = temp.prev;
                    }
                }
                //count
                ITEMS--;
            }
        }*/
        //drop_no colision
        int hash = getHash(key);
        if(collision){
            if (newEntries[hash] != null && newEntries[hash].key.equals(key)) {
                newEntries[hash] = null;
                ITEMS--;
            }
        }else {
            if (entries[hash] != null && entries[hash].key.equals(key)) {
                entries[hash] = null;
                ITEMS--;
            }
        }

    }

    private int getHash(String key) {
        // piggy backing on java string
        // hashcode implementation
        return key.hashCode() % /*SIZE*/currentSize;
    }

    @Override
    public String toString() {

        int bucket = 0;
        StringBuilder hashTableStr = new StringBuilder();
        /*
        for (HashEntry entry : entries) {
            if(entry == null) {
                bucket++;
                continue;
            }

            hashTableStr.append("\n bucket[")
                    .append(bucket)
                    .append("] = ")
                    .append(entry.toString());
            bucket++;
            HashEntry temp = entry.next;
            while(temp != null) {
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return hashTableStr.toString();
        */
        if(collision){
            for (HashEntry entry : newEntries) {
                if(entry == null) {
                    bucket++;
                    continue;
                }

                hashTableStr.append("\n bucket[")
                        .append(bucket)
                        .append("] = ")
                        .append(entry.toString());
                bucket++;
                HashEntry temp = entry.next;
                while(temp != null) {
                    hashTableStr.append(" -> ");
                    hashTableStr.append(temp.toString());
                    temp = temp.next;
                }
            }
            return hashTableStr.toString();
        }else {
            for (HashEntry entry : entries) {
                if(entry == null) {
                    bucket++;
                    continue;
                }

                hashTableStr.append("\n bucket[")
                        .append(bucket)
                        .append("] = ")
                        .append(entry.toString());
                bucket++;
                HashEntry temp = entry.next;
                while(temp != null) {
                    hashTableStr.append(" -> ");
                    hashTableStr.append(temp.toString());
                    temp = temp.next;
                }
            }
            return hashTableStr.toString();
        }


    }

    /**
     * Permet calcular quants elements col·lisionen (produeixen la mateixa posició dins la taula de hash) per a la clau donada.
     * @param key La clau que es farà servir per calcular col·lisions.
     * @return Una clau que, de fer-se servir, provoca col·lisió amb la que s'ha donat.
     */
    public String getCollisionsForKey(String key) {
        return getCollisionsForKey(key, 1).get(0);
    }

    /**
     * Permet calcular quants elements col·lisionen (produeixen la mateixa posició dins la taula de hash) per a la clau donada.
     * @param key La clau que es farà servir per calcular col·lisions.
     * @param quantity La quantitat de col·lisions a calcular.
     * @return Un llistat de claus que, de fer-se servir, provoquen col·lisió.
     */
    public ArrayList<String> getCollisionsForKey(String key, int quantity){
        /*
          Main idea:
          alphabet = {0, 1, 2}

          Step 1: "000"
          Step 2: "001"
          Step 3: "002"
          Step 4: "010"
          Step 5: "011"
           ...
          Step N: "222"

          All those keys will be hashed and checking if collides with the given one.
        * */

        final char[] alphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        ArrayList<Integer> newKey = new ArrayList();
        ArrayList<String> foundKeys = new ArrayList();

        newKey.add(0);
        int collision = getHash(key);;
        int current = newKey.size() -1;

        while (foundKeys.size() < quantity){
            //building current key
            String currentKey = "";
            for(int i = 0; i < newKey.size(); i++)
                currentKey += alphabet[newKey.get(i)];

            if(!currentKey.equals(key) && getHash(currentKey) == collision)
                foundKeys.add(currentKey);

            //increasing the current alphabet key
            newKey.set(current, newKey.get(current)+1);

            //overflow over the alphabet on current!
            if(newKey.get(current) == alphabet.length){
                int previous = current;
                do{
                    //increasing the previous to current alphabet key
                    previous--;
                    if(previous >= 0)  newKey.set(previous, newKey.get(previous) + 1);
                }
                while (previous >= 0 && newKey.get(previous) == alphabet.length);

                //cleaning
                for(int i = previous + 1; i < newKey.size(); i++)
                    newKey.set(i, 0);

                //increasing size on underflow over the key size
                if(previous < 0) newKey.add(0);

                current = newKey.size() -1;
            }
        }

        return  foundKeys;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        
        // Put some key values.
        for(int i=0; i<30; i++) {
            final String key = String.valueOf(i);
            hashTable.put(key, key);
        }

        // Print the HashTable structure
        Log.log("****   HashTable  ***");
        Log.log(hashTable.toString());
        Log.log("\nValue for key(20) : " + hashTable.get("20") );
    }

}