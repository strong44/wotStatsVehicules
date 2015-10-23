package com.wot.client;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.googlecode.gwt.charts.client.corechart.LineChart;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.wot.shared.AllCommunityAccount;
import com.wot.shared.Clan;
import com.wot.shared.CommunityAccount;
import com.wot.shared.CommunityClan;
import com.wot.shared.DataCommunityAccount;
import com.wot.shared.DataCommunityAccountVehicules;
import com.wot.shared.DataCommunityClanMembers;
import com.wot.shared.DataCommunityMembers;
import com.wot.shared.DataPlayerTankRatingsStatistics;
import com.wot.shared.FieldVerifier;
import com.wot.shared.ItemsDataClan;
import com.wot.shared.XmlListAchievement;
import com.wot.shared.XmlSrc;




/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WotStatsVehicules implements EntryPoint {
	
	// A panel where the thumbnails of uploaded images will be shown

	  
	/////////
	
    LineChart lineCharts;
    ///
	final HorizontalPanel hPanelLoading = new HorizontalPanel();
	final Label errorLabel = new Label();
	final DialogBox dialogBox = new DialogBox();
	
	final HTML serverResponseLabel = new HTML();
	final Button closeButton = new Button("Close");
	final Label textToServerLabel = new Label();
    final ListBox dropBoxClanUsers = new ListBox(true);

	final Button statsMembersButton = new Button("Send");
//	final Button findHistorizedStatsWN8Button = new Button("Send");
//	final Button findHistorizedStatsWRButton = new Button("Send");
	final Button findHistorizedStatsTanksButton = new Button("Send");
	
	/////////
    
	static boolean adminLogin = false ;
	static String noData = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQ8KRYghA2Xyp8gWTkK4ZNtBQL2nixsiYdAFDeFBCaj_ylXcfhK";
	//XmlWiki xmlWiki = null;
	//HandlerGetAllMembersClan handlerFindMembersClan ;
	
	String idClan ="" ;
	int offsetClan = 0;
	int limitClan = 100;
	
	int passSaving = 0;
	int nbUsersToTreat = 30;
	
	  RootPanel rootPanel ;
	  DockPanel dockPanel;
	  TabPanel tp = new TabPanel();
	  
	  
	  //tableau des stats joueurs
	  CellTable<CommunityAccount> tableStatsCommAcc = new  CellTable<CommunityAccount> (CommunityAccount.KEY_PROVIDER);
	  
	  //tableau des stats historisés des joueurs
	  CellTable<CommunityAccount> tableHistorizedStatsCommAcc = new  CellTable<CommunityAccount> (CommunityAccount.KEY_PROVIDER);

	  //tableau des stats tanks historisés des joueurs
	  CellTable<CommunityAccount> tableHistorizedStatsTanksCommAcc = new  CellTable<CommunityAccount> (CommunityAccount.KEY_PROVIDER);

	  //tableau des stats joueurs
	  CellTable<CommunityAccount> tableAchivementCommAcc = new  CellTable<CommunityAccount> (CommunityAccount.KEY_PROVIDER);

	  //tableau des clans
	  CellTable<ItemsDataClan> tableClan = new  CellTable<ItemsDataClan> (ItemsDataClan.KEY_PROVIDER);
	  
	  // Create a data provider for tab players.
	  ListDataProvider<CommunityAccount> dataStatsProvider = new ListDataProvider<CommunityAccount>(CommunityAccount.KEY_PROVIDER);

	  // Create a data provider for tab players historized .
	  ListDataProvider<CommunityAccount> dataHistorizedStatsProvider = new ListDataProvider<CommunityAccount>(CommunityAccount.KEY_PROVIDER);

	  // Create a data provider for tab players historized .
	  ListDataProvider<CommunityAccount> dataHistorizedStatsTanksProvider = new ListDataProvider<CommunityAccount>(CommunityAccount.KEY_PROVIDER);
		  
	  // Create a data provider for achievement players.
	  ListDataProvider<CommunityAccount> dataAchievementsProvider = new ListDataProvider<CommunityAccount>(CommunityAccount.KEY_PROVIDER);
	  
	  //create data provider for tab clans
	  ListDataProvider<ItemsDataClan> dataClanProvider = new ListDataProvider<ItemsDataClan>(ItemsDataClan.KEY_PROVIDER);
	     
	  HashMap<String, String>  hmAccNameAccId =new HashMap<String, String >();
	  HashMap<String, String>  hmAccIdAccName =new HashMap<String, String >();
	  HashMap<String, String>  hmAccUpperNameAccName =new HashMap<String, String >();
	  
	//
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final WotServiceAsync wotService = GWT
			.create(WotService.class);

	
	
	/*
	 * call this when we have data to put in table
	 */
	public  void buildACellTableForCommunityClan(Clan listClan) {
			    
	    //update dataprovider with some known list 
	    dataClanProvider.setList(listClan.getItems());
	    tableClan.addStyleName("gwt-CellTable");
	    tableClan.addStyleName("myCellTableStyle");

		// Create a CellTable.
	    //CellTable<CommunityAccount> table = new CellTable<CommunityAccount>();
		tableClan.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		//tableClan.setStyleName("gwt-CellTable");
	    
	    ListHandler<ItemsDataClan> columnSortHandler =
		        new ListHandler<ItemsDataClan>(dataClanProvider.getList());
	    tableClan.addColumnSortHandler(columnSortHandler);
	    
    
	    // Add a text column to show the name.
	    TextColumn<ItemsDataClan> nameColumn = new TextColumn<ItemsDataClan>() {
	      @Override
	      public String getValue(ItemsDataClan object) {
	        return object.getName();
	      }
	    };
	    tableClan.addColumn(nameColumn, "Name");

	    nameColumn.setSortable(true);
	    
	 // Add a ColumnSortEvent.ListHandler to connect sorting to the
	    columnSortHandler.setComparator(nameColumn,
	        new Comparator<ItemsDataClan>() {
	          public int compare(ItemsDataClan o1, ItemsDataClan o2) {
	            if (o1 == o2) {
	              return 0;
	            }

	            // Compare the name columns.
	            if (o1 != null) {
	              return (o2 != null) ? o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase()) : 1;
	            }
	            return -1;
	          }
	        });
	    //tableCommAcc.addColumnSortHandler(columnSortHandler);
	    
	 // We know that the data is sorted alphabetically by default.
	    tableClan.getColumnSortList().push(nameColumn);
	    
	    // Add a text column to show the id.
	    TextColumn<ItemsDataClan> idColumn = new TextColumn<ItemsDataClan>() {
	      @Override
	      public String getValue(ItemsDataClan object) {
	        return object.getId().toString();
	      }
	    };
	    tableClan.addColumn(idColumn, "Clan Id");

	    

	    
//		    emblemColumn.setSortable(true);
//		    
//		    // Add a ColumnSortEvent.ListHandler to connect sorting to the
//		    columnSortHandler.setComparator(emblemColumn,
//		        new Comparator<ItemsDataClan>() {
//		          public int compare(ItemsDataClan o1, ItemsDataClan o2) {
//		            if (o1 == o2) {
//		              return 0;
//		            }
//	
//		            // Compare the columns.
//		            if (o1 != null) {
//		            	String val1 = o1.getClan_emblem_url();
//		            	String val2 = o2.getClan_emblem_url();
//		              return (o2 != null) ? val1.compareTo(val2) : 1;
//		            }
//		            return -1;
//		          }
//		        });
	    //////////////////////////////////////////////////
	    
	    
	    // Add a text column 
	    TextColumn<ItemsDataClan> membersColumn = new TextColumn<ItemsDataClan>() {
	      @Override
	      public String getValue(ItemsDataClan object) {
	        return String.valueOf(object.getMember_count() );
	      }
	    };
	    tableClan.addColumn(membersColumn, "Nb members");
	    
	    membersColumn.setSortable(true);
	    
	 // Add a ColumnSortEvent.ListHandler to connect sorting to the
	    columnSortHandler.setComparator(membersColumn,
	        new Comparator<ItemsDataClan>() {
	          public int compare(ItemsDataClan o1, ItemsDataClan o2) {
	            if (o1 == o2) {
	              return 0;
	            }

	            // Compare the columns.
	            if (o1 != null) {
	            	int val1 = Integer.valueOf(o1.getMember_count());
	            	int val2 = Integer.valueOf(o2.getMember_count());
	              return (o2 != null) ? Integer.valueOf(val1).compareTo(Integer.valueOf(val2)) : 1;
	            }
	            return -1;
	          }
	        });
	    
		    
	    // Add a selection model to handle user selection.
	    final SingleSelectionModel<ItemsDataClan> selectionModel = new SingleSelectionModel<ItemsDataClan>();
	    tableClan.setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	    	  ItemsDataClan selected = selectionModel.getSelectedObject();
	    	  
	        if (selected != null) {
	          //Window.alert("You selected: " + selected.getName() +". You can find members now !");
	          idClan = selected.getId().toString();
	          
	          getMembersClan();
	        }
	        
	      }
	    });
	   
	    // Set the total row count. This isn't strictly necessary, but it affects
	    // paging calculations, so its good habit to keep the row count up to date.
	    
	    tableClan.setRowCount(listClan.getItems().size(), true); //no need to do here because we have add list to data provider

	    // Push the data into the widget.
	    tableClan.setRowData(0, listClan.getItems());            //idem no nedd dataprovider
	   
	 // Connect the table to the data provider.
	    dataClanProvider.addDataDisplay(tableClan);
	    dataClanProvider.refresh();
		    
	}


	
	/**
	   * Get a cell value from a record.
	   * 
	   * @param <C> the cell type
	   */
	  private static interface GetValue<C> {
	    C getValue(CommunityAccount contact);
	  }
	
	 /**
	   * Add a column with a header.
	   * 
	   * @param <C> the cell type
	   * @param cell the cell used to render the column
	   * @param headerText the header string
	   * @param getter the value getter for the cell
	   */
