package in.sp.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Underwriter {
	public int getId() {
		return id;
	}

	public Underwriter(int id, String name, String password, LocalDateTime doj, LocalDateTime dob) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.doj = doj;
		this.dob = dob;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int id;
	private String name;
	private String password;
	LocalDateTime doj;
	
	DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDoj() {
		return dob.format(df); 
	}

	public void setDoj(LocalDateTime doj) {
		this.doj = doj;
	}

	public String getDob() {
		return dob.format(df);
	}

	public Underwriter(String name, String password, LocalDateTime doj, LocalDateTime dob) {
		super();
		this.name = name;
		this.password = password;
		this.doj = doj;
		this.dob = dob;
	}

	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}

	LocalDateTime dob;

}
