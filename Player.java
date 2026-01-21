import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class Player {
    int keypr;   // key pressed?
    int rkey;    // key   (for press/release)
    public static enigma.console.Console cn = Enigma.getConsole("-- Welcome The MAZE --");
    TextAttributes b = new TextAttributes(Color.DARK_GRAY, Color.DARK_GRAY);
    TextAttributes PColor = new TextAttributes(Color.GREEN, Color.DARK_GRAY);
    TextAttributes Boulder = new TextAttributes(Color.cyan,Color.DARK_GRAY);
    TextAttributes XColor = new TextAttributes(Color.RED, Color.DARK_GRAY);
    TextAttributes Backpack = new TextAttributes(Color.ORANGE, Color.DARK_GRAY);
    TextAttributes InputQueue = new TextAttributes(Color.PINK, Color.DARK_GRAY);
    TextAttributes NumberColor = new TextAttributes(Color.YELLOW, Color.DARK_GRAY);
    TextAttributes Earth = new TextAttributes(Color.LIGHT_GRAY, Color.DARK_GRAY);

    public static int score = 0;
    private static double time=0;


    public Player(char[][] GameField, int px, int py,Queue inQueue) throws InterruptedException {
        Random rnd = new Random();
        KeyListener klis;
        // ------ Standard code for mouse and keyboard ------ Do not change

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
        
        cn.getTextWindow().addKeyListener(klis);
        // ----------------------------------------------------

        //Creating and printing the backpack part.
        int teleport = 3,indexinStack = 0;
       // double time = 0;
        Stack backPack = new Stack(8);;
        Stack tempforPrint = new Stack(8);
        for (int i = 0; i < 8; i++)
        {
            cn.getTextWindow().setCursorPosition(65,6+i);
            cn.getTextWindow().output("|   |");
        }
        cn.getTextWindow().setCursorPosition(65,14);
        cn.getTextWindow().output("+---+");
        cn.getTextWindow().setCursorPosition(64,15);
        cn.getTextWindow().output("Backpack", Backpack);

        int tempTime = -1;
        int tempTime2 = -1;

        
        while(true)
        {
            GameField[py][px] = ' ';
            int tempX = px,tempY = py;
            //Adjusting the position of P and whether it can move according to its movement with the arrow keys.
            if(keypr==1)
            {    // if keyboard button pressed
                Player.cn.getTextWindow().setCursorPosition(px,py);
                Player.cn.getTextWindow().output(' ',b);
                boolean movement = false;
                if(rkey==KeyEvent.VK_LEFT && GameField[py][px-1] != '#' ){
                    if(GameField[py][px-2] == ' ' && GameField[py][px-1] =='O')
                    {
                    	GameField[py][px-2] = 'O';
                    	GameField[py][px-1] = ' ';
                    	cn.getTextWindow().setCursorPosition(px-2,py);
                    	cn.getTextWindow().output('O', Boulder);
                    	 px--; movement = true;
                    }
                    else if(GameField[py][px-1] !='O')
                    {
                        px--; movement = true;
                    }
                }
                if(rkey==KeyEvent.VK_RIGHT && GameField[py][px+1] != '#') {
                    if(GameField[py][px+2] == ' ' && GameField[py][px+1] == 'O')
                    {
                    	GameField[py][px+2] = 'O';
                    	GameField[py][px+1] = ' ';
                    	cn.getTextWindow().setCursorPosition(px+2,py);
                    	cn.getTextWindow().output('O', Boulder);
                        px++; movement = true;
                    }
                    else if(GameField[py][px+1] !='O')
                    {
                        px++; movement = true;
                    }

                }
                if(rkey==KeyEvent.VK_UP && GameField[py-1][px] != '#' && GameField[py-1][px] != 'O') {
                    py--; movement = true;
                }
                if(rkey==KeyEvent.VK_DOWN && GameField[py+1][px] != '#' && GameField[py+1][px] != 'O') {
                    py++; movement = true;
                }
                if (movement) //What P can do in cases where it can be moved
                {
                    
                    
                	if(GameField[py][px] == ':')
                    {
                        GameField[py][px] = ' ';
                    }
                    else if(GameField[py][px] == '1' || GameField[py][px] == '2' || GameField[py][px] == '3' )
                    {

                        if(backPack.isEmpty())
                        {
                            backPack.push(GameField[py][px]);
                        }
                        else
                        { //Assigning numbers into the backpack and getting different amount of points or teleport right in case the numbers are matched.
                            if((char) backPack.peek() == GameField[py][px])
                            {
                                if(GameField[py][px] == '1')
                                {
                                    score+=10;
                                }
                                else if(GameField[py][px] == '2')
                                {
                                    score+=40;
                                }
                                else if(GameField[py][px] == '3')
                                {
                                    score+=90; teleport+=1;
                                }
                                backPack.pop();
                            }
                            else { //Deleting the top element in case of backpacking full.
                                if(backPack.isFull()) {
                                    backPack.pop();
                                    backPack.push(GameField[py][px]);
                                }
                                else
                                    backPack.push(GameField[py][px]);
                            }
                        }
                    }

                }
            }

            Player.cn.getTextWindow().setCursorPosition(px,py);
            Player.cn.getTextWindow().output('P', PColor);
            GameField[py][px] = 'P';


            if(rkey==KeyEvent.VK_SPACE) { //Performing the teleport event.
                if (teleport > 0){
                    Player.cn.getTextWindow().setCursorPosition(px,py);
                    Player.cn.getTextWindow().output(' ',b);
                    GameField[py][px] = ' ';
                    teleport--;
                    while(true)
                    {
                        py = new Random().nextInt(23 - 1 + 1) + 1;
                        px = new Random().nextInt(53 - 1 + 1) + 1;
                        if(GameField[py][px]==':')
                        {
                            Player.cn.getTextWindow().setCursorPosition(px,py);
                            Player.cn.getTextWindow().output('P', PColor);
                            GameField[py][px]='P';
                            break;
                        }
                    }
                }

                rkey = KeyEvent.KEY_LOCATION_STANDARD;
            }

            keypr=0;
            int newTime = (int) (time - time % 1);
            cn.getTextWindow().setCursorPosition(58,18);
            cn.getTextWindow().output("Teleport = "+teleport, NumberColor);
            cn.getTextWindow().setCursorPosition(58,20);
            cn.getTextWindow().output("Score = "+score, NumberColor);
            cn.getTextWindow().setCursorPosition(58,22);
            cn.getTextWindow().output("Time = "+(int) (time - time % 1), NumberColor);
            for (int i = 0; i < 8; i++) {
                cn.getTextWindow().setCursorPosition(67,6+i);
                cn.getTextWindow().output(' ');
            }
            while (!backPack.isEmpty())
            {
                cn.getTextWindow().setCursorPosition(67,6+indexinStack);
                cn.getTextWindow().output((char) backPack.peek(), NumberColor);
                tempforPrint.push(backPack.pop());
                indexinStack++;
            }
            while (!tempforPrint.isEmpty())
            {
                backPack.push(tempforPrint.pop());
            }
            indexinStack = 0;


            time+= 0.1;
            Thread.sleep(100);

            int choosedir = 5;

            //Movement of Xs.
            if (newTime % 0.5 == 0 && tempTime2 != newTime && newTime != 0){
                tempTime2 = newTime;
                Random rand=new Random();
                choosedir=rand.nextInt(4);
                for(int i=1; i<24; i++)
                {
                    for(int j=1; j<54; j++)
                    {

                        if (GameField[i][j]=='X')
                        {

                            if(choosedir==0)
                            {
                                if(GameField[i+1][j]==' ')
                                {
                                    GameField[i+1][j]='X';
                                    GameField[i][j]=' ';
                                    cn.getTextWindow().setCursorPosition(j,i);
                                    cn.getTextWindow().output(GameField[i][j],b);
                                    cn.getTextWindow().setCursorPosition(j,i+1);
                                    cn.getTextWindow().output(GameField[i+1][j],XColor);
                                }
                            }
                            else if(choosedir==1)
                            {
                                if(GameField[i][j+1]==' ')
                                {
                                    GameField[i][j+1]='X';
                                    GameField[i][j]=' ';
                                    cn.getTextWindow().setCursorPosition(j,i);
                                    cn.getTextWindow().output(GameField[i][j],b);
                                    cn.getTextWindow().setCursorPosition(j+1,i);
                                    cn.getTextWindow().output(GameField[i][j+1],XColor);

                                }
                            }
                            else if(choosedir==2)
                            {
                                if(GameField[i-1][j]==' ')
                                {
                                    GameField[i-1][j]='X';
                                    GameField[i][j]=' ';
                                    cn.getTextWindow().setCursorPosition(j,i);
                                    cn.getTextWindow().output(GameField[i][j],b);
                                    cn.getTextWindow().setCursorPosition(j,i-1);
                                    cn.getTextWindow().output(GameField[i-1][j],XColor);

                                }

                            }
                            else if(choosedir==3)
                            {
                                if(GameField[i][j-1]==' ')
                                {
                                    GameField[i][j-1]='X';
                                    GameField[i][j]=' ';
                                    cn.getTextWindow().setCursorPosition(j,i);
                                    cn.getTextWindow().output(GameField[i][j],b);
                                    cn.getTextWindow().setCursorPosition(j-1,i);
                                    cn.getTextWindow().output(GameField[i][j-1],XColor);

                                }
                            }
                        }
                    }
                }

            }


            boolean death = false;

            
            for(int i=1; i<24; i++)
            {
                for(int j=1; j<54; j++)
                {
                	
                	if(GameField[i][j]=='P'&&(GameField[i+1][j]=='X'||GameField[i-1][j]=='X'||GameField[i][j-1]=='X'||GameField[i][j+1]=='X'))
                	{
                		cn.getTextWindow().setCursorPosition(32, 25);
                        cn.getTextWindow().output("Game Over", NumberColor);
                        death=true;
                		break;
                	}
                	if(GameField[i][j]=='X'&&(GameField[i+1][j]=='P'||GameField[i-1][j]=='P'||GameField[i][j-1]=='P'||GameField[i][j+1]=='P'))
                	{
                		cn.getTextWindow().setCursorPosition(32, 25);
                        cn.getTextWindow().output("Game Over", NumberColor);
                        death=true;
                		break;
                	}
                    
                	
                		
                }if(death) break;
                
            }if(death) break;
            
            for ( int i=23; i>0; i--){
                for (int j = 1; j < 54; j++){

                    if (GameField[i][j] == 'P' && i-1== tempY && j == tempX &&GameField[i - 2][j] == 'O' )
                    {
                        cn.getTextWindow().setCursorPosition(32, 25);
                        cn.getTextWindow().output("Game Over", NumberColor);
                        death = true;
                        break;
                    }
                }
                if(death)
                    break;
            }
            if(death)
                break;

            for ( int i=23; i>0; i--){
                for (int j = 1; j < 54; j++){
                    if (GameField[i][j] == ' ' && GameField[i - 1][j] == 'O'){
                        GameField[i][j] = 'O';
                        GameField[i - 1][j] = ' ';
                        cn.getTextWindow().setCursorPosition(j,i);
                        cn.getTextWindow().output(GameField[i][j], Boulder);
                        cn.getTextWindow().setCursorPosition(j,i-1);
                        cn.getTextWindow().output(GameField[i-1][j],b);
                    } else if (i != 24 && GameField[i+1][j] == 'P' && GameField[i - 1][j] == 'O' && GameField[i][j] == ' ') {
                        cn.getTextWindow().setCursorPosition(32, 25);
                        cn.getTextWindow().output("Game Over");
                    } else if (GameField[i][j] == 'O' && GameField[i - 1][j] == 'O') {
                        if (j != 54 && j != 0 && GameField[i][j + 1] ==' ' && GameField[i][j - 1] ==' '&& GameField[i - 1][j - 1] ==' ' && GameField[i - 1][j + 1] ==' '){
                            int a = rnd.nextInt(2);
                            if (a == 0){

                                GameField[i - 1][j] = ' ';
                                GameField[i][j - 1] = 'O';
                                cn.getTextWindow().setCursorPosition(j,i - 1);
                                cn.getTextWindow().output(GameField[i-1][j],b);
                                cn.getTextWindow().setCursorPosition(j - 1,i);
                                cn.getTextWindow().output(GameField[i][j - 1], Boulder);

                            }
                            else {
                                GameField[i - 1][j] = ' ';
                                GameField[i][j + 1] = 'O';
                                cn.getTextWindow().setCursorPosition(j,i - 1);
                                cn.getTextWindow().output(GameField[i-1][j],b);
                                cn.getTextWindow().setCursorPosition(j + 1,i);
                                cn.getTextWindow().output(GameField[i][j + 1], Boulder);
                            }
                        } else if (GameField[i][j + 1] ==' ' && GameField[i - 1][j + 1] ==' ') {
                            GameField[i - 1][j] = ' ';
                            GameField[i][j + 1] = 'O';
                            cn.getTextWindow().setCursorPosition(j,i - 1);
                            cn.getTextWindow().output(GameField[i-1][j],b);
                            cn.getTextWindow().setCursorPosition(j + 1,i);
                            cn.getTextWindow().output(GameField[i][j + 1], Boulder);
                        } else if (GameField[i][j - 1] ==' '&& GameField[i - 1][j - 1] ==' ') {
                            GameField[i - 1][j] = ' ';
                            GameField[i][j - 1] = 'O';
                            cn.getTextWindow().setCursorPosition(j,i - 1);
                            cn.getTextWindow().output(GameField[i-1][j],b);
                            cn.getTextWindow().setCursorPosition(j - 1,i);
                            cn.getTextWindow().output(GameField[i][j - 1], Boulder);
                        }
                    } else if (GameField[i][j] == 'X' && GameField[i - 1][j] == 'O') {
                        GameField[i][j] = 'O';
                        GameField[i - 1][j] = ' ';
                        cn.getTextWindow().setCursorPosition(j,i);
                        cn.getTextWindow().output(GameField[i][j], Boulder);
                        cn.getTextWindow().setCursorPosition(j,i-1);
                        cn.getTextWindow().output(GameField[i-1][j],b);
                        score = score + 900;
                    }
                }

            }

            if(newTime % 3 == 0 && tempTime != newTime && newTime != 0)
            { //Pushing and re-falling of boulders over time.
                tempTime = newTime;

                //Queue
                int printIndex = 0;
                char[] elements = {'O','1','2','3','X',':',' '};
                int pb = new Random().nextInt(23 - 1 + 1) + 1;
                int pa = new Random().nextInt(53 - 1 + 1) + 1;
                if(GameField[pb][pa] != '#' && (GameField[pb][pa]==':' || GameField[pb][pa]== ' ' ));
                {
                    cn.getTextWindow().setCursorPosition(pa,pb);
                    if ((char)inQueue.peek() == 'O'){
                        Player.cn.getTextWindow().output((char)inQueue.peek(), Boulder);

                    } else if ((char)inQueue.peek() == '1' || (char)inQueue.peek() == '2' || (char)inQueue.peek() == '3') {
                        Player.cn.getTextWindow().output((char)inQueue.peek(), NumberColor);

                    } else if ((char)inQueue.peek() == 'X') {
                        Player.cn.getTextWindow().output((char)inQueue.peek(),XColor);

                    } else if ((char)inQueue.peek() == ':') {
                        Player.cn.getTextWindow().output((char) inQueue.peek(),Earth);

                    } else if ((char)inQueue.peek() == ' ') {
                        Player.cn.getTextWindow().output((char) inQueue.peek(),b);
                    }
                    GameField[pb][pa] = (char) inQueue.peek();
                    char temp = (char) inQueue.peek();
                    inQueue.dequeue();
                    int randomnum = rnd.nextInt(40) + 1;
                    //Queue's adding a new element as the element is deleted in the game.

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
                    if(temp == 'O')
                    {
                        while (true)
                        {
                            pb = new Random().nextInt(23 - 1 + 1) + 1;
                            pa = new Random().nextInt(53 - 1 + 1) + 1;
                            if(GameField[pb][pa] == 'O')
                            {
                                GameField[pb][pa] = ':';
                                cn.getTextWindow().setCursorPosition(pa,pb);
                                cn.getTextWindow().output((':'),Earth);
                                break;
                            }
                        }
                    }

                }
                for (int i = 0; i < inQueue.size(); i++) {
                    Player.cn.getTextWindow().setCursorPosition(57+i,2);
                    Player.cn.getTextWindow().output((char)inQueue.peek(), InputQueue);
                    inQueue.enqueue(inQueue.dequeue());
                }

            }
        }
    }
}




