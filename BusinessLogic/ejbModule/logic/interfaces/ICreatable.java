package logic.interfaces;

import java.math.BigDecimal;
import java.util.Date;

public interface ICreatable {
    public Date getCreatedDate();

    public void setCreatedDate(Date var1);

    public BigDecimal getCreatedBy();

    public void setCreatedBy(BigDecimal var1);
}