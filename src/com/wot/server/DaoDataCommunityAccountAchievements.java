package com.wot.server;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DaoDataCommunityAccountAchievements implements Serializable {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	
	private static final long serialVersionUID = -5885034969613644473L;
	public int getMedalCarius() {
		return medalCarius;
	}
	public void setMedalCarius(int medalCarius) {
		this.medalCarius = medalCarius;
	}
	public int getMedalHalonen() {
		return medalHalonen;
	}
	public void setMedalHalonen(int medalHalonen) {
		this.medalHalonen = medalHalonen;
	}
	public int getInvader() {
		return invader;
	}
	public void setInvader(int invader) {
		this.invader = invader;
	}
	public int getMedalFadin() {
		return medalFadin;
	}
	public void setMedalFadin(int medalFadin) {
		this.medalFadin = medalFadin;
	}
	public int getMedalEkins() {
		return medalEkins;
	}
	public void setMedalEkins(int medalEkins) {
		this.medalEkins = medalEkins;
	}
	public int getMousebane() {
		return mousebane;
	}
	public void setMousebane(int mousebane) {
		this.mousebane = mousebane;
	}
	public int getMedalKay() {
		return medalKay;
	}
	public void setMedalKay(int medalKay) {
		this.medalKay = medalKay;
	}
	public int getDefender() {
		return defender;
	}
	public void setDefender(int defender) {
		this.defender = defender;
	}
	public int getMedalLeClerc() {
		return medalLeClerc;
	}
	public void setMedalLeClerc(int medalLeClerc) {
		this.medalLeClerc = medalLeClerc;
	}
	public int getSupporter() {
		return supporter;
	}
	public void setSupporter(int supporter) {
		this.supporter = supporter;
	}
	public int getMedalAbrams() {
		return medalAbrams;
	}
	public void setMedalAbrams(int medalAbrams) {
		this.medalAbrams = medalAbrams;
	}
	public int getMedalPoppel() {
		return medalPoppel;
	}
	public void setMedalPoppel(int medalPoppel) {
		this.medalPoppel = medalPoppel;
	}
	public int getMedalOrlik() {
		return medalOrlik;
	}
	public void setMedalOrlik(int medalOrlik) {
		this.medalOrlik = medalOrlik;
	}
	public int getSniper() {
		return sniper;
	}
	public void setSniper(int sniper) {
		this.sniper = sniper;
	}
	public int getWarrior() {
		return warrior;
	}
	public void setWarrior(int warrior) {
		this.warrior = warrior;
	}
	public int getTitleSniper() {
		return titleSniper;
	}
	public void setTitleSniper(int titleSniper) {
		this.titleSniper = titleSniper;
	}
	public int getMedalWittmann() {
		return medalWittmann;
	}
	public void setMedalWittmann(int medalWittmann) {
		this.medalWittmann = medalWittmann;
	}
	public int getMedalBurda() {
		return medalBurda;
	}
	public void setMedalBurda(int medalBurda) {
		this.medalBurda = medalBurda;
	}
	public int getScout() {
		return scout;
	}
	public void setScout(int scout) {
		this.scout = scout;
	}
	public int getBeasthunter() {
		return beasthunter;
	}
	public void setBeasthunter(int beasthunter) {
		this.beasthunter = beasthunter;
	}
	public int getRaider() {
		return raider;
	}
	public void setRaider(int raider) {
		this.raider = raider;
	}
	public int getMedalOskin() {
		return medalOskin;
	}
	public void setMedalOskin(int medalOskin) {
		this.medalOskin = medalOskin;
	}
	public int getMedalBillotte() {
		return medalBillotte;
	}
	public void setMedalBillotte(int medalBillotte) {
		this.medalBillotte = medalBillotte;
	}
	public int getMedalLavrinenko() {
		return medalLavrinenko;
	}
	public void setMedalLavrinenko(int medalLavrinenko) {
		this.medalLavrinenko = medalLavrinenko;
	}
	public int getMedalKolobanov() {
		return medalKolobanov;
	}
	public void setMedalKolobanov(int medalKolobanov) {
		this.medalKolobanov = medalKolobanov;
	}
	public int getLumberjack() {
		return lumberjack;
	}
	public void setLumberjack(int lumberjack) {
		this.lumberjack = lumberjack;
	}
	public int getTankExpert() {
		return tankExpert;
	}
	public void setTankExpert(int tankExpert) {
		this.tankExpert = tankExpert;
	}
	public int getDiehard() {
		return diehard;
	}
	public void setDiehard(int diehard) {
		this.diehard = diehard;
	}
	public int getMedalKnispel() {
		return medalKnispel;
	}
	public void setMedalKnispel(int medalKnispel) {
		this.medalKnispel = medalKnispel;
	}
	
	@Persistent
	private int medalCarius;
	
	@Persistent
	private int medalHalonen;
	
	@Persistent
	private int invader;
	
	@Persistent
	private int medalFadin;
	
	@Persistent
	private int medalEkins;
	
	@Persistent
	private int mousebane;
	
	@Persistent
    private int medalKay;
	
	@Persistent
    private int defender;
	
	@Persistent
    private int medalLeClerc;
	
	@Persistent
    private int supporter;
	
	@Persistent
    private int medalAbrams;
	
	@Persistent
    private int medalPoppel;
	
	@Persistent
    private int medalOrlik;
	
	@Persistent
    private int sniper;
	
	@Persistent
    private int warrior;
	
	@Persistent
    private int titleSniper;
	
	@Persistent
    private int medalWittmann;
	
	@Persistent
    private int medalBurda;
	
	@Persistent
    private int scout;
	
	@Persistent
    private int beasthunter;
	
	@Persistent
    private int raider;
	
	@Persistent
    private int medalOskin;
	
	@Persistent
    private int medalBillotte;
	
	@Persistent
    private int medalLavrinenko;
	
	@Persistent
    private int medalKolobanov;
	
	@Persistent
    private int lumberjack;
	
	@Persistent
    private int tankExpert;
	
	@Persistent
    private int diehard;
	
	@Persistent
    private int medalKnispel;
}