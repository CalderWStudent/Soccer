public class Team {
    private final String name;
    private int totalWins;
    private int totalLosses;
    private int totalTies;
    private int totalGoalsScored;
    private int totalGoalsAllowed;

    public Team(String teamName) {
        name = teamName;
    }

    public String getName() {
        return name;
    }

    // These methods make incrementing the data fields of a Team more clean. Instead of "setTotalWins(getTotalWins() + 1)" you simply call "addWin()"

    public void addWin(int wins) {
        this.totalWins +=wins;
    }

    public void addLoss(int losses) {
        this.totalLosses +=losses;
    }

    public void addTie(int ties) {
        this.totalTies +=ties;
    }

    public void addGoalScored(int goalsScored) {
        this.totalGoalsScored +=goalsScored;
    }

    public void addGoalAllowed(int goalsAllowed) {
        this.totalGoalsAllowed +=goalsAllowed;
    }

    // Print out the total scoring info for this team across all the games.
    public void printInfo() {
        System.out.println("\n" + name +
                           "\n" + "Wins: " + totalWins + ", Losses: " + totalLosses + ", Ties: " + totalTies +
                           "\n" + "Points Scored: " + totalGoalsScored + ", Points Allowed: " + totalGoalsAllowed);
    }
}