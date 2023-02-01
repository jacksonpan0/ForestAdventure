import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.sound.sampled.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import java.awt.Font;
public class GameProject {
	static private class EventsCanvas extends JPanel {
		//Global variables to read key input, store bird and towers, and determine game state
		private KeyRead keyact;
		private ArrayList<Bird> bird;
		private ArrayList<Tower> towers;
		private ArrayList<Endzone> endzone;
		private ArrayList<Enemies> enemies;
		private Color ink;
		private Clip music;
		public boolean gameStart = false;
		public boolean gameOver = false;
		public boolean gameWin = false;
		private int score = 0;
		private Image pic;
		private Image pic2;
		private Image pic3;
		private Image pic4;
		private Image pic5;
		private Image pic6;
		private Image pic7;
		private boolean loaded;
		Font myFont = new Font("SYMBOL", Font.ITALIC, 24);
		//Creation of bird object and towers
		Bird newBird = new Bird(5, 300, ink);
		Tower btowers1 = new Tower(60, 420, 60, 450, ink);
		Tower btowers1c = new Tower(0, 420, 60, 450, ink);
		Tower ttowers1 = new Tower((60), (-200), 60, 450, ink);
		Tower ttowers1c = new Tower((0), (-180), 60, 450, ink);
		Tower btowers2 = new Tower(165, 490, 60, 450, ink);
		Tower btowers2c = new Tower(105, 470, 60, 450, ink);
		Tower ttowers2 = new Tower(165, (-140), 60, 450, ink);
		Tower ttowers2c = new Tower(105, (-130), 60, 450, ink);
		Tower btowers3 = new Tower(270, 425, 60, 450, ink);
		Tower btowers3c = new Tower(210, 420, 60, 450, ink);
		Tower ttowers3 = new Tower(270, (-160), 60, 450, ink);
		Tower ttowers3c = new Tower(210, (-177), 60, 450, ink);
		Tower btowers4 = new Tower(375, 380, 60, 500, ink);
		Tower btowers4c = new Tower(315, 395, 60, 500, ink);
		Tower ttowers4 = new Tower(375, (-180), 60, 450, ink);
		Tower ttowers4c = new Tower(315, (-199), 60, 450, ink);
		Tower btowers5 = new Tower(480, 475, 60, 450, ink);
		Tower btowers5c = new Tower(420, 405, 60, 450, ink);
		Tower ttowers5 = new Tower(480, (-137), 60, 450, ink);
		Tower ttowers5c = new Tower(420, (-127), 60, 450, ink);
		Tower btowers6 = new Tower(585, 400, 60, 450, ink);
		Tower btowers6c = new Tower(525, 475, 60, 450, ink);
		Tower ttowers6 = new Tower(585, (-169), 60, 450, ink);
		Tower ttowers6c = new Tower(525, (-169), 60, 450, ink);
		Tower btowers7 = new Tower(690, 500, 60, 450, ink);
		Tower btowers7c = new Tower(630, 480, 60, 450, ink);
		Tower ttowers7 = new Tower(690, (-100), 60, 450, ink);
		Tower ttowers7c = new Tower(630, (-178), 60, 450, ink);
		Tower btowers8 = new Tower(795, 420, 60, 450, ink);
		Tower btowers8c = new Tower(735, 470, 60, 450, ink);
		Tower ttowers8 = new Tower(795, (-250), 60, 450, ink);
		Tower ttowers8c = new Tower(735, (-275), 60, 450, ink);
		Tower btowers9 = new Tower(900, 400, 60, 450, ink);
		Tower btowers9c = new Tower(840, 400, 60, 450, ink);
		Tower ttowers9 = new Tower(900, (-280), 60, 450, ink);
		Tower ttowers9c = new Tower(840, (-280), 60, 450, ink);
		Enemies enemy1 = new Enemies(147, 320, 40, 40, ink);
		Enemies enemy2 = new Enemies(272, 370, 35, 45, ink);
		Enemies enemy3 = new Enemies(590, 290, 40, 40, ink);
		Enemies enemy4 = new Enemies(705, 360, 35, 45, ink);
		Enemies enemy5 = new Enemies(835, 300, 95, 95, ink);
		Enemies enemy6 = new Enemies(495, 390, 75, 75, ink);
		Endzone endOfGameZone = new Endzone(945, (-100), 155, 1200, ink);

		class KeyRead extends KeyAdapter {
			private int x = 30;
			private int y = 300;
			public int towerWidth = 60;
		
