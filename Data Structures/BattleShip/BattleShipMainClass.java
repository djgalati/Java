package p1_package;

import java.util.Scanner;

public class BattleShipMainClass
{

    public static void main(String[] args)
    {
        int computerWaitTime = 1000;
        char shipTypeChar, shipOrientChar, userResponseChar;
        int shipXlocInput, shipYlocInput, fireCount, shipCount = 0;
        int boardWidth, boardHeight, computerScore, humanScore;
        boolean continueFlag, continueFiringFlag;
        BattleShipGameClass BSGC;
        Scanner kbInput = new Scanner( System.in );

        System.out.println( "BATTLE SHIP GAME\n" );

        System.out.print( "Enter the desired board WIDTH: " );
        boardWidth = kbInput.nextInt();

        System.out.print( "Enter the desired board HEIGHT: " );
        boardHeight = kbInput.nextInt();

        BSGC = new BattleShipGameClass( boardHeight, boardWidth );

        do
        {
            continueFlag = false;

            System.out.print( "\nEnter a ship letter( A,B,C,D,F ): " );

            shipTypeChar = kbInput.next().charAt( 0 );

            System.out.print( "Enter the ship orientation( V, H ): " );

            shipOrientChar = kbInput.next().charAt( 0 );

            System.out.print( "Enter x position to place bow of ship: " );
            shipXlocInput = kbInput.nextInt();

            System.out.print( "Enter y position to place bow of ship: " );
            shipYlocInput = kbInput.nextInt();

            if( !BSGC.placeShip( shipTypeChar, BattleShipGameClass.HUMAN,
                    shipXlocInput, shipYlocInput, shipOrientChar ) )
            {
                System.out.println( "Error in attempt to create ship, "
                        + "Please retry with different input.");

                continueFlag = true;
            }
            else
            {
                System.out.print( "\nA(n) " + BSGC.getShipName( shipTypeChar )
                        + " has been placed in a " );

                if( shipOrientChar == 'V' )
                {
                    System.out.print( "vertical orientation " );
                }

                System.out.println( "at an x location of " + shipXlocInput
                        + ", and a y location of " + shipYlocInput
                        + "." );

                shipCount++;


                System.out.print( "\nDo you wish to continue adding ships to your area (y/n): ");

                userResponseChar = kbInput.next().charAt( 0 );

                if( userResponseChar == 'y' || userResponseChar == 'Y' )
                {
                    continueFlag = true;
                }
            }
        }
        while( continueFlag );

        System.out.println( "\nYour current ship locations: " );
        BSGC.displayFields( ShipClass.HIDDEN_STATE );

        BSGC.setComputerShips( shipCount );

        // Play game
        System.out.println( "\nGame play starts, "
                + "computer will fire as many times as the player does" );

        do
        {
            continueFlag = false;
            fireCount = 0;

            do
            {
                continueFiringFlag = false;
                fireCount++;

                System.out.print( "\nEnter x position to fire at ship: " );
                shipXlocInput = kbInput.nextInt();

                System.out.print( "Enter y position to fire at ship: " );
                shipYlocInput = kbInput.nextInt();

                if( BSGC.fireMissile( BattleShipGameClass.HUMAN,
                        shipXlocInput, shipYlocInput ) )
                {
                    System.out.println( "You got a hit!" );
                }

                else
                {
                    System.out.println( "You missed . . .");
                }

                System.out.print( "Do you wish to continue firing (y/n): ");

                userResponseChar = kbInput.next().charAt( 0 );

                if( userResponseChar == 'y' || userResponseChar == 'Y' )
                {
                    continueFiringFlag = true;
                }

                else
                {
                    while( fireCount > 0 )
                    {
                        System.out.println( "\nComputer fires missile " );

                        if( BSGC.computerFiresMissile() )
                        {
                            System.out.println( "     - Computer scores hit!" );
                        }

                        else
                        {
                            System.out.println( "     - Computer misses . . ." );
                        }

                        fireCount--;

                        try
                        {
                            Thread.sleep( computerWaitTime );
                        }

                        catch( InterruptedException ie )
                        {
                            ie.printStackTrace();
                        }
                    }
                }
            }
            while( continueFiringFlag );

            BSGC.displayFields( ShipClass.EXPOSED_STATE );

            System.out.print( "Do you wish to continue playing (y/n): ");

            userResponseChar = kbInput.next().charAt( 0 );

            if( userResponseChar == 'y' || userResponseChar == 'Y' )
            {
                continueFlag = true;
            }
        }
        while( continueFlag );

        humanScore = BSGC.getScore( BattleShipGameClass.HUMAN );
        computerScore = BSGC.getScore( BattleShipGameClass.COMPUTER );
        System.out.println( "Human Score: " + humanScore );
        System.out.println( "Computer Score: " + computerScore );

        if( humanScore > computerScore )
        {
            System.out.println( "Human Player Wins!" );
        }

        else
        {
            System.out.println( "Computer Player Wins!" );
        }

        kbInput.close();
    }

}
