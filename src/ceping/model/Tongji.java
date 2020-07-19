package ceping.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//评估统计
@Entity
@Table(name="t_Tongji")
public class Tongji {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;//评估用户
	
	
	private int ziping;//自评得分
	
	private int huping;//教师互评总分

	private int shuliang1;//互评人数
	
	private double pingjun1;//互评平均分
	
	private int pingjiao;//学生评教总分
	
	private int shuliang2;//评教人数
	
	private double pingjun2;//评教平均分

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getZiping() {
		return ziping;
	}

	public void setZiping(int ziping) {
		this.ziping = ziping;
	}

	public int getHuping() {
		return huping;
	}

	public void setHuping(int huping) {
		this.huping = huping;
	}

	public int getShuliang1() {
		return shuliang1;
	}

	public void setShuliang1(int shuliang1) {
		this.shuliang1 = shuliang1;
	}

	public double getPingjun1() {
		return pingjun1;
	}

	public void setPingjun1(double pingjun1) {
		this.pingjun1 = pingjun1;
	}

	public int getPingjiao() {
		return pingjiao;
	}

	public void setPingjiao(int pingjiao) {
		this.pingjiao = pingjiao;
	}

	public int getShuliang2() {
		return shuliang2;
	}

	public void setShuliang2(int shuliang2) {
		this.shuliang2 = shuliang2;
	}

	public double getPingjun2() {
		return pingjun2;
	}

	public void setPingjun2(double pingjun2) {
		this.pingjun2 = pingjun2;
	}


	
	
	
	
}
