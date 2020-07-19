package ceping.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ceping.dao.TongjiDao;
import ceping.model.Tongji;












public class TongjiDaoImpl extends HibernateDaoSupport implements  TongjiDao{


	public void deleteBean(Tongji bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Tongji bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Tongji selectBean(String where) {
		List<Tongji> list = this.getHibernateTemplate().find("from Tongji " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Tongji "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Tongji> selectBeanList(final int start,final int limit,final String where) {
		return (List<Tongji>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Tongji> list = session.createQuery("from Tongji "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Tongji bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
