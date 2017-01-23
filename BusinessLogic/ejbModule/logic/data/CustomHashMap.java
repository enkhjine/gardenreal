package logic.data;

import logic.data.CustomHashMapEntry;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.Query;

public class CustomHashMap
implements Serializable {
    private static final long serialVersionUID = 1;
    private List<CustomHashMapEntry> hashMapEntries = new ArrayList<CustomHashMapEntry>();
    private int size = 0;

    public CustomHashMap() {
    }

    public CustomHashMap(HashMap<String, ?> hashMap) {
        for (Map.Entry entry : hashMap.entrySet()) {
            this.hashMapEntries.add(new CustomHashMapEntry(entry.getKey().toString(), entry.getValue()));
        }
    }

    public CustomHashMap(Map<String, String> filters, boolean likeByContains) {
        try {
            for (String filterProperty : filters.keySet()) {
                String filterValue = filters.get(filterProperty);
                if (filterValue == null || filterValue.isEmpty()) continue;
                filterValue = "%" + filterValue + (likeByContains ? "%" : "");
                this.put(filterProperty, filterValue);
            }
        }
        catch (Exception e) {
            System.out.println("CustomHashMap constructor: " + e.getMessage());
        }
    }

    public boolean contains(String key) {
        for (CustomHashMapEntry entry : this.hashMapEntries) {
            if (!entry.getKey().equals(key)) continue;
            return true;
        }
        return false;
    }

    public int containsInternal(String key) {
        if (this.hashMapEntries.size() > 0) {
            for (CustomHashMapEntry entry : this.hashMapEntries) {
                if (!entry.getKey().equals(key)) continue;
                return this.hashMapEntries.indexOf(entry);
            }
        }
        return -1;
    }

    public void clear() {
        this.hashMapEntries.clear();
        this.size = 0;
    }

    public void put(String key, Object value) {
        int index = this.containsInternal(key);
        if (index > -1) {
            this.hashMapEntries.remove(index);
        } else {
            ++this.size;
        }
        this.hashMapEntries.add(new CustomHashMapEntry(key, value));
    }

    public Object get(String key) {
        for (CustomHashMapEntry entry : this.hashMapEntries) {
            if (!entry.getKey().equals(key)) continue;
            return this.get(entry);
        }
        return null;
    }

    private Object get(CustomHashMapEntry entry) {
        return entry.getEditValue();
    }

    public HashMap<String, Object> ConvertToHashMap() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        for (CustomHashMapEntry entry : this.hashMapEntries) {
            hashMap.put(entry.getKey(), entry.getEditValue());
        }
        return hashMap;
    }

    public void setQuery(Query query) {
        for (CustomHashMapEntry entry : this.hashMapEntries) {
            query.setParameter(entry.getKey(), this.get(entry));
        }
    }

    public void setQuery(Query query, String jpql) {
        for (CustomHashMapEntry entry : this.hashMapEntries) {
            if (!jpql.contains((CharSequence)(":" + entry.getKey()))) continue;
            query.setParameter(entry.getKey(), this.get(entry));
        }
    }

    public void setHashMapEntries(List<CustomHashMapEntry> hasMapEntries) {
        this.hashMapEntries = hasMapEntries;
    }

    public List<CustomHashMapEntry> getHashMapEntries() {
        return this.hashMapEntries;
    }

    public int size() {
        return this.hashMapEntries.size();
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addRange(CustomHashMap map) {
        for (CustomHashMapEntry entry : map.hashMapEntries) {
            this.put(entry.getKey(), this.get(entry));
        }
    }

    public int hashCode() {
        if (this.getSize() == 0) {
            return 0;
        }
        return super.hashCode();
    }

    public void remove(String key) {
        for (CustomHashMapEntry entry : this.hashMapEntries) {
            if (!entry.getKey().equals(key)) continue;
            this.hashMapEntries.remove(this.hashMapEntries.indexOf(entry));
            this.size = this.size();
            break;
        }
    }
}