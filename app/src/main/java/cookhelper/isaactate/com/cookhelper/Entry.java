package cookhelper.isaactate.com.cookhelper;

/**
 * Created by Jimmy on 11/29/2016.
 */

public class Entry<K,V> {
    K key;
    V value;

    K getKey(){
        return key;
    }
    V getValue(){
        return value;
    }

    public Entry(K k, V v){
        key = k;
        value = v;
    }
    public String toString(){
        return value.toString();
    }

}
