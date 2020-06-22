package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import card.Card;
import card.Summer;

public class ViticultureGUI extends JPanel{
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Thread.currentThread().setPriority(Thread.MAX_PRIORITY);		
				new ViticultureGUI();
			}
		});	
	}
	
	//Initialize private variables for the game
	private enum Season {WAKEUP,SUMMER,SPRING,FALL,WINTER,END_OF_YEAR};
	private Season currentSeason = Season.WAKEUP;
	
	//Initialize constants for GUI
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 800;
	
	private static final int PANEL_BOARD_HEIGHT = 100;
	private static final int LEFT_MARGIN = 200;
	private static final int CARD_WIDTH = 100;
	private static final int CARD_HEIGHT = 120;
	private static final int CARD_PADDING = 10;
	private static final int PLAYER_CARD_V_OFFSET = 500;
	//Initialize buttons
	private JButton button = new JButton("sum text");
	private JButton button2 = new JButton(new ImageIcon("images/board/images/board__03.gif"));
	public ViticultureGUI() {
		setupWidgets();
		attachListeners();
		setupMainPanel();
		setupFrame();
	}
	
	private void setupWidgets() {
		button.setVisible(true);
	}
	
	private void setupMainPanel() {
		//setLayout(null);
		add(button);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		button2.setBorder(emptyBorder);
		
		//button2.setLayout(null);
		add(button2);
		button2.setBounds(259,194, 200,100);
		setBackground(Color.BLUE);
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
		setMinimumSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
		setBounds(new java.awt.Rectangle(WINDOW_WIDTH,WINDOW_HEIGHT));
	}
	
	private void drawBoard(Graphics g, int x, int y) {
		String path = "images/board/images/";
		String file1 = path += "board__01.gif";
		g.drawImage(ImageLoader.getImage(file1), x,y,this);
		String file2 = "images/board/images/board__02.gif";
		g.drawImage(ImageLoader.getImage(file2), x,194,this);
		//JButton button1 = new JButton(new ImageIcon("images/board/images/board__02.gif"));
		JButton button1 = new JButton();
		//add(button1,259,194);
	}

	private void setupFrame() {
		JFrame frame = new JFrame("Viticulture");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void attachListeners() {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("You went and hit that button!");
				repaint();
			}
			
		});
		
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Raise da roof!");
				repaint();
			}
			
		});
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		//game = new ViticultureModel();
		Summer c = new Summer(2);
		boolean game = true;
		if(game != false) {
			ArrayList<Card> hand = new ArrayList<>();
			hand.add(c);
			hand.add(c);
			hand.add(c);
			hand.add(c);
			drawCards(g,hand,PLAYER_CARD_V_OFFSET);
			drawBoard(g,0,0);
		}
	}
	
	private void drawCards(Graphics g, ArrayList<Card> hand, int height) {
		int XPos = LEFT_MARGIN;
		for(int i = 0; i < hand.size(); i++) {
			Card c = hand.get(i);
			drawCard(g,c,XPos,height);
			XPos += CARD_WIDTH + CARD_PADDING;
		}
	}
	
	private void drawCard(Graphics g, Card c, int x, int y) {
		//if facedown do we want this case?
		String cardName = getImageFileName(c);
		g.drawImage(ImageLoader.getImage(cardName),x,y,this);
	}
	
	private static String getImageFileName(Card c) {
		String retValue;
		return "images/" + c.getName() + ".png";
	}
}
