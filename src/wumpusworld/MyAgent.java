package wumpusworld;


/**
 * Contains starting code for creating your own Wumpus World agent.
 * Currently the agent only make a random decision each turn.
 * 
 * @author Johan Hagelbäck
 */
public class MyAgent implements Agent
{
    private static int m_firstTime = 0;
    private World w;
    int rnd;
    
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
        //Solve first map without general code 
       
        //First time
        if(m_firstTime == 0)
        {
            w.doAction(World.A_MOVE);
            m_firstTime++;  
            return 0;
        }
        
        if(direction == World.DIR_RIGHT)
        {
            //Current square, right, up, down, left
            CheckSquare(x, y, direction);
            return 0;
        }
        
        if(direction == World.DIR_UP)
        {
            //Current square, right, up, down, left
            CheckSquare(x, y, direction);
            return 0;
        }
          
        return 0;          
    }
    
    private void CheckSquare(int x, int y, int direction)
    {
        if(direction == World.DIR_RIGHT)
        {
            if (w.hasBreeze(x, y)) 
        {
            System.out.println("Square has breeze");
        }
        
        if (w.hasStench(x, y)) 
        {
            if(w.isUnknown(x + 1, y))
                w.doAction(World.A_TURN_LEFT);
                    
        }

        if (w.hasPit(x, y)) 
        {
            System.out.println("Square has pit");
        }
        }
        
        
        
   
        
        //Check surrounding of square
    }
}

