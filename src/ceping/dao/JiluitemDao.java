package ceping.dao;

import java.util.List;

import ceping.model.Jiluitem;





public interface JiluitemDao  {
	
	
	
	public void insertBean(Jiluitem bean);
	
	public void deleteBean(Jiluitem bean);
	
	public void updateBean(Jiluitem bean);

	public Jiluitem selectBean(String where);
	
	public List<Jiluitem> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
