// -------------------------------------------------------
// Assignment #3 Question #1
// Written by: Razvan Ivan (ID: 40096278)
// For COMP 248 Section (Q) � Fall 2018
// --------------------------------------------------------

import java.util.Scanner;
import java.util.Random;

//Purpose of the program: To simulate what a World Cup tournament is like,
//starting from the Round of 16 with 16 teams, where the user enters his/her
//favorite team and then the program simulates the tournament multiple times
//until the team of the user has won the tournament or until the maximum tournament
//iterations of 20 has been reached.

@SuppressWarnings("Duplicates")

public class World_Cup_Simulator {

    public static void main(String[] args) {

        int userTeam;//Defines the variable which will contain the index of the array
                    // (i.e. the position in the array where the user's favorite is located
                    // in the array) with all the teams that participate into the tournament.

        //Prints a welcome message to the user.
        System.out.println("*********************************************************************");
        System.out.println("\nWelcome to my world cup simulator!");
        System.out.println("\n*********************************************************************");

        //A statement which calls the method Team_Checker (see the Team_checker method for more info on what it does).
        userTeam = Team_Checker();

        //An if statement which checks to see if the method called Tournament (see the Tournament method for more info
        //on what it does) returns true. If it does then it means that the team that won the world cup tournament
        //is the user's favorite team and so the program will congratulate the user on that. However, if after 20 tournaments the
        //user's favorite team has not won then the method Tournament will return false and the program will inform the user of the sad news.
        if(Tournament(userTeam))
            System.out.println("\nCongrats on your favorite team winning!");

        else
            System.out.println("Sorry, your team did not win in 20 tournaments");

        //Displays a closing message.
        System.out.println("\n*********************************************************************");

        System.out.println("\nThank you for using my world cup simulator!");

        System.out.println("\n*********************************************************************");

        System.out.println("\nThis program was written by Razvan Ivan on the 10th of November 2018.");

        System.out.println("\n*************************END OF THE PROGRAM.*************************");






    }

    /**
     * Purpose:To check if the team that the user has entered is participating in the round of 16 of the world cup.
     * @return An integer which corresponds to the index at which the user's favorite team is located in the array that has all names of the teams of the round of 16 in string format.
     */
    public static int Team_Checker() {

        //This is where the different variables that will be used in the method are defined.
        String teamUser;//A string which contains the user's favorite team.
        int favTeam;//An integer variable which contains the place in the array (the index) at which the user's favorite team is located.
        String[] teams16 = {"Uruguay", "Portugal", "France", "Argentina", "Brazil", "Mexico",
                "Belgium", "Japan", "Spain", "Russia", "Croatia", "Denmark", "Sweden", "Switzerland",
                "Colombia", "England"};//An array that contains 16 strings where each is filled with the name of a team
                                        //that is qualified to play in the round of 16.
        Boolean check = false;//A boolean which is changed to true if the user enters a team that is present in the
                                //round of 16. Otherwise, it stays false which will make it so that the program will tell
                                //the user that the team he/she entered is not playing in the round of 16.

        Scanner keyboard = new Scanner(System.in);//Defines an object called keyboard which is an instance of the Scanner class.

        //Asks the user to enter their favorite team and then stores the answer in the string teamUser.
        System.out.print("\nPlease enter you favorite team:");
        teamUser = keyboard.next();

        //A for loop which iterates through all the array that contains the team names of the teams that will play in the round of 16.
        for(favTeam = 0; favTeam<teams16.length; favTeam++){
            if(teamUser.equalsIgnoreCase(teams16[favTeam])){ //An if statement which checks to see if the user's favorite
                check = true;                                //team matches with the current team name at a specific position.
                break;
            }
        }

        //An if statement which checks to see if the boolean called check is still false and if it is, it will tell the user that the team
        //he/she entered is not in the round of 16 and then the program will end.
        if(check != true) {
            System.out.println("Your team is not in the Round of 16.");
            System.exit(0);
        }

        return favTeam; //Returns the integer favTeam.
    }

