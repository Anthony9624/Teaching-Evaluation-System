package ceping.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//评估记录
@Entity
@Table(name="t_Jiluitem")
public class Jiluitem {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="jiluid")
	private Jilu jilu;
	
	@ManyToOne
	@JoinColumn(name="neirongid")
	private Neirong neirong;
	
	private String dengji;//等级
	
	private int defen;//得分
	
	private String shijian;//评估时间
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Jilu getJilu() {
		return jilu;
	}

	public void setJilu(Jilu jilu) {
		this.jilu = jilu;
	}

	public Neirong getNeirong() {
		return neirong;
	}

	public void setNeirong(Neirong neirong) {
		this.neirong = neirong;
	}

	public String getDengji() {
		return dengji;
	}

	public void setDengji(String dengji) {
		this.dengji = dengji;
	}

	public int getDefen() {
		return defen;
	}

	public void setDefen(int defen) {
		this.defen = defen;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	

	


	
	
	
	
}
