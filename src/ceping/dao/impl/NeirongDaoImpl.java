package ceping.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ceping.dao.NeirongDao;
import ceping.model.Neirong;












public class NeirongDaoImpl extends HibernateDaoSupport implements  NeirongDao{


	public void deleteBean(Neirong bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Neirong bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Neirong selectBean(String where) {
		List<Neirong> list = this.getHibernateTemplate().find("from Neirong " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Neirong "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Neirong> selectBeanList(final int start,final int limit,final String where) {
		return (List<Neirong>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Neirong> list = session.createQuery("from Neirong "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Neirong bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