//	  private <C> Column<CommunityAccount, C> addColumn(Cell<C> cell, String headerText,
//	      final GetValue<C> getter, FieldUpdater<CommunityAccount, C> fieldUpdater) {
//	    Column<CommunityAccount, C> column = new Column<CommunityAccount, C>(cell) {
//	      @Override
//	      public C getValue(CommunityAccount object) {
//	        return getter.getValue(object);
//	      }
//	      
//	    };
//	    column.setFieldUpdater(fieldUpdater);
////	    if (cell instanceof AbstractEditableCell<?, ?>) {
////	      editableCells.add((AbstractEditableCell<?, ?>) cell);
////	    }
//	    tableAchivementCommAcc.addColumn(column, headerText);
//	    return column;
//	  }
	
	
	/**
		 * This is the entry point method.
		 */
	public void onModuleLoad() {

		Window.enableScrolling(true);
        Window.setMargin("0px");
////////////////////////////
        
        // Create a FormPanel and point it at a service.
//         final FormPanel form = new FormPanel();
//         form.setAction(UPLOAD_ACTION_URL);
// 
//         // Because we're going to add a FileUpload widget, we'll need to set the
//         // form to use the POST method, and multipart MIME encoding.
//         form.setEncoding(FormPanel.ENCODING_MULTIPART);
//         form.setMethod(FormPanel.METHOD_POST);
 
         // Create a panel to hold all of the form widgets.
//         VerticalPanel panel = new VerticalPanel();
//         form.setWidget(panel);
// 
//         // Create a TextBox, giving it a name so that it will be submitted.
//         final TextBox tb = new TextBox();
//         tb.setName("textBoxFormElement");
//         panel.add(tb);
 
         // Create a ListBox, giving it a name and some values to be associated
         // with its options.
//         ListBox lb = new ListBox();
//         lb.setName("listBoxFormElement");
//         lb.addItem("foo", "fooValue");
//         lb.addItem("bar", "barValue");
//         lb.addItem("baz", "bazValue");
//         panel.add(lb);
 
//         // Create a FileUpload widget.
//         FileUpload upload = new FileUpload();
//         upload.setName("uploadFormElement");
//         panel.add(upload);
// 
//         // Add a 'submit' button.
//         panel.add(new Button("Submit", new ClickHandler() {
//         public  void onClick(ClickEvent event) {
//                 form.submit();
//             }
//         }));
 
         // Add an event handler to the form.
//         form.addSubmitHandler(new FormPanel.SubmitHandler() {
//             public void onSubmit(SubmitEvent event) {
//                 // This event is fired just before the form is submitted. We can
//                 // take this opportunity to perform validation.
//                 if (tb.getText().length() == 0) {
//                     Window.alert("The text box must not be empty");
//                     event.cancel();
//                 }
//             }
//         });
 
//         form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
//             public void onSubmitComplete(SubmitCompleteEvent event) {
//                 // When the form submission is successfully completed, this
//                 // event is fired. Assuming the service returned a response of type
//                 // text/html, we can get the result text here (see the FormPanel
//                 // documentation for further explanation).
//             }
//         });
// 
//         RootPanel.get().add(form);
// 
  			
        
        ///////////////////////////////
			 /**
			   * An instance of the constants.
			   */
			//final CwConstants constants = GWT.create(CwConstants.class);
			
			rootPanel = RootPanel.get();
			Date today = new Date();
			String StrDayOfMonth = DateTimeFormat.getFormat("d").format(today);
			Integer dayOfMonth = Integer.valueOf(StrDayOfMonth);
			
			if (dayOfMonth>30) {
				dayOfMonth = dayOfMonth - 30 ;
			}else
				if (dayOfMonth>20) {
					dayOfMonth = dayOfMonth - 20;
				}	
				else
					if (dayOfMonth>10) {
						dayOfMonth = dayOfMonth - 10;
					}
			
			rootPanel.setStyleName("mybody"+ dayOfMonth);
			
			dockPanel = new DockPanel();
			rootPanel.add(dockPanel, 29, 265);
			dockPanel.setSize("1193px", "550px");

	
		    int posTop = 10;
		    
			//button search Clans
			//int posLeft = 10;
			posTop = posTop + 35;
			Button searchClansButton = new Button("Clans");
			rootPanel.add(searchClansButton, 10, posTop);
			searchClansButton.setSize("80px", "28px");

			//bouton plus de clans
//			final Button searchClansButtonMore = new Button("100 Clans +");
//			rootPanel.add(searchClansButtonMore, 95, posTop);
//			searchClansButtonMore.setSize("120px", "28px");
//			searchClansButtonMore.setEnabled(false);
			
			
			//label clan
			//lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			
			//nom du clan a rechercher
			final TextBox nameClan = new TextBox();
			rootPanel.add(nameClan, 300, posTop);
			nameClan.setText("NOVA SNAIL");
			nameClan.setSize("125px", "18px");
			nameClan.addFocusHandler(new FocusHandler() {
				
				@Override
				public void onFocus(FocusEvent event) {
					// TODO Auto-generated method stub
					offsetClan = 0;
					limitClan = 100;
				}
			});
			// Focus the name clan 
			nameClan.setFocus(true);
			
			//bouton login for admin functions
			final Button loginAdminButton = new Button("Admin login");
			rootPanel.add(loginAdminButton, 700, posTop);
			loginAdminButton.setSize("125px", "28px");
			loginAdminButton.setEnabled(true);
			
			//nom du login 
			final TextBox nameLogin = new TextBox();
			rootPanel.add(nameLogin, 850, posTop);
			nameLogin.setText("t..");
			nameLogin.setSize("125px", "18px");
			
			nameLogin.addFocusHandler(new FocusHandler() {
				
				@Override
				public void onFocus(FocusEvent event) {
					// TODO Auto-generated method stub
					passSaving = 0;
					nbUsersToTreat = 30;
				}
			});
			
			final Button persistStatsButton = new Button("Save stats");
			rootPanel.add(persistStatsButton, 1000, posTop);
			persistStatsButton.setSize("125px", "28px");
			persistStatsButton.setEnabled(false);
			
			// Add a handler to set admin login true
			loginAdminButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (nameLogin.getText().equalsIgnoreCase("thleconn")){
						persistStatsButton.setEnabled(true);
						adminLogin = true;
					}else {
						persistStatsButton.setEnabled(false);
						adminLogin = false;
					}
				}
			});
			//

			
			
			//next row button search Clan's users
			posTop = posTop + 35;
			final Button searchUsersClanButton = new Button("Clan's Users");
			rootPanel.add(searchUsersClanButton, 10, posTop);
			searchUsersClanButton.setSize("210px", "28px");
			searchUsersClanButton.setEnabled(false);

			// Add a drop box with the clan's users
		    //final ListBox dropBoxClanUsers = new ListBox(true);
		    dropBoxClanUsers.setSize("300px", "150px");
		    dropBoxClanUsers.ensureDebugId("cwListBox-dropBox");
		    dropBoxClanUsers.setVisibleItemCount(20);
		    //dropBoxClanUsers.setMultipleSelect(true);
		    rootPanel.add(dropBoxClanUsers, 300, posTop);

	
			//next row -- button search stats member's clan
			posTop = posTop + 35 ;
			//final Button statsMembersButton = new Button("Send");
			statsMembersButton.setText("Stats");
			rootPanel.add(statsMembersButton, 10, posTop);
			statsMembersButton.setSize("210px", "28px");
			statsMembersButton.setEnabled(false);


			//findHistorizedStatsTanksButton
			posTop = posTop + 35 ;
			//final Button findHistorizedStatsWRButton = new Button("Send");
			findHistorizedStatsTanksButton.setText("Tanks");
			rootPanel.add(findHistorizedStatsTanksButton, 10, posTop);
			findHistorizedStatsTanksButton.setSize("210px", "28px");
			findHistorizedStatsTanksButton.setEnabled(false);

			//next row - button achievement's member
		    posTop = posTop + 70 ;
		    
			//loading .gif
		    posTop = posTop + 35 ;
			Image image = new Image("loading.gif");
			hPanelLoading.add(image);
			hPanelLoading.setVisible(false);
		    rootPanel.add(hPanelLoading, 350, posTop);
		    
			// Create the popup dialog box in case of error

			dialogBox.setText("Remote Procedure Call");
			dialogBox.setAnimationEnabled(true);

			// We can set the id of a widget by accessing its Element
			closeButton.getElement().setId("closeButton");
			VerticalPanel dialogVPanel = new VerticalPanel();
			dialogVPanel.addStyleName("dialogVPanel");
			dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
			dialogVPanel.add(textToServerLabel);
			dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
			dialogVPanel.add(serverResponseLabel);
			dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
			dialogVPanel.add(closeButton);
			dialogBox.setWidget(dialogVPanel);
		
			
			
			// Add a handler to close the DialogBox
			closeButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogBox.hide();
