package com.wot.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.wot.shared.AllCommunityAccount;
import com.wot.shared.Clan;
import com.wot.shared.CommunityAccount;
import com.wot.shared.CommunityClan;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface WotServiceAsync {
	void persistStats(String input, int indexBegin, int indexEnd, List<String> listIdUser, AsyncCallback<List<String>> callback)
			throws IllegalArgumentException;

	void getClans(String input, int offsetClan, AsyncCallback<Clan> callback)
			throws IllegalArgumentException;
	
	void getAllMembersClanAndStats(List<String> listIdUser, AsyncCallback<AllCommunityAccount> callBack);
	
	void getAllStatsFromDossierCache(String fileName, AsyncCallback<AllCommunityAccount> callBack);

	void getAllMembersClanAndStatsHistorised(String textToServer,
			List<String> listIdUser, AsyncCallback<AllCommunityAccount> callBack);
	
	void getAllMembersClan(String textToServer,
			AsyncCallback<CommunityClan> asyncCallback);
	
	void getHistorizedStats(String stat, List<String> listIdUser, AsyncCallback<List<CommunityAccount>> callBack);
	
	void getHistorizedStatsTanks(List<String> listIdUser, AsyncCallback<List<CommunityAccount>> callBack);
}
