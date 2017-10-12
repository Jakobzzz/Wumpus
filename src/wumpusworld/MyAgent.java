package wumpusworld;

/**
 * Contains starting code for creating your own Wumpus World agent.
 * Currently the agent only make a random decision each turn.
 * 
 * @author Johan Hagelbäck
 */
public class MyAgent implements Agent
{
    private World w;
    int rnd;
    //Location of the wumpus
    int wX = 2;
    int wY = 2;
    
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
        if (w.hasBreeze(cX, cY))
        {
            System.out.println("I am in a Breeze");
            if (w.getDirection() == World.DIR_RIGHT)
            {
                int x = cX + 1;
                int y = cY;
                System.out.println("I am facing Right");
                if (w.isValidPosition(x, y))
                {
                    if (w.isVisited(x, y))
                    {
                        if (w.hasStench(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasBreeze(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasPit(x, y))
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                        else
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                    }
                    if ((w.hasStench(x, y + 1) && !w.isValidPosition(x, y - 1)) || (w.hasStench(x, y - 1) && !w.isValidPosition(x, y + 1))
                        || (w.hasStench(x, y + 1) || w.hasStench(x, y - 1)) || (w.hasStench(x, y - 1) && isSafe(cX, y - 1)) || (w.hasStench(x, y + 1) && isSafe(cX, y + 1)))
                    {
                        setWumpus(x, y);
                        rnd = decideRandomTurn();
                        if (rnd == 0)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            return;
                        }
                        else
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            return;
                        }
                    }

                    if (hasWumpus(x, y - 1) || hasWumpus(x, y + 1))
                    {
                        System.out.println("Wumpus");
                        w.doAction(World.A_MOVE);
                        return;
                    }
                    if ((w.isUnknown(x, y + 1) || !w.isValidPosition(x, y + 1)) && (w.isUnknown(x, y - 1) || !w.isValidPosition(x, y - 1)))
                    {
                        if (w.hasStench(x - 2, y + 1) || w.hasStench(x - 2, y - 1))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                    }
                    else if (w.hasBreeze(x, y + 1) || w.hasBreeze(x, y - 1))
                    {
                        rnd = decideRandomTurn();
                        if (rnd == 0)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            return;
                        }
                        else
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            return;
                        }
                    }
                    else
                    {
                        w.doAction(World.A_MOVE);
                        return;
                    }
                }
                else
                {
                    rnd = decideRandomTurn();
                    if (rnd == 0)
                    {
                        w.doAction(World.A_TURN_LEFT);
                        return;
                    }
                    else
                    {
                        w.doAction(World.A_TURN_RIGHT);
                        return;
                    }
                }
            }
            if (w.getDirection() == World.DIR_LEFT)
            {
                int x = cX - 1;
                int y = cY;
                System.out.println("I am facing Left");
                if (w.isValidPosition(x, y))
                {
                    if (w.isVisited(x, y))
                    {
                        if (w.hasStench(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasBreeze(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasPit(x, y))
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                        else
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                    }
                    if ((w.hasStench(x, y + 1) && !w.isValidPosition(x, y - 1)) || (w.hasStench(x, y - 1) && !w.isValidPosition(x, y + 1))
                        || (w.hasStench(x, y + 1) || w.hasStench(x, y - 1)) || (w.hasStench(x, y - 1) && isSafe(cX, y - 1)) || (w.hasStench(x, y + 1) && isSafe(cX, y + 1)))
                    {
                        setWumpus(x, y);
                        rnd = decideRandomTurn();
                        if (rnd == 0)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            return;
                        }
                        else
                    {   
                            w.doAction(World.A_TURN_RIGHT);
                            return;
                        }
                    }

                    if (hasWumpus(x, y - 1) || hasWumpus(x, y + 1))
                    {
                        System.out.println("Wumpus");
                        w.doAction(World.A_MOVE);
                        return;
                    }
                    if ((w.isUnknown(x, y + 1) || !w.isValidPosition(x, y + 1)) && (w.isUnknown(x, y - 1) || !w.isValidPosition(x, y - 1)))
                    {
                        if (w.hasStench(x + 2, y - 1) || w.hasStench(x + 2, y + 1))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                    }
                    else if (w.hasBreeze(x, y + 1) || w.hasBreeze(x, y - 1))
                    {
                        rnd = decideRandomTurn();
                        if (rnd == 0)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            return;
                        }
                        else
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            return;
                        }
                    }
                    else
                    {
                        w.doAction(World.A_MOVE);
                        return;
                    }
                }
                else
                {
                    rnd = decideRandomTurn();
                    if (rnd == 0)
                    { 
                        w.doAction(World.A_TURN_LEFT);
                        return;
                    }
                }
            }
            if (w.getDirection() == World.DIR_UP)
            {
                int x = cX;
                int y = cY + 1;
                System.out.println("I am facing Up");
                if (w.isValidPosition(x, y))
                {
                    if (w.isVisited(x, y))
                    {
                        if (w.hasStench(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasBreeze(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasPit(x, y))
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                        else
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                    }
                    if ((w.hasStench(x + 1, y) && !w.isValidPosition(x - 1, y)) || (w.hasStench(x - 1, y) && !w.isValidPosition(x + 1, y))
                        || (w.hasStench(x + 1, y) || w.hasStench(x - 1, y)) || (w.hasStench(x - 1, y) && isSafe(x - 1, cY)) || (w.hasStench(x, y + 1) && isSafe(x + 1, cY)))
                    {
                        setWumpus(x, y);
                        rnd = decideRandomTurn();
                        if (rnd == 0)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            return;
                        }
                        else
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            return;
                        }
                    }

                    if (hasWumpus(x - 1, y) || hasWumpus(x + 1, y))
                    {
                        System.out.println("Wumpus");
                        w.doAction(World.A_MOVE);
                        return;
                    }
                    if ((w.isUnknown(x + 1, y) || !w.isValidPosition(x + 1, y)) && (w.isUnknown(x - 1, y) || !w.isValidPosition(x - 1, y)))
                    {
                        if (w.hasStench(x + 1, y - 2) || w.hasStench(x - 1, y - 2))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                    } 
                    else if (w.hasBreeze(x + 1, y) || w.hasBreeze(x - 1, y))
                    {
                        rnd = decideRandomTurn();
                        if (rnd == 0)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            return;
                        }
                        else
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            return;
                        }
                    }
                    else
                    {
                        w.doAction(World.A_MOVE);
                        return;
                    }
                }
                else
                { 
                    rnd = decideRandomTurn();
                    if (rnd == 0)
                    {
                        w.doAction(World.A_TURN_LEFT);
                        return;
                    }
                    else
                    {
                        w.doAction(World.A_TURN_RIGHT);
                        return;
                    }
                }
            }
            if (w.getDirection() == World.DIR_DOWN)
            {
                int x = cX;
                int y = cY - 1;
                System.out.println("I am facing Down");
                if (w.isValidPosition(x, y))
                {
                    if (w.isVisited(x, y))
                    {
                        if (w.hasStench(x, y))
                        {
                            w.doAction(World.A_MOVE);
                        }
                        else if (w.hasBreeze(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasPit(x, y))
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                        else
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                    }
                    if ((w.hasStench(x, y + 1) && !w.isValidPosition(x, y - 1)) || (w.hasStench(x, y - 1) && !w.isValidPosition(x, y + 1))
                        || (w.hasStench(x, y + 1) || w.hasStench(x, y - 1)) || (w.hasStench(x, y - 1) && isSafe(x - 1, cY)) || (w.hasStench(x + 1, y) && isSafe(x + 1, cY)))
                    {
                        setWumpus(x, y);
                        rnd = decideRandomTurn();
                        if (rnd == 0)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            return;
                        }
                        else
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            return;
                        }
                    }
                    if (hasWumpus(x - 1, y) || hasWumpus(x + 1, y))
                    {
                        System.out.println("Wumpus");
                        w.doAction(World.A_MOVE);
                        return;
                    }
                    if ((w.isUnknown(x + 1, y) || !w.isValidPosition(x + 1, y)) && (w.isUnknown(x - 1, y) || !w.isValidPosition(x - 1, y)))
                    {
                        if (w.hasStench(x - 1, y + 2) || w.hasStench(x + 1, y + 2))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                    }
                    else if (w.hasBreeze(x + 1, y) || w.hasBreeze(x - 1, y))
                    {
                        rnd = decideRandomTurn();
                        if (rnd == 0)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            return;
                        }
                        else
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            return;
                        }
                    }
                    else
                    {
                        w.doAction(World.A_MOVE);
                        return;
                    }
                }
                else
                {
                    rnd = decideRandomTurn();
                    if (rnd == 0)
                    {
                        w.doAction(World.A_TURN_LEFT);
                        return;
                    }
                    else
                    {
                        w.doAction(World.A_TURN_RIGHT);
                        return;
                    }
                }
            }
        }
        if (w.hasStench(cX, cY))
        {
            System.out.println("I am in a Stench");

            if (w.getDirection() == World.DIR_RIGHT)
            {
                int x = cX + 1;
                int y = cY;
                System.out.println("I am facing Right");
                if (w.isValidPosition(x, y))
                {
                    if (w.isVisited(x, y))
                    {
                        if (w.hasStench(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasBreeze(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasPit(x, y))
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                        else
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                    }
                    if ((w.isUnknown(x, y + 1) || !w.isValidPosition(x, y + 1)) && (w.isUnknown(x, y - 1) || !w.isValidPosition(x, y - 1)))
                    {
                        if (w.hasStench(x - 2, y + 1) || w.hasStench(x - 2, y - 1))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                    }
                    else if (w.hasStench(x, y + 1) || w.hasStench(x, y - 1))
                    {
                        rnd = decideRandomTurn();
                        if (rnd == 0)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            return;
                        }
                        else
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            return;
                        }
                    }
                    else
                    {
                        w.doAction(World.A_MOVE);
                        return;
                    }
                }
                else
                {
                    rnd = decideRandomTurn();
                    if (rnd == 0)
                    {
                        w.doAction(World.A_TURN_LEFT);
                        return;
                    }
                    else
                    {
                        w.doAction(World.A_TURN_RIGHT);
                        return;
                    }
                }
            }
            if (w.getDirection() == World.DIR_LEFT)
            {
                int x = cX - 1;
                int y = cY;
                System.out.println("I am facing Left");
                if (w.isValidPosition(x, y))
                {
                    if (w.isVisited(x, y))
                    {
                        if (w.hasStench(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasBreeze(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasPit(x, y))
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                        else
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                    }
                    if ((w.isUnknown(x, y + 1) || !w.isValidPosition(x, y + 1)) && (w.isUnknown(x, y - 1) || !w.isValidPosition(x, y - 1)))
                    {
                        if (w.hasStench(x + 2, y + 1) || w.hasStench(x + 2, y - 1))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                    }
                    else if (w.hasStench(x, y + 1) || w.hasStench(x, y - 1))
                    {
                        rnd = decideRandomTurn();
                        if (rnd == 0)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            return;
                        }
                        else
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            return;
                        }
                    }
                    else
                    {
                        w.doAction(World.A_MOVE);
                        return;
                    }
                }
                else
                {
                    rnd = decideRandomTurn();
                    if (rnd == 0)
                    {
                        w.doAction(World.A_TURN_LEFT);
                        return;
                    }
                    else
                    {
                        w.doAction(World.A_TURN_RIGHT);
                        return;
                    }
                }
            }
            if (w.getDirection() == World.DIR_UP)
            {
                int x = cX;
                int y = cY + 1;
                System.out.println("I am facing Up");
                if (w.isValidPosition(x, y))
                {
                    if (w.isVisited(x, y))
                    {
                        if (w.hasStench(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasBreeze(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasPit(x, y))
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                        else
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                    }
                    if ((w.isUnknown(x + 1, y) || !w.isValidPosition(x + 1, y)) && (w.isUnknown(x - 1, y) || !w.isValidPosition(x - 1, y)))
                    {
                        if (w.hasStench(x + 1, y - 2) || w.hasStench(x - 1, y - 2))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                    }
                    else if (w.hasStench(x + 1, y) || w.hasStench(x - 1, y))
                    {
                        rnd = decideRandomTurn();
                        if (rnd == 0)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            return;
                        }
                        else
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            return;
                        }
                    }
                    else
                    {
                        w.doAction(World.A_MOVE);
                        return;
                    }
                }
                else
                {
                    rnd = decideRandomTurn();
                    if (rnd == 0)
                    {
                        w.doAction(World.A_TURN_LEFT);
                        return;
                    }
                    else
                    {
                        w.doAction(World.A_TURN_RIGHT);
                        return;
                    }
                }
            }
            if (w.getDirection() == World.DIR_DOWN)
            {
                int x = cX;
                int y = cY - 1;
                System.out.println("I am facing Down");
                if (w.isValidPosition(x, y))
                {
                    if (w.isVisited(x, y))
                    {
                        if (w.hasStench(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasBreeze(x, y))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else if (w.hasPit(x, y))
                        {
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                        else
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                    }
                    if ((w.isUnknown(x + 1, y) || !w.isValidPosition(x + 1, y)) && (w.isUnknown(x - 1, y) || !w.isValidPosition(x - 1, y)))
                    {
                        if (w.hasStench(x + 1, y + 2) || w.hasStench(x - 1,  y + 2))
                        {
                            w.doAction(World.A_MOVE);
                            return;
                        }
                        else
                        { 
                            rnd = decideRandomTurn();
                            if (rnd == 0)
                            {
                                w.doAction(World.A_TURN_LEFT);
                                return;
                            }
                            else
                            {
                                w.doAction(World.A_TURN_RIGHT);
                                return;
                            }
                        }
                    }
                    else if (w.hasStench(x + 1, y) || w.hasStench(x - 1, y))
                    {
                        rnd = decideRandomTurn();
                        if (rnd == 0)
                        {
                            w.doAction(World.A_TURN_LEFT);
                            return;
                        }
                        else
                        {
                            w.doAction(World.A_TURN_RIGHT);
                            return;
                        }
                    }
                    else
                    {
                        w.doAction(World.A_MOVE);
                        return;
                    }
                }
                else
                {
                    rnd = decideRandomTurn();
                    if (rnd == 0)
                    {
                        w.doAction(World.A_TURN_LEFT);
                        return;
                    }
                    else
                    {
                        w.doAction(World.A_TURN_RIGHT);
                        return;
                    }
                }
            }
        }
        if (w.hasPit(cX, cY))
        {
            System.out.println("I am in a Pit");
        }
        
        //decide next move
        rnd = decideRandomMove();
        if (rnd==0)
        {
            w.doAction(World.A_TURN_LEFT);
            w.doAction(World.A_MOVE);
        }
        
        if (rnd==1)
        {
            w.doAction(World.A_MOVE);
        }
                
        if (rnd==2)
        {
            w.doAction(World.A_TURN_LEFT);
            w.doAction(World.A_TURN_LEFT);
            w.doAction(World.A_MOVE);
        }
                        
        if (rnd==3)
        {
            w.doAction(World.A_TURN_RIGHT);
            w.doAction(World.A_MOVE);
        }
                
    }    
    
     /**
     * Genertes a random instruction for the Agent.
     */
    public int decideRandomMove()
    {
      return (int)(Math.random() * 4);
    }
    
    public int decideRandomTurn()
    {
        return (int)(Math.random() * 2);
    }

    public void setWumpus(int x, int y)
    {
        wX = x;
        wY = y;

        return;
    }

    public boolean hasWumpus(int x, int y)
    {
        if (x == wX && y == wY)
            return true;
        
        return false;
    }

    public boolean isSafe(int x, int y)
    {
        if (w.hasStench(x, y))
        {
            return true;
        }
        
        if (w.hasBreeze(x, y))
        {
            return true;
        }

        if (w.hasPit(x, y))
        {
            return true;
        }

        return false;
    }
}