			public void keyPressed(KeyEvent key)
	        {
				int keyCode = key.getKeyCode();
	            if(keyCode == KeyEvent.VK_W) {
	            	newBird.getBound();
	            	newBird.changePosy(y += (-5));
	            	endGame();
	            } 
	            //Reads the down movement
	            if(keyCode == KeyEvent.VK_S) {
	            	newBird.getBound();
	            	newBird.changePosy(y += (5));
	            	endGame();
	            }
	            //Reads the left movement
	            if(keyCode == KeyEvent.VK_A) {
	            	newBird.getBound();
	            	newBird.changePosx(x += (-5));
	            	endGame();
	            }
	            //Reads the right movement
	            if(keyCode == KeyEvent.VK_D) {
	            	newBird.getBound();
	            	newBird.changePosx(x += (5));
	            	if(newBird.getX() > ttowers9.getX()) {
	            		gameWin = true;
	            		gameWon();
	            	}
	            	if (gameStart == true) {
	            		if(newBird.getX() >= towerWidth){
								score+=10;
								towerWidth += 60;
						}
	            	}
	            	endGame();
	            }
	            draw();
	        }
		}

		//Class creation of the bird, has position change, color, and collision methods
		class Bird extends Ellipse2D.Float {
			private Color c;
			private static final float rad = 45;
			private double velX = 0;
			private double velY = 0;
			Bird(float x, float y, Color c){
				setFrame(x, y, rad, rad);
	            this.c = c;
			}

			public void changePos(float x, float y) {
	            this.x = x;
	            this.y = y;
	        }

			public void changePosx(float x) {
				this.x = x;
			}

			public void changePosy(float y) {
				this.y = y;
			}
			

			public Color getColor() {
				return this.c;
			}


			public boolean getBound() {
				for(int i = 0; i <= 35; i++) {
					if(newBird.intersects(towers.get(i))){
						return gameOver = true;	
					}
				}
				for(int j = 0; j <= 4; j++) {
					if(newBird.intersects(enemies.get(j))) {
						gameOver = true;
						enemyEncounter();
					}
				}
				if(newBird.intersects(endOfGameZone)){
					return gameOver = true;
				}

				return gameOver = false;	
			}		
		}
		
		//Class creation of the towers, has color methods
		class Tower extends Rectangle2D.Float {
			private Color tc;
			
			Tower(float x, float y, float width, float height, Color tc){
				setFrame(x, y, width, height);
				this.tc = tc;
			}
			
			public Color getColor() {
				return this.tc;
			}

		}
		
		class Endzone extends Rectangle2D.Float { 
			private Color ec;
			
			Endzone(float x, float y, float width, float height, Color ec){
				setFrame(x, y, width, height);
				this.ec = ec;
			}
			
			public Color getColor() {
				return this.ec;
			}
		}
		
		class Enemies extends Rectangle2D.Float{
			private Color esc;			
			
			Enemies(float x, float y, float width, float height, Color esc){
				setFrame(x, y, width, height);
				this.esc = esc;
			}
		}
		

		
		//Paint component to paint objects
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;
			super.paintComponent(g2d);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			for (Bird c:bird) {
				//g2d.setColor(new Color(103,71,54));
				g2d.setColor(Color.PINK);
                g2d.fill(c);
            }
			for (Tower tc:towers) {
				g2d.setColor(new Color(21,71,52));
				g2d.fill(tc);
			}
			for (Endzone ec:endzone) {
				g2d.setColor(new Color(156,175,136));
				g2d.fill(ec);
			}
			String s = Integer.toString(score);
			g2d.setColor(Color.WHITE);
			g2d.setFont(myFont);
			g2d.drawString(s,590,31);
			for (Enemies esc:enemies) {
				//g2d.setColor(new Color(103,71,54));
				g2d.setColor(Color.BLACK);
				g2d.fill(esc);
			}
			if(loaded) {
				g2d.drawImage(pic,(int)newBird.getX()-3,(int)newBird.getY()-3,null);
				g2d.drawImage(pic2,133, 310,null);
				g2d.drawImage(pic3,268, 360,null);
				g2d.drawImage(pic4,700, 350,null);
				g2d.drawImage(pic5,577, 277,null);
				g2d.drawImage(pic6, 785, 245,null);
				g2d.drawImage(pic7, 415, 360, null);
			}
		}

		//Panel
		private void insideUI(){
            this.setSize(new Dimension(1100, 1000));
            this.setBackground(new Color(103,71,54));
            this.setFocusable(true);
            this.requestFocus();
	    }
		
