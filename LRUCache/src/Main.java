import utils.LRUCache;

// 2 2.1 3 4 5
public class Main {
    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>(4);
        cache.put("1", "1");
        cache.put("1", "1.1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");
        cache.remove("3");
        System.out.println(cache.get("1"));
        System.out.println(cache.get("2"));
        System.out.println(cache.get("3"));
        System.out.println(cache.get("1"));
        System.out.println(cache.get("4"));
        System.out.println(cache.get("5"));
        cache.put("5", "5");
        System.out.println(cache.get("1"));
        System.out.println(cache.get("2"));
        System.out.println(cache.get("3"));
        System.out.println(cache.get("1"));
        System.out.println(cache.get("4"));
        System.out.println(cache.get("5"));
        cache.resize(1);
        System.out.println(cache.get("1"));
        System.out.println(cache.get("2"));
        System.out.println(cache.get("3"));
        System.out.println(cache.get("1"));
        System.out.println(cache.get("4"));
        System.out.println(cache.get("5"));
        cache.evictAll();
        System.out.println(cache.get("1"));
        System.out.println(cache.get("2"));
        System.out.println(cache.get("3"));
        System.out.println(cache.get("1"));
        System.out.println(cache.get("4"));
        System.out.println(cache.get("5"));
        cache.resize(-1);
    }
}
