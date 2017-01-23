package logic.interfaces;

import logic.data.DatabaseInfo;
import logic.interfaces.IDataAccessor;

public interface IBusiness {
    public DatabaseInfo getDatabaseInfo();

    public void setDatabaseInfo(DatabaseInfo var1);

    public IDataAccessor getDataAccessor() throws Exception;

    public void setDataAccessor(IDataAccessor var1) throws Exception;
}