    /**
     * Purpose: Simulates the whole sequence of 20 tournaments from round of 16 to the finals and checks at the end of every tournament
     *          if the user's team has won. If it hasn't, another tournament is simulated and thus the method repeats the process for a
     *          maximum of 20 times before stopping. If the user's team has won, then the method prints out a couple of different pieces
     *          of information (total goals for each match, goal average, etc.) and then returns true.
     *
     * @param favTeam
     * @return Returns a boolean where true means that the user's favorite team has won in at least 20 tournament iterations and where
     *         false means that the user's favorite team has still not won in 20 tournament iterations.
     */
    public static Boolean Tournament(int favTeam) {


        String[] teamsRound16 = {"Uruguay", "Portugal", "France", "Argentina", "Brazil", "Mexico",
                "Belgium", "Japan", "Spain", "Russia", "Croatia", "Denmark", "Sweden", "Switzerland",
                "Colombia", "England"};//An array that contains strings. Those strings are the names of all the teams that participate in the round of 16.
        String[] teamsRound8 = new String[8];//An array that contains strings which will store the names of the winners of the round of 16.
        String[] teamsRound4 = new String[4];//An array that contains strings which will store the names of the winners of the quarter-finals.
        String[] teamsRound2 = new String[2];//An array that contains strings which will store the names of the winners of the semi-finals.
        String winner;//A string object which stores the name of the winner of the tournament.
        String teamUser = teamsRound16[favTeam];//A string object which stores the name of the user's favorite team using the index provided.

        int[] teams16Scores = new int[16];//An array that contains integers which will store the number of goals that each team scored during their match of the round of 16.
        int[] teams8Scores = new int[8];//An array that contains integers which will store the number of goals that each team scored during their match of the quarter-finals.
        int[] teams4Scores = new int[4];//An array that contains integers which will store the number of goals that each team scored during their match of the semi-finals.
        int[] teams2Scores = new int[2];//An array that contains integers which will store the number of goals that each team scored during the final game.
        int[][]totalScores = new int[20][15];//An array that contains integers. Each row represents one tournament and each column represents one game out of the fifteen games that are played in total in a single tournament. The number of rows of the array is 20 since the maximum amount of tournaments is 20.
        double[] goalsSum = new double[20];//An array that contains doubles where each will store the total amount of goals scored in a specific tournament. The length of the array is 20 since the maximum amount of tournaments is 20.
        double goalsAverageTournament=0;//A double variable which will store the average goals scored in a match when taking into account all matches from all the tournaments combined.

        int index1=0;//An integer variable used to iterate through the teams16Scores, teams8Scores, teams4Scores, and teams2Scores arrays.
        int index2=0;//An integer variable used to iterate through the different columns of the totalScores 2D array.
        int index3=0;//An integer variable used to iterate through the teams8Scores, teams4Scores, and teams2Scores arrays.
        int count=0;//An integer variable which is used as a counter to count the number of matches played where the number of goals scored was higher than the average goals in a match when taking into account all matches from all the tournaments combined.

        Random rdm = new Random();//Creates an instance of the class Random which is called rdm. rdm will be used to generate random numbers which will simulate the final score-line for every match.

        //A for loop which contains the whole tournament simulation within it. The loop will simulate another tournament up until the user's favorite team has won or until 20 tournaments have been simulated.
        for(int tournamentNumber=0; tournamentNumber<20; tournamentNumber++)
        {
            //A for loop which iterates through the array that will contain the amount of goals scored by each team in the round of 16. The for loop essentially fills up every spot of that array with a random number between 0 and 4.
            for (int i = 0; i < 16; i++)
            {
                teams16Scores[i] = rdm.nextInt(4 + 1);//Uses the object rdm of the class Random to put a random number in a spot in the array.
            }

            //Prints out the number of the tournament and also tells the user that the matches that will follow were played in the round of 16.
            System.out.println("\nTournament #:" + tournamentNumber);
            System.out.println("\nRound of 16:");

            //A do-while loop which essentially pairs up teams two by two so that 8 matches take place in the round of 16. Each iteration of the do-while loop
            //looks at a pair of teams and determines who the winner is based on who scored the most goals. It then keeps going until it has formed 8 pairs (or created 8 games).
            do
            {
                //An if statement which checks to see if the two paired up teams have scored the same amount of goals. If they have scored an equal amount of goals, then
                //the rdm object of the Random class randomly generates a 1 or a 0 and based on the result obtained (0 or 1), one of the paired up team has its goals scored
                //increased by 1.
                if (teams16Scores[index1] == teams16Scores[index1 + 1])
                {
                    if (rdm.nextInt(2) == 0)
                        teams16Scores[index1]++;
                    else
                        teams16Scores[index1 + 1]++;
                }

                //Prints out the names of two teams who have played against each other and the amount of goals each has scored.
                System.out.println(teamsRound16[index1] + " " + teams16Scores[index1] + ":" + teams16Scores[index1 + 1] + " " + teamsRound16[index1 + 1]);

                //An if-else statement which checks to see which team from the two paired up teams has scored more goals. Once it finds out which of the two
                //teams has scored the most goals, it stores the name of the winning team in the array that contains the names of the
                //teams that will play in the quarter-finals.
                if (teams16Scores[index1] > teams16Scores[index1 + 1])
                {
                    teamsRound8[index3] = teamsRound16[index1];
                    index3++;
                }
                else
                {
                    teamsRound8[index3] = teamsRound16[index1 + 1];
                    index3++;
                }

                //Stores the total amount of goals scored by the two paired up teams in the corresponding place in the array that stores all the total goals of each match for each tournament.
                totalScores[tournamentNumber][index2] = teams16Scores[index1] + teams16Scores[index1 + 1];

                //Increments index2 and index 1. To know more about their use, please refer to the section where both variables were declared.
                index2++;
                index1 += 2;

            } while (index1 < 16);//The condition of the do-while loop which basically makes it so that the program loops through the whole teams16Scores array which stores the goals scored by each team in the round of 16.

            //Resets index1 and index3 back to 0 since they will be reused to iterate through the beginning of the arrays teams8Scores and teamsRound4 respectively.
            index1 = 0;
            index3 = 0;

            //A for loop which iterates through the array that will contain the amount of goals scored by each team in the quarter-finals. The for loop essentially fills up every spot of that array with a random number between 0 and 4.
            for (int i = 0; i < 8; i++)
            {
                teams8Scores[i] = rdm.nextInt(4 + 1);
            }

            //Tells the user that the matches that will follow were played in the quarter-finals.
            System.out.println("\nQuarter-finals:");

            //A do-while loop which essentially pairs up teams two by two so that 4 matches take place in the quarter-finals. Each iteration of the do-while loop
            //looks at a pair of teams and determines who the winner is based on who scored the most goals. It then keeps going until it has formed 4 pairs (or created 4 games).
            do
            {

                //An if statement which checks to see if the two paired up teams have scored the same amount of goals. If they have scored an equal amount of goals, then
                //the rdm object of the Random class randomly generates a 1 or a 0 and based on the result obtained (0 or 1), one of the paired up team has its goals scored
                //increased by 1.
                if (teams8Scores[index1] == teams8Scores[index1 + 1])
                {
                    if (rdm.nextInt(2) == 0)
                        teams8Scores[index1]++;
                    else
                        teams8Scores[index1 + 1]++;
                }

                //Prints out the names of two teams who have played against each other and the amount of goals each has scored.
                System.out.println(teamsRound8[index1] + " " + teams8Scores[index1] + ":" + teams8Scores[index1 + 1] + " " + teamsRound8[index1 + 1]);

                //An if-else statement which checks to see which team from the two paired up teams has scored more goals. Once it finds out which of the two
                //team has scored the most goals, it stores the name of the winning team in the array that contains the names of the
                //teams that will play in the semi-finals.
                if (teams8Scores[index1] > teams8Scores[index1 + 1])
                {
                    teamsRound4[index3] = teamsRound8[index1];
                    index3++;
                }
                else
                {
                    teamsRound4[index3] = teamsRound8[index1 + 1];
                    index3++;
                }

                //Stores the total amount of goals scored by the two paired up teams in the corresponding place in the array that stores all the total goals of each match for each tournament.
                totalScores[tournamentNumber][index2] = teams8Scores[index1] + teams8Scores[index1 + 1];

                //Increments index2 and index 1. To know more about their use, please refer to the section where both variables were declared.
                index2++;
                index1 += 2;

            } while (index1 < 8);//The condition of the do-while loop which basically makes it so that the program loops through the whole teams8Scores array which stores the goals scored by each team in the quarter-finals.

            //Resets index1 and index3 back to 0 since they will be reused to iterate through the beginning of the arrays teams4Scores and teamsRound2 respectively.
            index1 = 0;
            index3 = 0;

            //A for loop which iterates through the array that will contain the amount of goals scored by each team in the semi-finals. The for loop essentially fills up every spot of that array with a random number between 0 and 4.
            for (int i = 0; i < 4; i++)
            {
                teams4Scores[i] = rdm.nextInt(4 + 1);
            }

            //Tells the user that the matches that will follow were played in the quarter-finals.
            System.out.println("\nSemi-finals");

            //A do-while loop which essentially pairs up teams two by two so that 2 matches take place in the semi-finals. Each iteration of the do-while loop
            //looks at a pair of teams and determines who the winner is based on who scored the most goals. It then keeps going until it has formed 2 pairs or 2 games.
            do
            {

                //An if statement which checks to see if the two paired up teams have scored the same amount of goals. If they have scored an equal amount of goals, then
                //the rdm object of the Random class randomly generates a 1 or a 0 and based on the result obtained (0 or 1), one of the paired up team has its goals scored
                //increased by 1.
                if (teams4Scores[index1] == teams4Scores[index1 + 1])
                {
                    if (rdm.nextInt(2) == 0)
                        teams4Scores[index1]++;
                    else
                        teams4Scores[index1 + 1]++;
                }

                //Prints out the names of two teams who have played against each other and the amount of goals each has scored.
                System.out.println(teamsRound4[index1] + " " + teams4Scores[index1] + ":" + teams4Scores[index1 + 1] + " " + teamsRound4[index1 + 1]);

                //An if-else statement which checks to see which team from the two paired up teams has scored more goals. Once it finds out which of the two
                //team has scored the most goals, it stores the name of the winning team in the array that contains the names of the
                //teams that will play in the finals.
                if (teams4Scores[index1] > teams4Scores[index1 + 1])
                {
                    teamsRound2[index3] = teamsRound4[index1];
                    index3++;
                }
                else
                {
                    teamsRound2[index3] = teamsRound4[index1 + 1];
                    index3++;
                }

                //Stores the total amount of goals scored by the two paired up teams in the corresponding place in the array that stores all the total goals of each match for each tournament.
                totalScores[tournamentNumber][index2] = teams4Scores[index1] + teams4Scores[index1 + 1];

                //Increments index2 and index 1. To know more about their use, please refer to the section where both variables were declared.
                index2++;
                index1 += 2;

            } while (index1 < 4);//The condition of the do-while loop which basically makes it so that the program loops through the whole teams4Scores array which stores the goals scored by each team in the semi-finals.

            //Resets index1 back to 0 since it will be reused to iterate through the beginning of the array teams2Scores. Index3 is reset back to 0 so it can be reused at the beginning of the first for loop in case another tournament takes place.
            index1 = 0;
            index3 = 0;

            //A for loop which iterates through the array that will contain the amount of goals scored by the two teams in the finals. The for loop essentially fills up every spot of that array with a random number between 0 and 4.
            for (int i = 0; i < 2; i++)
            {
                teams2Scores[i] = rdm.nextInt(4 + 1);
            }

            //Tells the user that the match that will follow was played in the finals.
            System.out.println("\nFinals:");

            //An if statement which checks to see if the two remaining teams have scored the same amount of goals. If they have scored an equal amount of goals, then
            //the rdm object of the Random class randomly generates a 1 or a 0 and based on the result obtained (0 or 1), one of the paired up team has its goals scored
            //increased by 1.
            if (teams2Scores[index1] == teams2Scores[index1 + 1])
            {
                if (rdm.nextInt(2) == 0)
                    teams2Scores[index1]++;
                else
                    teams2Scores[index1 + 1]++;
            }

            //Prints out the names of two teams who have played in the final game and the amount of goals each has scored.
            System.out.println(teamsRound2[index1] + " " + teams2Scores[index1] + ":" + teams2Scores[index1 + 1] + " " + teamsRound2[index1 + 1]);

            //An if-else statement which checks to see which team scored more than the other. Once the program finds out who it is, then the name of that
            //team will be stored as a string in the string object named winner.
            if (teams2Scores[index1] > teams2Scores[index1 + 1])
                winner = teamsRound2[index1];

            else
                winner = teamsRound2[index1 + 1];

            //Stores the total amount of goals scored by the two teams in the corresponding place in the array that stores all the total goals of each match for each tournament.
            totalScores[tournamentNumber][index2] = teams4Scores[index1] + teams4Scores[index1 + 1];

            //Prints out who the winner of the current tournament is.
            System.out.println("\n***The winner is " + winner + " ***\n");

            //Index2 is reset back to 0 so it can be reused at the beginning of the first for loop in case another iteration of the tournament takes place.
            index2=0;

            //An if statement which checks to see if the winner of the current tournament is the same as the user's favorite team. If it is, then a few calculations
            //will take place (further discussed below) and the method will return true and will end. If, however, the user's favorite team is not the same as the winner of the
            //current tournament, then the program will return at the beginning of the first for loop that was seen and this will start the iteration of a new tournament if
            //the iteration limit of 20 tournament has not yet been reached.
            if (winner.equals(teamUser))
            {
                //Prints out how many tournaments it took for the user's favorite team to win. We add plus one since the tournamentNumber variable started at zero and not one.
                System.out.println("\nIt took " + (tournamentNumber +1) + " tournament(s) of the game for " + teamUser + " to win!");

                //A for loop that prints out the total amount of goals for each match and does this for every tournament that was simulated. The for loop
                //also stores the total goals of every tournament in the corresponding place of the goalsSum array (where the first place in the array
                //represents the total amount of goals scored in the first tournament and so on and so forth).
                for( int y = 0; y<= tournamentNumber; y++)
                {
                    System.out.println("\n\nTournament #" + (y+1) + " total goals:");//We add plus one to y since it started at zero and because displaying tournament #0 doesn't really makes sense.
                    for(int i = 0; i<totalScores[y].length; i++)
                    {
                        System.out.print(" ");
                        System.out.print(totalScores[y][i]);
                        System.out.print(", ");
                        goalsSum[y]+= totalScores[y][i];


                    }

                    //Prints out the average of the goals scored per game for each tournament that was simulated.
                    System.out.print("||  Average: " + Math.round((goalsSum[y]/15)*10.0)/10. );
                }

                //A for loop which adds all the goals of all the tournaments into the variable goalsAverageTournament which will be used to calculate
                //the average of goals per match based on all the matches of all the tournaments that were simulated.
                for(int y =0; y<=tournamentNumber; y++)
                    goalsAverageTournament += goalsSum[y];

                //Calculates the average of goals per match based on all the matches of all the tournaments that were simulated and puts the result into
                //the variable goalsAverageTournament. This operation uses the previous value of the variable goalsAverageTournament which was the sum
                //of all the goals of every match of every tournament.
                goalsAverageTournament = Math.round((goalsAverageTournament/(15*(tournamentNumber+1)))*10.0)/10.;

                //Prints out the average goals scored per match when taking into account all the matches from all the simulated tournaments.
                System.out.println("\n\nAverage goals for " + (tournamentNumber+1) + " tournament(s): " + goalsAverageTournament);

                //A for loop with a nested for loop which iterate through the total goals scored of every match for every tournament. The for loops check
                //to see if every match that was simulated has a higher total goals scored than the average of all the goals for all the tournaments. If it
                //does have a higher total then the variable count is increased. The point of these two for loops is to know how many matches have a higher total goals
                //scored count than the average of the goals scored per match when taking into account all games of all the tournaments.
                for( int y = 0; y<= tournamentNumber; y++)
                    for(int i = 0; i<totalScores[y].length; i++)
                        if(totalScores[y][i] > goalsAverageTournament)
                            count++;

                //Tells the user how many of the matches that were played have a higher total goals scored count than the average of the goals scored per match when taking into account all games of all the tournaments.
                System.out.println("Total matches in all tournaments over the average goal value: " + count);

                return true;
            }
        }
        return false;
    }
}

