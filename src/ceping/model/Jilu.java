package ceping.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//评估记录
@Entity
@Table(name="t_Jilu")
public class Jilu {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="fromuserid")
	private User fromuser;//评估用户
	
	
	@ManyToOne
	@JoinColumn(name="touserid")
	private User touser;//被评估用户
	
	private int denfen;//得分
	
	
	private String shijian;//评估时间
	
	private String leixing;//教师自评 教师互评 学生评教

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getFromuser() {
		return fromuser;
	}

	public void setFromuser(User fromuser) {
		this.fromuser = fromuser;
	}

	public User getTouser() {
		return touser;
	}

	public void setTouser(User touser) {
		this.touser = touser;
	}

	public int getDenfen() {
		return denfen;
	}

	public void setDenfen(int denfen) {
		this.denfen = denfen;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	

	


	
	
	
	
}