//					searchClanButton.setEnabled(true);
//					searchClanButton.setFocus(true);
				}
			});
			
			// Create a handler for the Button search all clans
			class HandlerGetClans implements ClickHandler, KeyUpHandler {
				/**
				 * Fired when the user clicks on the sendButton.
				 */
				public void onClick(ClickEvent event) {
					//si c'est bouton find more on incr�mente offset por trouver les 100 clans suivnats
//					if ( ((Button)event.getSource()).getText().equalsIgnoreCase(searchClansButtonMore.getText())) {
//						offsetClan = offsetClan + 100;
//						limitClan = 100;
//					} else {
						//bouton find clan offset 0
					offsetClan = 0;
					limitClan = 100;
					//}
					//recherche des clans
					getClans();
				}
	
				/**
				 * Fired when the user types in the nameField.
				 */
				public void onKeyUp(KeyUpEvent event) {
					if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//						if ( event.getSource() instanceof Button && ((Button)event.getSource()).getText().equalsIgnoreCase(searchClansButtonMore.getText())) {
//							offsetClan = offsetClan + 100;
//							limitClan = 100;
//						} else {
						offsetClan = 0;
						limitClan = 100;
//						}
						getClans();
					}
				}
	
				
				/**
				 * Send the name from the nameField to the server and wait for a response.
				 */
				private void getClans() {
					hPanelLoading.setVisible(true);
					tableStatsCommAcc.setVisible(false);
					// First, we validate the input.
					errorLabel.setText("");
					String textToServer = nameClan.getText();
					if (!FieldVerifier.isValidName(textToServer)) {
						errorLabel.setText("Please enter at least four characters");
						return;
					}
	
					// Then, we send the input to the server.
					//searchClanButton.setEnabled(false);
					textToServerLabel.setText(textToServer);
					serverResponseLabel.setText("");
					wotService.getClans(textToServer , offsetClan,
							new AsyncCallback<Clan>() {
								public void onFailure(Throwable caught) {
									hPanelLoading.setVisible(false);
									// Show the RPC error message to the user
									dialogBox
											.setText("Remote Procedure Call - Failure");
									serverResponseLabel
											.addStyleName("serverResponseLabelError");
									serverResponseLabel.setHTML(SERVER_ERROR);
									dialogBox.center();
									closeButton.setFocus(true);
								}
	
								
								public void onSuccess(Clan listClan) {
									hPanelLoading.setVisible(false);
									try {
										//String translatedText =Translate.execute("Bonjour le monde", Languages.FRENCH, Languages.ENGLISH);
										//System.out.println("Bonjour le monde : " + translatedText);
										
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									String status= listClan.getStatus();
//									"status": "ok", 
//									  "status_code": "NO_ERROR", 
									
									if (status.equalsIgnoreCase("ok")) {  
//										dockPanel.remove(tableStatsCommAcc);
//										dockPanel.remove(tableClan);
//										if (pagerStatsCommunityAccount != null) 
//											dockPanel.remove(pagerStatsCommunityAccount);
//										if (pagerClan != null) 
//											dockPanel.remove(pagerClan);
										
										if (dataClanProvider.getDataDisplays()!= null && !dataClanProvider.getDataDisplays().isEmpty()) 
											dataClanProvider.removeDataDisplay(tableClan);
										
										tableClan = new  CellTable<ItemsDataClan> (ItemsDataClan.KEY_PROVIDER);
										
										//construct column in celltable tableClan , set data set sort handler etc ..
										buildACellTableForCommunityClan(listClan);
										
									    ScrollPanel sPanel = new ScrollPanel();
									    //
									    sPanel.setStyleName("myCellTableStyle");
									    sPanel.setAlwaysShowScrollBars(true);
									    sPanel.setHeight("500px");
									    //sPanel.add(pagerClan);
									    sPanel.add(tableClan);
									    tp.add(sPanel, "Clans");
										dockPanel.add(tp, DockPanel.SOUTH);
										tp.selectTab(0);

										tableClan.setVisible(true);
										tableClan.setFocus(true);

										searchUsersClanButton.setEnabled(true);
										
										//on autorise le bouton  more clans s'il y a en core 100 �lments dans TAB
//										if(listClan.getItems().size()== 100)
//											searchClansButtonMore.setEnabled(true);
//										else {
//											searchClansButtonMore.setEnabled(false);
//										}
										if(listClan.getItems().size()== 1) {
											
											tableClan.getSelectionModel().setSelected(listClan.getItems().get(0), true);
											
											//On pourrait chosir tout de suite les users du clan
											
											
										}
										
									}else {
										dialogBox
										.setText(status);
										serverResponseLabel
												.addStyleName("serverResponseLabelError");
										serverResponseLabel.setHTML(status + " An error arrived , please Retry again ! " );
										dialogBox.center();
										closeButton.setFocus(true);
									}
									
								}
						});
					//searchClanButton.setEnabled(true);
					//searchClanButton.setFocus(true);
				}
				
				

				
				
				//
				
				
			}
			/////////////////////////
			//creare a handler for persist data in datastore
			// Create a handler for the Button search all clans
			class HandlerPersistStats implements ClickHandler, KeyUpHandler {
				/**
				 * Fired when the user clicks on the sendButton.
				 */
				public void onClick(ClickEvent event) {
					//persist stats
					persisStats(passSaving, nbUsersToTreat);
					passSaving = 1 ;
					nbUsersToTreat = 30;
				}
	
				/**
				 * Fired when the user types in the nameField.
				 */
				public void onKeyUp(KeyUpEvent event) {
					if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
						persisStats(passSaving, nbUsersToTreat);
						passSaving = 1 ;
						nbUsersToTreat = 30;
					}
				}
	
				
				/**
				 * Send the name from the nameField to the server and wait for a response.
				 */
				private void persisStats(int indexBeginSaveStatsUser, int indexEndSaveStatsUser) {
					hPanelLoading.setVisible(true);
				    // recup des users selected in dropBoxClanUsers
					List<String> listIdUser = new ArrayList<String>();
					int itemCount = dropBoxClanUsers.getItemCount();
					for(int i = 0 ;  i< itemCount ; i++) {
						if (dropBoxClanUsers.isItemSelected(i)) {
							listIdUser.add(dropBoxClanUsers.getValue(i));
						}
					}
					
					// First, we validate the input.
					errorLabel.setText("");
					String textToServer = idClan;
					if (!FieldVerifier.isValidName(textToServer)) {
						errorLabel.setText("Please enter at least four characters");
						return;
					}
	
					// Then, we send the input to the server.
					//searchClanButton.setEnabled(false);
					textToServerLabel.setText(textToServer);
					serverResponseLabel.setText("");
					wotService.persistStats(textToServer , indexBeginSaveStatsUser, indexEndSaveStatsUser, listIdUser,//offsetClan,
							new AsyncCallback<List<String>>() {
								public void onFailure(Throwable caught) {
									hPanelLoading.setVisible(false);
									// Show the RPC error message to the user
									dialogBox
											.setText("Remote Procedure Call - Failure");
									serverResponseLabel
											.addStyleName("serverResponseLabelError");
									serverResponseLabel.setHTML(SERVER_ERROR);
									dialogBox.center();
									closeButton.setFocus(true);
								}
	
								
								public void onSuccess(List<String> listClan) {
									hPanelLoading.setVisible(false);
//									if (listClan.size()== 0) {
//
//										dialogBox
//										.setText(idClan);
//										serverResponseLabel
//												.addStyleName("serverResponseLabelError");
//										serverResponseLabel.setHTML(idClan + " An error arrived , please Retry again ! " );
//										dialogBox.center();
//										closeButton.setFocus(true);
//									}
									
								}
						});
					//searchClanButton.setEnabled(true);
					//searchClanButton.setFocus(true);
				}
				
				
				
			}
			
			
			
			
			
			///////////
