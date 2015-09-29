package com.wot.shared;

import java.io.Serializable;
import java.util.List;

public class DataCommunityClan implements Serializable {
	

	/**
	 * {"status":"ok","count":1,
"data":{"500006074":{"members_count":56,"description":"_* Age minimum : 16ans et +_\n_* Vocal, mais non obligatoire \n_* PRESENCE REGULIERES sur le canal,
aux compagnies et sur le forum ( 1 à 2 fois par semaines )\n_* pas de language SMS  \n_* Période d'essai de 3 semaines \n\n\nToutes les candidatures se font via un formulaire de candidature sur notre forum \"ici\":http:\/\/nova.snail.xooit.fr\/.\nSi vous désirez plus d’informations sur les clans, prenez contact avec hentz44[NVS], il se fera un plaisir de vous répondre !\n.\nAu plaisir de vous revoir sur le champ de bataille !","description_html":"<p>_* Age minimum : 16ans et +_<\/p><p>_* Vocal, mais non obligatoire <\/p><p>_* PRESENCE REGULIERES sur le canal,aux compagnies et sur le forum ( 1 à 2 fois par semaines )<\/p><p>_* pas de language SMS  <\/p><p>_* Période d'essai de 3 semaines <\/p><p><\/p><p><\/p><p>Toutes les candidatures se font via un formulaire de candidature sur notre forum \"ici\":http:\/\/nova.snail.xooit.fr\/.<\/p><p>Si vous désirez plus d’informations sur les clans, prenez contact avec hentz44[NVS], 
il se fera un plaisir de vous répondre !<\/p><p>.<\/p><p>Au plaisir de vous revoir sur le champ de bataille !<\/p>","created_at":1328978449,
"request_availability":false,"updated_at":1381406836,"private":null,"abbreviation":"NVS",
"emblems":{"large":"http:\/\/clans.worldoftanks.eu\/media\/clans\/emblems\/clans_5\/500006074\/emblem_64x64.png",
"small":"http:\/\/clans.worldoftanks.eu\/media\/clans\/emblems\/clans_5\/500006074\/emblem_24x24.png",
"bw_tank":"http:\/\/clans.worldoftanks.eu\/media\/clans\/emblems\/clans_5\/500006074\/emblem_64x64_tank.png",
"medium":"http:\/\/clans.worldoftanks.eu\/media\/clans\/emblems\/clans_5\/500006074\/emblem_32x32.png"},
"clan_id":500006074,
"members":{"503224066":{"account_id":503224066,"created_at":1362947902,"updated_at":0,"account_name":"voleurbleu","role":"private",
"role_i18n":{"ru":"??????","fr":"Soldat","en":"Soldier","th":"????","vi":"lính","de":"Schütze","tr":"Asker","it":"??????","hu":"??????",
"zh-cn":"??","pl":"zolnierz","ms":"??????","cs":"Voják","es":"Soldado"}},"503777028":{"account_id":503777028,"created_at":1369331057,"updated_at":0,
"account_name":"mwa2","role":"private","role_i18n":{"ru":"??????","fr":"Soldat","en":"Soldier","th":"????","vi":"lính","de":"Schütze","tr":"Asker","it":"??????","hu":"??????",
"zh-cn":"??","pl":"zolnierz","ms":"??????","cs":"Voják","es":"Soldado"}},"506772357":{"account_id":506772357,"created_at":1366938640,"updated_at":0,"account_name":"cinespace",
"role":"private","role_i18n":{"ru":"??????","fr":"Soldat","en":"Soldier","th":"????","vi":"lính","de":"Schütze","tr":"Asker","it":"??????","hu":"??????","zh-cn":"??","pl":
"zolnierz","ms":"??????","cs":"Voják","es":"Soldado"}},"500423304":{"account_id":500423304,"created_at":1330525514,"updated_at":0,"account_name":"JAPPY","role":"private",
"role_i18n":{"ru":"??????","fr":"Soldat","en":"Soldier","th":"????","vi":"lính","de":"Schütze","tr":"Asker","it":"??????","hu":"??????","zh-cn":"??","pl":"zolnierz",
"ms":"??????","cs":"Voják","es":"Soldado"}},"505539337":{"account_id":505539337,"created_at":1362770042,"updated_at":0,"account_name":"u71","role":"private","role_i18n":
{"ru":"??????","fr":"Soldat","en":"Soldier","th":"????","vi":"lính","de":"Schütze","tr":"Asker","it":"??????","hu":"??????","zh-cn":"??","pl":"zolnierz","ms":"??????",
"cs":"Voják","es":"Soldado"}},"502360074":{"account_id":502360074,"created_at":1354486401,"updated_at":0,"account_name":"Lt_Staller","role":"private","role_i18n":
{"ru":"??????","fr":"Soldat","en":"Soldier","th":"????","vi":"lính","de":"Schütze","tr":"Asker","it":"??????","hu":"??????","zh-cn":"??","pl":"zolnierz","ms":"??????",
"cs":"Voják","es":"Soldado"}},"504390799":{"account_id":504390799,"created_at":1351765257,"updated_at":0,"account_name":"Mercenaries14","role":"private","role_i18n":
{"ru":"??????","fr":"Soldat","en":"Soldier","th":"????","vi":"lính","de":"Schütze","tr":"Asker","it":"??????","hu":"??????","zh-cn":"??","pl":"zolnierz","ms":"??????",
"cs":"Voják","es":"Soldado"}},
"504713110":{"account_id":504713110,"created_at":1350678840,"updated_at":0,"account_name":"stadetoulousain","role":"commander","role_i18n":
{"ru":"?????? ????????","fr":"?????? ????????","en":"?????? ????????","th":"?????? ????????","vi":"?????? ????????","de":"?????? ????????","tr":"?????? ????????",
"it":"?????? ????????","hu":"?????? ????????","zh-cn":"?????? ????????","pl":"?????? ????????","ms":"?????? ????????","cs":"?????? ????????","es":"?????? 
	 */
	private static final long serialVersionUID = -2019183340585632262L;
	List< DataCommunityClanMembers> members; //m�dailles du joueur

	public List< DataCommunityClanMembers> getMembers() {
		return members;
	}

	public void setMembers(List< DataCommunityClanMembers> members) {
		this.members = members;
	}

	
}
