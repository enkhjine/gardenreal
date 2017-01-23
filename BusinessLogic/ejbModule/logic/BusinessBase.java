package logic;

import java.util.Date;

import logic.data.DatabaseInfo;
import logic.interfaces.IDataAccessor;
import logic.interfaces.IBusiness;

public class BusinessBase implements IBusiness {
    protected IDataAccessor dataAccessor;
    protected DatabaseInfo databaseInfo;
    protected Date createdDate;

    public BusinessBase() {
    }

    public BusinessBase(DatabaseInfo databaseInfo) {
        this.databaseInfo = databaseInfo;
    }

    public IDataAccessor getDataAccessor() {
        return this.dataAccessor;
    }

    public void setDataAccessor1(IDataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    public DatabaseInfo getDatabaseInfo() {
        return this.databaseInfo;
    }

    public void setDatabaseInfo(DatabaseInfo databaseInfo) {
        this.databaseInfo = databaseInfo;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

	@Override
	public void setDataAccessor(IDataAccessor var1) throws Exception {
		// TODO Auto-generated method stub
		
	}
}