//			// Create a handler for search clan's members
//			class HandlerGetAllStatsFromDossierCache implements ClickHandler, KeyUpHandler {
//				/**
//				 * Fired when the user clicks on the sendButton.
//				 */
//				public void onClick(ClickEvent event) {
//					getStats();
//					
//				}
//	
//				/**
//				 * Fired when the user types in the nameField.
//				 */
//				public void onKeyUp(KeyUpEvent event) {
//					if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//						getStats();
//						
//					}
//				}
//	
//				/**
//				 * Send the name from the nameField to the server and wait for a response.
//				 */
//				private void getStats() {
//					
//			        String filename = fileUpload.getFilename();
//			        String textToServer = filename;
//			        
//			        if (filename.length() == 0) {
//			        	errorLabel.setText("Please enter at least four characters");
//						
//						/////
//						dialogBox
//						.setText("Select a file before!!");
//						serverResponseLabel
//								.addStyleName("serverResponseLabelError");
//						serverResponseLabel.setHTML("Click on a file before !"  );
//						dialogBox.center();
//						closeButton.setFocus(true);
//						return;
//			          
//			        } else {
//			          Window.alert("file size OK");
//			          
////			          FileReader fileReader = null;
////			          try {
////						  File fileToUpload = new File(filename);
////						  fileReader = new FileReader(fileToUpload); 
////						  
////						  
////						  CharBuffer buffer = CharBuffer.allocate(1000*1000);
////						  int bytesRead = -1;
////						  while ((bytesRead = fileReader.read(buffer)) != -1) {
////						    	System.out.println("bytesRead " + bytesRead );
////						  }
////						  
////						  
////					} catch (FileNotFoundException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					} catch (IOException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
////			        finally {
////			        	try {
////							if (fileReader != null ) fileReader.close();
////						} catch (IOException e) {
////							// TODO Auto-generated catch block
////							e.printStackTrace();
////						}
////			        }
//			          
//			          //TODO : Display stats of dossier cache
//			        }
//					
//					// First, we validate the input.
//					hPanelLoading.setVisible(true);
//					
//				    // Sending filename <file *.dat> to server 
//					// Then, we send the input to the server.
//					//searchClanButton.setEnabled(false);
//					textToServerLabel.setText(textToServer);
//					serverResponseLabel.setText("");
//					wotService.getAllStatsFromDossierCache(filename,
//							new AsyncCallback<AllCommunityAccount>() {
//								public void onFailure(Throwable caught) {
//									hPanelLoading.setVisible(false);
//									// Show the RPC error message to the user
//									dialogBox
//											.setText("Remote Procedure Call - Failure");
//									serverResponseLabel
//											.addStyleName("serverResponseLabelError");
//									serverResponseLabel.setHTML(SERVER_ERROR);
//									dialogBox.center();
//									closeButton.setFocus(true);
//									
//								}
//	
//								public void onSuccess(AllCommunityAccount listAccount) {
//									hPanelLoading.setVisible(false);
//									dockPanel.remove(tableStatsCommAcc);
//									dockPanel. remove(tableClan);
//									
//
//									
//									if (dataStatsProvider.getDataDisplays()!= null && !dataStatsProvider.getDataDisplays().isEmpty()) 
//										dataStatsProvider.removeDataDisplay(tableStatsCommAcc);
//									
//									//on re-construit 1 nouveau tableau
//									tableStatsCommAcc = new  CellTable<CommunityAccount> (CommunityAccount.KEY_PROVIDER);
//									
//									//construct column in celltable tableCommAcc , set data set sort handler etc ..
//									buildACellTableForStatsCommunityAccount(listAccount.getListCommunityAccount());
//									  
//									//Create a Pager to control the table.
////								    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
////								    pagerStatsCommunityAccount = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
////								    pagerStatsCommunityAccount.setDisplay(tableStatsCommAcc);
//									
//								    
//								    /////////
//								    ScrollPanel sPanel = new ScrollPanel();
//								    //
//								    sPanel.setStyleName("myCellTableStyle");
//								    sPanel.setAlwaysShowScrollBars(true);
//								    sPanel.setHeight("500px");
//								    //sPanel.add(pagerClan);
//								    sPanel.add(tableStatsCommAcc);
//								    tp.add(sPanel, "Stats");
//								    int count = tp.getWidgetCount();
//									dockPanel.add(tp, DockPanel.SOUTH);
//									tp.selectTab(count-1);
//								    
//									tableClan.setVisible(true);
//									//pagerClan.setVisible(true);
//									
//									tableStatsCommAcc.setFocus(true);
//								}
//							});
//				}
//			}
//			///////////			
			
			
			
			///////////
			// Create a handler for search clan's members
			class HandlerGetHistorizedStatsTanks implements ClickHandler, KeyUpHandler {
				/**
				 * Fired when the user clicks on the sendButton.
				 */
				public void onClick(ClickEvent event) {
					getHistorizedStatsTanks();
					offsetClan = 0;
					limitClan = 100;
				}
	
				/**
				 * Fired when the user types in the nameField.
				 */
				public void onKeyUp(KeyUpEvent event) {
					if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
						getHistorizedStatsTanks();
						offsetClan = 0;
						limitClan = 100;
					}
				}
	
				/**
				 * Send the name from the nameField to the server and wait for a response.
				 */
				private void getHistorizedStatsTanks() {
					// First, we validate the input.
					hPanelLoading.setVisible(true);
					
				    // recup des users selected in dropBoxClanUsers
					List<String> listIdUser = new ArrayList<String>();
					int itemCount = dropBoxClanUsers.getItemCount();
					for(int i = 0 ;  i< itemCount ; i++) {
						if (dropBoxClanUsers.isItemSelected(i)) {
							listIdUser.add(dropBoxClanUsers.getValue(i));
						}
					}
					
					
					errorLabel.setText("");
					String textToServer = idClan;
					if (!FieldVerifier.isValidName(textToServer)) {
						errorLabel.setText("Please enter at least four characters");
						
						/////
						dialogBox
						.setText("Select a Clan before!!");
						serverResponseLabel
								.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML("Click on a clan before find members !"  );
						dialogBox.center();
						closeButton.setFocus(true);
						return;
					}
	
					// Then, we send the input to the server.
					//searchClanButton.setEnabled(false);
					textToServerLabel.setText(textToServer);
					serverResponseLabel.setText("");
					wotService.getHistorizedStatsTanks( listIdUser,
							new AsyncCallback<List<CommunityAccount>>() {
								public void onFailure(Throwable caught) {
									hPanelLoading.setVisible(false);
									// Show the RPC error message to the user
									dialogBox
											.setText("Remote Procedure Call - Failure");
									serverResponseLabel
											.addStyleName("serverResponseLabelError");
									serverResponseLabel.setHTML(SERVER_ERROR);
									dialogBox.center();
									closeButton.setFocus(true);
									
								}
	
								public void onSuccess(List<CommunityAccount> listAccount) {
									hPanelLoading.setVisible(false);
									dockPanel.remove(tableHistorizedStatsTanksCommAcc);
									dockPanel.remove(tableClan);
									
//									if (pagerHistorizedStatsTanksCommunityAccount != null) 
//										dockPanel.remove(pagerHistorizedStatsTanksCommunityAccount);
//									if (pagerClan != null) 
//										dockPanel.remove(pagerClan);
									
									if (dataHistorizedStatsTanksProvider.getDataDisplays()!= null && !dataHistorizedStatsTanksProvider.getDataDisplays().isEmpty()) 
										dataHistorizedStatsTanksProvider.removeDataDisplay(tableHistorizedStatsTanksCommAcc);
									
									//on re-construit 1 nouveau tableau
									tableHistorizedStatsTanksCommAcc = new  CellTable<CommunityAccount> (CommunityAccount.KEY_PROVIDER);
									
									
									//construct column in celltable tableCommAcc , set data set sort handler etc ..
									buildACellTableForHistorizedStatsTanksCommunityAccount(listAccount);
									
									
									
									 ///////////
								    ScrollPanel sPanel = new ScrollPanel();
								    //
								    sPanel.setStyleName("myCellTableStyle");
								    sPanel.setAlwaysShowScrollBars(true);
								    sPanel.setHeight("500px");
								    sPanel.setWidth("1000px");
								    //sPanel.add(pagerClan);
								    LineChartExample lineChartExample = new LineChartExample("Stat Tank", listAccount); 
								    lineChartExample.setVisible(true);
								    sPanel.add(lineChartExample);
								    tp.add(sPanel, "History " + "Stat Tank");
								    int count = tp.getWidgetCount();
									dockPanel.add(tp, DockPanel.SOUTH);
									tp.selectTab(count-1);
									
//									
							    
								    //add to dock panel ======
//								    dockPanel.add(pagerHistorizedStatsTanksCommunityAccount, DockPanel.SOUTH);
//								    pagerHistorizedStatsTanksCommunityAccount.setPage(10);
//								    pagerHistorizedStatsTanksCommunityAccount.setVisible(true);
									
//									dockPanel.add(tableHistorizedStatsTanksCommAcc, DockPanel.SOUTH);
//									tableHistorizedStatsTanksCommAcc.setVisible(true);
								    
//									dockPanel.add(pagerClan, DockPanel.SOUTH);
//									dockPanel.add(tableClan, DockPanel.SOUTH);
//									tableClan.setVisible(true);
//									pagerClan.setVisible(true);
									
//									tableHistorizedStatsTanksCommAcc.setFocus(true);
									//dialogBox.center();
									//closeButton.setFocus(true);
								}
							});
					//searchClanButton.setEnabled(true);
					//searchClanButton.setFocus(true);
				}
				
				
				
			}
		////
			
			
		////
			class HandlerGetAllMembersClan implements ClickHandler, KeyUpHandler {
				/**
				 * Fired when the user clicks on the sendButton.
				 */
				public void onClick(ClickEvent event) {
					getMembersClan();
					//offsetClan = 0;
					//limitClan = 100;
				}
	
				/**
				 * Fired when the user types in the nameField.
				 */
				public void onKeyUp(KeyUpEvent event) {
					if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
						getMembersClan();
						//offsetClan = 0;
						//limitClan = 100;
					}
				}
	
				/**
				 * Send the name from the nameField to the server and wait for a response.
				 */
				private void getMembersClan() {
					// First, we validate the input.
					hPanelLoading.setVisible(true);
				    
					errorLabel.setText("");
					String textToServer = idClan;
					if (!FieldVerifier.isValidName(textToServer)) {
						errorLabel.setText("Please enter at least four characters");
						
						/////
						dialogBox
						.setText("Select a Clan before!!");
						serverResponseLabel
								.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML("Click on a clan before find members !"  );
						dialogBox.center();
						closeButton.setFocus(true);
						hPanelLoading.setVisible(false);
						return;
					}
	
					// Then, we send the input to the server.
					//searchClanButton.setEnabled(false);
					textToServerLabel.setText(textToServer);
					serverResponseLabel.setText("");
					wotService.getAllMembersClan(textToServer,
							new AsyncCallback<CommunityClan>() {
								public void onFailure(Throwable caught) {
									hPanelLoading.setVisible(false);
									// Show the RPC error message to the user
									dialogBox
											.setText("Remote Procedure Call - Failure");
									serverResponseLabel
											.addStyleName("serverResponseLabelError");
									serverResponseLabel.setHTML(SERVER_ERROR);
									dialogBox.center();
									closeButton.setFocus(true);
									
								}
	
								public void onSuccess(CommunityClan listAccount) {
									hPanelLoading.setVisible(false);
									//dropBoxClanUsers
									dropBoxClanUsers.clear();
									List<String> listAccName = new ArrayList<String>();
									hmAccNameAccId =new HashMap<String, String >();
									hmAccIdAccName =new HashMap<String, String >();
									hmAccUpperNameAccName =new HashMap<String, String >();
									
									for (DataCommunityClanMembers dataCom :  listAccount.getData().getMembers()) {
										for (DataCommunityMembers dataComMembers : dataCom.getMembers()) {
											listAccName.add(dataComMembers.getAccount_name().toUpperCase());
											//hashmap nom en majuscule / nom origine
											hmAccUpperNameAccName.put(dataComMembers.getAccount_name().toUpperCase(), dataComMembers.getAccount_name());
											
											hmAccNameAccId.put(dataComMembers.getAccount_name(), dataComMembers.getAccount_id());
											hmAccIdAccName.put(dataComMembers.getAccount_id(), dataComMembers.getAccount_name());
											//dropBoxClanUsers.addItem(dataCom.getAccount_name());
										}
									}
									//sort the list 
									Collections.sort(listAccName);
									
									//add to the list 
									for (String accName : listAccName) {
										//list box contain  name of user and id of user
										String originalName = hmAccUpperNameAccName.get(accName);
										dropBoxClanUsers.addItem(originalName, hmAccNameAccId.get(originalName));
									}
									dropBoxClanUsers.setFocus(true);
									statsMembersButton.setEnabled(true);
//									findHistorizedStatsWN8Button.setEnabled(true);
//									findHistorizedStatsWRButton.setEnabled(true);
									findHistorizedStatsTanksButton.setEnabled(true);
								}
							});
				}
				
				
				
			}
		////

			// Add a handler to send the name to the server
			//HandlerGetAllMembersClanAndStats handlerFindMembers = new HandlerGetAllMembersClanAndStats();
			//statsMembersButton.addClickHandler(handlerFindMembers);

			
			// Add a handler to send to get the dossier cache
//			HandlerGetAllStatsFromDossierCache handlerGetAllStatsFromDossierCache = new HandlerGetAllStatsFromDossierCache();
//			fileUploadButton.addClickHandler(handlerGetAllStatsFromDossierCache);

			// Add a handler to find historized stats 
//			HandlerGetHistorizedStats handlerGetHistorizedStats = new HandlerGetHistorizedStats("WN8");
//			findHistorizedStatsWN8Button.addClickHandler(handlerGetHistorizedStats);
//
			
			HandlerGetHistorizedStatsTanks handlerGetHistorizedStatsBattle = new HandlerGetHistorizedStatsTanks();
			findHistorizedStatsTanksButton.addClickHandler(handlerGetHistorizedStatsBattle);
			
			// Add a handler to find historized stats tanks
			//HandlerGetHistorizedStatsTanks handlerGetHistorizedStatsTanks = new HandlerGetHistorizedStatsTanks();
			//findHistorizedStatsERButton.addClickHandler(handlerGetHistorizedStatsTanks);

			// Add a handler to find clans
			HandlerGetClans handlerGetClans = new HandlerGetClans();
			searchClansButton.addClickHandler(handlerGetClans);
			
			// Add a handler to find clans more
			nameClan.addKeyUpHandler(handlerGetClans);
	
			//HandlerPersistStats
			// Add a handler to persist stats
			HandlerPersistStats handlerPersistStats = new HandlerPersistStats();
			persistStatsButton.addClickHandler(handlerPersistStats);
			//nameClan.addKeyUpHandler(handlerGetClans);
			
			
			//Add a handler to find clan's users button : searchUsersClanButton
			HandlerGetAllMembersClan handlerFindMembersClan = new HandlerGetAllMembersClan();
			searchUsersClanButton.addClickHandler(handlerFindMembersClan);
			
			
			// button HandlerGetAchivementsMember
