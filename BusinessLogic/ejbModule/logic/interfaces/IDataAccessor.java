package logic.interfaces;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import logic.data.CustomHashMap;
import logic.data.DatabaseInfo;

public interface IDataAccessor {
    public DatabaseInfo getDatabaseInfo();

    public void setDatabaseInfo(DatabaseInfo var1);

    public EntityManager getEntityManager() throws Exception;

    public EntityManager getNewEntityManager() throws Exception;

    public <T> List<T> getAll(Class<T> var1) throws Exception;

    public <T> List<T> getAll(Class<T> var1, CustomHashMap var2) throws Exception;

    public <T> List<T> getAll(Class<T> var1, Date var2) throws Exception;

    public <T> List<T> getAll(Class<T> var1, CustomHashMap var2, Date var3) throws Exception;

    public <T> List<T> getAll(Class<T> var1, CustomHashMap var2, CustomHashMap var3) throws Exception;

    public <T> List<T> getAll(String var1) throws Exception;

    public <T> List<T> getAll(String var1, Date var2) throws Exception;

    public <T> List<T> getAll(String var1, CustomHashMap var2, Date var3) throws Exception;

    public <T> List<T> getByAnyField(Class<T> var1, String var2, Object var3) throws Exception;

    public <T> List<T> getByAnyField(Class<T> var1, String var2, Object var3, CustomHashMap var4) throws Exception;

    public <T> List<T> getByCriteria(Class<T> var1, CustomHashMap var2) throws Exception;

    public <T> List<T> getByCriteria(Class<T> var1, CustomHashMap var2, CustomHashMap var3) throws Exception;

    public <T> T getByPkId(Class<T> var1, Object var2) throws Exception;

    public <T> T getByPkId(Class<T> var1, Object var2, boolean var3) throws Exception;

    public <T> T getById(Class<T> var1, Object var2) throws Exception;

    public <T> List<T> getByNamedQuery(Class<T> var1, String var2);

    public <T> List<T> getByNamedQuery(Class<T> var1, String var2, CustomHashMap var3);

    public <T> List<T> getByNativeQuery(Class<T> var1, String var2, CustomHashMap var3) throws Exception;

    public <T> List<T> getByNativeQuery(String var1, String var2, CustomHashMap var3) throws Exception;

    public <T> List<T> getByNativeQuery(String var1, CustomHashMap var2) throws Exception;

    public <T> List<T> getByNativeQuery(Class<T> var1, String var2, CustomHashMap var3, int var4, int var5) throws Exception;

    public <T> List<T> getByNativeQuery(String var1, String var2, CustomHashMap var3, int var4, int var5) throws Exception;

    public <T> List<T> getByNativeQuery(String var1, CustomHashMap var2, int var3, int var4) throws Exception;

    public <T> Object getByNativeQuerySingle(Class<T> var1, String var2, CustomHashMap var3) throws Exception;

    public <T> Object getByNativeQuerySingle(String var1, CustomHashMap var2) throws Exception;

    public <T> List<T> getByQuery(Class<T> var1, String var2, CustomHashMap var3) throws Exception;

    public <T> List<T> getByQuery(Class<T> var1, String var2, CustomHashMap var3, CustomHashMap var4) throws Exception;

    public <T> List<T> getByQuery(Class<T> var1, String var2, CustomHashMap var3, int var4, int var5) throws Exception;

    public List<?> getByQuery(String var1, CustomHashMap var2) throws Exception;

    public Object getByQuerySingle(String var1, CustomHashMap var2) throws Exception;

    public <T> List<?> search(String var1, String var2, CustomHashMap var3, int var4, int var5) throws Exception;

    public <T> List<?> search(Class<T> var1, String var2, CustomHashMap var3, int var4, int var5) throws Exception;

    public <T> List<?> search(Class<T> var1, String var2, CustomHashMap var3, CustomHashMap var4, int var5, int var6) throws Exception;

    public <T> List<?> searchByCriteria(Class<T> var1, String var2, CustomHashMap var3, int var4, int var5) throws Exception;

    public <T> List<T> searchByCriteria(Class<T> var1, String var2, CustomHashMap var3, CustomHashMap var4, int var5, int var6) throws Exception;

    public <T> List<T> getByRange(Class<T> var1, CustomHashMap var2, CustomHashMap var3, int var4, int var5) throws Exception;

    public <T> List<T> getByRange(Class<T> var1, CustomHashMap var2, CustomHashMap var3, int var4, int var5, boolean var6) throws Exception;

    public <T> T find(Class<T> var1, Object var2) throws Exception;

    public <T> T findByEntity(T var1) throws Exception;

    public <T> Object insert(T var1) throws Exception;

    public <T> void insert_Local(T var1) throws Exception;

    public <T> void insert(List<T> var1) throws Exception;

    public <T> void update(T var1) throws Exception;

    public <T> void update(List<T> var1) throws Exception;

    public <T> void delete(T var1) throws Exception;

    public <T> void delete(List<T> var1) throws Exception;

    public <T> void deleteByPkId(Class<T> var1, Object var2) throws Exception;

    public <T> int deleteByAnyField(Class<T> var1, String var2, Object var3) throws Exception;

    public <T> void deleteAll(Class<T> var1) throws Exception;

    public void modify(List<?> var1) throws Exception;

    public int executeNonQuery(String var1, CustomHashMap var2) throws Exception;

    public List<Integer> executeNonQuery(List<String> var1, List<CustomHashMap> var2) throws Exception;

    public int executeNonQuery(String var1) throws Exception;

    public int executeNativeNonQuery(String var1, CustomHashMap var2) throws Exception;

    public int executeNativeNonQuery(String var1, CustomHashMap var2, boolean var3) throws Exception;

    public <T> T refresh(T var1) throws Exception;

    public <T> T getReference(Class<T> var1, Object var2) throws Exception;

    public <T> long count(Class<T> var1) throws Exception;

    public <T> long count(Class<T> var1, CustomHashMap var2) throws Exception;

    public <T> long count(Class<T> var1, CustomHashMap var2, boolean var3) throws Exception;

    public <T> List<T> count(String var1, String var2, String var3, CustomHashMap var4) throws Exception;

    public void flush() throws Exception;

    public void close();

    public boolean useJndi();

    public <T> Date getLastModified(Class<T> var1) throws Exception;

    public <T> Date getLastModified(String var1) throws Exception;

    public Connection getConnectionByEntityManager() throws Exception;

    public HashMap<String, List<?>> getMultiResult(List<Class<?>> var1, String var2, CustomHashMap var3, List<Integer> var4) throws Exception;

    public CustomHashMap getEntityLastModifications(Date var1);

    public void release() throws Exception;
}