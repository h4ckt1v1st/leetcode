class TimeMap {

    Map<String, List<Pair>> map;

    public TimeMap() {
        this.map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        List<Pair> list = this.map.getOrDefault(key, new ArrayList<Pair>());
        list.add(new Pair(value, timestamp));
        this.map.put(key, list);
    }
    
    public String get(String key, int timestamp) {
        String result = "";

        List<Pair> listOfPairs = this.map.getOrDefault(key, List.of());

        // Apply binary search;
        int left = 0;
        int right = listOfPairs.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (timestamp >= listOfPairs.get(mid).timestamp) {
                // Positive case. We may have our best possible anser here. 
                result = listOfPairs.get(mid).value;
                left = mid + 1;

                // We may break here if we found an exact match. AKA == case.
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}

class Pair {
    // We can make these 2 private and expose getters and setters.
    String value;
    int timestamp;
    public Pair(String value, int timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    // If we need to print for debugging.
    public String toString() {
        return this.value + " ::: " + this.timestamp;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */