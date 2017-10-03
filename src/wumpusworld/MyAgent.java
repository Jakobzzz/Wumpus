package wumpusworld;

import java.util.Vector;

/**
 * Contains starting code for creating your own Wumpus World agent.
 * Currently the agent only make a random decision each turn.
 * 
 * @author Johan Hagelbäck
 */
public class MyAgent implements Agent
{
    private static int m_firstTime = 0;
    private String m_perception;
    private static Vector<String> m_sentences = new Vector<>();
    private World w;
    int rnd;
      
    private enum Direction
    {
        UP(0),
        RIGHT(1),
        DOWN(2),
        LEFT(3);  
        
        private int value;
        private Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    }
    
    /**
     * Creates a new instance of your solver agent.
     * 
     * @param world Current world state 
     */
    public MyAgent(World world)
    {
        w = world;   
    }
   
            
    /**
     * Asks your solver agent to execute an action.
     */

    public void doAction()
    {
        //Location of the player
        int cX = w.getPlayerX();
        int cY = w.getPlayerY();
                
        //Basic action:
        //Grab Gold if we can.
        if (w.hasGlitter(cX, cY))
        {
            w.doAction(World.A_GRAB);
            return;
        }
        
        //Basic action:
        //We are in a pit. Climb up.
        if (w.isInPit())
        {
            w.doAction(World.A_CLIMB);
            return;
        }
        
        //Test the environment
//        if (w.hasBreeze(cX, cY))
//        {
//            System.out.println("I am in a Breeze");
//        }
//        if (w.hasStench(cX, cY))
//        {
//            System.out.println("I am in a Stench");
//        }
//        if (w.hasPit(cX, cY))
//        {
//            System.out.println("I am in a Pit");
//        }
//        if (w.getDirection() == World.DIR_RIGHT)
//        {
//            System.out.println("I am facing Right");
//        }
//        if (w.getDirection() == World.DIR_LEFT)
//        {
//            System.out.println("I am facing Left");
//        }
//        if (w.getDirection() == World.DIR_UP)
//        {
//            System.out.println("I am facing Up");
//        }
//        if (w.getDirection() == World.DIR_DOWN)
//        {
//            System.out.println("I am facing Down");
//        }
        
        
        //Player agent
        //w.doAction(World.A_MOVE);
        MakeNextMove(cX, cY, w.getDirection());
            
        //decide next move
        //rnd = decideRandomMove();
//        if (rnd==0)
//        {
//            w.doAction(World.A_TURN_LEFT);
//            w.doAction(World.A_MOVE);
//        }
//        
//        if (rnd==1)
//        {
//            w.doAction(World.A_MOVE);
//        }
//                
//        if (rnd==2)
//        {
//            w.doAction(World.A_TURN_LEFT);
//            w.doAction(World.A_TURN_LEFT);
//            w.doAction(World.A_MOVE);
//        }
//                        
//        if (rnd==3)
//        {
//            w.doAction(World.A_TURN_RIGHT);
//            w.doAction(World.A_MOVE);
//        }          
    }    
    
     /**
     * Genertes a random instruction for the Agent.
     */
    public int decideRandomMove()
    {
      return (int)(Math.random() * 4);
    }
    
    private int MakeNextMove(int x, int y, int direction)
    {      
        //Check surrounding area of the player       
        //First time
        if(m_firstTime == 0)
        {
            w.doAction(World.A_MOVE);
            m_firstTime++;  
            return 0;
        }
        
        if(direction == World.DIR_RIGHT)
        {
            String current, right, left;
            
            //Current square, right, left
            current = PerceptSentence(x, y);
            right = PerceptSentence(x + 1, y);
            left = PerceptSentence(x - 1, y);
            m_perception = left + ", " + current + ", " + right;
            m_sentences.add(m_perception);
            System.out.println(m_perception);
            
            if(m_perception.contentEquals("None, Stench, None"))
            {
                SetDirection(Direction.LEFT);                       
                w.doAction(World.A_MOVE);
            }
        }
        
        if(direction == World.DIR_UP)
        {
            String current, top, bottom;
            
            //Current square, top, bottom
            //1. Construct sentence from perception
            current = PerceptSentence(x, y);
            top = PerceptSentence(x, y + 1);
            bottom = PerceptSentence(x, y - 1);
            m_perception = bottom + ", " + current + ", " + top;
            m_sentences.add(m_perception);
            System.out.println(m_perception);
            
            //2. Ask knowledge base what action to perform from the informaion
            //available
            
            //3. Perform task from the action taken
            
            
            if(m_perception.contentEquals("None, Stench, None"))
            {           
                w.doAction(World.A_MOVE);
            }
            
            if(m_perception.contentEquals("Stench, Breeze, None"))
            {           
                SetDirection(Direction.RIGHT);
                w.doAction(World.A_MOVE);
            }
        }
        
        if(direction == World.DIR_LEFT)
        {
            String current, left, right;
            
            //Current square
            current = PerceptSentence(x, y);
            left = PerceptSentence(x - 1, y);
            right = PerceptSentence(x + 1, y);
            m_perception = left + ", " + current + ", " + right;
            m_sentences.add(m_perception);
            System.out.println(m_perception);
            
            if(m_perception.contentEquals("Invalid, None, Stench"))
            {           
                SetDirection(Direction.UP);
                w.doAction(World.A_MOVE);
            }
        }
        
        if(direction == World.DIR_DOWN)
        {
            String current, top, down;
            
            //Current square
            current = PerceptSentence(x, y);
            top = PerceptSentence(x, y + 1);
            down = PerceptSentence(x, y - 1);
            m_perception = down + ", " + current + ", " + top;
            m_sentences.add(m_perception);
            System.out.println(m_perception);
        }
          
        return 0;          
    }
    
    private String PerceptSentence(int x, int y)
    {   
        if(w.isValidPosition(x, y))
        {      
            if (w.hasBreeze(x, y)) 
            {
                return "Breeze";
            }

            if (w.hasStench(x, y)) 
            {
                return "Stench";
            }

            if (w.hasPit(x, y)) 
            {
                return "Pit";
            }
            
            if(w.isUnknown(x, y))
            {
                return "None";
            }
            
            if(w.isVisited(x, y))
            {
                return "None";
            }
            
            if(w.getPlayerX() == 1 && w.getPlayerY() == 1)
            {
                return "Safe";
            }       
        }
        else
        {
            return "Invalid";
        }
        
        return "";
    }
    
    private void SetDirection(Direction direction)
    {
        while(w.getDirection() != direction.getValue())
             w.doAction(World.A_TURN_LEFT);            
    }
}

