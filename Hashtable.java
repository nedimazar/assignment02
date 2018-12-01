import java.util.ArrayList;

public class Hashtable {

    //Simple HashNode class to use like a linked list
    public class HashNode{
        private String key;
        private String value;
        private HashNode next;

        public HashNode(String key, String value){
            this.key = key;
            this.value = value;
            this.next = null;
        }
        public void setValue(String newValue){
            this.value = newValue;
        }
    }

    private ArrayList<HashNode> buckets;
    int noOfItems = 0;
    private final int numBuckets = 314527;

    public Hashtable(){
        this.buckets = new ArrayList<>(numBuckets);
        for (int i = 0; i < numBuckets; i++){
            this.buckets.add(null);
        }
    }

    int getBucket(String key){
        return(Math.abs(key.hashCode() % numBuckets));
    }

    boolean containsKey(String key){
        return (get(key) != null);
    }

    String get(String key){
        int bucketID = getBucket(key);
        HashNode node = buckets.get(bucketID);
        while(node != null){
            if (node.key.equals(key) ){
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    void put(String key, String value){
        int bucketID = getBucket(key);
        HashNode node = buckets.get(bucketID);

        while(node != null){
            if (node.key.equals(key)){
                node.setValue(value);
                return;
            }
            node = node.next;
        }
        HashNode newNode = new HashNode(key, value);
        newNode.next = buckets.get(bucketID);
        buckets.set(bucketID, newNode);
        noOfItems++;
    }

    String remove(String key){
        int bucketID = getBucket(key);
        HashNode node = buckets.get(bucketID);
        HashNode previous = null;

        while (node != null){
            if (node.key.equals(key)){
                break;
            }
            previous = node;
            node = node.next;
        }
        //A node is null
        if(node == null){
            return null;
        }
        --noOfItems;
        //B deleting head
        if (previous == null){
            buckets.set(bucketID, node.next);
        } else {//C deleting from middle or last item
            previous.next = node.next;
        }
        return  node.value;
    }


    public static void main(String args[]){
        Hashtable h = new Hashtable();
        h.put("abc", "sss");
        System.out.println(h.containsKey("abc"));

    }

}
