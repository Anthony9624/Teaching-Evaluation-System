package ceping.dao;

import java.util.List;

import ceping.model.Neirong;





public interface NeirongDao  {
	
	
	
	public void insertBean(Neirong bean);
	
	public void deleteBean(Neirong bean);
	
	public void updateBean(Neirong bean);

	public Neirong selectBean(String where);
	
	public List<Neirong> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
