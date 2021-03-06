package pl.schoolmanager.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "school")
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String type;
	
	@OneToMany (mappedBy = "school", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Division> division = new ArrayList<>();
	
	@OneToMany (mappedBy = "school", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Subject> subject = new ArrayList<>();
	
	@ManyToMany (mappedBy = "school")
	private List<Student> student;
	
	@OneToMany (mappedBy = "school")
	private List<Teacher>teacher;

	@OneToOne (mappedBy = "school")
	private TimeTable timeTable;

	public School() {
		super();
	}

	public School(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Division> getDivision() {
		return division;
	}

	public void setDivision(List<Division> division) {
		this.division = division;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public List<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(List<Teacher> teacher) {
		this.teacher = teacher;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	public String getNameForForm() {
		return this.name + " (" +this.type +")";
	}

	public TimeTable getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(TimeTable timeTable) {
		this.timeTable = timeTable;
	}
	
	public List<String> getTimeTableList() {
		TimeTable tt = this.timeTable;
		if (tt == null) {
			tt = new TimeTable();
		}
		List<String> timeTableList = new ArrayList<>();
		LocalTime first = tt.getStart();
		LocalTime start = first;
		LocalTime finish = start.plusMinutes(45);
		timeTableList.add(start + " - " + finish);
		
		for (int i = 0; i < 7; i++) {
			start = finish.plusMinutes(tt.getList().get(i));
			finish = start.plusMinutes(45);
			timeTableList.add(start + " - " + finish);
		}
		return timeTableList;
	}
	
}