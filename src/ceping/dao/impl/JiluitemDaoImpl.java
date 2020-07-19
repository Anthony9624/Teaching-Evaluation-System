package ceping.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ceping.dao.JiluitemDao;
import ceping.model.Jiluitem;












public class JiluitemDaoImpl extends HibernateDaoSupport implements  JiluitemDao{


	public void deleteBean(Jiluitem bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Jiluitem bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Jiluitem selectBean(String where) {
		List<Jiluitem> list = this.getHibernateTemplate().find("from Jiluitem " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Jiluitem "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Jiluitem> selectBeanList(final int start,final int limit,final String where) {
		return (List<Jiluitem>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Jiluitem> list = session.createQuery("from Jiluitem "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Jiluitem bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
