package com.jordanec.sbrestapistormpath.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idSponsor", scope=Sponsor.class)
@Entity
@Table(name = "Sponsor", catalog = "FutbolDB_V3")
public class Sponsor implements java.io.Serializable {
	private static final long serialVersionUID = -6228725019222475841L;
	private Integer idSponsor;
	private String name;
	private Set<Team> teams = new HashSet<Team>(0);
	private Set<Player> players = new HashSet<Player>(0);

	public Sponsor() {}

	public Sponsor(String name) {
		this.name = name;
	}

	public Sponsor(Integer idSponsor, String name, Set<Team> teams, Set<Player> players) {
		super();
		this.idSponsor = idSponsor;
		this.name = name;
		this.teams = teams;
		this.players = players;
	}

	public Sponsor(String name, Set<Team> teams, Set<Player> players) {
		this.name = name;
		this.teams = teams;
		this.players = players;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idSponsor", unique = true, nullable = false)
	public Integer getIdSponsor() {
		return this.idSponsor;
	}

	public void setIdSponsor(Integer idSponsor) {
		this.idSponsor = idSponsor;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "sponsors")
	public Set<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sponsor")
	public Set<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Sponsor [idSponsor=" + idSponsor + ", name=" + name +/* ", teams=" + teams + ", players=" + players +*/ "]";
	}
	
}