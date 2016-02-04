package com.jordanec.sbrestapistormpath.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPlayer", scope = Player.class)
@Entity
@Table(name = "Player", catalog = "FutbolDB_V3", uniqueConstraints = @UniqueConstraint(columnNames = "dni") )
public class Player implements java.io.Serializable {
	private static final long serialVersionUID = 5861496569277658592L;
	private Integer idPlayer;
	private Team team;
	private Country country;
	private Sponsor sponsor;
	private int dni;
	private String name;
	private String lastName;
	private String secondLastName;
	private double salary;
	private int yearsOfRemainingContract;
	private Date birthDate;
	private String position;
	private Integer age;

	public Player() {}

	public Player(String name, String lastName, String secondLastName) {
		this.name = name;
		this.lastName = lastName;
		this.secondLastName = secondLastName;
	}
	
	public Player(Integer idPlayer, Team team, Country country, Sponsor sponsor, int dni, String name,
			String lastName, String secondLastName, double salary, int yearsOfRemainingContract, Date birthDate,
			String position, Integer age) {
		this.idPlayer = idPlayer;
		this.team = team;
		this.country = country;
		this.sponsor = sponsor;
		this.dni = dni;
		this.name = name;
		this.lastName = lastName;
		this.secondLastName = secondLastName;
		this.salary = salary;
		this.yearsOfRemainingContract = yearsOfRemainingContract;
		this.birthDate = birthDate;
		this.position = position;
		this.age = age;
	}

	public Player(Country country, int dni, String name, String lastName, String secondLastName, double salary,
			int yearsOfRemainingContract, Date birthDate, String position) {
		this.country = country;
		this.dni = dni;
		this.name = name;
		this.lastName = lastName;
		this.secondLastName = secondLastName;
		this.salary = salary;
		this.yearsOfRemainingContract = yearsOfRemainingContract;
		this.birthDate = birthDate;
		this.position = position;
	}

	public Player(Team team, Country country, Sponsor sponsor, int dni, String name, String lastName,
			String secondLastName, double salary, int yearsOfRemainingContract, Date birthDate, String position,
			Integer age) {
		this.team = team;
		this.country = country;
		this.sponsor = sponsor;
		this.dni = dni;
		this.name = name;
		this.lastName = lastName;
		this.secondLastName = secondLastName;
		this.salary = salary;
		this.yearsOfRemainingContract = yearsOfRemainingContract;
		this.birthDate = birthDate;
		this.position = position;
		this.age = age;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idPlayer", unique = true, nullable = false)
	public Integer getIdPlayer() {
		return this.idPlayer;
	}

	public void setIdPlayer(Integer idPlayer) {
		this.idPlayer = idPlayer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTeam")
	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCountry", nullable = false)
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSponsor")
	public Sponsor getSponsor() {
		return this.sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	@Column(name = "dni", unique = true, nullable = false)
	public int getDni() {
		return this.dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "lastName", nullable = false, length = 45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "secondLastName", nullable = false, length = 45)
	public String getSecondLastName() {
		return this.secondLastName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	@Column(name = "salary", nullable = false, precision = 22, scale = 0)
	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Column(name = "yearsOfRemainingContract", nullable = false)
	public int getYearsOfRemainingContract() {
		return this.yearsOfRemainingContract;
	}

	public void setYearsOfRemainingContract(int yearsOfRemainingContract) {
		this.yearsOfRemainingContract = yearsOfRemainingContract;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthDate", nullable = false, length = 10)
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "position", nullable = false, length = 45)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Player [idPlayer=" + idPlayer + ", team=" + team + ", country=" + country + ", sponsor=" + sponsor
				+ ", dni=" + dni + ", name=" + name + ", lastName=" + lastName + ", secondLastName=" + secondLastName
				+ ", salary=" + salary + ", yearsOfRemainingContract=" + yearsOfRemainingContract + ", birthDate="
				+ birthDate + ", position=" + position + ", age=" + age + "]";
	}
	
}
