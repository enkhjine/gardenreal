package logic;


import logic.data.CustomHashMapEntry;
import logic.data.CustomHashMap;
import logic.data.Tools;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SuperBusinessLogic {

	private String applicationName = "Hospital";
	private EntityManager entityManager;
	
	public EntityManager entityManager() throws Exception{
		if(entityManager == null){
			logic.data.DatabaseInfo databaseInfo = new logic.data.DatabaseInfo(applicationName);
	    	EntityManagerFactory emf = Persistence.createEntityManagerFactory(applicationName, databaseInfo.getProperties());
	    	entityManager = emf.createEntityManager();
		}
    	return entityManager;
	}
	
	public void setEntityManager(EntityManager eManager){
		if(eManager == null && entityManager != null) {
			entityManager.flush();
			entityManager.close();
			entityManager = null;
		}
		entityManager = eManager;
	}
	
	public SuperBusinessLogic() {
		
    }
	
	public <T> void insert(T entity) throws Exception {
        EntityManager em = entityManager();
        em.persist(entity);
        em.flush();
    }

    public <T> void insert(List<T> entities) throws Exception {
    	if (entities.size() == 0) {
            return;
        }
        EntityManager em = entityManager();
        for (T entity : entities) {
            em.persist(entity);
        }
        em.flush();
    }

    public <T> void update(T entity) throws Exception {
    	EntityManager em = entityManager();
        em.merge(entity);
        em.flush();
    }

    public <T> void update(List<T> entities) throws Exception {
    	if (entities.size() == 0) {
            return;
        }
        EntityManager em = entityManager();
        for (T entity : entities) {
            em.merge(entity);
        }
        em.flush();
    }

    public <T> void delete(T entity) throws Exception {
    	EntityManager em = entityManager();
    	em.remove(entity);
        em.flush();
    }

    public <T> void delete(List<T> entities) throws Exception {
    	if (entities.size() == 0) {
            return;
        }
        EntityManager em = entityManager();
        for (T entity : entities) {
            em.remove(entity);
        }
        em.flush();
    }

    public <T> void deleteByPkId(Class<T> type, Object pkId) throws Exception {
    	T obj = this.getByPkId(type, pkId);
        if (obj != null) {
            this.delete(obj);
        }
    }

    public <T> void deleteAll(Class<T> type) throws Exception {
    	String entityName = Tools.getClassName(type);
        String cmd = String.format("DELETE FROM %s o", entityName);
        EntityManager em = entityManager();
        Query query = entityManager().createQuery(cmd);
        query.executeUpdate();
        em.flush();
    }

    public <T> int deleteByAnyField(Class<T> type, String field, Object fieldValue) throws Exception {
    	String entityName = Tools.getClassName(type);
        String function = " = ";
        if (fieldValue instanceof List) {
            function = " IN ";
        }
        String cmd = String.format("DELETE FROM %1$s o WHERE o.%2$s %3$s :%2$s", entityName, field, function);
        EntityManager em = entityManager();
        Query query = entityManager().createQuery(cmd);
        query.setParameter(field, fieldValue);
        int affectedRowCount = query.executeUpdate();
        em.flush();
        return affectedRowCount;
    }

    public <T> List<T> getAll(Class<T> type) throws Exception {
        String jpql;
        jpql = String.format("SELECT o FROM %s o", Tools.getClassName(type));
        return this.getByQuery(type, jpql, null);
    }
    
    public int executeNonQuery(String jpql, CustomHashMap parameters) throws Exception {
        return this.executeNonQuery(jpql, parameters, false);
    }

    private int executeNonQuery(String jpql, CustomHashMap parameters, boolean isNative) throws Exception {
        EntityManager em = entityManager();
        Query query = null;
        query = isNative ? em.createNativeQuery(jpql) : em.createQuery(jpql);
        if (parameters != null && parameters.size() > 0) {
            parameters.setQuery(query, jpql);
        }
        return query.executeUpdate();
    }

    public List<Integer> executeNonQuery(List<String> queries, List<CustomHashMap> parameters) throws Exception {
        if (queries == null || queries.isEmpty()) {
            return null;
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        EntityManager em = entityManager();
        int index = 0;
        for (String jpql : queries) {
            Query query = em.createQuery(jpql);
            CustomHashMap parameter = null;
            try {
                parameter = parameters.get(index);
            }
            catch (Exception var10_10) {
                // empty catch block
            }
            if (parameter != null && parameter.size() > 0) {
                parameter.setQuery(query, jpql);
            }
            result.add(query.executeUpdate());
            ++index;
        }
        return result;
    }

    public <T> List<T> getByAnyField(Class<T> type, String field, Object fieldValue) throws Exception {
    	return this.getByAnyField(type, field, fieldValue, null);
    }

    public <T> List<T> getByAnyField(Class<T> type, String field, Object fieldValue, CustomHashMap sortOrders) throws Exception {
    	EntityManager em = entityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(type);
        Root root = criteriaQuery.from(type);
        criteriaQuery.where((Expression)this.getPredicate(criteriaBuilder, root, field, fieldValue));
        this.addOrderCriteria(criteriaBuilder, criteriaQuery, root, sortOrders);
        TypedQuery query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
    
    private <T> void addOrderCriteria(CriteriaBuilder criteriaBuilder, CriteriaQuery<T> criteriaQuery, Root<T> root, CustomHashMap sortOrders) {
        ArrayList<Order> orderList = new ArrayList<Order>();
        if (sortOrders != null && sortOrders.size() > 0) {
            for (CustomHashMapEntry entry : sortOrders.getHashMapEntries()) {
                boolean isAscending = true;
                if (entry.getEditValue() != null && entry.getEditValue().equals("DESC")) {
                    isAscending = false;
                }
                Order order = isAscending ? criteriaBuilder.asc((Expression)root.get(entry.getKey())) : criteriaBuilder.desc((Expression)root.get(entry.getKey()));
                orderList.add(order);
            }
            criteriaQuery.orderBy(orderList);
        }
    }
    
    private <T> Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root<T> root, String field, Object value) {
        Predicate predicate = null;
        if (value instanceof List) {
            List collection = (List)value;
            predicate = root.get(field).in((Collection)collection);
        } else {
            predicate = criteriaBuilder.equal((Expression)root.get(field), value);
        }
        return predicate;
    }

    public <T> List<T> getByQuery(Class<T> type, String jpql, CustomHashMap parameters, CustomHashMap dateParameters) throws Exception {
    	return this.getByQuery(type, jpql, parameters, dateParameters, 0, 0);
    }

    public <T> List<T> getByQuery(Class<T> type, String jpql, CustomHashMap parameters, int firstResult, int maxResult) throws Exception {
    	return this.getByQuery(type, jpql, parameters, null, firstResult, maxResult);
    }

    public List<?> getByQuery(String jpql, CustomHashMap parameters) throws Exception {
    	EntityManager em = entityManager();
        Query query = em.createQuery(jpql);
        if (parameters != null && parameters.size() > 0) {
            parameters.setQuery(query, jpql);
        }
        return query.getResultList();
    }
    
    @TransactionAttribute(value=TransactionAttributeType.NOT_SUPPORTED)
    public <T> List<T> getByQuery(Class<T> type, String jpql, CustomHashMap parameters, CustomHashMap dateParameters, int firstResult, int maxResult) throws Exception {
        EntityManager em = entityManager();
        TypedQuery query = em.createQuery(jpql, type);
        if (firstResult > 0) {
            query.setFirstResult(firstResult);
        }
        if (maxResult > 0) {
            query.setMaxResults(maxResult);
        }
        query.setHint("javax.persistence.cache.storeMode", (Object)CacheStoreMode.REFRESH);
        if (parameters != null && parameters.size() > 0) {
            parameters.setQuery((Query)query, jpql);
        }
        if (dateParameters != null && dateParameters.size() > 0) {
            for (CustomHashMapEntry entry : dateParameters.getHashMapEntries()) {
                if (!jpql.contains((CharSequence)entry.getKey())) continue;
                query.setParameter(entry.getKey(), (java.util.Date)entry.getEditValue(), TemporalType.DATE);
            }
        }
        return query.getResultList();
    }

    public Object getByQuerySingle(String jpql, CustomHashMap parameters) throws Exception {
    	EntityManager em = entityManager();
        Query query = em.createQuery(jpql);
        if (parameters != null && parameters.size() > 0) {
            parameters.setQuery(query, jpql);
        }
        return query.getSingleResult();
    }

    public <T> T getByPkId(Class<T> T, Object pkId) throws Exception {
        EntityManager em = entityManager();
        HashMap<String, Object> findProperties = new HashMap<String, Object>();
        findProperties.put("javax.persistence.cache.storeMode", "REFRESH");
        return (T)em.find(T, pkId, findProperties);
    }
    
    public <T> String getPkIdFieldName(Class<T> type) throws Exception {
        return String.valueOf(Tools.invokeMethod(type, (String)"getPkIdFieldName"));
    }

    @TransactionAttribute(value=TransactionAttributeType.NOT_SUPPORTED)
    public <T> T getById(Class<T> type, Object id) throws Exception {
        try {
            String query;
            
            query = String.format("SELECT o FROM %s o WHERE o.%s = :id", Tools.getClassName(type), this.getIdFieldName(type));
            
            TypedQuery typedQuery = entityManager().createQuery(query, type);
            typedQuery.setParameter("id", id);
            return (T)typedQuery.getSingleResult();
        }
        catch (NoResultException ex) {
            return null;
        }
    }
    
    public <T> String getIdFieldName(Class<T> type) throws Exception {
        return String.valueOf(Tools.invokeMethod(type, (String)"getIdFieldName"));
    }
    
    private <T> String getJpql(Class<T> type, CustomHashMap filter, String searchValue) throws Exception {
        return this.getJpql(type, filter, null, searchValue);
    }
    
    private <T> String getJpql(Class<T> type, CustomHashMap filter, CustomHashMap sortOrders, String searchValue) throws Exception {
        try {
            if (filter == null) {
                filter = new CustomHashMap();
            }
            if (sortOrders == null) {
                sortOrders = new CustomHashMap();
            }
            String jpql = String.valueOf(Tools.invokeMethod(type, (String)"getJpql", (Object[])new Object[]{filter, sortOrders, searchValue}, (Class[])new Class[]{CustomHashMap.class, CustomHashMap.class, String.class}));
            jpql = Tools.getJpqlOrder((String)jpql, (CustomHashMap)sortOrders);
            return jpql;
        }
        catch (NoSuchMethodException ex) {
            Tools.writeToLog((Exception)ex);
            throw new Exception("ListInfo \u0434\u044d\u044d\u0440 getJpql \u0444\u0443\u043d\u043a\u0446 (CustomHashMap parameters, CustomHashMap sortOrders, String searchValue) \u0433\u044d\u0441\u044d\u043d \u043f\u0430\u0440\u0430\u043c\u0435\u0442\u0440\u0443\u0443\u0434\u0442\u0430\u0439 \u0431\u0430\u0439\u043d\u0430.");
        }
        catch (Exception ex) {
            Tools.writeToLog((Exception)ex);
            throw ex;
        }
    }

    public <T> List<T> getByNativeQuery(Class<T> type, String jpql, CustomHashMap parameters) throws Exception {
        return this.getByNativeQueryAll(type, jpql, parameters, 0, 0);
    }

    public <T> List<T> getByNativeQuery(String type, String jpql, CustomHashMap parameters) throws Exception {
        return this.getByNativeQueryAll(type, jpql, parameters, 0, 0);
    }

    public <T> List<T> getByNativeQuery(String jpql, CustomHashMap parameters) throws Exception {
        return this.getByNativeQueryAll(null, jpql, parameters, 0, 0);
    }

    public <T> List<T> getByNativeQuery(Class<T> type, String jpql, CustomHashMap parameters, int firstResult, int maxResult) throws Exception {
        return getByNativeQueryAll(type, jpql, parameters, firstResult, maxResult);
    }

    public <T> List<T> getByNativeQuery(String jpql, CustomHashMap parameters, int firstResult, int maxResult) throws Exception {
    	return getByNativeQueryAll(null, jpql, parameters, firstResult, maxResult);
    }

    public <T> Object getByNativeQuerySingle(String jpql, CustomHashMap parameters) throws Exception {
        Query query = entityManager().createNativeQuery(jpql);
        if (parameters != null && parameters.size() > 0) {
            parameters.setQuery(query);
        }
        return query.getSingleResult();
    }

    public <T> Object getByNativeQuerySingle(Class<T> type, String jpql, CustomHashMap parameters) throws Exception {
    	TypedQuery<T> typedQuery = entityManager().createQuery(jpql, type);
    	typedQuery.setHint("javax.persistence.cache.storeMode", (Object)CacheStoreMode.REFRESH);
    	if (parameters != null && parameters.size() > 0) {
    		parameters.setQuery((Query)typedQuery, jpql);
    	}
    	return typedQuery.getSingleResult();
    }

    public <T> List<T> getByQuery(Class<T> type, String jpql, CustomHashMap parameters) throws Exception {
    	TypedQuery<T> typedQuery = entityManager().createQuery(jpql, type);
    	typedQuery.setHint("javax.persistence.cache.storeMode", (Object)CacheStoreMode.REFRESH);
    	if (parameters != null && parameters.size() > 0) {
    		parameters.setQuery((Query)typedQuery, jpql);
    	}
    	return (List<T>)typedQuery.getResultList();
    }
    
    public <T> List<T> getByNativeQueryAll(Object type, String jpql, CustomHashMap parameters, int firstResult, int maxResult) throws Exception {
        Query query = null;
        if (type == null) {
            query = entityManager().createNativeQuery(jpql);
        } else if (type instanceof String) {
            query = entityManager().createNativeQuery(jpql, (String)type);
        } else if (type instanceof Class) {
            query = entityManager().createNativeQuery(jpql, (Class)type);
        }
        if (firstResult > 0) {
            query.setFirstResult(firstResult);
        }
        if (maxResult > 0) {
            query.setMaxResults(maxResult);
        }
        if (parameters != null && parameters.size() > 0) {
            parameters.setQuery(query);
        }
        return query.getResultList();
    }
    
}