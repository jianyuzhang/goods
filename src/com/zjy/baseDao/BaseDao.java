/**
 * 
 */
package com.zjy.baseDao;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.zjy.baseDao.FunIMsgFormat;
import com.zjy.baseDao.Page;
import com.zjy.baseDao.PersistenceException;

public abstract class BaseDao<T, ID> extends AbstractBaseDao implements
		IBaseDAO<T, ID> {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	public static final String POSTFIX_SELECTBYID = ".selectById";
	public static final String POSTFIX_SELECTALL = ".selectAll";
	public static final String POSTFIX_SELECTBYPROPERTIES = ".selectByProperties";
	public static final String POSTFIX_SELECTBYIDS = ".selectByIds";
	public static final String POSTFIX_SELECTBYMAP = ".selectByMap";
	public static final String POSTFIX_SELECTIDSLIKEBYMAP = ".selectIdsLikeByMap";
	public static final String POSTFIX_PKSELECTMAP = ".pkSelectByMap";
	public static final String POSTFIX_COUNT = ".count";
	public static final String POSTFIX_COUNTLIKEBYMAP = ".countLikeByMap";
	public static final String POSTFIX_INSERT = ".insert";
	public static final String POSTFIX_DELETEBYID = ".deleteById";
	public static final String POSTFIX_DELETEBYIDS = ".deleteByIds";
	public static final String POSTFIX_DELETEBYIDSMAP = ".deleteByIdsMap";
	public static final String POSTFIX_DELETEBYMAP = ".deleteByMap";
	public static final String POSTFIX_UPDATE = ".update";
	public static final String POSTFIX_UPDATEBYMAP = ".updateByMap";
	public static final String POSTFIX_UPDATEBYIDSMAP = ".updateByIdsMap";

	protected Class<T> clazz;
	protected String clazzName;

	public String getClazzName() {
		return clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	protected T t;

	protected SqlSession sqlSession = this.getSqlSession();

	@SuppressWarnings("unchecked")
	public BaseDao() {
		clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		clazzName = clazz.getName();
	}

	/**
	 * 通过id得到实体对象
	 */
	@Override
	public T selectById(ID id) {
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("开始调用{}的findById方法,传入的参数是{}。"), new Object[] {
				this.getClass().getName(), id });
		if (id == null)
			return null;
		T t = getSqlSession().selectOne(clazzName + POSTFIX_SELECTBYID, id);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的findById方,执行的结果是{}。"), new Object[] {
				this.getClass().getName(), t });
		return t;

	}

	@Override
	public List<T> selectAll() {
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("开始调用{}的findAll()方法。"), this.getClass().getName());
		List<T> list = getSqlSession()
				.selectList(clazzName + POSTFIX_SELECTALL);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的findAll()方法,结果是{}，size={}。"), new Object[] {
				this.getClass().getName(), list, list.size() });
		return list;
	}

	@Override
	public T selectByProperties(String propertie, Object values) {
		return selectByProperties(new String[] { propertie }, values);
	}

	@Override
	public T selectByProperties(String[] properties, Object... values) {
		return findOneByStatementPostfix(".selectByProperties", properties,
				values, null, null);
	}

	@Override
	public <E> E selectByProperties(String statement, String[] properties,
			Object... values) {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的方法,传递的参数是statement={},properties={},propertyValues={}。"),
				new Object[] { this.getClass().getName(), statement,
						properties, values });
		if (StringUtils.isEmpty(statement))
			return null;
		Map<String, Object> map = buildMap(properties, values);
		E t = getSqlSession().selectOne(clazzName + statement, map);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的{}方法,结果是{}。"), new Object[] {
				this.getClass().getName(), statement, t });
		return t;
	}

	/**
	 * 根据ids获取实体列表
	 * 
	 * @param ids
	 * @return
	 */
	@Override
	public List<T> selectByIds(List<ID> ids) {
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("开始调用{}的findByIds方法,传入的参数是{}。"), new Object[] {
				this.getClass().getName(), ids });
		if (CollectionUtils.isEmpty(ids)) {
			throw new PersistenceException("传入参数为空！");
		}
		List<T> list = getSqlSession().selectList(
				clazzName + POSTFIX_SELECTBYIDS, ids);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的findByIds方法,结果是{}，size={}。"), new Object[] {
				this.getClass().getName(), list, list.size() });
		return list;
	}

	/**
	 * 根据条件查询结果
	 * 
	 * @param properties
	 *            查询条件名
	 * @param propertyValues
	 *            查询条件值
	 * @return
	 */
	public List<T> selectByMap(String[] properties, Object[] propertyValues,
			String orderBy, String order) throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的selectByMap方法,传入的参数是properties={},propertyValues={},orderBy={},order={}。"),
				new Object[] { this.getClass().getName(), properties,
						propertyValues, orderBy, order });
		Map<String, Object> map = buildMapWithOrder(properties, propertyValues,
				orderBy, order);
		List<T> list = getSqlSession().selectList(
				clazzName + POSTFIX_SELECTBYMAP, map);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的selectByMap方法,结果是{}，size={}。"),
				new Object[] { this.getClass().getName(), list, list.size() });
		return list;
	}

	/**
	 * 根据条件分页查询结果
	 * 
	 * @param properties
	 * @param propertyValues
	 * @param offset
	 *            起始记录
	 * @param limit
	 *            最大记录数
	 * @param orderBy
	 * @param order
	 * @return
	 * @throws PersistenceException
	 */
	public List<T> selectByMap(String[] properties, Object[] propertyValues,
			int offset, int limit, String orderBy, String order)
			throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的selectByMap方法,传入的参数是properties={},propertyValues={},orderBy={},order={}。"),
				new Object[] { this.getClass().getName(), properties,
						propertyValues, orderBy, order });
		Map<String, Object> map = buildMapWithOrder(properties, propertyValues,
				orderBy, order);
		RowBounds rowBound = new RowBounds(offset, limit);
		List<T> list = getSqlSession().selectList(
				clazzName + POSTFIX_SELECTBYMAP, map, rowBound);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的selectByMap方法,结果是{}，size={}。"),
				new Object[] { this.getClass().getName(), list, list.size() });
		return list;
	}

	/** like分页查询(不走列表缓存) */
	public List<T> pageLikeByMap(String[] properties, Object[] propertyValues,
			String orderBy, String order, int pageSize, int pageNo)
			throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的pageLikeByMap方法,传入的参数是properties={},propertyValues={},orderBy={},order={},pageSize={},pageNo={}。"),
				new Object[] { this.getClass().getName(), properties,
						propertyValues, orderBy, order, pageSize, pageNo });
		Map<String, Object> map = buildMapWithOrderAndPagination(properties,
				propertyValues, orderBy, order, pageSize, pageNo);
		List<ID> ids = getSqlSession().selectList(
				clazzName + POSTFIX_SELECTIDSLIKEBYMAP, map);
		List<T> list = selectByIds(ids);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的pageLikeByMap方法,结果是{}，size={}。"),
				new Object[] { this.getClass().getName(), list, list.size() });
		return list;
	}

	/**
	 * 新增对象
	 */
	@Override
	public int insert(T entity) throws PersistenceException {
		int i=0;
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("开始调用{}的insert方法,传入的参数是entity={}。"), new Object[] {
				this.getClass().getName(), entity });
		if (entity == null)
			throw new PersistenceException("保存的数据为空！");
		try {
			 i=getSqlSession().insert(clazzName + POSTFIX_INSERT, entity);
		} catch (Exception ex) {
			logger.debug(FunIMsgFormat.MsgStyle.ERROR_REPORT
					.getFormat("执行{}的insert方法出现异常，具体原因是：{}"), new Object[] {
					this.getClass().getName(), ex.getStackTrace() });
			throw new PersistenceException("执行数据插入出错。", ex);
		}
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的insert方法,结果是{}。"), new Object[] {
				this.getClass().getName(), entity });
		return i;
	}

	/*
	 * 批量插入对象 De.Li
	 */

	public int insert(List<T> entityList) throws PersistenceException {
		int insertRows = 0;
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("开始调用{}的insert方法,传入的参数是entity={}。"), new Object[] {
				this.getClass().getName(), entityList });
		if (entityList == null || entityList.isEmpty())
			throw new PersistenceException("保存的数据为空！");
		try {
			insertRows = getSqlSession().insert(clazzName + ".insertList",
					entityList);
		} catch (Exception ex) {
			logger.debug(FunIMsgFormat.MsgStyle.ERROR_REPORT
					.getFormat("执行{}的insert方法出现异常，具体原因是：{}"), new Object[] {
					this.getClass().getName(), ex.getStackTrace() });
			throw new PersistenceException("执行数据插入出错。", ex);
		}
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的insert方法,结果是{}。"), new Object[] {
				this.getClass().getName(), entityList });
		return insertRows;
	}

	/**
	 * 更新对象
	 */
	@Override
	public int update(T entity) throws PersistenceException {
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("开始调用{}的update方法,传入的参数是entity={}。"), new Object[] {
				this.getClass().getName(), entity });
		if (entity == null)
			throw new PersistenceException("更新的数据为空！");
		int record = 0;
		try {
			record = getSqlSession().update(clazzName + POSTFIX_UPDATE, entity);
		} catch (Exception ex) {
			logger.debug(FunIMsgFormat.MsgStyle.ERROR_REPORT
					.getFormat("执行{}的update方法出现异常，具体原因是：{}"), new Object[] {
					this.getClass().getName(), ex.getStackTrace() });
			throw new PersistenceException("执行数据更新出错。", ex);
		}
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的update方法,结果是{}。"), new Object[] {
				this.getClass().getName(), entity });
		return record;
	}

	/*
	 * 批量更新插入对象 De.Li
	 */

	public int replace(List<T> entityList) throws PersistenceException {
		int replaceRows = 0;
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("开始调用{}的replace方法,传入的参数是entity={}。"), new Object[] {
				this.getClass().getName(), entityList });
		if (entityList == null || entityList.isEmpty())
			throw new PersistenceException("保存的数据为空！");
		try {
			replaceRows = getSqlSession().insert(clazzName + ".replaceList",
					entityList);
		} catch (Exception ex) {
			logger.debug(FunIMsgFormat.MsgStyle.ERROR_REPORT
					.getFormat("执行{}的replace方法出现异常，具体原因是：{}"), new Object[] {
					this.getClass().getName(), ex.getStackTrace() });
			throw new PersistenceException("执行数据插入出错。", ex);
		}
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的replace方法,结果是{}。"), new Object[] {
				this.getClass().getName(), entityList });
		return replaceRows;
	}

	/**
	 * 根据ID删除对象
	 */
	@Override
	public void deleteById(ID id) throws PersistenceException {
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("开始调用{}的deleteById方法,传递的参数是id={}。"), new Object[] {
				this.getClass().getName(), id });
		if (id == null)
			throw new PersistenceException("主键为空！");
		try {
			getSqlSession().delete(clazzName + POSTFIX_DELETEBYID, id);
		} catch (Exception ex) {
			logger.debug(FunIMsgFormat.MsgStyle.ERROR_REPORT
					.getFormat("执行{}的deleteById方法出现异常，具体原因是：{}"), new Object[] {
					this.getClass().getName(), ex.getStackTrace() });
			throw new PersistenceException("执行删除出错。", ex);
		}
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的deleteById方法。"), this.getClass().getName());
	}

	/**
	 * 根据ID删除对象
	 */
	@Override
	public void deleteByIds(List<ID> ids) throws PersistenceException {
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("开始调用{}的deleteByIds方法,传递的参数是ids={}。"), new Object[] {
				this.getClass().getName(), ids });
		if (CollectionUtils.isEmpty(ids))
			throw new PersistenceException("主键为空！");
		try {
			getSqlSession().delete(clazzName + POSTFIX_DELETEBYIDS, ids);
		} catch (Exception ex) {
			logger.debug(
					FunIMsgFormat.MsgStyle.ERROR_REPORT
							.getFormat("执行{}的deleteByIds方法出现异常，具体原因是：{}"),
					new Object[] { this.getClass().getName(),
							ex.getStackTrace() });
			throw new PersistenceException("执行批量删除出错。", ex);
		}
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的deleteByIds方法。"), this.getClass().getName());
	}

	@Override
	public Integer count(String propertyName, Object propertyValue) {
		return count(new String[] { propertyName },
				new Object[] { propertyValue });
	}

	@Override
	public Integer count(String[] propertyNames, Object... propertyValues)
			throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的count方法,传入的参数propertyNames={},propertyValues={}"),
				new Object[] { this.getClass().getName(), propertyNames,
						propertyValues });
		Map<String, Object> map = buildMap(propertyNames, propertyValues);
		int cout = getSqlSession().selectOne(clazzName + POSTFIX_COUNT, map);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的count方法,执行的结果是{}。"), new Object[] {
				this.getClass().getName(), cout });

		return cout;
	}

	public Integer count(Map<String, Object> map) {
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("开始调用{}的count方法,传入的参数是：{}"), map.toString());
		int cout = getSqlSession().selectOne(clazzName + POSTFIX_COUNT, map);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的count方法,执行的结果是{}。"), new Object[] {
				this.getClass().getName(), cout });

		return cout;
	}

	@Override
	public Integer countLikeByMap(String[] propertyNames,
			Object[] propertyValues) throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的countLikeByMap方法,传入的参数propertyNames={},propertyValues={}"),
				new Object[] { this.getClass().getName(), propertyNames,
						propertyValues });
		Map<String, Object> map = buildMap(propertyNames, propertyValues);
		Integer cout = (Integer) getSqlSession().selectOne(
				clazzName + POSTFIX_COUNTLIKEBYMAP, map);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的countLikeByMap方法,执行的结果是{}。"), new Object[] {
				this.getClass().getName(), cout });
		return cout;
	}

	/** 根据自定义SqlMap中的条件语句查询出记录数量 */
	@Override
	public Integer countByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues)
			throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的countByStatementPostfix方法,传入的参数statementPostfix={},propertyNames={},propertyValues={}"),
				new Object[] { this.getClass().getName(), statementPostfix,
						properties, propertyValues });
		if (StringUtils.isEmpty(statementPostfix))
			return 0;
		Map<String, Object> map = buildMap(properties, propertyValues);
		Integer cout = (Integer) getSqlSession().selectOne(
				clazzName + statementPostfix, map);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的countByStatementPostfix方法，执行的结果是{}。"),
				new Object[] { this.getClass().getName(), cout });
		if (cout != null) {
			return cout.intValue();
		}

		return 0;

	}

	/**
	 * 直接从数据库查询出ids列表（包括符合条件的所有id）
	 * 
	 * @param properties
	 *            查询条件字段名
	 * @param propertyValues
	 *            字段取值
	 * @return
	 */
	@Override
	public List<ID> findIdsByMap(String[] properties, Object[] propertyValues,
			String orderBy, String order) throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的findIdsByMap方法,传入的参数是properties={},propertyValues={},orderBy={},order={}。"),
				new Object[] { this.getClass().getName(), properties,
						propertyValues, orderBy, order });
		Map<String, Object> map = buildMapWithOrder(properties, propertyValues,
				orderBy, order);
		List<ID> list = getSqlSession().selectList(
				clazzName + POSTFIX_PKSELECTMAP, map);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的findIdsByMap方法,结果是{}，size={}。"),
				new Object[] { this.getClass().getName(), list, list.size() });
		return list;
	}

	/**
	 * 直接从数据库查询出ids列表（包括符合条件的所有id）
	 * 
	 * @param <E>
	 * 
	 * @param properties
	 *            查询条件字段名
	 * @param propertyValues
	 *            字段取值
	 * @return
	 */
	@Override
	public <E> List<E> findByStatementPostfix(String statement,
			String[] properties, Object[] propertyValues, int offset, int limit)
			throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的{}方法,传入的参数是properties={},propertyValues={}。"),
				new Object[] { this.getClass().getName(), statement,
						properties, propertyValues });
		Map<String, Object> map = buildMap(properties, propertyValues);
		RowBounds row = new RowBounds(offset, limit);
		List<E> list = getSqlSession().selectList(clazzName + statement, map,
				row);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的{}方法,结果是{}，size={}。"), new Object[] {
				this.getClass().getName(), statement, list, list.size() });
		return list;
	}

	/**
	 * 根据条件查询结果
	 * 
	 * @param properties
	 *            查询条件名
	 * @param propertyValues
	 *            查询条件值
	 * @return
	 */
	@Override
	public List<T> findByMap(String[] properties, Object[] propertyValues,
			String orderBy, String order) throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的findByMap方法,传入的参数是properties={},propertyValues={},orderBy={},order={}。"),
				new Object[] { this.getClass().getName(), properties,
						propertyValues, orderBy, order });
		Map<String, Object> map = buildMapWithOrder(properties, propertyValues,
				orderBy, order);
		List<T> list = getSqlSession().selectList(
				clazzName + POSTFIX_SELECTBYMAP, map);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的findByMap方法,结果是{}，size={}。"), new Object[] {
				this.getClass().getName(), list, list.size() });
		return list;
	}

	/**
	 * 分页查询（未处理缓存）
	 * 
	 * @param properties
	 *            查询条件字段名
	 * @param propertyValues
	 *            字段取值
	 * @return
	 */
	@Override
	public List<T> pageQueryByMap(String[] properties, Object[] propertyValues,
			String orderBy, String order, int pageSize, int pageNo)
			throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的pageQueryByMap方法,传入的参数是properties={},propertyValues={},orderBy={},order={},pageSize={},pageNo={}。"),
				new Object[] { this.getClass().getName(), properties,
						propertyValues, orderBy, order, pageSize, pageNo });
		Map<String, Object> map = buildMapWithOrderAndPagination(properties,
				propertyValues, orderBy, order, pageSize, pageNo);
		List<T> list = getSqlSession().selectList(
				clazzName + POSTFIX_SELECTBYMAP, map);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的pageQueryByMap方法,结果是{}，size={}。"),
				new Object[] { this.getClass().getName(), list, list.size() });
		return list;
	}

	/**
	 * 分页查询出id列表（处理缓存）
	 * 
	 * @param properties
	 *            查询条件字段名
	 * @param propertyValues
	 *            字段取值
	 * @return
	 */
	@Override
	public List<ID> pageQueryIdsByMap(String[] properties,
			Object[] propertyValues, String orderBy, String order,
			int pageSize, int pageNo) throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的pageQueryIdsByMap方法,传入的参数是properties={},propertyValues={},orderBy={},order={},pageSize={},pageNo={}。"),
				new Object[] { this.getClass().getName(), properties,
						propertyValues, orderBy, order, pageSize, pageNo });
		Map<String, Object> map = buildMapWithOrderAndPagination(properties,
				propertyValues, orderBy, order, pageSize, pageNo);
		List<ID> list = getSqlSession().selectList(
				clazzName + POSTFIX_PKSELECTMAP, map);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的pageQueryIdsByMap方法,结果是{}，size={}。"),
				new Object[] { this.getClass().getName(), list, list.size() });
		return list;
	}

	/**
	 * 更新对象的部分属性
	 */
	@Override
	public int update(ID id, String propertie, Object propertyValue)
			throws PersistenceException {
		return update(id, new String[] { propertie },
				new Object[] { propertyValue });
	}

	/**
	 * 更新对象的部分属性
	 */
	@Override
	public int update(ID id, String[] properties, Object[] propertyValues)
			throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的update方法,传入的参数是id={},properties={},propertyValues={}。"),
				new Object[] { this.getClass().getName(), id, properties,
						propertyValues });
		if (id == null)
			throw new PersistenceException("主键为空！");
		Map<String, Object> map = buildMap(properties, propertyValues);
		map.put("id", id);
		int count = 0;
		try {
			count = getSqlSession()
					.update(clazzName + POSTFIX_UPDATEBYMAP, map);
		} catch (Exception ex) {
			logger.debug(FunIMsgFormat.MsgStyle.ERROR_REPORT
					.getFormat("执行{}的update方法出现异常，具体原因是：{}"), new Object[] {
					this.getClass().getName(), ex.getStackTrace() });
			throw new PersistenceException("执行数据更新出错。", ex);
		}
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的update方法,结果是{}。"), new Object[] {
				this.getClass().getName(), count });
		return count;
	}

	/**
	 * 根据ID列表更新对象的部分属性
	 */
	@Override
	public int updateByIdsMap(List<ID> ids, String[] properties,
			Object[] propertyValues) throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的updateByIdsMap方法,传入的参数是ids={},properties={},propertyValues={}。"),
				new Object[] { this.getClass().getName(), ids, properties,
						propertyValues });
		if (CollectionUtils.isEmpty(ids))
			new PersistenceException("主键为空！");
		Map<String, Object> map = buildMap(properties, propertyValues);
		map.put("ids", ids);
		int count = 0;
		try {
			count = getSqlSession().update(clazzName + POSTFIX_UPDATEBYIDSMAP,
					map);
		} catch (Exception ex) {
			logger.debug(
					FunIMsgFormat.MsgStyle.ERROR_REPORT
							.getFormat("执行{}的updateByIdsMap方法出现异常，具体原因是：{}"),
					new Object[] { this.getClass().getName(),
							ex.getStackTrace() });
			throw new PersistenceException("执行批量数据更新出错。", ex);
		}
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的updateByIdsMap方法,结果是{}。"), new Object[] {
				this.getClass().getName(), count });
		return count;
	}

	/** 根据ID及条件删除对象 */
	@Override
	public void deleteByIdsMap(List<ID> ids, String[] properties,
			Object[] propertyValues) throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的deleteByIdsMap方法,传递的参数是ids={},properties={},propertyValues={}。"),
				new Object[] { this.getClass().getName(), ids, properties,
						propertyValues });
		if (CollectionUtils.isEmpty(ids))
			throw new PersistenceException("主键为空！");
		Map<String, Object> map = buildMap(properties, propertyValues);
		map.put("ids", ids);
		try {
			getSqlSession().delete(clazzName + POSTFIX_DELETEBYIDSMAP, map);
		} catch (Exception ex) {
			logger.debug(
					FunIMsgFormat.MsgStyle.ERROR_REPORT
							.getFormat("执行{}的deleteByIdsMap方法出现异常，具体原因是：{}"),
					new Object[] { this.getClass().getName(),
							ex.getStackTrace() });
			throw new PersistenceException("执行批量删除出错。", ex);
		}
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的deleteByIdsMap方法。"), this.getClass()
				.getName());
	}

	/**
	 * 根据条件删除对象
	 */
	@Override
	public int deleteByMap(String[] properties, Object[] propertyValues)
			throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的deleteByMap方法,传递的参数是properties={},propertyValues={}。"),
				new Object[] { this.getClass().getName(), properties,
						propertyValues });
		Map<String, Object> map = buildMap(properties, propertyValues);
		int count = 0;
		try {
			count = getSqlSession()
					.delete(clazzName + POSTFIX_DELETEBYMAP, map);
		} catch (Exception ex) {
			logger.debug(
					FunIMsgFormat.MsgStyle.ERROR_REPORT
							.getFormat("执行{}的deleteByMap方法出现异常，具体原因是：{}"),
					new Object[] { this.getClass().getName(),
							ex.getStackTrace() });
			throw new PersistenceException("执行批量删除出错。", ex);
		}
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的deleteByMap方法,执行的结果是{}。"), new Object[] {
				this.getClass().getName(), count });
		return count;
	}

	/**
	 * 根据自定义SqlMap中的条件语句查询出列表(注意：不处理缓存)
	 */
	@Override
	public List<T> findByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues, String orderBy,
			String order) throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的findByStatementPostfix方法,传递的参数是statementPostfix={},properties={},propertyValues={},orderBy={},order={}。"),
				new Object[] { this.getClass().getName(), statementPostfix,
						properties, propertyValues, orderBy, order });
		if (StringUtils.isEmpty(statementPostfix))
			return null;
		Map<String, Object> map = buildMapWithOrder(properties, propertyValues,
				orderBy, order);
		List<T> list = getSqlSession().selectList(clazzName + statementPostfix,
				map);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的findByStatementPostfix方法,结果是{}，size={}。"),
				new Object[] { this.getClass().getName(), list, list.size() });
		return list;
	}

	/**
	 * 根据自定义SqlMap中的条件语句查询出对象(注意：不处理缓存)
	 */
	@Override
	public T findOneByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues, String orderBy,
			String order) throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的findOneByStatementPostfix方法,传递的参数是statementPostfix={},properties={},propertyValues={},orderBy={},order={}。"),
				new Object[] { this.getClass().getName(), statementPostfix,
						properties, propertyValues, orderBy, order });
		if (StringUtils.isEmpty(statementPostfix))
			return null;
		Map<String, Object> map = buildMapWithOrder(properties, propertyValues,
				orderBy, order);
		T t = getSqlSession().selectOne(clazzName + statementPostfix, map);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的findOneByStatementPostfix方法,结果是{}。"),
				new Object[] { this.getClass().getName(), t });
		return t;
	}

	/**
	 * 根据自定义SqlMap中的条件语句查询出列表(注意：不处理缓存)
	 */
	@Override
	public List<T> pageQueryByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues, String orderBy,
			String order, int pageSize, int pageNo) throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的findByStatementPostfix方法,传递的参数是statementPostfix={},properties={},propertyValues={},orderBy={},order={},pageSize={},pageNo={}。"),
				new Object[] { this.getClass().getName(), statementPostfix,
						properties, propertyValues, orderBy, order, pageSize,
						pageNo });
		if (StringUtils.isEmpty(statementPostfix))
			return null;
		Map<String, Object> map = buildMapWithOrderAndPagination(properties,
				propertyValues, orderBy, order, pageSize, pageNo);
		List<ID> ids = getSqlSession().selectList(clazzName + statementPostfix,
				map);
		List<T> list = selectByIds(ids);
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("结束执行{}的pageQueryByStatementPostfix方法,结果是{}，size={}。"),
				new Object[] { this.getClass().getName(), list, list.size() });
		return list;
	}

	/**
	 * 根据自定义SqlMap中的条件语句更新数据（注意：不处理缓存）
	 */
	@Override
	public int updateByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues)
			throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的updateByStatementPostfix方法,传递的参数是statementPostfix={},properties={},propertyValues={}。"),
				new Object[] { this.getClass().getName(), statementPostfix,
						properties, propertyValues });
		if (StringUtils.isEmpty(statementPostfix))
			throw new PersistenceException("自定义SqlMap中的条件语句为空！");
		Map<String, Object> map = buildMap(properties, propertyValues);
		int record = 0;
		try {
			record = getSqlSession().update(clazzName + statementPostfix, map);
		} catch (Exception ex) {
			logger.debug(
					FunIMsgFormat.MsgStyle.ERROR_REPORT
							.getFormat("执行{}的updateByStatementPostfix方法出现异常，具体原因是：{}"),
					new Object[] { this.getClass().getName(),
							ex.getStackTrace() });
			throw new PersistenceException("执行数据更新时出错！", ex);
		}
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的updateByStatementPostfix方法。"), this
				.getClass().getName());

		return record;
	}

	/**
	 * 根据自定义SqlMap中的条件语句删除数据（注意：不处理缓存）
	 */
	@Override
	public void deleteByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues)
			throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的deleteByStatementPostfix方法,传递的参数是statementPostfix={},properties={},propertyValues={}。"),
				new Object[] { this.getClass().getName(), statementPostfix,
						properties, propertyValues });
		if (StringUtils.isEmpty(statementPostfix))
			throw new PersistenceException("自定义SqlMap中的条件语句为空！");
		Map<String, Object> map = buildMap(properties, propertyValues);
		try {
			getSqlSession().delete(clazzName + statementPostfix, map);
		} catch (Exception ex) {
			logger.debug(
					FunIMsgFormat.MsgStyle.ERROR_REPORT
							.getFormat("执行{}的deleteByStatementPostfix方法出现异常，具体原因是：{}"),
					new Object[] { this.getClass().getName(),
							ex.getStackTrace() });
			throw new PersistenceException("执行数据删除时出错！", ex);
		}
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的deleteByStatementPostfix方法。"), this
				.getClass().getName());
	}

	/**
	 * 根据自定义SqlMap中的条件语句 插入数据（注意：不处理缓存）
	 */
	@Override
	public void insertByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues)
			throws PersistenceException {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的insertByStatementPostfix方法,传递的参数是statementPostfix={},properties={},propertyValues={}。"),
				new Object[] { this.getClass().getName(), statementPostfix,
						properties, propertyValues });
		if (StringUtils.isEmpty(statementPostfix))
			throw new PersistenceException("自定义SqlMap中的条件语句为空！");
		Map<String, Object> map = buildMap(properties, propertyValues);
		try {
			getSqlSession().insert(clazzName + statementPostfix, map);
		} catch (Exception ex) {
			logger.debug(
					FunIMsgFormat.MsgStyle.ERROR_REPORT
							.getFormat("执行{}的insertByStatementPostfix方法出现异常，具体原因是：{}"),
					new Object[] { this.getClass().getName(),
							ex.getStackTrace() });
			throw new PersistenceException("执行数据插入时出错！", ex);
		}
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的insertByStatementPostfix方法。"), this
				.getClass().getName());
	}

	@Override
	public <E> List<E> listByPage(String statement, Page page) {
		logger.debug(
				FunIMsgFormat.MsgStyle.DEFAULT_LOG
						.getFormat("开始调用{}的findByStatementPostfix方法,传递的参数是statementPostfix={}。"),
				new Object[] { this.getClass().getName(), statement });
		if (StringUtils.isEmpty(statement))
			throw new PersistenceException("自定义SqlMap中的条件语句为空！");
		if (!statement.endsWith("ByPage"))
			throw new PersistenceException("statement定义错误！分页查询必须以ByPage结尾");
		List<E> list = getSqlSession().selectList(clazzName + statement, page);
		logger.debug(FunIMsgFormat.MsgStyle.DEFAULT_LOG
				.getFormat("结束执行{}的listByPage方法。"), this.getClass().getName());
		return list;
	}

	private Map<String, Object> buildMap(String[] propertyNames,
			Object[] propertyValues) throws PersistenceException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!ArrayUtils.isEmpty(propertyNames)
				&& !ArrayUtils.isEmpty(propertyValues)) {
			int prosKeyLen = propertyNames.length;
			int prosValueLen = propertyValues.length;
			if (prosKeyLen != prosValueLen)
				throw new PersistenceException("传递的参数不匹配！");
			for (int i = 0; i < prosKeyLen; i++) {
				map.put(propertyNames[i], propertyValues[i]);
			}
		}
		return map;
	}

	private Map<String, Object> buildMapWithOrder(String[] propertyNames,
			Object[] propertyValues, String orderBy, String order)
			throws PersistenceException {
		Map<String, Object> map = buildMap(propertyNames, propertyValues);
		if (StringUtils.isNotEmpty(orderBy)) {
			map.put("orderBy", orderBy);
			map.put("order", order);
		}
		return map;
	}

	private Map<String, Object> buildMapWithOrderAndPagination(
			String[] propertyNames, Object[] propertyValues, String orderBy,
			String order, int pageSize, int pageNo) throws PersistenceException {
		Map<String, Object> map = buildMap(propertyNames, propertyValues);
		if (StringUtils.isNotEmpty(orderBy)) {
			map.put("orderBy", orderBy);
			map.put("order", order);
		}
		map.put("limit", true);
		map.put("start", (pageNo - 1) * pageSize);// limit 操作
		map.put("end", pageSize);
		return map;
	}

}