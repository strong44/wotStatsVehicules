package com.wot.server;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DaoDataCommunityAccountRatings2 implements Serializable {

	private static final long serialVersionUID = -2580719093758162023L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	@Persistent
	  public DaoDataCommunityAccountRatingsElement2 spotted = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 dropped_ctf_points = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 battle_avg_xp = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 xp = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 battles = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 damage_dealt = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 ctf_points = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 integrated_rating = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 battle_avg_performance = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 battle_avg_performanceCalc = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 frags = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 battle_wins = new DaoDataCommunityAccountRatingsElement2();
	
	  //info calculée
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 ratioCtfPoints = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 ratioDamagePoints = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 ratioDroppedCtfPoints = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 ratioDestroyedPoints = new DaoDataCommunityAccountRatingsElement2();
	
	@Persistent
	public DaoDataCommunityAccountRatingsElement2 ratioDetectedPoints = new DaoDataCommunityAccountRatingsElement2();

	@Persistent
	private Double averageLevelCal;

	@Persistent
	private Double wn8;
	
	

	public Double getAverageLevel() {
		return averageLevelCal;
	}
	public Double getRatioDetectedPoints() {
		return ratioDetectedPoints.getValue();
	}
	public Double getRatioDestroyedPoints() {
		return ratioDestroyedPoints.getValue();
	}
	public Double getWn8() {
		return wn8;
	}
	public void setRatioDestroyedPoints(Double ratioDestroyedPoints) {
		this.ratioDestroyedPoints.setValue( ratioDestroyedPoints);
	}
	public Double getRatioDroppedCtfPoints() {
		return ratioDroppedCtfPoints.getValue();
	}
	public Double getRatioDamagePoints() {
		return ratioDamagePoints.getValue();
	}
	public Double getRatioCtfPoints() {
		return ratioCtfPoints.getValue();
	}
	public int getSpotted() {
		return spotted.getValue().intValue();
	}
	public void setSpotted(Double spotted) {
		this.spotted.setValue( spotted);
	}
	public int getDropped_ctf_points() {
		return dropped_ctf_points.getValue().intValue();
	}
	public void setDropped_ctf_points(Double  dropped_ctf_points) {
		this.dropped_ctf_points.setValue( dropped_ctf_points);
	}
	public int getBattle_avg_xp() {
		return battle_avg_xp.getValue().intValue();
	}
	public void setBattle_avg_xp(Double battle_avg_xp) {
		this.battle_avg_xp.setValue(battle_avg_xp);
	}
	public int getBattles() {
		return battles.getValue().intValue();
	}
	public void setBattles(Double battles) {
		this.battles.setValue( battles);
	}
	public int getDamage_dealt() {
		return damage_dealt.getValue().intValue();
	}
	public void setDamage_dealt(Double damage_dealt) {
		this.damage_dealt.setValue( damage_dealt);
	}
	
	//win rate
	public int getBattle_avg_performance() {
		return battle_avg_performance.getValue().intValue();
	}
	//ratio wins/battle
	public void setBattle_avg_performance(Double battle_avg_performance) {
		this.battle_avg_performance.setValue( battle_avg_performance);
	}
	public int getIntegrated_rating() {
		return integrated_rating.getValue().intValue();
	}
	public void setIntegrated_rating(Double integrated_rating) {
		this.integrated_rating.setValue(integrated_rating);
	}
	public int getFrags() {
		return frags.getValue().intValue();
	}
	public void setFrags(Double frags) {
		this.frags.setValue(frags);
	}
	public int getXp() {
		return xp.getValue().intValue();
	}
	public void setXp(Double xp) {
		this.xp.setValue(xp);
	}
	public int getCtf_points() {
		return ctf_points.getValue().intValue();
	}
	public void setCtf_points(Double ctf_points) {
		this.ctf_points.setValue(ctf_points);
	}
	public int getBattle_wins() {
		return battle_wins.getValue().intValue();
	}
	public void setBattle_wins(Double battle_wins) {
		this.battle_wins.setValue(battle_wins);
	}
	  
	public Double getBattle_avg_performanceCalc() {
		return battle_avg_performanceCalc.getValue();
	}
	public void setBattle_avg_performanceCalc(Double battle_avg_performanceCalc) {
		this.battle_avg_performanceCalc.setValue(battle_avg_performanceCalc);
	}
	public void setRatioCtfPoints(Double ctfPointsCal) {
		// TODO Auto-generated method stub
		this.ratioCtfPoints.setValue(ctfPointsCal );
	}
	public void setRatioDamagePoints(Double ratioFragsPoints) {
		// TODO Auto-generated method stub
		this.ratioDamagePoints.setValue(ratioFragsPoints);
	}
	public void setRatioDroppedCtfPoints(Double ratioDroppedCtfPoints) {
		// TODO Auto-generated method stub
		this.ratioDroppedCtfPoints.setValue(ratioDroppedCtfPoints);
	}
	public void setRatioDetectedPoints(Double ratioDetectedPoints) {
		this.ratioDetectedPoints.setValue(ratioDetectedPoints);
		
	}
	public void setAverageLevel(Double averageLevel) {
		// TODO Auto-generated method stub
		this.averageLevelCal = averageLevel;
	}
	public void setWn8(Double wn8) {
		this.wn8 = wn8;
		
	}
	
	
}
