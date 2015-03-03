package com.zjy.baseDao;

import java.util.List;

import com.zjy.baseDao.Page;
import com.zjy.baseDao.PersistenceException;

public interface IBaseDAO<T, ID> {

	public Integer count(String propertyName, Object propertyValue);

	public Integer count(String[] propertyNames, Object... propertyValues);

	public Integer countLikeByMap(String[] propertyNames,
			Object[] propertyValues);

	public Integer countByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues);

	public T selectById(ID id);

	public T selectByProperties(String propertie, Object values);

	public T selectByProperties(String[] properties, Object... values);

	public <E> E selectByProperties(String statement, String[] properties,
			Object... values);

	public List<T> selectAll();

	public List<T> selectByIds(List<ID> ids);

	/**
	 * 直接从数据库查询出ids列表（包括符合条件的所有id）
	 * 
	 * @param properties
	 *            查询条件字段名
	 * @param propertyValues
	 *            字段取值
	 * @return
	 */
	public List<ID> findIdsByMap(String[] properties, Object[] propertyValues,
			String orderBy, String order);

	/**
	 * 根据条件查询结果
	 * 
	 * @param properties
	 *            查询条件名
	 * @param propertyValues
	 *            查询条件值
	 * @return
	 */
	public List<T> findByMap(String[] properties, Object[] propertyValues,
			String orderBy, String order);

	/**
	 * 分页查询（未处理缓存）
	 * 
	 * @param properties
	 *            查询条件字段名
	 * @param propertyValues
	 *            字段取值
	 * @return
	 */
	public List<T> pageQueryByMap(String[] properties, Object[] propertyValues,
			String orderBy, String order, int pageSize, int pageNo);

	/**
	 * 分页查询出id列表（处理缓存）
	 * 
	 * @param properties
	 *            查询条件字段名
	 * @param propertyValues
	 *            字段取值
	 * @return
	 */
	public List<ID> pageQueryIdsByMap(String[] properties,
			Object[] propertyValues, String orderBy, String order,
			int pageSize, int pageNo);

	/** like分页查询(不走列表缓存) */
	public List<T> pageLikeByMap(String[] properties, Object[] propertyValues,
			String orderBy, String order, int pageSize, int pageNo);

	/**
	 * 新增对象
	 */
	public int insert(T entity) throws PersistenceException;

	/**
	 * 新增对象List
	 */
	public int insert(List<T> entityList) throws PersistenceException;

	/**
	 * 更新对象
	 */
	public int update(T entity) throws PersistenceException;

	/**
	 * 更新对象的部分属性
	 */
	public int update(ID id, String propertie, Object propertyValue)
			throws PersistenceException;

	/**
	 * 更新对象的部分属性
	 */
	public int update(ID id, String[] properties, Object[] propertyValues)
			throws PersistenceException;

	/**
	 * 根据ID列表更新对象的部分属性
	 */
	public int updateByIdsMap(List<ID> ids, String[] properties,
			Object[] propertyValues) throws PersistenceException;

	// 根据主键，批量插入数据数据
	public int replace(List<T> entityList) throws PersistenceException;

	/**
	 * 根据ID删除对象
	 */
	public void deleteById(ID id) throws PersistenceException;

	/**
	 * 根据ID删除对象
	 */
	public void deleteByIds(List<ID> ids) throws PersistenceException;

	/** 根据ID及条件删除对象 */
	public void deleteByIdsMap(List<ID> ids, String[] properties,
			Object[] propertyValues) throws PersistenceException;

	/**
	 * 根据条件删除对象
	 */
	public int deleteByMap(String[] properties, Object[] propertyValues)
			throws PersistenceException;

	/**
	 * 根据自定义SqlMap中的条件语句查询出列表
	 */
	public List<T> findByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues, String orderBy,
			String order);

	/**
	 * 根据自定义SqlMap中的条件语句查询出对象
	 */
	public T findOneByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues, String orderBy,
			String order);

	/**
	 * 根据自定义SqlMap中的条件语句查询出列表
	 */
	public List<T> pageQueryByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues, String orderBy,
			String order, int pageSize, int pageNo);

	/**
	 * 根据自定义SqlMap中的条件语句更新数据
	 */
	public int updateByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues)
			throws PersistenceException;

	/**
	 * 根据自定义SqlMap中的条件语句删除数据
	 */
	public void deleteByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues)
			throws PersistenceException;

	/**
	 * 根据自定义SqlMap中的条件语句插入数据
	 */
	public void insertByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues)
			throws PersistenceException;

	public <E> List<E> findByStatementPostfix(String statement,
			String[] properties, Object[] propertyValues, int offset, int limit)
			throws PersistenceException;

	/**
	 * 分页查询
	 * 
	 * @param <E>
	 * @param statement
	 * @param page
	 * @return
	 */
	public <E> List<E> listByPage(String statement, Page page);
}
