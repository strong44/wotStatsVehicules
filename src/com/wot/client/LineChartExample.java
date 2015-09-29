package com.wot.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.SimplePanel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.LineChart;
import com.googlecode.gwt.charts.client.corechart.LineChartOptions;
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.VAxis;
import com.wot.shared.CommunityAccount;
import com.wot.shared.DataPlayerInfos;

public class LineChartExample extends SimplePanel {
	private LineChart chart;
	List<CommunityAccount> listAccount ;
	String stat; 
	
	public LineChartExample(String stat, List<CommunityAccount> listAccount) {
		this.listAccount = listAccount;
		this.stat = stat;
		//super(Unit.PX);
		initialize();
	}

	private void initialize() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				// Create and attach the chart
				chart = new LineChart();
				add(chart);
				draw();
			}
		});
	}

	private void draw() {
		//String[] countries = new String[] { "Austria", "Bulgaria", "Denmark", "Greece" };
		
		
		//new stats WOT
		List<String> listStats = new ArrayList<String>();
		for (CommunityAccount commAcc: listAccount) {
			listStats.add(commAcc.getName());
			
		}
		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "dates");
		
//		for (int i = 0; i < countries.length; i++) {
//			dataTable.addColumn(ColumnType.NUMBER, countries[i]);
//		}
		//
		for (String nameStat: listStats) {
			dataTable.addColumn(ColumnType.NUMBER, nameStat);
		}
		
		//dataTable.addRows(years.length);
		//
		CommunityAccount commAccount = listAccount.get(0);
		//String  tabDates[] = new String [100] ;
		//init tabDates
		int i = 0;
//		for(String date :commAccount.listDates) {
//			tabDates[i] = date;
//			i++;
//		}
		
		
//		//wn8 directe 
//		Double [] tabWn8= new Double [100];
//		i = 0;
//		for(DataPlayerInfos data :commAccount.listDataPlayerInfos) {
//			tabWn8[i] = data.getStatistics().getAllStatistics().getWn8();
//			i++;
//		}
		//
		//wn8 directe 
//		Double [] tabWr= new Double [100];
//		i = 0;
//		for(DataPlayerInfos data :commAccount.listDataPlayerInfos) {
//			tabWr[i] = data.getStatistics().getAllStatistics().getBattle_avg_performanceCalc()*100;
//			i++;
//		}
		
		
//		Integer [] tabBattlesWins = new Integer [100];
//		i = 0;
//		for(Integer wn :commAccount.listBattlesWins) {
//			tabBattlesWins[i] = wn;
//			i++;
//		}
		
//		Integer [] tabBattles = new Integer [100];
//		i = 0;
//		for(Integer wn :commAccount.listbattles) {
//			tabBattles[i] = wn;
//			i++;
//		}
		
		//
		dataTable.addRows(commAccount.listDates.size());
		
//		for (int i = 0; i < years.length; i++) {
//			dataTable.setValue(i, 0, String.valueOf(years[i]));
//		}
		//
		//on set la légende des abscisses ligne 0 
//		i = 0;
//		for (String date: commAccount.listDates ) {
//			//String partdate= date.substring(0, 8);
//			dataTable.setValue(i, 0, String.valueOf(i));
//			i++;
//		}
		for (int row = 0; row < commAccount.listDates.size(); row++) {
			int index = commAccount.listDates.size() -1 ;
			index = index - row;
			String partdate = commAccount.listDates.get(index);
			//dd-Mm-AAAA
			partdate = partdate.substring(0, 5);
			dataTable.setValue(row, 0, String.valueOf(partdate));
			
			
		}
		//
//		for (int col = 0; col < values.length; col++) {
//			for (int row = 0; row < values[col].length; row++) {
		
		
//				dataTable.setValue(row, col + 1, values[col][row]);
//			}
//		}
		//