		//Determine if game has started and adds in bird and towers
		public void startGame() {
		 bird.add(newBird);
   	     towers.add(btowers1);
   	     towers.add(btowers1c);
   	     towers.add(ttowers1);
   	     towers.add(ttowers1c);
   	     towers.add(btowers2);
   	     towers.add(btowers2c);
   	     towers.add(ttowers2);
   	     towers.add(ttowers2c);
   	     towers.add(btowers3);
   	     towers.add(btowers3c);
   	     towers.add(ttowers3);
   	     towers.add(ttowers3c);
   	     towers.add(btowers4);
   	     towers.add(btowers4c);
   	     towers.add(ttowers4);
   	     towers.add(ttowers4c);
   	     towers.add(btowers5);
   	     towers.add(btowers5c);
   	     towers.add(ttowers5);
   	     towers.add(ttowers5c);
   	     towers.add(btowers6);
   	     towers.add(btowers6c);
   	     towers.add(ttowers6);
   	     towers.add(ttowers6c);
   	     towers.add(btowers7);
   	     towers.add(btowers7c);
   	     towers.add(ttowers7);
   	     towers.add(ttowers7c);
   	     towers.add(btowers8);
   	     towers.add(btowers8c);
   	     towers.add(ttowers8);
   	     towers.add(ttowers8c);
   	     towers.add(btowers9);
   	     towers.add(btowers9c);
   	     towers.add(ttowers9);
   	     towers.add(ttowers9c); 
   	     endzone.add(endOfGameZone);
   	     enemies.add(enemy1);
   	     enemies.add(enemy2);
   	     enemies.add(enemy3);
   	     enemies.add(enemy4);
   	     enemies.add(enemy5);
   	     enemies.add(enemy6);
   	     gameStart = true;
   	    JPanel scoreP = new JPanel();
		scoreP.setPreferredSize(new Dimension (85,40));
		scoreP.setLayout(new GridLayout(1,1, 1, 1));
		scoreP.setBackground(new Color(21,71,52));
		scoreP.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
		this.add(scoreP, BorderLayout.NORTH);
		scoreP.setVisible(true);
		JLabel scoreText = new JLabel("Score: ");
		scoreText.setFont(myFont);
		scoreText.setForeground(Color.WHITE);
		scoreP.add(scoreText);   
		loadKirby();
		loadMagolor();
		
		}
		
		//Determine if player has lost and if so ends the game
		public void endGame() {
			if(gameOver == true) {
	                JPanel endGameMain = new JPanel();
	                endGameMain.setPreferredSize(new Dimension(1100,1000));
	                endGameMain.setBackground(new Color(255,102,102));
	                endGameMain.setBorder(BorderFactory.createEmptyBorder(150, 150, 150, 150));
	                this.add(endGameMain, BorderLayout.CENTER);
	                endGameMain.setVisible(true);
	                JLabel endGameText = new JLabel("You lose! Your score was " + score + "!");
	                endGameText.setFont(new Font("Comic Sans",Font.BOLD, 55));
	                endGameText.setBackground(Color.BLACK);
	                endGameText.setLocation(500,300);
	                endGameMain.add(endGameText);
	                this.validate();
			}
		}
		
		public void gameWon() {
			if(gameWin == true) {
		                JPanel gameWonMain = new JPanel();
		                gameWonMain.setPreferredSize(new Dimension(1100,1000));
		                gameWonMain.setBackground(new Color(102,255,102));
		                gameWonMain.setBorder(BorderFactory.createEmptyBorder(100, 150, 5, 0));
		                this.add(gameWonMain, BorderLayout.CENTER);
		                gameWonMain.setVisible(true);
		                JLabel gameWonText = new JLabel("You win! Your score was " + score + "!");
		                gameWonText.setFont(new Font("Comic Sans",Font.BOLD, 55));
		                gameWonText.setForeground(Color.BLACK);
		                gameWonText.setLocation(500, 300);
		                gameWonMain.add(gameWonText);
		                this.validate();

			}
		}
		
		public void loadKirby() {
			pic = new ImageIcon("/Users/pnjnlng/Downloads/smallkirb.png").getImage();
			loaded = true;
			repaint();
		}
		
		public void loadMagolor() {
			pic2 = new ImageIcon("/Users/pnjnlng/Downloads/magolor.png").getImage();
			pic3 = new ImageIcon("/Users/pnjnlng/Downloads/marx.png").getImage();
			pic4 = new ImageIcon("/Users/pnjnlng/Downloads/marx.png").getImage();
			pic5 = new ImageIcon("/Users/pnjnlng/Downloads/magolor.png").getImage(); 
			pic6 = new ImageIcon("/Users/pnjnlng/Downloads/kingdedede.png").getImage();
			pic7 = new ImageIcon("/Users/pnjnlng/Downloads/megaknight.png").getImage();
			loaded = true;
			repaint();
		}
		
		public void enemyEncounter() {
			endGame();
        }
		
		public void resetGame() {
			gameStart = true;
			
			startGame();
		}

		
		
		//Canvas to store events
		EventsCanvas(){
			insideUI();
	        this.keyact = new KeyRead();
	        this.addKeyListener(this.keyact);
	        bird = new ArrayList<>();
	        towers = new ArrayList<>();
	        endzone = new ArrayList<>();
	        enemies = new ArrayList<>();
	        ink = Color.black;
	        startGame();
	        newBird.getBound();
	    }
		
		
		private void draw() {
			repaint(); 
			}
	}
	
	
	
	public void sound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("ProjectMusic.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	//Main method
	public static void main(String[] args) throws InterruptedException{
		JFrame frame = new JFrame();
        EventsCanvas panel = new EventsCanvas();
        frame.setTitle("Bird Fly");
        frame.setPreferredSize(new Dimension(1100, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.pack();
    	frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		frame.setFocusable(false);
		frame.setResizable(false);
        frame.add(panel); 
        GameProject newGame = new GameProject();
        newGame.sound();
	}
}