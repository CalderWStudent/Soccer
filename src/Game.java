import java.util.Random;

public class Game {
    private final int id;
    private final double temperature;
    private final Team awayTeam;
    private final Team homeTeam;
    private final int awayTeamScore;
    private final int homeTeamScore;

    public Game(int id, double temperature, Team awayTeam, Team homeTeam) {
        this.id = id;
        this.temperature = temperature;

        Random random = new Random();

        // Set the away and home Teams from the supplied teams array.
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;

        // Remap temperature values to the maximum limit of goals scored.
        int maxScore = tempToScore(temperature);

        // Set random scores associated with each Team.
        awayTeamScore = random.nextInt(maxScore);
        homeTeamScore = random.nextInt(maxScore);

        recordScoringToTeams();

        printInfo();
    }

    private static int tempToScore(double temperature) {
        double inMin = 40;
        double inMax = 60;
        double outMin = 4;
        double outMax = 8;
        double clampedTemp = Math.clamp(temperature, inMin, inMax);
        return (int)((clampedTemp - inMin) / (inMax - inMin) * (outMax - outMin) + outMin);
    }

    private void recordScoringToTeams() {
        // Increment the goals scored by each Team class by the team score variables.
        awayTeam.addGoalScored(awayTeamScore);
        homeTeam.addGoalScored(homeTeamScore);
        awayTeam.addGoalAllowed(homeTeamScore);
        homeTeam.addGoalAllowed(awayTeamScore);

        // Add to the wins, losses, and ties of each team by comparing the score from each team.
        if (awayTeamScore > homeTeamScore) {
            awayTeam.addWin(1);
            homeTeam.addLoss(1);
        }
        else if (awayTeamScore < homeTeamScore) {
            awayTeam.addLoss(1);
            homeTeam.addWin(1);
        }
        else {
            awayTeam.addTie(1);
            homeTeam.addTie(1);
        }
    }

    public void printInfo() {
        System.out.println("  Game #" + id +
                           "\n" +"    Away Team: " + awayTeam.getName() + ", " + awayTeamScore +
                           "\n" +"    Home Team: " + homeTeam.getName() + ", " + homeTeamScore);
    }
}