//		for (int col = 0; col < listStats.size() ; col++) {
//			for (int row = 0; row < commAccount.listDates.size(); row++) {
//				int bw = tabBattlesWins[col] ;
//				int b = tabBattles[col] ;
//				double db =  (double)bw/(double)b ;
//				int intdb = (int) (db * 100);
//				db = (double) intdb / (double)100 ;
//				dataTable.setValue(row, col + 1, db);
//			}
//		}
//		
		int col = 0 ;
		double avg = 0.0;
		int previousNbBattle = 0;
		for (CommunityAccount commAcc: listAccount) {
			avg = 0.0;
			previousNbBattle = 0;
			for (int row = 0; row < commAccount.listDates.size(); row++) {
				int index = commAccount.listDates.size() -1 ;
				index = index - row;
				 DataPlayerInfos dataPlayerInfos = commAcc.listDataPlayerInfos.get(index);
				 if (stat.equalsIgnoreCase("WN8")) {
					 double tmp = dataPlayerInfos.getStatistics().getAllStatistics().getWn8();
					 
					 if (dataPlayerInfos.getStatistics().getAllStatistics().getWn8()!=0 && !Double.isNaN(dataPlayerInfos.getStatistics().getAllStatistics().getWn8())){
						 avg = dataPlayerInfos.getStatistics().getAllStatistics().getWn8();
					 }
					 if (avg != 0.0) {
						 int nb = (int) (avg * 100);
						 avg = (double)nb/100;
						 dataTable.setValue(row, col + 1, avg);
					 }
				 }
				 
				 if (stat.equalsIgnoreCase("WR")) {
					 if (dataPlayerInfos.getStatistics().getAllStatistics().getBattle_avg_performanceCalc() ==null) {
						 if (dataPlayerInfos.getStatistics().getAllStatistics().getWins() !=0 && dataPlayerInfos.getStatistics().getAllStatistics().getBattles() != 0) {
							 avg = (double)dataPlayerInfos.getStatistics().getAllStatistics().getWins()/ (double)dataPlayerInfos.getStatistics().getAllStatistics().getBattles();
							 avg = avg *100 ;
						 } 
						 if (avg != 0.0)
							 dataTable.setValue(row, col + 1, avg);
					 } else {
						 avg = dataPlayerInfos.getStatistics().getAllStatistics().getBattle_avg_performanceCalc() *  100 ;
						 if (avg != 0.0) {
							 int nb = (int) (avg * 100);
							 avg = (double)nb/100;
							 dataTable.setValue(row, col + 1, avg);
						 }
					 }
				 }
				 
				 if (stat.equalsIgnoreCase("BATTLE")) {
//					 if (dataPlayerInfos.getStatistics().getAllStatistics().getBattle_avg_performanceCalc() == null) {
//						 if (dataPlayerInfos.getStatistics().getAllStatistics().getWins() !=0 && dataPlayerInfos.getStatistics().getAllStatistics().getBattles() != 0) {
//							 avg = (double)dataPlayerInfos.getStatistics().getAllStatistics().getWins()/ (double)dataPlayerInfos.getStatistics().getAllStatistics().getBattles();
//							 avg = avg *100 ;
//						 } 
//						 if (avg != 0.0)
//							 dataTable.setValue(row, col + 1, avg);
//					 } else {
//						 avg = dataPlayerInfos.getStatistics().getAllStatistics().getBattle_avg_performanceCalc() *  100 ;
//						 if (avg != 0.0) {
//							 int nb = (int) (avg * 100);
//							 avg = (double)nb/100;
//							 dataTable.setValue(row, col + 1, avg);
//						 }
//					 }
					 if (dataPlayerInfos.getStatistics().getAllStatistics()!= null) {
						 int nbBattle = dataPlayerInfos.getStatistics().getAllStatistics().getBattles();
						 avg = nbBattle;
						 if (previousNbBattle == 0 )
							 dataTable.setValue(row, col + 1, previousNbBattle);
						 else {
							 //diff par rapoort au précédent
							 dataTable.setValue(row, col + 1, nbBattle - previousNbBattle);
						 }
						 previousNbBattle = nbBattle;
							 
					 }else {
						 dataTable.setValue(row, col + 1, avg);
					 }
				 }
			}
			
			col++;
		}



		// Set options
		LineChartOptions options = LineChartOptions.create();
		options.setBackgroundColor("#f0f0f0");
		options.setFontName("Tahoma");
		options.setFontSize(9.0);
		options.setTitle(stat);
		options.setHAxis(HAxis.create("Dates"));
		options.setVAxis(VAxis.create(stat));
		options.setHeight(500);
		options.setWidth(1000);

		// Draw the chart
		chart.draw(dataTable, options);
	}
}