import enigma.console.TextAttributes;
import enigma.core.Enigma;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

import javax.management.modelmbean.ModelMBeanAttributeInfo;

public class Game {

    static char[][] Gamefield=new char[25][55];
    TextAttributes b = new TextAttributes(Color.black, Color.DARK_GRAY);
	TextAttributes space = new TextAttributes(Color.DARK_GRAY, Color.DARK_GRAY);
    TextAttributes c = new TextAttributes(Color.black, Color.BLACK);
    TextAttributes XColor = new TextAttributes(Color.RED,Color.DARK_GRAY);
    TextAttributes white = new TextAttributes(Color.WHITE, Color.DARK_GRAY);
    TextAttributes NumberColor = new TextAttributes(Color.YELLOW, Color.DARK_GRAY);
	TextAttributes yellow = new TextAttributes(Color.YELLOW, Color.BLACK);
    TextAttributes InputQueue = new TextAttributes(Color.PINK, Color.DARK_GRAY);
    TextAttributes Boulder = new TextAttributes(Color.cyan,Color.DARK_GRAY);
    TextAttributes Earth = new TextAttributes(Color.LIGHT_GRAY, Color.DARK_GRAY);
    TextAttributes red = new TextAttributes(Color.RED, Color.black);
    TextAttributes green = new TextAttributes(Color.GREEN, Color.black);
	TextAttributes magenta = new TextAttributes(Color.MAGENTA, Color.black);
    int keypr;   // key pressed?
    int rkey;    // key   (for press/release)
    KeyListener klis; 
    enigma.console.Console cn = Enigma.getConsole("-- Welcome The MAZE --",120,32,20);
    public Game() throws Exception {   // --- Contructor
   	   	
    	
        klis=new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
               if(keypr==0) {
                  keypr=1;
                  rkey=e.getKeyCode();
               }
            }
            public void keyReleased(KeyEvent e) {}
         };
         Player.cn.getTextWindow().addKeyListener(klis);	

