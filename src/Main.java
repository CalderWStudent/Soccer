import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    static void main(String[] args) {
        // The program works with more than four teams as well, as long as it is an even number. Here is an example Array for the names.
//        final String[] teamNames = {"Crows", "Jays", "Magpies", "Ravens", "Macaws", "Cockatoos", "Amazons", "Conures"};
        final String[] teamNames = {"Crows", "Jays", "Magpies", "Ravens"};
        final int freezingTemp = 32;
        int minTemp = 20;
        int maxTemp = 80;
        double highestTemp = 0;
        double tempSum = 0;
        int weekCount = 0;

        ArrayList<Game> gamesPlayed = new ArrayList<>();
        int currentId = 0;
        int consecutiveFreezing = 0;

        // Create Teams using the team names and add them to an array. The reason for using a separate array of names is so they names are easily accessible in one array if you want to change them, and also so there doesn't need to be multiple team constructors repeated.
        final Team[] teams = new Team[teamNames.length];
        for (int i = 0; i < teamNames.length; i++) {
            teams[i] = new Team(teamNames[i]);
        }

        // Create a Random object for generating random numbers, like temperatures and team matchups.
        Random random = new Random();

        // Create additional space in the console for clarity.
        System.out.println("");

        // The main weekly loop.
        while(consecutiveFreezing < 3) {
            weekCount++;

            // Set a random temperature for the week.
            double temperature = roundToTenths(random.nextDouble(minTemp, maxTemp));

            // Update the highest temperature to display at the end.
            if (temperature > highestTemp) {
                highestTemp = temperature;
            }

            // Add to the sum of temperatures to be averaged at the end.
            tempSum+=temperature;

            System.out.println("\nWeek #" + weekCount +
                               "\n" +"  Temperature: " + temperature + "°F");

            // If this week's temperature is greater than the freezing temperature, reset the consecutive number of freezing weeks in a row and create Games with full team information. Otherwise, add to the consecutive number and print a message.
            if (temperature > freezingTemp) {
                consecutiveFreezing = 0;

                // Create  an ArrayList with the Teams in a random order so each week has different matchups.
                ArrayList<Team> weekTeams = getRandomMatchups(random, teams);

                // Loop through the randomly sorted ArrayList and create a Game for every two Teams, with those Teams supplied to it for scoring. The Game will then print its info to the console.
                for (int i = 0; i < teams.length; i+=2) {
                    currentId++;
                    gamesPlayed.add(new Game(currentId, temperature, weekTeams.get(i), weekTeams.get(i + 1)));
                }
            }
            else {
                consecutiveFreezing++;

                System.out.println("  It is too cold to play.");
            }

            // Decrease the minimum and maximum random temperature ranges for every week to mimic the effect of temperatures decreasing as it gets closer to Winter, and so that the program doesn't run for unrealistically long amounts of time.
            minTemp = Math.clamp(minTemp - 1, 0, 80);
            maxTemp = Math.clamp(maxTemp - 2, minTemp, 80);
        }

        // Divide the averageTemp variable (which is currently the sum) by the number of Games to get the average temperature value, then round it down to the tenths place for readability.
        double averageTemp = (double) tempSum / weekCount;
        averageTemp = (double)(int)(averageTemp * 10) / 10;
        // It does not matter that the Games are in pairs with the same temperature value, the sum will still be the same as long as it is divided by the total number of Games.

        // Print out the final info notifying the user that the Games have ended, displaying the hottest and average temperature values, and all the scoring for each Team.
        System.out.println("\n\nThe season is over.\n");
        System.out.println("\nHottest Temperature: " + highestTemp + "°F" +
                           "\nAverage Temperature: " + averageTemp + "°F");
        for (Team team : teams) {
            team.printInfo();
        }

        // Create additional space in the console for clarity.
        System.out.println("");
    }

    private static ArrayList<Team> getRandomMatchups(Random random, Team[] teams) {

        // Create a temporary ArrayList from the main Array of teams to take the teams from without changing the main Array.
        ArrayList<Team> availableTeams = new ArrayList<>(Arrays.asList(teams));

        // The ArrayList to be returned.
        ArrayList<Team> randomTeams = new ArrayList<>();

        for (int i = 0; i < teams.length; i++) {
            // Get a team at a random index and add it ot the ArrayList so that the teams are in a random order. To prevent the program from picking the same team twice, remove the chosen team from the available list of teams to pick from.
            int addIndex = random.nextInt(availableTeams.size());
            randomTeams.add(availableTeams.get(addIndex));
            availableTeams.remove(addIndex);
        }
        return randomTeams;
    }

    private static double roundToTenths(double input) {
        return (double)(int)(input * 10) / 10;
    }
}