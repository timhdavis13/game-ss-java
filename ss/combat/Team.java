package com.thd.ss.combat;
/**
 * Created 06/13/2018
 * 
 * @author timhdavis.
 * 
 */

public class Team 
{
	// Private members:
	private String teamName;
	
	// Primary teams:
	
	public static final Team PlayerTeam = new Team("Player Team");
	public static final Team EnemyTeam = new Team("Enemy Team");
	
	// Constructor:
	public Team(String teamName)
	{
		this.setTeamName(teamName);
	}

	// Getters and setters:
	
	/**
	 * @return the teamName
	 */
	public String getTeamName() { return teamName; }

	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) 
	{
		this.teamName = teamName;
	}
	
	// Static methods:
	
	public static boolean isOnSameTeam(Team team1, Team team2)
	{
		return (team1.getTeamName().equals(team2.getTeamName()));
	}
	
	
	
}
