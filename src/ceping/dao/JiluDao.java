package ceping.dao;

import java.util.List;

import ceping.model.Jilu;





public interface JiluDao  {
	
	
	
	public void insertBean(Jilu bean);
	
	public void deleteBean(Jilu bean);
	
	public void updateBean(Jilu bean);

	public Jilu selectBean(String where);
	
	public List<Jilu> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
