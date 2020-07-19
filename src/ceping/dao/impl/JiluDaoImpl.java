package ceping.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ceping.dao.JiluDao;
import ceping.model.Jilu;












public class JiluDaoImpl extends HibernateDaoSupport implements  JiluDao{


	public void deleteBean(Jilu bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Jilu bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Jilu selectBean(String where) {
		List<Jilu> list = this.getHibernateTemplate().find("from Jilu " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Jilu "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Jilu> selectBeanList(final int start,final int limit,final String where) {
		return (List<Jilu>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Jilu> list = session.createQuery("from Jilu "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Jilu bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