//game over input name 
    boolean menu = true;
    while(true)
    {
		Queue name1 = new Queue(10);
		Queue score1 = new Queue(10);
		Queue name2 = new Queue(10);
		Queue score2 = new Queue(10);
		Queue name3 = new Queue(10);
		Queue score3 = new Queue(10);
    	Player.cn.getTextWindow().setCursorPosition(0,0);
      	for (int i = 0; i <60; i++) {
             for (int j = 0; j <140; j++) {
                 Player.cn.getTextWindow().output(' ',c);
             }
         }
    	
        cn.getTextWindow().setCursorPosition(3,3);
        cn.getTextWindow().output("Welcome to Gravity Game!",green);
        cn.getTextWindow().setCursorPosition(3,4);
        cn.getTextWindow().output("------------------------",green);
        cn.getTextWindow().setCursorPosition(3,7);
        cn.getTextWindow().output("Use arrow keys",green);
        cn.getTextWindow().setCursorPosition(3,9);
        cn.getTextWindow().output("Press Enter to select",green);
        
        cn.getTextWindow().setCursorPosition(3,13);
        cn.getTextWindow().output("Play",green);
        cn.getTextWindow().setCursorPosition(3,17);
        cn.getTextWindow().output("Options",green);
        cn.getTextWindow().setCursorPosition(3,21);
        cn.getTextWindow().output("Help",green);
        cn.getTextWindow().setCursorPosition(3,25);
        cn.getTextWindow().output("Credits",green);
        cn.getTextWindow().setCursorPosition(3,29);
        cn.getTextWindow().output("Exit",green);
        
        cn.getTextWindow().setCursorPosition(8,13);
        cn.getTextWindow().output("---------------",green);
        cn.getTextWindow().setCursorPosition(26,12);
        cn.getTextWindow().output("Easy",green);
        cn.getTextWindow().setCursorPosition(26,13);
        cn.getTextWindow().output("Medium",green);
        cn.getTextWindow().setCursorPosition(26,14);
        cn.getTextWindow().output("Hard",green);
    	   	   	
        int line =13;
        int linePlay = 0;
        int xNumber;
        int boulderNumber;
        int numNumber;		
        boolean mode = false;
        boolean options = false;
        boolean credits = false;
        boolean help = false;
        boolean exit = false;
        while(menu)
        {
        	cn.getTextWindow().setCursorPosition(0,line);
            cn.getTextWindow().output("  ");
       	   cn.getTextWindow().setCursorPosition(23,linePlay+12);
           cn.getTextWindow().output("  "); 
	        if(keypr==1)
	        {    // if keyboard button pressed
	           if(rkey==KeyEvent.VK_UP) {
	        	   if(line!=13)
	        		   line-=4; 
	        	   else if(linePlay!= 0 && mode ) //lineplay 0sa easy, 1se normal , 2yse hard
	        		   linePlay-=1;
	           }
	           else if(rkey==KeyEvent.VK_DOWN && line!=29) {
	        	   if(!mode)
	        		   line+=4; 
	        	   if(line == 13 && linePlay!= 2 && mode )
	        		   linePlay+=1;
	            }     	       
	           else if(rkey==KeyEvent.VK_RIGHT && line ==13)   	   
					mode = true;
	           else if(rkey==KeyEvent.VK_LEFT && line ==13)        	   
					mode = false;
	           else if(rkey==KeyEvent.VK_ENTER && line ==13 && mode)       	   
					break;
	           else if(rkey==KeyEvent.VK_ENTER && line ==17)       	   
					options = true;
	           else if(rkey==KeyEvent.VK_ENTER && line ==21)        	   
					help = true;	           
	           else if(rkey==KeyEvent.VK_ENTER && line ==25)    	   
					credits = true;
	           else if(rkey==KeyEvent.VK_ENTER && line ==29){
					exit = true;
					break;
	           }
	           	           
		        keypr = 0;
		        rkey = KeyEvent.KEY_LOCATION_STANDARD;
	        } 
	           if(options)
	           {
	         	   for (int i = 0; i <23; i++) {
	        		   Player.cn.getTextWindow().setCursorPosition(42,i+3);
	        		   Player.cn.getTextWindow().output("																		    "); 
	        	   }     	   
	        	   Player.cn.getTextWindow().setCursorPosition(55,5);
	        	   Player.cn.getTextWindow().output(" OPTIONS ",red);
	        	   Player.cn.getTextWindow().setCursorPosition(55,6);
	               Player.cn.getTextWindow().output("---------",red);
	        	   Player.cn.getTextWindow().setCursorPosition(45,9);
	        	   Player.cn.getTextWindow().output("Easy");
	        	   Player.cn.getTextWindow().setCursorPosition(45,11);
	        	   Player.cn.getTextWindow().output("Coming soon.");
				   options = false;
	           }
	           else if(help)
	           {
	        	   
	        	   help= false;
	        	   for (int i = 0; i <23; i++) {
	        		   Player.cn.getTextWindow().setCursorPosition(42,i+3);
	        		   Player.cn.getTextWindow().output("										     								 "); 
	        	   }		
	        	   
	        	   Player.cn.getTextWindow().setCursorPosition(55,3);
	        	   Player.cn.getTextWindow().output("HOW TO PLAY ",red);  
	        	   Player.cn.getTextWindow().setCursorPosition(55,4);
	        	   Player.cn.getTextWindow().output("------------",red);  
	        	   Player.cn.getTextWindow().setCursorPosition(45,7);
	        	   Player.cn.getTextWindow().output("You are P, X is your enemy, : is earth and O is boulder.");  
	        	   Player.cn.getTextWindow().setCursorPosition(45,8);
	        	   Player.cn.getTextWindow().output("Numbers are collectible items by P");  
	        	   Player.cn.getTextWindow().setCursorPosition(45,10);
	        	   Player.cn.getTextWindow().output("1: 10 score point ");  
	        	   Player.cn.getTextWindow().setCursorPosition(45,11);
	        	   Player.cn.getTextWindow().output("2: 40 score point ");  
	        	   Player.cn.getTextWindow().setCursorPosition(45,12);
	        	   Player.cn.getTextWindow().output("3: 90 score point and 1 teleport right");  
	        	   Player.cn.getTextWindow().setCursorPosition(45,14);
	        	   Player.cn.getTextWindow().output("The enemies are constantly moving and if they touch you, you die!");  
	               
	        	   Player.cn.getTextWindow().setCursorPosition(45,16);
	        	   Player.cn.getTextWindow().output("If the Boulders kill the enemy, you will gain 900 points. If they ");  
	        	   Player.cn.getTextWindow().setCursorPosition(42,17);
	        	   Player.cn.getTextWindow().output("fall on you, you die!");  
	               Player.cn.getTextWindow().setCursorPosition(45,21);
	               Player.cn.getTextWindow().output("Do your best score!");  
	               Player.cn.getTextWindow().setCursorPosition(45,25);
	               Player.cn.getTextWindow().output("Contact: gravity@deu.io",red);  
	           }
	           else if(credits)
	           {
	        	   credits = false;
	        	   for (int i = 0; i <23; i++) {
	        		   Player.cn.getTextWindow().setCursorPosition(42,i+3);
	        		   Player.cn.getTextWindow().output("                     	 					    			        "); 
	        	   }   
	        	   
	        	   Player.cn.getTextWindow().setCursorPosition(55,5);
	        	   Player.cn.getTextWindow().output("CREDITS  ",red);  
	               Player.cn.getTextWindow().setCursorPosition(55,6);
	               Player.cn.getTextWindow().output("-------",red);
	               Player.cn.getTextWindow().setCursorPosition(45,9);
	               Player.cn.getTextWindow().output("YUSUF APAK");  
	               Player.cn.getTextWindow().setCursorPosition(45,13);
	               Player.cn.getTextWindow().output("MURAT GÜLER");
	               Player.cn.getTextWindow().setCursorPosition(45,17);
	               Player.cn.getTextWindow().output("KAAN YILMAZ");  
	               Player.cn.getTextWindow().setCursorPosition(45,21);
	               Player.cn.getTextWindow().output("DOĞA ECE KOCA");	               	               
	           }
	        
	        
	           if(mode)
	           {
	        	   cn.getTextWindow().setCursorPosition(23,linePlay+12);
	               cn.getTextWindow().output("->",red); 
	           }
	           if (!mode){
	        	   cn.getTextWindow().setCursorPosition(0,line);
	               cn.getTextWindow().output("->",red);  
	           }
	           
	        
	        Thread.sleep(100);
        }
        if(exit)
        	System.exit(0);
    	menu = false;
   		
        if(linePlay == 0) {
        xNumber = 4;
        boulderNumber =130;
        numNumber = 60;       	
        }
        else  if(linePlay == 1) {
        	xNumber = 7;
            boulderNumber =180;
            numNumber = 30;       	
        }
        else {
        	xNumber = 15;
            boulderNumber =240;
            numNumber = 12;       	
        }
        
        	Player.cn.getTextWindow().setCursorPosition(0,0);
	      	for (int i = 0; i <60; i++) {
	             for (int j = 0; j <140; j++) {
	                 Player.cn.getTextWindow().output(' ',b);
	             }
	         }
	      	
	        //We started to make assignments into the field in a random way.
	        for(int i=0; i<25; i++)
	        {
	            for(int j=0; j<55; j++)
	            {
	                Gamefield[i][j]='#';
	                if(i>0&&i<24&&j>0&&j<54)
	                {
	                    Gamefield[i][j]=':';
	                }
	            }
	        }
	
	        for(int j=0; j<50; j++)
	        {
	            Gamefield[8][j]='#';
	        }
	        for(int j=5; j<55; j++)
	        {
	            Gamefield[16][j]='#';
	        }
	
	        int counter=0;
	
	
	        //'X'
	        while(counter < xNumber)
	        {
	            int rndx = new Random().nextInt(23) + 1;
	            int rndy = new Random().nextInt(53) + 1;
	            if (Gamefield[rndx][rndy]==':') {
	
	                Gamefield[rndx][rndy]='X';
	                counter++;
	            }
	        }
	
	        counter = 0;
	        //'O'
	        while(counter < boulderNumber)
	        {
	            int rndx = new Random().nextInt(23 - 1 + 1) + 1;
	            int rndy = new Random().nextInt(53 - 1 + 1) + 1;
	            if (Gamefield[rndx][rndy]==':') {
	
	                Gamefield[rndx][rndy]='O';
	                counter++;
	            }
	        }
	        counter = 0;
	
	        //' '
	
	        while(counter < 200)
	        {
	            int rndx = new Random().nextInt(23 - 1 + 1) + 1;
	            int rndy = new Random().nextInt(53 - 1 + 1) + 1;
	            if (Gamefield[rndx][rndy]==':') {
	
	                Gamefield[rndx][rndy]=' ';
	                counter++;
	            }
	        }
	        counter = 0;
	
	        //'1','2','3'
	        while(counter < numNumber)
	        {
	            int randnum = new Random().nextInt(3 - 1 + 1) + 1;
	            char[] numbers=new char[3];
	            numbers[0]='1';
	            numbers[1]='2';
	            numbers[2]='3';
	
	            char number = 0;
	            if(randnum==1) number=numbers[0];
	            if(randnum==2) number=numbers[1];
	            if(randnum==3) number=numbers[2];
	
	            int rndx = new Random().nextInt(23 - 1 + 1) + 1;
	            int rndy = new Random().nextInt(53 - 1 + 1) + 1;
	            if (Gamefield[rndx][rndy]==':') {
	
	                Gamefield[rndx][rndy]=number;
	                counter++;
	            }
	        }
	        counter = 0;
	
	        //By creating a circular queue, we have assigned our staff who will enter the game area.
	        Queue inQueue = new Queue(15);
	
	        double time = 0;
	        int newTime = (int) (time - time % 1);
	
	        //We have assigned the queued elements to the array.
	        char[] elements = {'O','1','2','3','X',':',' '};
	
	
	        Random rand = new Random();
	
	        for(int t=0; t<15; t++)
	        {
	            int randomnum = rand.nextInt(40) + 1;
	            //We made the assignments according to probability.
	
	            if(randomnum >= 1 && randomnum <= 10)
	            {
	                inQueue.enqueue(elements[0]);
	            }
	            else if(randomnum >10 && randomnum <= 16)
	            {
	                inQueue.enqueue(elements[1]);
	            }
	            else if(randomnum >16 && randomnum <= 21)
	            {
	                inQueue.enqueue(elements[2]);
	            }
	            else if(randomnum >21 && randomnum <= 25)
	            {
	                inQueue.enqueue(elements[3]);
	            }
	            else if(randomnum == 26)
	            {
	                inQueue.enqueue(elements[4]);
	            }
	            else if(randomnum >26 && randomnum <= 35)
	            {
	                inQueue.enqueue(elements[5]);
	            }
	            else if(randomnum >35 && randomnum <= 40)
	            {
	                inQueue.enqueue(elements[6]);
	            }
	        }
	        //Printable parts of our queue.
	        Player.cn.getTextWindow().setCursorPosition(57,0);
	        Player.cn.getTextWindow().output("Input",InputQueue);
	        Player.cn.getTextWindow().setCursorPosition(57,1);
	        Player.cn.getTextWindow().output("<<<<<<<<<<<<<<<");
	        Player.cn.getTextWindow().setCursorPosition(57,2);
	
	        for (int i = 0; i < inQueue.size(); i++) {
	            Player.cn.getTextWindow().output(inQueue.peek()+"", InputQueue);
	            inQueue.enqueue(inQueue.dequeue());
	        }
	        Player.cn.getTextWindow().setCursorPosition(57,3);
	        Player.cn.getTextWindow().output("<<<<<<<<<<<<<<<");
	
	        int pa = 0;
	        int pb = 0;
	        for(int k=0; k<15; k++)
	        {
	            if(newTime %3 == 0 )
	            {
	                pa = new Random().nextInt(23 - 1 + 1) + 1;
	                pb = new Random().nextInt(53 - 1 + 1) + 1;
	                if(Gamefield[pa][pb]==':' || Gamefield[pa][pb]== ' ');
	                {
	                    Player.cn.getTextWindow().setCursorPosition(pb,pa);
	                    Player.cn.getTextWindow().output((char)inQueue.peek());
	
	                }
	            }
	
	            else continue;
	        }
	
	        int px = 0;
	        int py = 0;
	        //'P'
	        while(counter < 1)
	        {
	            py = new Random().nextInt(23 - 1 + 1) + 1;
	            px = new Random().nextInt(53 - 1 + 1) + 1;
	            if(Gamefield[py][px]==':')
	            {
	                Gamefield[py][px]='P';
	                counter++;
	            }
	        }
	
	        //Printing the parts we made in the array.
	        for(int i=0; i<25; i++)
	        {
	            for(int j=0; j<55; j++)
	            {
	                Player.cn.getTextWindow().setCursorPosition(j,i);
	                if(Gamefield[i][j]=='#')
	                {
	                    Player.cn.getTextWindow().output(Gamefield[i][j],white);
	                }
	                else if(Gamefield[i][j]=='1'||Gamefield[i][j]=='2'||Gamefield[i][j]=='3')
	                {
	                    Player.cn.getTextWindow().output(Gamefield[i][j], NumberColor);
	                }
	                else if(Gamefield[i][j]=='X')
	                {
	                    Player.cn.getTextWindow().output(Gamefield[i][j], XColor);
	                }
	                else if(Gamefield[i][j]=='O')
	                {
	                    Player.cn.getTextWindow().output(Gamefield[i][j], Boulder);
	                }
	                else if(Gamefield[i][j]==':')
	                {
	                    Player.cn.getTextWindow().output(Gamefield[i][j], Earth);
	                }
	                else
	                {
	                    Player.cn.getTextWindow().output(' ',space);
	                }
	            }
	        }
			Player player = new Player(Gamefield,px,py,inQueue);

		for (int i = 0; i <60; i++) {
			for (int j = 0; j <140; j++) {
				Player.cn.getTextWindow().output(' ',c);
			}
		}


		Player.cn.getTextWindow().setCursorPosition(35,5);
		Player.cn.getTextWindow().output("┏━━━┓ ┏━━━┓ ┏━┓┏━┓ ┏━━━┓   ┏━━━┓ ┏┓  ┏┓ ┏━━━┓ ┏━━━┓\n" +
				"                             	  ┃┏━┓┃ ┃┏━┓┃ ┃┃┗┛┃┃ ┃┏━━┛   ┃┏━┓┃ ┃┗┓┏┛┃ ┃┏━━┛ ┃┏━┓┃\n" +
				"                             	  ┃┃ ┗┛ ┃┃ ┃┃ ┃┏┓┏┓┃ ┃┗━━┓   ┃┃ ┃┃ ┗┓┃┃┏┛ ┃┗━━┓ ┃┗━┛┃\n" +
				"                             	  ┃┃┏━┓ ┃┗━┛┃ ┃┃┃┃┃┃ ┃┏━━┛   ┃┃ ┃┃  ┃┗┛┃  ┃┏━━┛ ┃┏┓┏┛\n" +
				"                             	  ┃┗┻━┃ ┃┏━┓┃ ┃┃┃┃┃┃ ┃┗━━┓   ┃┗━┛┃  ┗┓┏┛  ┃┗━━┓ ┃┃┃┗┓\n" +
				"                             	  ┗━━━┛ ┗┛ ┗┛ ┗┛┗┛┗┛ ┗━━━┛   ┗━━━┛   ┗┛   ┗━━━┛ ┗┛┗━┛",red);


		String filepath = "src/highscoretable.txt";
		EasyHighScoreTableRead(name1,score1,filepath);
		NormalHighScoreTableRead(name2,score2,filepath);
		HardHighScoreTableRead(name3,score3,filepath);
		Player.cn.getTextWindow().setCursorPosition(7,16);  //We take the input name
		Player.cn.getTextWindow().output("What is your name ? ",green);
		String name = Player.cn.readLine();
		
		if(line == 13 && linePlay == 0 && mode)
		{
			name1.enqueue(name);
			score1.enqueue(player.score);
		}
		else if(line == 13 && linePlay == 1 && mode)
		{
			name2.enqueue(name);
			score2.enqueue(player.score);
		}
		else if(line == 13 && linePlay == 2 && mode)
		{
			name3.enqueue(name);
			score3.enqueue(player.score);
		}

		sortQueue(score1,name1);
		sortQueue(score2,name2);
		sortQueue(score3,name3);

		Player.cn.getTextWindow().setCursorPosition(52, 15);
		Player.cn.getTextWindow().output("╦ ╦┬┌─┐┬ ┬  ╔═╗┌─┐┌─┐┬─┐┌─┐  ╔╦╗┌─┐┌┐ ┬  ┌─┐\n" +
				"                                                    ╠═╣││ ┬├─┤  ╚═╗│  │ │├┬┘├┤    ║ ├─┤├┴┐│  ├┤ \n" +
				"                                                    ╩ ╩┴└─┘┴ ┴  ╚═╝└─┘└─┘┴└─└─┘   ╩ ┴ ┴└─┘┴─┘└─┘",magenta);
		Player.cn.getTextWindow().setCursorPosition(45, 20);
		Player.cn.getTextWindow().output("Easy Mode",yellow);
		Player.cn.getTextWindow().setCursorPosition(70, 20);
		Player.cn.getTextWindow().output("Normal Mode",green);
		Player.cn.getTextWindow().setCursorPosition(95, 20);
		Player.cn.getTextWindow().output("Hard Mode",red);

		HighScoreTablePrint(name1,score1,name2,score2,name3,score3);


		Player.cn.getTextWindow().setCursorPosition(7,20);
		Player.cn.getTextWindow().output("Do you want to play again ?",green);
		Player.cn.getTextWindow().setCursorPosition(3,22);
		Player.cn.getTextWindow().output("Yes",green);
		Player.cn.getTextWindow().setCursorPosition(3,24);
		Player.cn.getTextWindow().output("No ",green);


		int playAgainLine = 22;
		keypr = 0;
		rkey = KeyEvent.KEY_LOCATION_STANDARD;

	        while(true)
	        {

		        if(keypr==1)
		        {
		           cn.getTextWindow().setCursorPosition(0,playAgainLine);
				   cn.getTextWindow().output("  ");
		           if(rkey==KeyEvent.VK_UP && playAgainLine != 22 ) {
		        	   playAgainLine-=2;
		           }
		           else if(rkey==KeyEvent.VK_DOWN && playAgainLine != 24 ) {
		        	   playAgainLine+=2;
		           }
		           else if(rkey==KeyEvent.VK_ENTER && playAgainLine == 22 ) {
		        	   break;
		           }
		           else if(rkey==KeyEvent.VK_ENTER && playAgainLine == 24 ) {
		        	   menu = true;
		        	   break;
		           }
				    keypr = 0;
				    rkey = KeyEvent.KEY_LOCATION_STANDARD;
		        }
		        cn.getTextWindow().setCursorPosition(0,playAgainLine);
		        cn.getTextWindow().output("->",red);

	        }
	    }   	
   }

	static void sortQueue(Queue score_queue, Queue name_queue){
		// Here I am sorting the data we read from the txt.
		Stack name = new Stack(name_queue.size());
		Stack score = new Stack(score_queue.size());
		Stack tempStack_name = new Stack(score_queue.size());
		Stack tempStack_score = new Stack(score_queue.size());

		// First of all, I make it easier to sort these values by transferring them to the stack.
		while (!name_queue.isEmpty()){
			name.push(name_queue.dequeue());
			score.push(score_queue.dequeue());
		}

		while(!score.isEmpty()) {

			int tmp = (int)score.pop();
			String tmps = (String)name.pop();

			while(!tempStack_score.isEmpty() && (int)tempStack_score.peek() > tmp) {
				score.push(tempStack_score.pop());
				name.push(tempStack_name.pop());
			}

			tempStack_score.push(tmp);
			tempStack_name.push(tmps);
		}
		//Then, after sorting these values, I transfer them back to the queues.
		while (!tempStack_score.isEmpty()){
			score_queue.enqueue(tempStack_score.pop());
			name_queue.enqueue(tempStack_name.pop());
		}
	}
	static void EasyHighScoreTableRead(Queue name, Queue score, String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) { //I provided error checks with try catches. Like file not found exception.
			String line;
			int count = 0;
			boolean startReading = false; // variable to track if we should start reading from the file
			while ((line = reader.readLine()) != null) {
				if (line.equalsIgnoreCase("-- Normal Mode --")) { // check if we should stop reading from the file
					break;
				}
				if (startReading) { // check if we should start reading from the file
					String[] parts = line.split("\\s+");
					if (parts.length == 2) {
						name.enqueue(parts[0]);
						score.enqueue(Integer.parseInt(parts[1]));
						count++;
					} else {
						continue;
					}
					if (count >= 10) { // Exit the loop if 10 valid entries have been added
						break;
					}
				} else if (line.equalsIgnoreCase("-- Easy Mode --")) { // check if we should start reading from the file
					startReading = true;
				}
			}
		} catch (FileNotFoundException e) { //I made the error prints.
			try{
				File newfile = new File("src/highscoretable.txt");
				FileWriter writer = new FileWriter(newfile);
				BufferedWriter yaz = new BufferedWriter(writer);
				yaz.write("-- Easy Mode --\n-- Normal Mode --\n-- Hard Mode --");
				yaz.close();
			}
			catch(Exception hata){
				hata.printStackTrace();
			}
		} catch (IOException e) {
			System.out.println("Error!!!");
			e.printStackTrace();
		}
	}
	static void NormalHighScoreTableRead(Queue name, Queue score, String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			int count = 0;
			boolean startReading = false; // variable to track if we should start reading from the file
			while ((line = reader.readLine()) != null) {
				if (line.equalsIgnoreCase("-- Hard Mode --")) { // check if we should stop reading from the file
					break;
				}
				if (startReading) { // check if we should start reading from the file
					String[] parts = line.split("\\s+");
					if (parts.length == 2) {
						name.enqueue(parts[0]);
						score.enqueue(Integer.parseInt(parts[1]));
						count++;
					} else {
						continue;
					}
					if (count >= 10) { // Exit the loop if 10 valid entries have been added
						break;
					}
				} else if (line.equalsIgnoreCase("-- Normal Mode --")) { // check if we should start reading from the file
					startReading = true;
				}
			}
		} catch (IOException e) {
			System.out.println("Error!!!");
			e.printStackTrace();
		}
	}
	static void HardHighScoreTableRead(Queue name, Queue score, String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			int count = 0;
			boolean startReading = false; // variable to track if we should start reading from the file
			while ((line = reader.readLine()) != null) {
				if (startReading) { // check if we should start reading from the file
					String[] parts = line.split("\\s+");
					if (parts.length == 2) {
						name.enqueue(parts[0]);
						score.enqueue(Integer.parseInt(parts[1]));
						count++;
					} else {
						continue;
					}
					if (count >= 10) { // Exit the loop if 10 valid entries have been added
						break;
					}
				} else if (line.equalsIgnoreCase("-- Hard Mode --")) {// check if we should start reading from the file
					startReading = true;
				}
			}
		} catch (IOException e) {
			System.out.println("Error!!!");
			e.printStackTrace();
		}
	}
	static void HighScoreTablePrint(Queue name1, Queue score1, Queue name2, Queue score2, Queue name3, Queue score3){
		try {
			// Delete existing contents before writing.
			FileWriter myWriter = new FileWriter("src/highscoretable.txt",false);
			myWriter.write("-- Easy Mode --\n");
			for (int i = 1; i <= name1.size(); i++) {
				myWriter.write(String.format("%-10s %-6d\n", name1.peek(), score1.peek()));
				Player.cn.getTextWindow().setCursorPosition(45, 21 + i);
				Player.cn.getTextWindow().output(name1.peek().toString(),new TextAttributes(Color.yellow));
				Player.cn.getTextWindow().setCursorPosition(55, 21 + i);
				Player.cn.getTextWindow().output(score1.peek().toString(),new TextAttributes(Color.yellow));
				name1.enqueue(name1.dequeue());
				score1.enqueue(score1.dequeue());
			}

			myWriter.write("\n-- Normal Mode --\n");
			for (int i = 1; i <= name2.size(); i++) {
				myWriter.write(String.format("%-10s %-6s\n",name2.peek(), score2.peek()));
				Player.cn.getTextWindow().setCursorPosition(70,21+i);
				Player.cn.getTextWindow().output(name2.peek().toString(),new TextAttributes(Color.green));
				Player.cn.getTextWindow().setCursorPosition(80,21+i);
				Player.cn.getTextWindow().output(score2.peek().toString(), new TextAttributes(Color.green));
				name2.enqueue(name2.dequeue());
				score2.enqueue(score2.dequeue());
			}
			myWriter.write("\n-- Hard Mode --\n");
			for (int i = 1; i <= name3.size(); i++) {
				myWriter.write(String.format("%-10s %-6s\n",name3.peek(), score3.peek()));
				Player.cn.getTextWindow().setCursorPosition(95,21+i);
				Player.cn.getTextWindow().output((String) name3.peek(),new TextAttributes(Color.red));
				Player.cn.getTextWindow().setCursorPosition(105,21+i);
				Player.cn.getTextWindow().output(score3.peek().toString(),new TextAttributes(Color.red));
				name3.enqueue(name3.dequeue());
				score3.enqueue(score3.dequeue());
			}
			myWriter.close();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
