import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Driver extends JFrame implements ActionListener{
	private int grapeDeck_numCards; //grapes
	private int grapeDisc = 0;
	private int sumDeck_numCards; //summer visitors
	private int sumDisc = 0;
	private int ordersDeck_numCards; //wine order cards
	private int ordersDisc = 0;
	private int wintDeck_numCards;//winter visitors
	private int wintDisc = 0;
	private String b1_title = "Board Front";
	private String b2_title = "Board Back";
	private String[] roundPhases = {"Create Players", "Pick Turn Order", "Summer", "Winter", "End of Year"};
	private int currentPhase = 0;
	private int currentPlayer = 0;
	//private String currentPhaseTitle = "";
	//private boolean game = true;
	
	private Deck grapeDeck;
	private Deck summerDeck;
	private Deck winterDeck;
	private Deck orderDeck;
	//private Scanner sc = new Scanner(System.in);
	
	private Card_Mama mama1;
	private Card_Mama mama2;
	private Card_Papa papa1;
	private Card_Papa papa2;
	private Player player1;
	private Player player2;
	private Player[] playerArray;
	
	//GUI
	JLabel lbl_playerInfo;
	
	
	public Driver() {
		//String progress;
		grapeDeck = new Deck("grape");
		System.out.println(grapeDeck.getCards());
		grapeDeck.shuffle();
		
		summerDeck = new Deck("summer");
		System.out.println(summerDeck.getCards());
		summerDeck.shuffle();
		
		winterDeck = new Deck("winter");
		System.out.println(winterDeck.getCards());
		winterDeck.shuffle();
		
		orderDeck = new Deck("orders");
		System.out.println(orderDeck.getCards());
		orderDeck.shuffle();
		
		playerArray = new Player[2];
		
		mama1 = new Card_Mama();
		papa1 = new Card_Papa();
		player1 = new Player(1, mama1, papa1);
		playerArray[0] = player1;
		
		mama2 = new Card_Mama();
		papa2 = new Card_Papa();
		player2 = new Player(2, mama2, papa2);
		playerArray[1] = player2;
		
		//init UI vars
		grapeDeck_numCards = grapeDeck.getCards().size();
		sumDeck_numCards = summerDeck.getCards().size();
		wintDeck_numCards = winterDeck.getCards().size();
		ordersDeck_numCards = orderDeck.getCards().size();
		
		try {
			initUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			/*
			for(int i = 0; i < roundPhases.length; i++) {
				switch(i) {
				case 0:
					System.out.println(roundPhases[i]);
					progress = sc.next();
					break;
				case 1:
					System.out.println(roundPhases[i]);
					progress = sc.next();
					break;
				case 2:
					System.out.println(roundPhases[i]);
					progress = sc.next();
					break;
				case 3:
					System.out.println(roundPhases[i]);
					progress = sc.next();
					break;
				case 4:
					System.out.println(roundPhases[i]);
					progress = sc.next();
					break;
				default:
					break;
				}
			}*/
		
		
	}
	
  private void initUI() throws IOException {
	  	
        setSize(800, 547);
        setTitle("Viticulture Main Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //BufferedImage myImage = ImageIO.read(new File("images/board_front.png"));
        //JFrame myJFrame = new JFrame("Image pane");
        //ImagePanel imagePanel = new ImagePanel(myImage);
       
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        Panel boardFront = initUI_Front();
        Panel boardBack = initUI_Back();
        
        tabbedPane.addTab("Main Board", boardFront);
        tabbedPane.addTab("My Field", boardBack);
        add(tabbedPane);  
        
    }    

  private Panel initUI_Front() {
	  Panel boardFront = new Panel(new GridLayout(3,1));
	//Deck Panel
      Panel deckPanel = new Panel(new GridLayout(2,4));
      
      JButton btn_grapeDeck = new JButton("Grapes Deck: " + grapeDeck_numCards);
      btn_grapeDeck.setBackground(Color.decode("#40B32F"));
      deckPanel.add(btn_grapeDeck);
      // Construct an anonymous instance of an anonymous inner class.
      // The source Button adds the anonymous instance as ActionEvent listener
      btn_grapeDeck.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            grapeDeck_numCards--;
            System.out.println("Implement grapeDeck Draw activity");
            playerArray[currentPlayer].draw(grapeDeck);
            lbl_playerInfo.setText(playerArray[currentPlayer].toString());
            btn_grapeDeck.setText("Grapes Deck: " + grapeDeck.getCards().size());
         }
      });
      //btn_grapeDeck.setPreferredSize(new Dimension(100, 40));
      
      JButton btn_sumDeck = new JButton("Summer Visitor Deck: " + sumDeck_numCards);
      btn_sumDeck.setBackground(Color.decode("#E5C120"));
      deckPanel.add(btn_sumDeck);
      btn_sumDeck.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
             sumDeck_numCards--;
             System.out.println("Implement sumDeck Draw activity");
             playerArray[currentPlayer].draw(summerDeck);
             lbl_playerInfo.setText(playerArray[currentPlayer].toString());
             btn_sumDeck.setText("Summer Visitor Deck: " + sumDeck_numCards);
          }
       });
      
      JButton btn_ordersDeck = new JButton("Wine Orders Deck: " + ordersDeck_numCards);
      btn_ordersDeck.setBackground(Color.decode("#983E8C"));
      deckPanel.add(btn_ordersDeck);
      btn_ordersDeck.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
             ordersDeck_numCards--;
             System.out.println("Implement ordersDeck Draw activity");
             playerArray[currentPlayer].draw(orderDeck);
             lbl_playerInfo.setText(playerArray[currentPlayer].toString());
             btn_ordersDeck.setText("Wine Orders Deck: " + ordersDeck_numCards);
          }
       });
      
      JButton btn_wintDeck = new JButton("Winter Visitor Deck: " + wintDeck_numCards);
      btn_wintDeck.setBackground(Color.decode("#295F9E"));
      deckPanel.add(btn_wintDeck);
      btn_wintDeck.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
             wintDeck_numCards--;
             System.out.println("Implement wintDeck Draw activity");
             playerArray[currentPlayer].draw(winterDeck);
             lbl_playerInfo.setText(playerArray[currentPlayer].toString());
             btn_wintDeck.setText("Winter Visitors Deck: " + wintDeck_numCards);
          }
       });
     
      
      JButton btn_grapeDisc = new JButton("Grapes Discard: " + grapeDisc);
      btn_grapeDisc.setBackground(Color.decode("#93C68B"));
      deckPanel.add(btn_grapeDisc);
      
      JButton btn_SumDisc = new JButton("Summer Visitor Discard: " + sumDisc);
      btn_SumDisc.setBackground(Color.decode("#E0D7AE"));
      deckPanel.add(btn_SumDisc);
      
      JButton btn_ordersDisc = new JButton("Wine Orders Discard: " + ordersDisc);
      btn_ordersDisc.setBackground(Color.decode("#A5789F"));
      deckPanel.add(btn_ordersDisc);
      
      JButton btn_wintDisc = new JButton("Winter Visitor Discard: " + wintDisc);
      btn_wintDisc.setBackground(Color.decode("#6A8AAF"));
      deckPanel.add(btn_wintDisc);
      
      //Image newImage = yourImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
      // ImageIcon img1 = new ImageIcon("images/board_activity.jpg");
      
      //JLabel label1 = new JLabel(img1);
     // deckPanel.add(label1);
      
      //Activity Panels
      Panel activityPanel = new Panel(new GridLayout(1,2));

      
      Panel activityPanel_sum = new Panel(new GridLayout(3,2));
      activityPanel.add(activityPanel_sum);
      
      Panel activityPanel_win = new Panel(new GridLayout(3,2));
      activityPanel.add(activityPanel_win);
      
      //summer Draw
      Panel act_sum1 = new Panel(new GridLayout(2,1));
      activityPanel_sum.add(act_sum1);
      
      Panel act_sum1_spots = new Panel(new GridLayout(1,3));
      act_sum1.add(act_sum1_spots);
      
      //Summer Play
      Panel act_sum2 = new Panel(new GridLayout(2,1));
      activityPanel_sum.add(act_sum2);
      
      Panel act_sum2_spots = new Panel(new GridLayout(1,3));
      act_sum2.add(act_sum2_spots);
      
      //Summer Market
      Panel act_sum3 = new Panel(new GridLayout(2,1));
      activityPanel_sum.add(act_sum3);
      
      Panel act_sum3_spots = new Panel(new GridLayout(1,3));
      act_sum3.add(act_sum3_spots);
      
      //Summer Tour
      Panel act_sum4 = new Panel(new GridLayout(2,1));
      activityPanel_sum.add(act_sum4);
      
      Panel act_sum4_spots = new Panel(new GridLayout(1,3));
      act_sum4.add(act_sum4_spots);
      
      //Summer Plant
      Panel act_sum5 = new Panel(new GridLayout(2,1));
      activityPanel_sum.add(act_sum5);
      
      Panel act_sum5_spots = new Panel(new GridLayout(1,3));
      act_sum5.add(act_sum5_spots);
      
      //Summer Build
      Panel act_sum6 = new Panel(new GridLayout(2,1));
      activityPanel_sum.add(act_sum6);
      
      Panel act_sum6_spots = new Panel(new GridLayout(1,3));
      act_sum6.add(act_sum6_spots);
      
      //winter Draw
      Panel act_win1 = new Panel(new GridLayout(2,1));
      activityPanel_win.add(act_win1);
      
      Panel act_win1_spots = new Panel(new GridLayout(1,3));
      act_win1.add(act_win1_spots);
      
      //Winter Play
      Panel act_win2 = new Panel(new GridLayout(2,1));
      activityPanel_win.add(act_win2);
      
      Panel act_win2_spots = new Panel(new GridLayout(1,3));
      act_win2.add(act_win2_spots);
      
      //Winter Harvest
      Panel act_win3 = new Panel(new GridLayout(2,1));
      activityPanel_win.add(act_win3);
      
      Panel act_win3_spots = new Panel(new GridLayout(1,3));
      act_win3.add(act_win3_spots);
      
      //Winter Make Wine
      Panel act_win4 = new Panel(new GridLayout(2,1));
      activityPanel_win.add(act_win4);
      
      Panel act_win4_spots = new Panel(new GridLayout(1,3));
      act_win4.add(act_win4_spots);
      
      //Winter Train Worker
      Panel act_win5 = new Panel(new GridLayout(2,1));
      activityPanel_win.add(act_win5);
      
      Panel act_win5_spots = new Panel(new GridLayout(1,3));
      act_win5.add(act_win5_spots);
      
      //Winter Fill Order
      Panel act_win6 = new Panel(new GridLayout(2,1));
      activityPanel_win.add(act_win6);
      
      Panel act_win6_spots = new Panel(new GridLayout(1,3));
      act_win6.add(act_win6_spots);
     
      //Activity Buttons//
      //Summer draw
      JButton act_sum1_img = new JButton("Draw summer");
      act_sum1_img.setBackground(Color.decode("#E5C120"));
      act_sum1.add(act_sum1_img);
      
      JButton btn_sum1_spot1 = new JButton("Extra Card");
      btn_sum1_spot1.setBackground(Color.decode("#E5C120"));
      act_sum1_spots.add(btn_sum1_spot1);
      
      JButton btn_sum1_spot2 = new JButton("2");
      btn_sum1_spot2.setBackground(Color.decode("#E5C120"));
      act_sum1_spots.add(btn_sum1_spot2);
      
      JButton btn_sum1_spot3 = new JButton("3");
      btn_sum1_spot3.setBackground(Color.decode("#E5C120"));
      act_sum1_spots.add(btn_sum1_spot3);
      
 
      //Summer Play
      JButton act_sum2_img = new JButton("Play summer");
      act_sum2_img.setBackground(Color.decode("#E5C120"));
      act_sum2.add(act_sum2_img);
      
      JButton btn_sum2_spot1 = new JButton("Extra Card");
      btn_sum2_spot1.setBackground(Color.decode("#E5C120"));
      act_sum2_spots.add(btn_sum2_spot1);
      
      JButton btn_sum2_spot2 = new JButton("2");
      btn_sum2_spot2.setBackground(Color.decode("#E5C120"));
      act_sum2_spots.add(btn_sum2_spot2);
      
      JButton btn_sum2_spot3 = new JButton("3");
      btn_sum2_spot3.setBackground(Color.decode("#E5C120"));
      act_sum2_spots.add(btn_sum2_spot3);
      
      //Summer Market
      JButton act_sum3_img = new JButton(" Buy/Sell");
      act_sum3_img.setBackground(Color.decode("#E5C120"));
      act_sum3.add(act_sum3_img);
      
      JButton btn_sum3_spot1 = new JButton("Extra Card");
      btn_sum3_spot1.setBackground(Color.decode("#E5C120"));
      act_sum3_spots.add(btn_sum3_spot1);
      
      JButton btn_sum3_spot2 = new JButton("2");
      btn_sum3_spot2.setBackground(Color.decode("#E5C120"));
      act_sum3_spots.add(btn_sum3_spot2);
      
      JButton btn_sum3_spot3 = new JButton("3");
      btn_sum3_spot3.setBackground(Color.decode("#E5C120"));
      act_sum3_spots.add(btn_sum3_spot3);
      
      //Summer Tour
      JButton act_sum4_img = new JButton("Give Tour");
      act_sum4_img.setBackground(Color.decode("#E5C120"));
      act_sum4.add(act_sum4_img);
      
      JButton btn_sum4_spot1 = new JButton("Extra Card");
      btn_sum4_spot1.setBackground(Color.decode("#E5C120"));
      act_sum4_spots.add(btn_sum4_spot1);
      
      JButton btn_sum4_spot2 = new JButton("2");
      btn_sum4_spot2.setBackground(Color.decode("#E5C120"));
      act_sum4_spots.add(btn_sum4_spot2);
      
      JButton btn_sum4_spot3 = new JButton("3");
      btn_sum4_spot3.setBackground(Color.decode("#E5C120"));
      act_sum4_spots.add(btn_sum4_spot3);
      
      //Summer Plant
      JButton act_sum5_img = new JButton("Plant");
      act_sum5_img.setBackground(Color.decode("#E5C120"));
      act_sum5.add(act_sum5_img);
      
      JButton btn_sum5_spot1 = new JButton("Extra Card");
      btn_sum5_spot1.setBackground(Color.decode("#E5C120"));
      act_sum5_spots.add(btn_sum5_spot1);
      
      JButton btn_sum5_spot2 = new JButton("2");
      btn_sum5_spot2.setBackground(Color.decode("#E5C120"));
      act_sum5_spots.add(btn_sum5_spot2);
      
      JButton btn_sum5_spot3 = new JButton("3");
      btn_sum5_spot3.setBackground(Color.decode("#E5C120"));
      act_sum5_spots.add(btn_sum5_spot3);
      
      //Summer Build
      JButton act_sum6_img = new JButton("Build Structure");
      act_sum6_img.setBackground(Color.decode("#E5C120"));
      act_sum6.add(act_sum6_img);
      
      JButton btn_sum6_spot1 = new JButton("Extra Card");
      btn_sum6_spot1.setBackground(Color.decode("#E5C120"));
      act_sum6_spots.add(btn_sum6_spot1);
      
      JButton btn_sum6_spot2 = new JButton("2");
      btn_sum6_spot2.setBackground(Color.decode("#E5C120"));
      act_sum6_spots.add(btn_sum6_spot2);
      
      JButton btn_sum6_spot3 = new JButton("3");
      btn_sum6_spot3.setBackground(Color.decode("#E5C120"));
      act_sum6_spots.add(btn_sum6_spot3);
      
      //Winter Draw
      JButton act_win1_img = new JButton("Draw Winter");
      act_win1_img.setBackground(Color.decode("#295F9E"));
      act_win1.add(act_win1_img);
      
      JButton btn_win1_spot1 = new JButton("Extra Card");
      btn_win1_spot1.setBackground(Color.decode("#295F9E"));
      act_win1_spots.add(btn_win1_spot1);
      
      JButton btn_win1_spot2 = new JButton("2");
      btn_win1_spot2.setBackground(Color.decode("#295F9E"));
      act_win1_spots.add(btn_win1_spot2);
      
      JButton btn_win1_spot3 = new JButton("3");
      btn_win1_spot3.setBackground(Color.decode("#295F9E"));
      act_win1_spots.add(btn_win1_spot3);
      
      //Winter Play
      JButton act_win2_img = new JButton("Play Winter");
      act_win2_img.setBackground(Color.decode("#295F9E"));
      act_win2.add(act_win2_img);
      
      JButton btn_win2_spot1 = new JButton("Extra Card");
      btn_win2_spot1.setBackground(Color.decode("#295F9E"));
      act_win2_spots.add(btn_win2_spot1);
      
      JButton btn_win2_spot2 = new JButton("2");
      btn_win2_spot2.setBackground(Color.decode("#295F9E"));
      act_win2_spots.add(btn_win2_spot2);
      
      JButton btn_win2_spot3 = new JButton("3");
      btn_win2_spot3.setBackground(Color.decode("#295F9E"));
      act_win2_spots.add(btn_win2_spot3);
      
      //Winter Harvest
      JButton act_win3_img = new JButton("Harvest");
      act_win3_img.setBackground(Color.decode("#295F9E"));
      act_win3.add(act_win3_img);
      
      JButton btn_win3_spot1 = new JButton("Extra Card");
      btn_win3_spot1.setBackground(Color.decode("#295F9E"));
      act_win3_spots.add(btn_win3_spot1);
      
      JButton btn_win3_spot2 = new JButton("2");
      btn_win3_spot2.setBackground(Color.decode("#295F9E"));
      act_win3_spots.add(btn_win3_spot2);
      
      JButton btn_win3_spot3 = new JButton("3");
      btn_win3_spot3.setBackground(Color.decode("#295F9E"));
      act_win3_spots.add(btn_win3_spot3);
      
      //Winter Make Wine
      JButton act_win4_img = new JButton("Make Wine");
      act_win4_img.setBackground(Color.decode("#295F9E"));
      act_win4.add(act_win4_img);
      
      JButton btn_win4_spot1 = new JButton("Extra Card");
      btn_win4_spot1.setBackground(Color.decode("#295F9E"));
      act_win4_spots.add(btn_win4_spot1);
      
      JButton btn_win4_spot2 = new JButton("2");
      btn_win4_spot2.setBackground(Color.decode("#295F9E"));
      act_win4_spots.add(btn_win4_spot2);
      
      JButton btn_win4_spot3 = new JButton("3");
      btn_win4_spot3.setBackground(Color.decode("#295F9E"));
      act_win4_spots.add(btn_win4_spot3);
      
      //Winter Train Worker
      JButton act_win5_img = new JButton("Train");
      act_win5_img.setBackground(Color.decode("#295F9E"));
      act_win5.add(act_win5_img);
      
      JButton btn_win5_spot1 = new JButton("Extra Card");
      btn_win5_spot1.setBackground(Color.decode("#295F9E"));
      act_win5_spots.add(btn_win5_spot1);
      
      JButton btn_win5_spot2 = new JButton("2");
      btn_win5_spot2.setBackground(Color.decode("#295F9E"));
      act_win5_spots.add(btn_win5_spot2);
      
      JButton btn_win5_spot3 = new JButton("3");
      btn_win5_spot3.setBackground(Color.decode("#295F9E"));
      act_win5_spots.add(btn_win5_spot3);
      
      //Winter Fill Order
      JButton act_win6_img = new JButton("Fill Order");
      act_win6_img.setBackground(Color.decode("#295F9E"));
      act_win6.add(act_win6_img);
      
      JButton btn_win6_spot1 = new JButton("Extra Card");
      btn_win6_spot1.setBackground(Color.decode("#295F9E"));
      act_win6_spots.add(btn_win6_spot1);
      
      JButton btn_win6_spot2 = new JButton("2");
      btn_win6_spot2.setBackground(Color.decode("#295F9E"));
      act_win6_spots.add(btn_win6_spot2);
      
      JButton btn_win6_spot3 = new JButton("3");
      btn_win6_spot3.setBackground(Color.decode("#295F9E"));
      act_win6_spots.add(btn_win6_spot3);
      
      //Bottom Panel
      Panel bottomPanel = new Panel(new GridLayout(4,1));
      
      //Player Scores
      JLabel lbl_scores = new JLabel("Player Scores: ");
      bottomPanel.add(lbl_scores);
      
      //Turn Order
      JLabel title_turnOrder = new JLabel("Turn Order");
      bottomPanel.add(title_turnOrder);
      
      Panel orderPanel = new Panel(new GridLayout(1,7));
      bottomPanel.add(orderPanel);
      
      JButton btn_order1 = new JButton("1st");
      orderPanel.add(btn_order1);
      
      JButton btn_order2 = new JButton("2nd");
      orderPanel.add(btn_order2);
      
      JButton btn_order3 = new JButton("3rd");
      orderPanel.add(btn_order3);
      
      JButton btn_order4 = new JButton("4th");
      orderPanel.add(btn_order4);
      
      JButton btn_order5 = new JButton("5th");
      orderPanel.add(btn_order5);
      
      JButton btn_order6 = new JButton("6th");
      orderPanel.add(btn_order6);
      
      JButton btn_order7 = new JButton("7th");
      orderPanel.add(btn_order7);

      
      //Current Player info display
    
      Panel playerInfo = new Panel(new FlowLayout());
      bottomPanel.add(playerInfo);
      
      lbl_playerInfo = new JLabel(playerArray[currentPlayer].toString());
      playerInfo.add(lbl_playerInfo);
     
      boardFront.add(deckPanel, BorderLayout.NORTH);
      boardFront.add(activityPanel, BorderLayout.CENTER);
      boardFront.add(bottomPanel, BorderLayout.SOUTH);
      
      return boardFront;
  }
  
  private Panel initUI_Back() {
	  Panel boardBack = new Panel(new GridLayout(3,1));
	  
	  //Fields
	  Panel fieldPanel = new Panel(new GridLayout(1,3));
	  
	  JButton btn_field1 = new JButton("Field 1");
	  btn_field1.setBackground(Color.decode("#40B32F"));
	  fieldPanel.add(btn_field1);
	  
	  JButton btn_field2 = new JButton("Field 2");
	  btn_field2.setBackground(Color.decode("#40B32F"));
	  fieldPanel.add(btn_field2);
	  
	  JButton btn_field3 = new JButton("Field 3");
	  btn_field3.setBackground(Color.decode("#40B32F"));
	  fieldPanel.add(btn_field3);
	  
	  //Structures
	  Panel structuresPanel = new Panel(new GridLayout(2,3));
	  
	  JButton btn_struct1 = new JButton("Trellis");
	  btn_struct1.setBackground(Color.decode("#AF9F6A"));
	  structuresPanel.add(btn_struct1);
	  
	  JButton btn_struct2 = new JButton("Windmill");
	  btn_struct2.setBackground(Color.decode("#AF9F6A"));
	  structuresPanel.add(btn_struct2);
	  
	  JButton btn_struct3 = new JButton("Cottage");
	  btn_struct3.setBackground(Color.decode("#AF9F6A"));
	  structuresPanel.add(btn_struct3);
	  
	  JButton btn_struct4 = new JButton("Yoke");
	  btn_struct4.setBackground(Color.decode("#AF9F6A"));
	  structuresPanel.add(btn_struct4);
	  
	  JButton btn_struct5 = new JButton("Tasting Room");
	  btn_struct5.setBackground(Color.decode("#AF9F6A"));
	  structuresPanel.add(btn_struct5);
	  
	  JButton btn_struct6 = new JButton("Irrigation");
	  btn_struct6.setBackground(Color.decode("#AF9F6A"));
	  structuresPanel.add(btn_struct6);
	  
	  //Wine
	  Panel winePanel = new Panel(new GridLayout(1,2));
	  
	  //crush grapes panel
	  Panel crushPanel = new Panel(new GridLayout(3,2));
	  winePanel.add(crushPanel);
	  
	  Panel crushWhite1 = new Panel(new GridLayout(1,3));
	  crushPanel.add(crushWhite1);
	  
	  JButton btn_white1 = new JButton("1");
	  btn_white1.setBackground(Color.decode("#F2EEC0"));
	  crushWhite1.add(btn_white1);
	  
	  JButton btn_white2 = new JButton("2");
	  btn_white2.setBackground(Color.decode("#F2EEC0"));
	  crushWhite1.add(btn_white2);
	  
	  JButton btn_white3 = new JButton("3");
	  btn_white3.setBackground(Color.decode("#F2EEC0"));
	  crushWhite1.add(btn_white3);
	  
	  Panel crushRed1 = new Panel(new GridLayout(1,3));
	  crushPanel.add(crushRed1);
	  
	  JButton btn_red1 = new JButton("1");
	  btn_red1.setBackground(Color.decode("#A52F43"));
	  crushRed1.add(btn_red1);
	  
	  JButton btn_red2 = new JButton("2");
	  btn_red2.setBackground(Color.decode("#A52F43"));
	  crushRed1.add(btn_red2);
	  
	  JButton btn_red3 = new JButton("3");
	  btn_red3.setBackground(Color.decode("#A52F43"));
	  crushRed1.add(btn_red3);
	  
	  Panel crushWhite2 = new Panel(new GridLayout(1,3));
	  crushPanel.add(crushWhite2);
	  
	  JButton btn_white4 = new JButton("4");
	  btn_white4.setBackground(Color.decode("#F2EEC0"));
	  crushWhite2.add(btn_white4);
	  
	  JButton btn_white5 = new JButton("5");
	  btn_white5.setBackground(Color.decode("#F2EEC0"));
	  crushWhite2.add(btn_white5);
	  
	  JButton btn_white6 = new JButton("6");
	  btn_white6.setBackground(Color.decode("#F2EEC0"));
	  crushWhite2.add(btn_white6);
	  
	  Panel crushRed2 = new Panel(new GridLayout(1,3));
	  crushPanel.add(crushRed2);
	  
	  JButton btn_red4 = new JButton("4");
	  btn_red4.setBackground(Color.decode("#A52F43"));
	  crushRed2.add(btn_red4);
	  
	  JButton btn_red5 = new JButton("5");
	  btn_red5.setBackground(Color.decode("#A52F43"));
	  crushRed2.add(btn_red5);
	  
	  JButton btn_red6 = new JButton("6");
	  btn_red6.setBackground(Color.decode("#A52F43"));
	  crushRed2.add(btn_red6);
	  
	  Panel crushWhite3 = new Panel(new GridLayout(1,3));
	  crushPanel.add(crushWhite3);
	  
	  JButton btn_white7 = new JButton("7");
	  btn_white7.setBackground(Color.decode("#F2EEC0"));
	  crushWhite3.add(btn_white7);
	  
	  JButton btn_white8 = new JButton("8");
	  btn_white8.setBackground(Color.decode("#F2EEC0"));
	  crushWhite3.add(btn_white8);
	  
	  JButton btn_white9 = new JButton("9");
	  btn_white9.setBackground(Color.decode("#F2EEC0"));
	  crushWhite3.add(btn_white9);
	  
	  Panel crushRed3 = new Panel(new GridLayout(1,3));
	  crushPanel.add(crushRed3);
	  
	  JButton btn_red7 = new JButton("7");
	  btn_red7.setBackground(Color.decode("#A52F43"));
	  crushRed3.add(btn_red7);
	  
	  JButton btn_red8 = new JButton("8");
	  btn_red8.setBackground(Color.decode("#A52F43"));
	  crushRed3.add(btn_red8);
	  
	  JButton btn_red9 = new JButton("9");
	  btn_red9.setBackground(Color.decode("#A52F43"));
	  crushRed3.add(btn_red9);
	  
	  //cellars panel
	  Panel cellarPanel = new Panel(new GridLayout(1,3));
	  winePanel.add(cellarPanel);
	  
	  //Small Cellar
	  Panel smCellarPanel = new Panel(new GridLayout(5,1));
	  cellarPanel.add(smCellarPanel);
	  JLabel smCellarLbl = new JLabel("Small Cellar");
	  smCellarPanel.add(smCellarLbl);
	  
	  Panel smCellarPanel_red = new Panel(new GridLayout(1,3));
	  smCellarPanel.add(smCellarPanel_red);
	  
	  JButton btn_sm_red1 = new JButton("1");
	  btn_sm_red1.setBackground(Color.decode("#A52F43"));
	  smCellarPanel_red.add(btn_sm_red1);
	  
	  JButton btn_sm_red2 = new JButton("2");
	  btn_sm_red2.setBackground(Color.decode("#A52F43"));
	  smCellarPanel_red.add(btn_sm_red2);
	  
	  JButton btn_sm_red3 = new JButton("3");
	  btn_sm_red3.setBackground(Color.decode("#A52F43"));
	  smCellarPanel_red.add(btn_sm_red3);
	  
	  Panel smCellarPanel_white = new Panel(new GridLayout(1,3));
	  smCellarPanel.add(smCellarPanel_white);
	  
	  JButton btn_sm_white1 = new JButton("1");
	  btn_sm_white1.setBackground(Color.decode("#F2EEC0"));
	  smCellarPanel_white.add(btn_sm_white1);
	  
	  JButton btn_sm_white2 = new JButton("2");
	  btn_sm_white2.setBackground(Color.decode("#F2EEC0"));
	  smCellarPanel_white.add(btn_sm_white2);
	  
	  JButton btn_sm_white3 = new JButton("3");
	  btn_sm_white3.setBackground(Color.decode("#F2EEC0"));
	  smCellarPanel_white.add(btn_sm_white3);
	  
	  //Medium Cellar Panel
	  Panel medCellarPanel = new Panel(new GridLayout(5,1));
	  cellarPanel.add(medCellarPanel);
	  
	  JLabel medCellarLbl = new JLabel("Medium Cellar");
	  medCellarPanel.add(medCellarLbl);
	  
	  //Medium Cellar - Red
	  Panel medCellarPanel_red = new Panel(new GridLayout(1,3));
	  medCellarPanel.add(medCellarPanel_red);
	  
	  JButton btn_med_red4 = new JButton("4");
	  btn_med_red4.setBackground(Color.decode("#A52F43"));
	  medCellarPanel_red.add(btn_med_red4);
	  
	  JButton btn_med_red5 = new JButton("5");
	  btn_med_red5.setBackground(Color.decode("#A52F43"));
	  medCellarPanel_red.add(btn_med_red5);
	  
	  JButton btn_med_red6 = new JButton("6");
	  btn_med_red6.setBackground(Color.decode("#A52F43"));
	  medCellarPanel_red.add(btn_med_red6);
	  
	  
	  //Medium Cellar - White
	  Panel medCellarPanel_white = new Panel(new GridLayout(1,3));
	  medCellarPanel.add(medCellarPanel_white);
	  
	  JButton btn_med_white4 = new JButton("4");
	  btn_med_white4.setBackground(Color.decode("#F2EEC0"));
	  medCellarPanel_white.add(btn_med_white4);
	  
	  JButton btn_med_white5 = new JButton("5");
	  btn_med_white5.setBackground(Color.decode("#F2EEC0"));
	  medCellarPanel_white.add(btn_med_white5);
	  
	  JButton btn_med_white6 = new JButton("6");
	  btn_med_white6.setBackground(Color.decode("#F2EEC0"));
	  medCellarPanel_white.add(btn_med_white6);
	  
	  //Medium Cellar Blush
	  Panel medCellarPanel_blush = new Panel(new GridLayout(1,3));
	  medCellarPanel.add(medCellarPanel_blush);
	  
	  JButton btn_med_blush4 = new JButton("4");
	  btn_med_blush4.setBackground(Color.decode("#F6A199"));
	  medCellarPanel_blush.add(btn_med_blush4);
	  
	  JButton btn_med_blush5 = new JButton("5");
	  btn_med_blush5.setBackground(Color.decode("#F6A199"));
	  medCellarPanel_blush.add(btn_med_blush5);
	  
	  JButton btn_med_blush6 = new JButton("6");
	  btn_med_blush6.setBackground(Color.decode("#F6A199"));
	  medCellarPanel_blush.add(btn_med_blush6);
	
	  
	  //Large Cellar
	  Panel lrgCellarPanel = new Panel(new GridLayout(5,3));
	  cellarPanel.add(lrgCellarPanel);
	  JLabel lrgCellarLbl = new JLabel("Large Cellar");
	  lrgCellarPanel.add(lrgCellarLbl);
	  
	  //Large Cellar - Red
	  Panel lrgCellarPanel_red = new Panel(new GridLayout(1,3));
	  lrgCellarPanel.add(lrgCellarPanel_red);
	  
	  JButton btn_cell_red7 = new JButton("7");
	  btn_cell_red7.setBackground(Color.decode("#A52F43"));
	  lrgCellarPanel_red.add(btn_cell_red7);
	  
	  JButton btn_cell_red8 = new JButton("8");
	  btn_cell_red8.setBackground(Color.decode("#A52F43"));
	  lrgCellarPanel_red.add(btn_cell_red8);
	  
	  JButton btn_cell_red9 = new JButton("9");
	  btn_cell_red9.setBackground(Color.decode("#A52F43"));
	  lrgCellarPanel_red.add(btn_cell_red9);
	  
	  //Large Cellar - White
	  Panel lrgCellarPanel_white = new Panel(new GridLayout(1,3));
	  lrgCellarPanel.add(lrgCellarPanel_white);
	  
	  JButton btn_cell_white7 = new JButton("7");
	  btn_cell_white7.setBackground(Color.decode("#F2EEC0"));
	  lrgCellarPanel_white.add(btn_cell_white7);
	  
	  JButton btn_cell_white8 = new JButton("8");
	  btn_cell_white8.setBackground(Color.decode("#F2EEC0"));
	  lrgCellarPanel_white.add(btn_cell_white8);
	  
	  JButton btn_cell_white9 = new JButton("9");
	  btn_cell_white9.setBackground(Color.decode("#F2EEC0"));
	  lrgCellarPanel_white.add(btn_cell_white9);
	  
	  //Large Cellar Blush
	  Panel lrgCellarPanel_blush = new Panel(new GridLayout(1,3));
	  lrgCellarPanel.add(lrgCellarPanel_blush);
	  
	  JButton btn_lrg_blush7 = new JButton("7");
	  btn_lrg_blush7.setBackground(Color.decode("#F6A199"));
	  lrgCellarPanel_blush.add(btn_lrg_blush7);
	  
	  JButton btn_lrg_blush8 = new JButton("8");
	  btn_lrg_blush8.setBackground(Color.decode("#F6A199"));
	  lrgCellarPanel_blush.add(btn_lrg_blush8);
	  
	  JButton btn_lrg_blush9 = new JButton("9");
	  btn_lrg_blush9.setBackground(Color.decode("#F6A199"));
	  lrgCellarPanel_blush.add(btn_lrg_blush9);
	  
	  //Large Cellar Sparkling
	  Panel lrgCellarPanel_sparkling = new Panel(new GridLayout(1,3));
	  lrgCellarPanel.add(lrgCellarPanel_sparkling);
	  
	  JButton btn_lrg_sparkling7 = new JButton("7");
	  btn_lrg_sparkling7.setBackground(Color.decode("#F2F0CD"));
	  lrgCellarPanel_sparkling.add(btn_lrg_sparkling7);
	  
	  JButton btn_lrg_sparkling8 = new JButton("8");
	  btn_lrg_sparkling8.setBackground(Color.decode("#F2F0CD"));
	  lrgCellarPanel_sparkling.add(btn_lrg_sparkling8);

	  JButton btn_lrg_sparkling9 = new JButton("9");
	  btn_lrg_sparkling9.setBackground(Color.decode("#F2F0CD"));
	  lrgCellarPanel_sparkling.add(btn_lrg_sparkling9);
	  
	  boardBack.add(fieldPanel);
	  boardBack.add(structuresPanel);
	  boardBack.add(winePanel);
	  return boardBack;
  }
 
  private Panel initUI_PlayerHand() throws IOException {
	  Panel hand = new Panel(new FlowLayout());
	  BufferedImage myImage = ImageIO.read(new File("images/surveyer.jpg"));
     
      ImagePanel imagePanel = new ImagePanel(myImage);
     
	
	  return hand;
  }
  public static void main(String[] args) {
		
		//Deck summerDeck = new Deck("summer");
		/*
		for(Card c : summerDeck.getCards()) {
			System.out.println(c.toString());
		}
		
		summerDeck.shuffle();
		System.out.println();
		for(Card c : summerDeck.getCards()) {
			System.out.println(c.toString());
		}
		
		Card_Mama mama1 = new Card_Mama();
		Card_Papa papa1 = new Card_Papa();
		
		Player player1 = new Player(1, mama1, papa1);
		System.out.println();
		System.out.println(player1.toString());
		
		System.out.println();
		player1.Draw(summerDeck);
		
		System.out.println(player1.toString());
		System.out.println();
		
		for(Card c : summerDeck.getCards()) {
			System.out.println(c.toString());
		}*/
		
		//create board
		EventQueue.invokeLater(() -> {
            Driver ex = new Driver();
            ex.setVisible(true);
        });
    
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("disable".equals(e.getActionCommand())) {
			System.out.println("button pressed");
        } 
		
	}
}