//			HandlerGetAchievementsMember myHandlerGetAchivementsMember = new HandlerGetAchievementsMember();
//			findAchievementsMemberButton.addClickHandler(myHandlerGetAchivementsMember);

			
			
			
		}


	
			/*
		 * call this when we have data to put in table
		 */
		public  void buildACellTableForHistorizedStatsTanksCommunityAccount(List<CommunityAccount> listCommAcc) {
	
			tableHistorizedStatsTanksCommAcc.setTitle("Historical Battles Tanks");
			tableHistorizedStatsTanksCommAcc.setPageSize(30);
			tableHistorizedStatsTanksCommAcc.addStyleName("gwt-CellTable");
			tableHistorizedStatsTanksCommAcc.addStyleName("myCellTableStyle");
			
		    //update dataprovider with some known list 
		    dataHistorizedStatsTanksProvider.setList(listCommAcc);
			
			// Create a CellTable.
		    tableHistorizedStatsTanksCommAcc.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		    
		    
		    ListHandler<CommunityAccount> columnSortHandler =
			        new ListHandler<CommunityAccount>(dataHistorizedStatsTanksProvider.getList());
		    tableHistorizedStatsTanksCommAcc.addColumnSortHandler(columnSortHandler);
		    
		    //
		    int sizeDate = 0;
		    final List<Date> listDates = new ArrayList<Date>(); 
		    for (CommunityAccount commAcc :  listCommAcc) {
		    	listDates.add(commAcc.getDateCommunityAccount());
		    }
		    sizeDate = listDates.size();
		    
		    // Add a text column to show the name of the player.
		    TextColumn<CommunityAccount> nameColumn = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		        return object.getName();
		      }
		    };
		    tableHistorizedStatsTanksCommAcc.addColumn(nameColumn, "Name");

		    nameColumn.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(nameColumn,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }

		            // Compare the name columns.
		            if (o1 != null) {
		              return (o2 != null) ? o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase()) : 1;
		            }else
		            	return -1;
		          }
		        });
		    
		 // We know that the data is sorted alphabetically by default.
		    tableHistorizedStatsTanksCommAcc.getColumnSortList().push(nameColumn);

	    	///
		    // TANK 1 JOUR 1  ///////////////////////
		    TextColumn<List<CommunityAccount>> jour1Tank1 = new TextColumn<List<CommunityAccount>>() {
		      @Override
		      public String getValue(List<CommunityAccount> object ) {
		    	  if (object.size() >= 2  ) {
		    		  List<DataPlayerTankRatingsStatistics> listTankPlayedFromLastDay = new ArrayList<DataPlayerTankRatingsStatistics>();
		    		  
		    		  //liste des stats des tanks au jour J 
		    		  List<DataPlayerTankRatingsStatistics> listTankDay0 = object.get(0).getListTankStatistics();
		    		  
		    		  //liste des stats des tanks au jour J + 1 
		    		  List<DataPlayerTankRatingsStatistics> listTankDay1 = object.get(1).getListTankStatistics();
		    		  
		    		  
		    		  //bcl sur les stats vehicules du joueur pour le jour en question (1) 
		    		  for (DataPlayerTankRatingsStatistics dataCommAccVeh0 : listTankDay0) {
		    			  //pour chaque vehicule du jour 0, il faut trouver le véhicule correspondant dans  ceux du jour 1 pour éventuellement détecté 
		    			  //qu'il a été joué ( + battle)
		    			  //et le mémoriser dans une liste de tanks joués
		    			  //A la fin on ne prendra que ceux qui ont été le + joués) 
		    			  //
		    			  for (DataPlayerTankRatingsStatistics dataCommAccVeh1 : listTankDay1) {
			    			  //char trouvé dans liste
			    			  if(dataCommAccVeh0.getTank_id() == dataCommAccVeh1.getTank_id() ) {
			    				  if (dataCommAccVeh0.getBattles() >  dataCommAccVeh1.getBattles()) {
			    					  //le char a été joué il faut l'ajouter  à la liste
			    					  dataCommAccVeh0.setCountBattleSincePreviousDay(dataCommAccVeh0.getBattles() - dataCommAccVeh1.getBattles());
			    					  dataCommAccVeh0.setWinCountBattleSincePreviousDay(dataCommAccVeh0.getWins() - dataCommAccVeh1.getWins());
			    					  listTankPlayedFromLastDay.add(dataCommAccVeh0);
			    				  }
			    				  break;
			    			  }
			    		  }
		    			  
		    		  }
		    		  
		    		  //Trier listTankPlayedFromLastDay selon getBattle_count 
		    		  Collections.sort(listTankPlayedFromLastDay);
		    		  object.setListVehPlayedSincePreviousDay0(listTankPlayedFromLastDay);
		    		  
		    		  if(listTankPlayedFromLastDay.size() > 0)
		    			  return String.valueOf(listTankPlayedFromLastDay.get(0).getName() ) ;
		    		  else
		    			  return "";
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    
		    
		    
		    String strDate =  listDates.get(0);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour1Tank1, "1er Tank Most played-" + strDate);
	
		    jour1Tank1.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour1Tank1,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
	            if (o1 != null) {
			    		String val1 = "";
			    		if (o1.getListVehPlayedDay0() != null && o1.getListVehPlayedDay0().size() > 0)
			    			 val1 =  o1.getListVehPlayedDay0().get(0).getName();
			    		else
			    			val1="";
			    		
			    		String val2 = "";
			    		if (o2.getListVehPlayedDay0() != null && o2.getListVehPlayedDay0().size() > 0)
			    			 val2 =  o2.getListVehPlayedDay0().get(0).getName();
			    		else
			    			val2="";
			    		
			    		return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
		    ////////////////////////////////////////////////////////
		    
	    	///
		    // TANK 1 BATTLE JOUR 1 ///////////////////////
		    TextColumn<CommunityAccount> jour1Tank1Battle = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  if (object.listBattlesTanks.size() >= 2  ) {
		    		  if(object.getListVehPlayedDay0().size() > 0)
		    			  return String.valueOf(object.getListVehPlayedDay0().get(0).getCountBattleSincePreviousDay() ) ;
		    		  else
		    			  return "";
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(0);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour1Tank1Battle, "Nb Battles tank" );
	
		    jour1Tank1Battle.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour1Tank1Battle,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
	            if (o1 != null) {
			    		Integer val1 = 0;
			    		if (o1.getListVehPlayedDay0() != null && o1.getListVehPlayedDay0().size() > 0)
			    			 val1 =  o1.getListVehPlayedDay0().get(0).getCountBattleSincePreviousDay();
			    		else
			    			val1=0;
			    		
			    		Integer val2 = 0;
			    		if (o2.getListVehPlayedDay0() != null && o2.getListVehPlayedDay0().size() > 0)
			    			 val2 =  o2.getListVehPlayedDay0().get(0).getCountBattleSincePreviousDay();
			    		else
			    			val2=0;
			    		
			    		return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
		    ////////////////////////////////////////////////////////
		    
		    
		    /////////////////////////////////////////////////////////////
		    // WR TANK 1 JOUR 1 ///////////////////////
		    TextColumn<CommunityAccount> jour1Tank1Wr = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  //////
		    	  if ( object.getListVehPlayedDay0() != null && object.getListVehPlayedDay0().size() > 0 &&  object.getListVehPlayedDay0().get(0) != null  ) {
		    		  int diff =  object.getListVehPlayedDay0().get(0).getCountBattleSincePreviousDay();
		    		  
		    		  int diffWins =  object.getListVehPlayedDay0().get(0).getWinCountBattleSincePreviousDay();
		    		  Double wrCal = (double) ((double)diffWins/(double)diff);
		    		  //on ne conserve que 2 digits après la virgule 
		    		  wrCal = wrCal * 100; //ex : 51,844444
		    		  int intWrCal = (int) (wrCal * 100); //ex : 5184
		    		  wrCal = (double)intWrCal / 100 ; //ex : 51,84
		    		  String wr = String.valueOf(wrCal);
		    		  
		    		  return   wr ;
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(0);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour1Tank1Wr, "Wr Tank" );
	
		    jour1Tank1Wr.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour1Tank1Wr,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
		            if (o1 != null && o1.getListVehPlayedDay0().size()> 0 && o2.getListVehPlayedDay0().size()> 0) {
		            	/////////////
		            	int diff1 = o1.getListVehPlayedDay0().get(0).getCountBattleSincePreviousDay();
			    		int diffWins1 = o1.getListVehPlayedDay0().get(0).getWinCountBattleSincePreviousDay();
			    		Double wrCal1 = (double) ((double)diffWins1/(double)diff1);
			    		//on ne conserve que 2 digits après la virgule 
			    		wrCal1 = wrCal1 * 100; //ex : 51,844444
			    		int intWrCal1 = (int) (wrCal1 * 100); //ex : 5184
			    		//wrCal1 = (double)intWrCal1 / 100 ; //ex : 51,84
			    		//String wr1 = String.valueOf(wrCal1);
		            	
			    		int diff2 = o2.getListVehPlayedDay0().get(0).getCountBattleSincePreviousDay();
			    		int diffWins2 = o2.getListVehPlayedDay0().get(0).getWinCountBattleSincePreviousDay();
			    		Double wrCal2 = (double) ((double)diffWins2/(double)diff2);
			    		//on ne conserve que 2 digits après la virgule 
			    		wrCal2 = wrCal2 * 100; //ex : 51,844444
			    		int intWrCal2 = (int) (wrCal2 * 100); //ex : 5184
			    		//wrCal2 = (double)intWrCal2 / 100 ; //ex : 51,84
			    		//String wr2 = String.valueOf(wrCal2);
		            	//
		            	Integer val1 = intWrCal1;
		            	Integer val2 = intWrCal2;
		              return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
//		    ////////////////////////////////////////////////////////
		    
		    
		    
	    	///
		    // TANK 2 JOUR 1  ///////////////////////
		    TextColumn<CommunityAccount> jour1Tank2 = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  if (object.listBattlesTanks.size() >= 3  ) {
		    		  
		    		  if(object.getListVehPlayedDay0().size() > 1)
		    			  return String.valueOf(object.getListVehPlayedDay0().get(1).getName() ) ;
		    		  else
		    			  return "";
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(0);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour1Tank2, "2nd Tank Most played-" + strDate);
	
		    jour1Tank2.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour1Tank2,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
	            if (o1 != null) {
			    		String val1 = "";
			    		if (o1.getListVehPlayedDay0().size() > 1)
			    			 val1 =  o1.getListVehPlayedDay0().get(1).getName();
			    		else
			    			val1="";
			    		
			    		String val2 = "";
			    		if (o2.getListVehPlayedDay0().size() > 1)
			    			 val2 =  o2.getListVehPlayedDay0().get(1).getName();
			    		else
			    			val2="";
			    		
			    		return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
		    ////////////////////////////////////////////////////////
		    
	    	///
		    // BATTLE TANK 2 JOUR 1 ///////////////////////
		    TextColumn<CommunityAccount> jour1Tank2Battle = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  if (object.listBattlesTanks.size() >= 3  ) {

		    		  
		    		  if(object.getListVehPlayedDay0().size() > 1)
		    			  return String.valueOf(object.getListVehPlayedDay0().get(1).getCountBattleSincePreviousDay() ) ;
		    		  else
		    			  return "";
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(0);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour1Tank2Battle, "Nb Battles tank" );
	
		    jour1Tank2Battle.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour1Tank2Battle,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
	            if (o1 != null) {
			    		Integer val1 = 0;
			    		if (o1.getListVehPlayedDay0().size() > 1)
			    			 val1 =  o1.getListVehPlayedDay0().get(1).getCountBattleSincePreviousDay();
			    		else
			    			val1=0;
			    		
			    		Integer val2 = 0;
			    		if (o2.getListVehPlayedDay0().size() > 1)
			    			 val2 =  o2.getListVehPlayedDay0().get(1).getCountBattleSincePreviousDay();
			    		else
			    			val2=0;
			    		
			    		return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
		    ////////////////////////////////////////////////////////
		    
		    
		    /////////////////////////////////////////////////////////////
		    // WR TANK 2 JOUR 1 ///////////////////////
		    TextColumn<CommunityAccount> jour1Tank2Wr = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  //////
		    	  if ( object.getListVehPlayedDay0() != null && object.getListVehPlayedDay0().size() > 1 &&  object.getListVehPlayedDay0().get(1) != null  ) {
		    		  int diff =  object.getListVehPlayedDay0().get(1).getCountBattleSincePreviousDay();
		    		  
		    		  int diffWins =  object.getListVehPlayedDay0().get(1).getWinCountBattleSincePreviousDay();
		    		  Double wrCal = (double) ((double)diffWins/(double)diff);
		    		  //on ne conserve que 2 digits après la virgule 
		    		  wrCal = wrCal * 100; //ex : 51,844444
		    		  int intWrCal = (int) (wrCal * 100); //ex : 5184
		    		  wrCal = (double)intWrCal / 100 ; //ex : 51,84
		    		  String wr = String.valueOf(wrCal);
		    		  
		    		  return   wr ;
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(0);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour1Tank2Wr, "Wr Tank" );
	
		    jour1Tank2Wr.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour1Tank2Wr,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
		            if (o1 != null && o1.getListVehPlayedDay0() != null && o1.getListVehPlayedDay0().size()> 1 && o2.getListVehPlayedDay0().size()> 1) {
		            	/////////////
		            	int diff1 = o1.getListVehPlayedDay0().get(1).getCountBattleSincePreviousDay();
			    		int diffWins1 = o1.getListVehPlayedDay0().get(1).getWinCountBattleSincePreviousDay();
			    		Double wrCal1 = (double) ((double)diffWins1/(double)diff1);
			    		//on ne conserve que 2 digits après la virgule 
			    		wrCal1 = wrCal1 * 100; //ex : 51,844444
			    		int intWrCal1 = (int) (wrCal1 * 100); //ex : 5184
			    		//wrCal1 = (double)intWrCal1 / 100 ; //ex : 51,84
			    		//String wr1 = String.valueOf(wrCal1);
		            	
			    		int diff2 = o2.getListVehPlayedDay0().get(1).getCountBattleSincePreviousDay();
			    		int diffWins2 = o2.getListVehPlayedDay0().get(1).getWinCountBattleSincePreviousDay();
			    		Double wrCal2 = (double) ((double)diffWins2/(double)diff2);
			    		//on ne conserve que 2 digits après la virgule 
			    		wrCal2 = wrCal2 * 100; //ex : 51,844444
			    		int intWrCal2 = (int) (wrCal2 * 100); //ex : 5184
			    		//wrCal2 = (double)intWrCal2 / 100 ; //ex : 51,84
			    		//String wr2 = String.valueOf(wrCal2);
		            	//
		            	Integer val1 = intWrCal1;
		            	Integer val2 = intWrCal2;
		              return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
//		    ////////////////////////////////////////////////////////
		    
		    
		    // TANK 3 JOUR 1  ///////////////////////
		    TextColumn<CommunityAccount> jour1Tank3 = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  if (object.listBattlesTanks.size() >= 4  ) {
		    		  
		    		  if(object.getListVehPlayedDay0().size() > 2)
		    			  return String.valueOf(object.getListVehPlayedDay0().get(2).getName() ) ;
		    		  else
		    			  return "";
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(0);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour1Tank3, "Third Tank Most played-" + strDate);
	
		    jour1Tank3.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour1Tank3,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
	            if (o1 != null) {
			    		String val1 = "";
			    		if (o1.getListVehPlayedDay0().size() > 2)
			    			 val1 =  o1.getListVehPlayedDay0().get(2).getName();
			    		else
			    			val1="";
			    		
			    		String val2 = "";
			    		if (o2.getListVehPlayedDay0().size() > 2)
			    			 val2 =  o2.getListVehPlayedDay0().get(2).getName();
			    		else
			    			val2="";
			    		
			    		return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
		    ////////////////////////////////////////////////////////
		    
	    	///
		    // BATTLE TANK 3 JOUR 1  ///////////////////////
		    TextColumn<CommunityAccount> jour1Tank3Battle = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  if (object.listBattlesTanks.size() >= 4  ) {

		    		  
		    		  if(object.getListVehPlayedDay0().size() > 2)
		    			  return String.valueOf(object.getListVehPlayedDay0().get(2).getCountBattleSincePreviousDay() ) ;
		    		  else
		    			  return "";
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(0);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour1Tank3Battle, "Nb Battles tank" );
	
		    jour1Tank2Battle.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour1Tank3Battle,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
	            if (o1 != null) {
			    		Integer val1 = 0;
			    		if (o1.getListVehPlayedDay0().size() > 2)
			    			 val1 =  o1.getListVehPlayedDay0().get(2).getCountBattleSincePreviousDay();
			    		else
			    			val1=0;
			    		
			    		Integer val2 = 0;
			    		if (o2.getListVehPlayedDay0().size() > 2)
			    			 val2 =  o2.getListVehPlayedDay0().get(2).getCountBattleSincePreviousDay();
			    		else
			    			val2=0;
			    		
			    		return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
		    ////////////////////////////////////////////////////////
		    
		    
		    /////////////////////////////////////////////////////////////
		    // WR TANK 3 JOUR 1 ///////////////////////
		    TextColumn<CommunityAccount> jour1Tank3Wr = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  //////
		    	  if ( object.getListVehPlayedDay0().size() > 2 &&  object.getListVehPlayedDay0().get(2) != null  ) {
		    		  int diff =  object.getListVehPlayedDay0().get(2).getCountBattleSincePreviousDay();
		    		  
		    		  int diffWins =  object.getListVehPlayedDay0().get(2).getWinCountBattleSincePreviousDay();
		    		  Double wrCal = (double) ((double)diffWins/(double)diff);
		    		  //on ne conserve que 2 digits après la virgule 
		    		  wrCal = wrCal * 100; //ex : 51,844444
		    		  int intWrCal = (int) (wrCal * 100); //ex : 5184
		    		  wrCal = (double)intWrCal / 100 ; //ex : 51,84
		    		  String wr = String.valueOf(wrCal);
		    		  
		    		  return   wr ;
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(0);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour1Tank3Wr, "Wr Tank" );
	
		    jour1Tank3Wr.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour1Tank3Wr,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
		            if (o1 != null && o1.getListVehPlayedDay0().size()> 2 && o2.getListVehPlayedDay0().size()> 2) {
		            	/////////////
		            	int diff1 = o1.getListVehPlayedDay0().get(2).getCountBattleSincePreviousDay();
			    		int diffWins1 = o1.getListVehPlayedDay0().get(2).getWinCountBattleSincePreviousDay();
			    		Double wrCal1 = (double) ((double)diffWins1/(double)diff1);
			    		//on ne conserve que 2 digits après la virgule 
			    		wrCal1 = wrCal1 * 100; //ex : 51,844444
			    		int intWrCal1 = (int) (wrCal1 * 100); //ex : 5184
			    		//wrCal1 = (double)intWrCal1 / 100 ; //ex : 51,84
			    		//String wr1 = String.valueOf(wrCal1);
		            	
			    		int diff2 = o2.getListVehPlayedDay0().get(2).getCountBattleSincePreviousDay();
			    		int diffWins2 = o2.getListVehPlayedDay0().get(2).getWinCountBattleSincePreviousDay();
			    		Double wrCal2 = (double) ((double)diffWins2/(double)diff2);
			    		//on ne conserve que 2 digits après la virgule 
			    		wrCal2 = wrCal2 * 100; //ex : 51,844444
			    		int intWrCal2 = (int) (wrCal2 * 100); //ex : 5184
			    		//wrCal2 = (double)intWrCal2 / 100 ; //ex : 51,84
			    		//String wr2 = String.valueOf(wrCal2);
		            	//
		            	Integer val1 = intWrCal1;
		            	Integer val2 = intWrCal2;
		              return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//

	    /////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////
	    	///
		    // TANK 1 JOUR 2  ///////////////////////
		    TextColumn<CommunityAccount> jour2Tank1 = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  if (object.listBattlesTanks.size() >= 2  ) {
		    		  List<DataCommunityAccountVehicules> listVehPlayed = new ArrayList<DataCommunityAccountVehicules>();
		    		  
		    		  DataCommunityAccount dataCommAccOfDay0 = object.listBattlesTanks.get(1);
		    		  DataCommunityAccount dataCommAccOfDay1 = object.listBattlesTanks.get(2);
		    		  //bcl sur les stats vehicules du joueur pour le jour en question (1) 
		    		  for (DataCommunityAccountVehicules dataCommAccVeh0 : dataCommAccOfDay0.getVehicles()) {
		    			  //pour chaque vehicule du jour 0, il faut trouver le véhicule correspondant dans  ceux du jour 1 pour éventuellement détecté qu'il a été joué ( + battle)
		    			  //et le mémoriser dans une liste de tanks joués
		    			  //A la fin on ne prendra que ceux qui ont été le + joués) 
		    			  //
		    			  for (DataCommunityAccountVehicules dataCommAccVeh1 : dataCommAccOfDay1.getVehicles()) {
			    			  //char trouvé dans liste
			    			  if(dataCommAccVeh0.getName().equalsIgnoreCase(dataCommAccVeh1.getName())) {
			    				  if (dataCommAccVeh0.getBattle_count() >  dataCommAccVeh1.getBattle_count()) {
			    					  //le char a été joué il faut l'ajouter  à la liste
			    					  dataCommAccVeh0.setCountBattleSincePreviousDay(dataCommAccVeh0.getBattle_count() - dataCommAccVeh1.getBattle_count());
			    					  dataCommAccVeh0.setWinCountBattleSincePreviousDay(dataCommAccVeh0.getWin_count() - dataCommAccVeh1.getWin_count());
			    					  listVehPlayed.add(dataCommAccVeh0);
			    				  }
			    				  break;
			    			  }
			    		  }
		    			  
		    		  }
		    		  
		    		  //Trier listVehPlayed selon getBattle_count 
		    		  Collections.sort(listVehPlayed);
		    		  object.setListVehPlayedSincePreviousDay1(listVehPlayed);
		    		  
		    		  if(listVehPlayed.size() > 0)
		    			  return String.valueOf(listVehPlayed.get(0).getName() ) ;
		    		  else
		    			  return "";
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(1);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour2Tank1, "1er Tank Most played-" + strDate);
	
		    jour2Tank1.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour2Tank1,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
	            if (o1 != null) {
			    		String val1 = "";
			    		if (o1.getListVehPlayedDay1().size() > 0)
			    			 val1 =  o1.getListVehPlayedDay1().get(0).getName();
			    		else
			    			val1="";
			    		
			    		String val2 = "";
			    		if (o2.getListVehPlayedDay1().size() > 0)
			    			 val2 =  o2.getListVehPlayedDay1().get(0).getName();
			    		else
			    			val2="";
			    		
			    		return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
		    ////////////////////////////////////////////////////////
		    
	    	///
		    // TANK 1 BATTLE JOUR 1 ///////////////////////
		    TextColumn<CommunityAccount> jour2Tank1Battle = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  if (object.listBattlesTanks.size() >= 2  ) {
		    		  if(object.getListVehPlayedDay1().size() > 0)
		    			  return String.valueOf(object.getListVehPlayedDay1().get(0).getCountBattleSincePreviousDay() ) ;
		    		  else
		    			  return "";
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(1);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour2Tank1Battle, "Nb Battles tank" );
	
		    jour2Tank1Battle.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour2Tank1Battle,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
	            if (o1 != null) {
			    		Integer val1 = 0;
			    		if (o1.getListVehPlayedDay1().size() > 0)
			    			 val1 =  o1.getListVehPlayedDay1().get(0).getCountBattleSincePreviousDay();
			    		else
			    			val1=0;
			    		
			    		Integer val2 = 0;
			    		if (o2.getListVehPlayedDay1().size() > 0)
			    			 val2 =  o2.getListVehPlayedDay1().get(0).getCountBattleSincePreviousDay();
			    		else
			    			val2=0;
			    		
			    		return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
		    ////////////////////////////////////////////////////////
		    
		    
		    /////////////////////////////////////////////////////////////
		    // WR TANK 1 JOUR 2 ///////////////////////
		    TextColumn<CommunityAccount> jour2Tank1Wr = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  //////
		    	  if ( object.getListVehPlayedDay1().size() > 0 &&  object.getListVehPlayedDay1().get(0) != null  ) {
		    		  int diff =  object.getListVehPlayedDay1().get(0).getCountBattleSincePreviousDay();
		    		  
		    		  int diffWins =  object.getListVehPlayedDay1().get(0).getWinCountBattleSincePreviousDay();
		    		  Double wrCal = (double) ((double)diffWins/(double)diff);
		    		  //on ne conserve que 2 digits après la virgule 
		    		  wrCal = wrCal * 100; //ex : 51,844444
		    		  int intWrCal = (int) (wrCal * 100); //ex : 5184
		    		  wrCal = (double)intWrCal / 100 ; //ex : 51,84
		    		  String wr = String.valueOf(wrCal);
		    		  
		    		  return   wr ;
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(1);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour2Tank1Wr, "Wr Tank" );
	
		    jour2Tank1Wr.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour2Tank1Wr,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
		            if (o1 != null && o1.getListVehPlayedDay1().size()> 0 && o2.getListVehPlayedDay1().size()> 0) {
		            	/////////////
		            	int diff1 = o1.getListVehPlayedDay1().get(0).getCountBattleSincePreviousDay();
			    		int diffWins1 = o1.getListVehPlayedDay1().get(0).getWinCountBattleSincePreviousDay();
			    		Double wrCal1 = (double) ((double)diffWins1/(double)diff1);
			    		//on ne conserve que 2 digits après la virgule 
			    		wrCal1 = wrCal1 * 100; //ex : 51,844444
			    		int intWrCal1 = (int) (wrCal1 * 100); //ex : 5184
			    		//wrCal1 = (double)intWrCal1 / 100 ; //ex : 51,84
			    		//String wr1 = String.valueOf(wrCal1);
		            	
			    		int diff2 = o2.getListVehPlayedDay1().get(0).getCountBattleSincePreviousDay();
			    		int diffWins2 = o2.getListVehPlayedDay1().get(0).getWinCountBattleSincePreviousDay();
			    		Double wrCal2 = (double) ((double)diffWins2/(double)diff2);
			    		//on ne conserve que 2 digits après la virgule 
			    		wrCal2 = wrCal2 * 100; //ex : 51,844444
			    		int intWrCal2 = (int) (wrCal2 * 100); //ex : 5184
			    		//wrCal2 = (double)intWrCal2 / 100 ; //ex : 51,84
			    		//String wr2 = String.valueOf(wrCal2);
		            	//
		            	Integer val1 = intWrCal1;
		            	Integer val2 = intWrCal2;
		              return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
//		    ////////////////////////////////////////////////////////
	    	///
		    // TANK 2 JOUR 2  ///////////////////////
		    TextColumn<CommunityAccount> jour2Tank2 = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  if (object.listBattlesTanks.size() >= 3  ) {
		    		  
		    		  if(object.getListVehPlayedDay1().size() > 1)
		    			  return String.valueOf(object.getListVehPlayedDay1().get(1).getName() ) ;
		    		  else
		    			  return "";
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(1);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour2Tank2, "2nd Tank Most played-" + strDate);
	
		    jour2Tank2.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour2Tank2,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
	            if (o1 != null) {
			    		String val1 = "";
			    		if (o1.getListVehPlayedDay1().size() > 1)
			    			 val1 =  o1.getListVehPlayedDay1().get(1).getName();
			    		else
			    			val1="";
			    		
			    		String val2 = "";
			    		if (o2.getListVehPlayedDay1().size() > 1)
			    			 val2 =  o2.getListVehPlayedDay1().get(1).getName();
			    		else
			    			val2="";
			    		
			    		return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
		    ////////////////////////////////////////////////////////
		    
	    	///
		    // BATTLE TANK 2 JOUR 2 ///////////////////////
		    TextColumn<CommunityAccount> jour2Tank2Battle = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  if (object.listBattlesTanks.size() >= 3  ) {

		    		  
		    		  if(object.getListVehPlayedDay1().size() > 1)
		    			  return String.valueOf(object.getListVehPlayedDay1().get(1).getCountBattleSincePreviousDay() ) ;
		    		  else
		    			  return "";
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(1);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour2Tank2Battle, "Nb Battles tank" );
	
		    jour2Tank2Battle.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour2Tank2Battle,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
	            if (o1 != null) {
			    		Integer val1 = 0;
			    		if (o1.getListVehPlayedDay1().size() > 1)
			    			 val1 =  o1.getListVehPlayedDay1().get(1).getCountBattleSincePreviousDay();
			    		else
			    			val1=0;
			    		
			    		Integer val2 = 0;
			    		if (o2.getListVehPlayedDay1().size() > 1)
			    			 val2 =  o2.getListVehPlayedDay1().get(1).getCountBattleSincePreviousDay();
			    		else
			    			val2=0;
			    		
			    		return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
		    ////////////////////////////////////////////////////////
		    
		    
		    /////////////////////////////////////////////////////////////
		    // WR TANK 2 JOUR 2 ///////////////////////
		    TextColumn<CommunityAccount> jour2Tank2Wr = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  //////
		    	  if ( object.getListVehPlayedDay1().size() > 1 &&  object.getListVehPlayedDay1().get(1) != null  ) {
		    		  int diff =  object.getListVehPlayedDay1().get(1).getCountBattleSincePreviousDay();
		    		  
		    		  int diffWins =  object.getListVehPlayedDay1().get(1).getWinCountBattleSincePreviousDay();
		    		  Double wrCal = (double) ((double)diffWins/(double)diff);
		    		  //on ne conserve que 2 digits après la virgule 
		    		  wrCal = wrCal * 100; //ex : 51,844444
		    		  int intWrCal = (int) (wrCal * 100); //ex : 5184
		    		  wrCal = (double)intWrCal / 100 ; //ex : 51,84
		    		  String wr = String.valueOf(wrCal);
		    		  
		    		  return   wr ;
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(1);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour2Tank2Wr, "Wr Tank" );
	
		    jour2Tank2Wr.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour2Tank2Wr,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
		            if (o1 != null && o1.getListVehPlayedDay1().size()> 1 && o2.getListVehPlayedDay1().size()> 1) {
		            	/////////////
		            	int diff1 = o1.getListVehPlayedDay1().get(1).getCountBattleSincePreviousDay();
			    		int diffWins1 = o1.getListVehPlayedDay1().get(1).getWinCountBattleSincePreviousDay();
			    		Double wrCal1 = (double) ((double)diffWins1/(double)diff1);
			    		//on ne conserve que 2 digits après la virgule 
			    		wrCal1 = wrCal1 * 100; //ex : 51,844444
			    		int intWrCal1 = (int) (wrCal1 * 100); //ex : 5184
			    		//wrCal1 = (double)intWrCal1 / 100 ; //ex : 51,84
			    		//String wr1 = String.valueOf(wrCal1);
		            	
			    		int diff2 = o2.getListVehPlayedDay1().get(1).getCountBattleSincePreviousDay();
			    		int diffWins2 = o2.getListVehPlayedDay1().get(1).getWinCountBattleSincePreviousDay();
			    		Double wrCal2 = (double) ((double)diffWins2/(double)diff2);
			    		//on ne conserve que 2 digits après la virgule 
			    		wrCal2 = wrCal2 * 100; //ex : 51,844444
			    		int intWrCal2 = (int) (wrCal2 * 100); //ex : 5184
			    		//wrCal2 = (double)intWrCal2 / 100 ; //ex : 51,84
			    		//String wr2 = String.valueOf(wrCal2);
		            	//
		            	Integer val1 = intWrCal1;
		            	Integer val2 = intWrCal2;
		              return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
//		    ////////////////////////////////////////////////////////
		    
		    
		    // TANK 3 JOUR 2  ///////////////////////
		    TextColumn<CommunityAccount> jour2Tank3 = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  if (object.listBattlesTanks.size() >= 4  ) {
		    		  
		    		  if(object.getListVehPlayedDay1().size() > 2)
		    			  return String.valueOf(object.getListVehPlayedDay1().get(2).getName() ) ;
		    		  else
		    			  return "";
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(1);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour2Tank3, "Third Tank Most played-" + strDate);
	
		    jour2Tank3.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour2Tank3,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
	            if (o1 != null) {
			    		String val1 = "";
			    		if (o1.getListVehPlayedDay1().size() > 2)
			    			 val1 =  o1.getListVehPlayedDay1().get(2).getName();
			    		else
			    			val1="";
			    		
			    		String val2 = "";
			    		if (o2.getListVehPlayedDay1().size() > 2)
			    			 val2 =  o2.getListVehPlayedDay1().get(2).getName();
			    		else
			    			val2="";
			    		
			    		return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
		    ////////////////////////////////////////////////////////
		    
	    	///
		    // BATTLE TANK 3 JOUR 2  ///////////////////////
		    TextColumn<CommunityAccount> jour2Tank3Battle = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  if (object.listBattlesTanks.size() >= 4  ) {

		    		  
		    		  if(object.getListVehPlayedDay1().size() > 2)
		    			  return String.valueOf(object.getListVehPlayedDay1().get(2).getCountBattleSincePreviousDay() ) ;
		    		  else
		    			  return "";
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(1);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour2Tank3Battle, "Nb Battles tank" );
	
		    jour2Tank3Battle.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour2Tank3Battle,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
	            if (o1 != null) {
			    		Integer val1 = 0;
			    		if (o1.getListVehPlayedDay1().size() > 2)
			    			 val1 =  o1.getListVehPlayedDay1().get(2).getCountBattleSincePreviousDay();
			    		else
			    			val1=0;
			    		
			    		Integer val2 = 0;
			    		if (o2.getListVehPlayedDay1().size() > 2)
			    			 val2 =  o2.getListVehPlayedDay1().get(2).getCountBattleSincePreviousDay();
			    		else
			    			val2=0;
			    		
			    		return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//
		    ////////////////////////////////////////////////////////
		    
		    
		    /////////////////////////////////////////////////////////////
		    // WR TANK 3 JOUR 2 ///////////////////////
		    TextColumn<CommunityAccount> jour2Tank3Wr = new TextColumn<CommunityAccount>() {
		      @Override
		      public String getValue(CommunityAccount object) {
		    	  //////
		    	  if ( object.getListVehPlayedDay1().size() > 2 &&  object.getListVehPlayedDay1().get(2) != null  ) {
		    		  int diff =  object.getListVehPlayedDay1().get(2).getCountBattleSincePreviousDay();
		    		  
		    		  int diffWins =  object.getListVehPlayedDay1().get(2).getWinCountBattleSincePreviousDay();
		    		  Double wrCal = (double) ((double)diffWins/(double)diff);
		    		  //on ne conserve que 2 digits après la virgule 
		    		  wrCal = wrCal * 100; //ex : 51,844444
		    		  int intWrCal = (int) (wrCal * 100); //ex : 5184
		    		  wrCal = (double)intWrCal / 100 ; //ex : 51,84
		    		  String wr = String.valueOf(wrCal);
		    		  
		    		  return   wr ;
		    	  }
		    	  else
		    		  return "";
		      }
		    };
		    strDate =  listDates.get(1);
		    tableHistorizedStatsTanksCommAcc.addColumn(jour2Tank3Wr, "Wr Tank" );
	
		    jour2Tank3Wr.setSortable(true);
		    
		 // Add a ColumnSortEvent.ListHandler to connect sorting to the
		    columnSortHandler.setComparator(jour2Tank3Wr,
		        new Comparator<CommunityAccount>() {
		          public int compare(CommunityAccount o1, CommunityAccount o2) {
		            if (o1 == o2) {
		              return 0;
		            }
	
		            // Compare the name columns.
		            if (o1 != null && o1.getListVehPlayedDay1().size()> 2 && o2.getListVehPlayedDay1().size()> 2) {
		            	/////////////
		            	int diff1 = o1.getListVehPlayedDay1().get(2).getCountBattleSincePreviousDay();
			    		int diffWins1 = o1.getListVehPlayedDay1().get(2).getWinCountBattleSincePreviousDay();
			    		Double wrCal1 = (double) ((double)diffWins1/(double)diff1);
			    		//on ne conserve que 2 digits après la virgule 
			    		wrCal1 = wrCal1 * 100; //ex : 51,844444
			    		int intWrCal1 = (int) (wrCal1 * 100); //ex : 5184
			    		//wrCal1 = (double)intWrCal1 / 100 ; //ex : 51,84
			    		//String wr1 = String.valueOf(wrCal1);
		            	
			    		int diff2 = o2.getListVehPlayedDay1().get(2).getCountBattleSincePreviousDay();
			    		int diffWins2 = o2.getListVehPlayedDay1().get(2).getWinCountBattleSincePreviousDay();
			    		Double wrCal2 = (double) ((double)diffWins2/(double)diff2);
			    		//on ne conserve que 2 digits après la virgule 
			    		wrCal2 = wrCal2 * 100; //ex : 51,844444
			    		int intWrCal2 = (int) (wrCal2 * 100); //ex : 5184
			    		//wrCal2 = (double)intWrCal2 / 100 ; //ex : 51,84
			    		//String wr2 = String.valueOf(wrCal2);
		            	//
		            	Integer val1 = intWrCal1;
		            	Integer val2 = intWrCal2;
		              return (o2 != null) ? val1.compareTo(val2) : 1;
		            }else
		            	return -1;
		          }
		        });
	    	//

	    
		    //////////////////////////////////////////////////////////////////
		    // Add a selection model to handle user selection.
		    final SingleSelectionModel<CommunityAccount> selectionModel = new SingleSelectionModel<CommunityAccount>();
		    tableHistorizedStatsTanksCommAcc.setSelectionModel(selectionModel);
		    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		      public void onSelectionChange(SelectionChangeEvent event) {
		    	  CommunityAccount selected = selectionModel.getSelectedObject();
		        if (selected != null) {
		          //Window.alert("You selected: " + selected.getName());
		        }
		      }
		    });
	
		    // Set the total row count. This isn't strictly necessary, but it affects
		    // paging calculations, so its good habit to keep the row count up to date.
		    tableHistorizedStatsTanksCommAcc.setRowCount(listCommAcc.size(), true); //no need to do here because we have add list to data provider
	
		    // Push the data into the widget.
		    tableHistorizedStatsTanksCommAcc.setRowData(0, listCommAcc);            //idem no nedd dataprovider
		    
		 // Connect the table to the data provider.
		    dataHistorizedStatsTanksProvider.addDataDisplay(tableHistorizedStatsTanksCommAcc);
		    dataHistorizedStatsTanksProvider.refresh();
	   }


			

			
			///////
			
//			static public SafeHtmlBuilder buildHtml(HashMap<String, XmlListAchievement> hashMapAch, String nameAch, CommunityAccount object) {
//				//String nameAch = "Beasthunter";
//				XmlListAchievement ach = hashMapAch.get(nameAch+".png");
//				String urlImgSrc2 =  ach.getSRCIMG().getSRC().get(0).getVALUE();
//				SafeHtmlBuilder sb = new SafeHtmlBuilder();
//				
//				String nb= String.valueOf(object.getData().getAchievements().getBeasthunter());
//				sb.appendEscaped(
//						"<div id=\"achievement\" >" + " <div class=\"floatleft\"> " +
//						" <img alt=\"" + nameAch+ ".png\" src=\"" + urlImgSrc2 + "\" width=\"67\" height=\"71\" />" + nb + "</div>");
//				
//				return sb;
//			}
			
			static public String buildHtmlHeader(HashMap<String, XmlListAchievement> hashMapAch, String nameAch) {
				//String nameAch = "Beasthunter";
				XmlListAchievement ach = hashMapAch.get(nameAch+".png");
			    String title2 = ach.getDESCRIPTION().getVALUE();
				SafeHtmlBuilder sb = new SafeHtmlBuilder();
				String html = "<div id=\"achievement\" >" + " <div class=\"floatleft\"> " +	  title2 + "</div>";
				return html;
			}
			
			static public String buildImgAch(HashMap<String, XmlListAchievement> hashMapAch, String nameAch, CommunityAccount object, int val) {
				//String nameAch = "Beasthunter";
				XmlListAchievement ach = hashMapAch.get(nameAch+".png");
				String urlImgSrc2 = "";
				if (ach.getSRCIMG().getSRC().size() == 1 ) {
					urlImgSrc2 =  ach.getSRCIMG().getSRC().get(0).getVALUE();
				} else {
					//urlImgSrc2
					for (XmlSrc xmlSrc : ach.getSRCIMG().getSRC()) {
						if (xmlSrc.getVALUE().contains(nameAch+".png")) {
							urlImgSrc2 = xmlSrc.getVALUE();
							break ;
						}
					}
				}
				return urlImgSrc2;
			}
			
			static public String getNameAch(HashMap<String, XmlListAchievement> hashMapAch, String nameAch) {
				//String nameAch = "Beasthunter";
				XmlListAchievement ach = hashMapAch.get(nameAch+".png");
				if(ach != null)
					return ach.getNAME();
				else 
					return null;
			}
			
			static void buildPopup(String nameAch, final HashMap<String, XmlListAchievement> hashMapAch) {
		    	//String nameAch = "Defender";
				String html = buildHtmlHeader(hashMapAch, nameAch);		    	    
				// Create the popup dialog box in case of error
				final DialogBox dialogBox = new DialogBox();
				dialogBox.setText("Achievement Description");
				dialogBox.setAnimationEnabled(true);
				Button closeButton = new Button("Close");
				// We can set the id of a widget by accessing its Element
				closeButton.getElement().setId("closeButtonAch");
				VerticalPanel dialogVPanel = new VerticalPanel();
				
				dialogVPanel.addStyleName("dialogVPanel");
				dialogVPanel.add(new HTML(html));
				dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
				dialogVPanel.add(closeButton);
				dialogBox.setWidget(dialogVPanel);
				//dialogBox.showRelativeTo(tableStatsCommAcc);
				dialogBox.center();
				
				closeButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						dialogBox.hide();
//							searchClanButton.setEnabled(true);
//							searchClanButton.setFocus(true);
					}
				});
			
			}


	
			
//		private SimpleLayoutPanel getSimpleLayoutPanel() {
//	             if (layoutPanel == null) {
//	                     layoutPanel = new SimpleLayoutPanel();
//	             }
//	             return layoutPanel;
//	     }

//	     private Widget getPieChart() {
//	             if (pieChart == null) {
//	                     pieChart = new PieChart();
//	             }
//	             return pieChart;
//	     }

//	     private void drawPieChart() {
//	             // Prepare the data
//	             DataTable dataTable = DataTable.create();
//	             dataTable.addColumn(ColumnType.STRING, "Name");
//	             dataTable.addColumn(ColumnType.NUMBER, "Donuts eaten");
//	             dataTable.addRows(4);
//	             dataTable.setValue(0, 0, "Michael");
//	             dataTable.setValue(1, 0, "Elisa");
//	             dataTable.setValue(2, 0, "Robert");
//	             dataTable.setValue(3, 0, "John");
//	             dataTable.setValue(0, 1, 5);
//	             dataTable.setValue(1, 1, 7);
//	             dataTable.setValue(2, 1, 3);
//	             dataTable.setValue(3, 1, 2);
//
//	             // Draw the chart
//	             pieChart.draw(dataTable);
//	     }		
			
/////////
			/////
			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void getMembersClan() {
				// First, we validate the input.
				hPanelLoading.setVisible(true);
			    
				errorLabel.setText("");
				String textToServer = idClan;
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					
					/////
					dialogBox
					.setText("Select a Clan before!!");
					serverResponseLabel
							.addStyleName("serverResponseLabelError");
					serverResponseLabel.setHTML("Click on a clan before find members !"  );
					dialogBox.center();
					closeButton.setFocus(true);
					hPanelLoading.setVisible(false);
					return;
				}

				// Then, we send the input to the server.
				//searchClanButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				wotService.getAllMembersClan(textToServer,
						new AsyncCallback<CommunityClan>() {
							public void onFailure(Throwable caught) {
								hPanelLoading.setVisible(false);
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
								
							}

							public void onSuccess(CommunityClan listAccount) {
								hPanelLoading.setVisible(false);
								//dropBoxClanUsers
								dropBoxClanUsers.clear();
								List<String> listAccName = new ArrayList<String>();
								hmAccNameAccId =new HashMap<String, String >();
								hmAccIdAccName =new HashMap<String, String >();
								hmAccUpperNameAccName =new HashMap<String, String >();
								
								for (DataCommunityClanMembers dataCom :  listAccount.getData().getMembers()) {
									for (DataCommunityMembers dataComMembers : dataCom.getMembers()) {
										listAccName.add(dataComMembers.getAccount_name().toUpperCase());
										//hashmap nom en majuscule / nom origine
										hmAccUpperNameAccName.put(dataComMembers.getAccount_name().toUpperCase(), dataComMembers.getAccount_name());
										
										hmAccNameAccId.put(dataComMembers.getAccount_name(), dataComMembers.getAccount_id());
										hmAccIdAccName.put(dataComMembers.getAccount_id(), dataComMembers.getAccount_name());
										//dropBoxClanUsers.addItem(dataCom.getAccount_name());
									}
								}
								//sort the list 
								Collections.sort(listAccName);
								
								//add to the list 
								for (String accName : listAccName) {
									//list box contain  name of user and id of user
									String originalName = hmAccUpperNameAccName.get(accName);
									dropBoxClanUsers.addItem(originalName, hmAccNameAccId.get(originalName));
								}
								dropBoxClanUsers.setFocus(true);
								statsMembersButton.setEnabled(true);
								
								findHistorizedStatsTanksButton.setEnabled(true);
							}
						});
			}	     
	     
/////
	     
	
			
			
}
