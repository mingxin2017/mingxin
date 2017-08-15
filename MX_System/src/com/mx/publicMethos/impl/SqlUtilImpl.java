package com.mx.publicMethos.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mx.publicMethos.ISqlUtil;
import com.mx.ssh.bean.PageBean;

@Repository("sqlUtilDAO")
public class SqlUtilImpl extends HibernateDaoSupport implements ISqlUtil {

	public static final int pageLinesNum = 8;// ÿҳ��ʾ����������

	/*
	 * ִ�в�ѯ��䣬����Ψһֵ�Ľ��eg:"select min(a.employeeid) from Emplyees a;"
	 * eg:"select count(*) from Emplyees a;"
	 */
	public Object queryHqlBySession(String sql) {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		SQLQuery query = session.createSQLQuery(sql);
		Object object = query.uniqueResult();
		session.close();
		return object;
	}


	/*
	 * ִ��hql/sql��ѯ��䣬�����ݷ���bean�У�bean��name��sql��as��name��ͬ���Զ�ע��
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryHqlListBySession(String sql, T bean) {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		SQLQuery query = session.createSQLQuery(sql).addEntity(bean.getClass());
		List<T> result = query.list();
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T> PageBean<T> queryForPage(String DB_table_name,String Primarykey,
			HashMap<String, String> conditionList, Class<T> bean, int pageCurrent) {

		PageBean<T> pb = new PageBean<T>();
		pb.init();
		pb.setCurrentPage(PageBean.countCurrentPage(pageCurrent));
		//System.out.println("sqlUtil�����õ�ǰҳ�룺"+pageCurrent);
		List<T> list = (List<T>) this.queryHqlListBySession(DB_table_name,Primarykey,
				conditionList, bean,pageCurrent);
		int allRow = this.queryHqlRowsNum(DB_table_name,Primarykey, conditionList);
		//System.out.println("sqlUtil��ִ�в��һ�ȡ����������"+allRow);
		pb.setAllRow(allRow);// �����ܼ�¼��
		
		pb.setPageSize(pageLinesNum);// ����ҳ����ʾ����
		//System.out.println("sqlUtil��ִ�в��һ�ȡ����ҳ�룺"+PageBean.countTotalPage(pageLinesNum, allRow));
		pb.setTotalPage(PageBean.countTotalPage(pageLinesNum, allRow));// ����ҳ��������
		pb.setList(list);

		return pb;

	}

	public <T> List<T> queryHqlListBySession(String DB_table_name,String Primarykey,
			HashMap<String, String> conditionList, Class<T> bean, int pageCurrent) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String conditionStr = " where 1=1 and ";
		if (conditionList != null) {

			Iterator<Entry<String, String>> iter = conditionList.entrySet()
					.iterator();
			while (iter.hasNext()) {
				Entry<String, String> entry = iter.next();
				conditionStr = conditionStr + entry.getKey() + entry.getValue()
						+ " and ";
			}
		}
		conditionStr = conditionStr + "1=1";

		String sql="select top " +
				pageLinesNum+" * from " +
				DB_table_name+" "+conditionStr+
				" and " +Primarykey+
				" not in(select top " +
				(pageCurrent-1)*pageLinesNum+" "+Primarykey +
				" from "+DB_table_name+" "+conditionStr+
				" order by "+Primarykey+" asc) " +
				"order by "+Primarykey+" asc";
				
		//String sql = "select * from " + DB_table_name + conditionStr;
		SQLQuery query = session.createSQLQuery(sql).addEntity(bean);
		List<T> result = query.list();
		session.close();
		return result;
	}

	// ���������б�conditiaonList��ѯ���ݿ�DB_table_name�з�������������������
	public int queryHqlRowsNum(String DB_table_name,String Primarykey,
			HashMap<String, String> conditionList) {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		String conditionStr = " where 1=1 and ";
		Iterator<Entry<String, String>> iter = conditionList.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			conditionStr = conditionStr + entry.getKey() + entry.getValue()
					+ " and ";
		}
		conditionStr = conditionStr + "1=1";
		String sql = "select count(*) from " + DB_table_name + conditionStr;
		session.close();

		return (Integer) this.queryHqlBySession(sql);// (int)Math.ceil((double)this.queryHqlPagesNum(sql)/pageLinesNum);
	}

	/*
	 * ִ��hql/sql������䣨delete��update����������Ӱ�����������
	 * eg:"delete from Emplyees a where a.id<10;"
	 */
	public int executeUpdate(String sql) {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		SQLQuery query = session.createSQLQuery(sql);
		int count = query.executeUpdate();
		session.close();
		return count;
	}

}