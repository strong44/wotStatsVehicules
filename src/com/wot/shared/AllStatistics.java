package com.wot.shared;

import java.io.Serializable;

public class AllStatistics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2455613465012253198L;
	/*
	"all": {
        "spotted": 11211,
        "hits": 47611,
        "battle_avg_xp": 450,
        "draws": 119,
        "wins": 4628,
        "losses": 4127,
        "capture_points": 10525,
        "battles": 8874,
        "damage_dealt": 5461529,
        "hits_percents": 60,
        "damage_received": 4402832,
        "shots": 79378,
        "xp": 3992558,
        "frags": 8188,
        "survived_battles": 2925,
        "dropped_capture_points": 8229
    },
	 */
	private int spotted ; 				//Enemies spotted
	private int hits ;					//Hits
	private int battle_avg_xp ;			//Average experience per battle
	private int draws ;					//égality
	private int wins ;					//battles wins
	private int losses ;				//battles Defeats
	private int capture_points ;		//Base capture points
	private int battles ;				//Battles fought
	private int damage_dealt ;			//Damage caused
	private int hits_percents ;			//Hit ratio
	private int damage_received ;		//Damage received
	private int shots ;					//Shots fired
	
	private int xp ;					//Total experience
	private int frags ;					//Vehicles destroyed
	private int survived_battles ;		//battle survived
	private int dropped_capture_points ;	//Base defense points
	
	//info calculée
	private Double 	ratioCtfPoints ;
	private Double 	ratioDamagePoints ;
	private Double 	ratioDroppedCtfPoints ;
	private Double 	ratioDestroyedPoints ;
	private Double 	ratioDetectedPoints ;
	private Double 	averageLevelTankCalc;
		
	//calculate field
	private double wn8;
	private Double perf;
	
	public int getSpotted() {
		return spotted;
	}

	public int getHits() {
		return hits;
	}

	public int getBattle_avg_xp() {
		return battle_avg_xp;
	}

	public int getDraws() {
		return draws;
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}

	public int getCapture_points() {
		return capture_points;
	}

	public int getBattles() {
		return battles;
	}

	public int getDamage_dealt() {
		return damage_dealt;
	}

	public int getHits_percents() {
		return hits_percents;
	}

	public int getDamage_received() {
		return damage_received;
	}

	public int getShots() {
		return shots;
	}

	public int getXp() {
		return xp;
	}

	public int getFrags() {
		return frags;
	}

	public int getSurvived_battles() {
		return survived_battles;
	}

	public int getDropped_capture_points() {
		return dropped_capture_points;
	}

	public Double getRatioCtfPoints() {
		return ratioCtfPoints;
	}

	public Double getRatioDamagePoints() {
		return ratioDamagePoints;
	}

	public Double getRatioDroppedCtfPoints() {
		return ratioDroppedCtfPoints;
	}

	public Double getRatioDestroyedPoints() {
		return ratioDestroyedPoints;
	}

	public Double getRatioDetectedPoints() {
		return ratioDetectedPoints;
	}

	public Double getAverageLevelTankCalc() {
		return averageLevelTankCalc;
	}

	public double getWn8() {
		return wn8;
	}

	public void setSpotted(int spotted) {
		this.spotted = spotted;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public void setBattle_avg_xp(int battle_avg_xp) {
		this.battle_avg_xp = battle_avg_xp;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public void setCapture_points(int capture_points) {
		this.capture_points = capture_points;
	}

	public void setBattles(int battles) {
		this.battles = battles;
	}

	public void setDamage_dealt(int damage_dealt) {
		this.damage_dealt = damage_dealt;
	}

	public void setHits_percents(int hits_percents) {
		this.hits_percents = hits_percents;
	}

	public void setDamage_received(int damage_received) {
		this.damage_received = damage_received;
	}

	public void setShots(int shots) {
		this.shots = shots;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public void setFrags(int frags) {
		this.frags = frags;
	}

	public void setSurvived_battles(int survived_battles) {
		this.survived_battles = survived_battles;
	}

	public void setDropped_capture_points(int dropped_capture_points) {
		this.dropped_capture_points = dropped_capture_points;
	}

	public void setRatioCtfPoints(Double ratioCtfPoints) {
		this.ratioCtfPoints = ratioCtfPoints;
	}

	public void setRatioDamagePoints(Double ratioDamagePoints) {
		this.ratioDamagePoints = ratioDamagePoints;
	}

	public void setRatioDroppedCtfPoints(Double ratioDroppedCtfPoints) {
		this.ratioDroppedCtfPoints = ratioDroppedCtfPoints;
	}

	public void setRatioDestroyedPoints(Double ratioDestroyedPoints) {
		this.ratioDestroyedPoints = ratioDestroyedPoints;
	}

	public void setRatioDetectedPoints(Double ratioDetectedPoints) {
		this.ratioDetectedPoints = ratioDetectedPoints;
	}

	public void setAverageLevelTankCalc(Double averageLevelTankCalc) {
		this.averageLevelTankCalc = averageLevelTankCalc;
	}

	public void setWn8(double wn8) {
		this.wn8 = wn8;
	}

	public void setBattle_avg_performanceCalc(Double perf) {
		this.perf = perf;
		
	}

	public Double getBattle_avg_performanceCalc() {
		return this.perf;
		
	}
	
	
}
