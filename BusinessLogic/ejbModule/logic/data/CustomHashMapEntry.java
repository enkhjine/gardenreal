package logic.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CustomHashMapEntry
implements Serializable {
    private static final long serialVersionUID = 1;
    private String key;
    private Object value;
    private Date dateValue;
    private List<Object> listValue;

    public CustomHashMapEntry() {
    }

    public CustomHashMapEntry(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public CustomHashMapEntry(String key, List<Object> listValue) {
        this.key = key;
        this.listValue = listValue;
    }

    public CustomHashMapEntry(String key, Date dateValue) {
        this.key = key;
        this.dateValue = dateValue;
    }

    public Object getEditValue() {
        if (this.getValue() == null) {
            if (this.getListValue() == null) {
                return this.getDateValue();
            }
            return this.getListValue();
        }
        return this.getValue();
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Deprecated
    public Object getValue() {
        return this.value;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Date getDateValue() {
        return this.dateValue;
    }

    public void setListValue(List<Object> listValue) {
        this.listValue = listValue;
    }

    public List<Object> getListValue() {
        return this.listValue;
    }
}