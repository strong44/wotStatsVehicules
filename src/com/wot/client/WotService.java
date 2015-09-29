package com.wot.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.wot.shared.AllCommunityAccount;
import com.wot.shared.Clan;
import com.wot.shared.CommunityAccount;
import com.wot.shared.CommunityClan;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface WotService extends RemoteService {
	List<String> persistStats(String clan, int indexBegin, int indexEnd, List<String> listIdUser) throws IllegalArgumentException;
	Clan getClans(String clan, int offset) throws IllegalArgumentException;
	AllCommunityAccount getAllMembersClanAndStats(List<String> listIdUser) throws IllegalArgumentException;
	AllCommunityAccount getAllStatsFromDossierCache(String fileName) throws IllegalArgumentException;
	
	AllCommunityAccount getAllMembersClanAndStatsHistorised(String idClan, List<String> listIdUser) throws IllegalArgumentException;
	CommunityClan getAllMembersClan(String idClan) throws IllegalArgumentException;
	List<CommunityAccount> getHistorizedStats(String stat, List<String> listIdUser) throws Exception;
	List<CommunityAccount> getHistorizedStatsTanks(List<String> listIdUser);
}
