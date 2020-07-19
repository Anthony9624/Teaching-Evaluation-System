package ceping.dao;

import java.util.List;

import ceping.model.Tongji;





public interface TongjiDao  {
	
	
	
	public void insertBean(Tongji bean);
	
	public void deleteBean(Tongji bean);
	
	public void updateBean(Tongji bean);

	public Tongji selectBean(String where);
	
	public List<Tongji> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
