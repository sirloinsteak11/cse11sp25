///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              Dungeon
// Files:              Dungeon.java
// Quarter:            CSE11 SPRING 2025
//
// Author:             DEONN ALMIROL
// Email:              DALMIROL@UCSD.EDU
// Instructor's Name:  BEN OCHOA
//
///////////////////////////////////////////////////////////////////////////////
//                   STUDENTS WHO GET HELP COMPLETE THIS SECTION
//                   You must fully acknowledge and credit sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but roommates, relatives, strangers, etc do.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   Avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * dungeon class to handle main gameplay loop and usage of all gameplay
 * elements
 * 
 * @author deonn
 */
public class Dungeon {
    // Necessary constants
    private final static int SPACING = 17;
    private final static String LEFT = "Left";
    private final static String RIGHT = "Right";

    /**
     * intended to be inaccessible consutrctor for dungeon
     */
    private Dungeon() {
        System.out.println("lolololol");
    }

    /**
     * calculates odds based on power and also uses the compareTO
     * method to refine this value
     * 
     * @param monster1 monster 1
     * @param monster2 monster 2
     * @return the overall betting odds
     */
    public static double calculateBettingOdds(Monster monster1,
                                              Monster monster2) {

        double power1 = monster1.calculatePower();
        double power2 = monster2.calculatePower();

        double monster1win = power1 / (power1 + power2);

        double odds = monster1win / (1 - monster1win);

        if (monster1.compareTo(monster2) < 0) {
            odds *= 0.8;
        }
        if (monster1.compareTo(monster2) > 0) {
            odds *= 1.2;
        }

        return odds;
    }

    /**
     * calls the monsters applyArmoryEffect
     * 
     * @param monster the monster to apply armory effect on
     */
    public static void armory(Monster monster) {
        monster.applyArmoryEffect();
    }

    public static int showdown(Monster monster1, Monster monster2) {
        int round = 0;

        while (monster1.getVitality() > 0 && monster2.getVitality() > 0) {
            printRound(round);
            printBothMonsters(monster1, monster2);
            monster1.performSpecialAbility(monster2);
            monster2.performSpecialAbility(monster1);
            printAttack(LEFT, monster1.attack(monster2));
            printAttack(RIGHT, monster2.attack(monster1));
            monster1.handleDeathrattle();
            monster2.handleDeathrattle();
            if (monster1.isAlive()) {
                monster1.rest();
            }
            if (monster2.isAlive()) {
                monster2.rest();
            }
            round++;
        }
        boolean poisoned = monster1.poisoned || monster2.poisoned;
        printFinalStats(monster1, monster2, poisoned);
        if(!monster1.isAlive() && !monster2.isAlive()) {
            printTieGame();
            return 0;
        }
        if (!monster2.isAlive()) {
            printWinner(LEFT);
            return 1;
        }
        if (!monster1.isAlive()) {
            printWinner(RIGHT);
            return 2;
        }

        return -1;
    }

        /* Below are helper methods to make showdown() work */

    /**
     * Use this method in showdown() to display the stats of both
     * monsters together
     *
     * @param monster1 Monster on the left side to display stats
     * @param monster2 Monster on the right side to display stats
     */
    public static void printBothMonsters(Monster monster1, Monster monster2) {
    	int armorSpacing = calcSpacing(Integer.toString(monster1.getArmor()));
    	int healthSpacing = calcSpacing(Integer.toString(monster1.getVitality()));
    	int strSpacing = calcSpacing(
                            	String.format("%.2f",monster1.getSpeed()));
    	int monsterSpacing = calcSpacing(monster1.getClass().getName());
    	String str = String.format( "(%s) %s  (%s)\n" +
            	"----------" + "        	" + "----------\n" +
            	"A: %d %s A: %d\n" +
            	"V: %d %s V: %d\n" +
            	"S: %.2f %s S: %.2f\n", monster1.getClass().getName(),
            	" ".repeat(monsterSpacing),monster2.getClass().getName(),
            	monster1.getArmor()," ".repeat(armorSpacing),monster2.getArmor(),
            	monster1.getVitality(), " ".repeat(healthSpacing),
            	monster2.getVitality(), monster1.getSpeed(),
            	" ".repeat(strSpacing), monster2.getSpeed()
            	);
    	System.out.println(str);
    }

     /**
      * Helper method for printBothMonsters()
      *
      * @param str String on the left - used to calculate spacing
      * @return An int describing how many spaces to put between strings
      */
     public static int calcSpacing(String str) {
     	int totalWidth = SPACING;
     	int str1Width = str.length();
     	int spacing = (totalWidth - str1Width);
     	if (spacing < 0) {
         	return 0;
     	}
     	return spacing;
     }

     /**
      * Use this method in showdown() to display the current round.
      * @param round An int of the round (should start at 0)
      */
     public static void printRound(int round) {
     	System.out.println();
     	System.out.println("Round " + round + ":");
     }

     /**
      * Use this method in showdown() to display the damage each round.
      *
      * @param side The side of the Monster that invoked the attack().
      * @param damage The int (damage) returned from an attack() call
      */
     public static void printAttack(String side, int damage) {
     	System.out.printf("%s does %d damage!\n",side, damage);
     }

     /**
      * Use this method in showdown() to display final stats and drain status.
      *
      * @param monster1 Left monster
      * @param monster2 Right monster
      * @param poisoned If either monster was poisoned
      */
     public static void printFinalStats(Monster monster1, Monster monster2,
                                    	boolean poisoned) {
     	System.out.println();
     	printBothMonsters(monster1, monster2);
     	if (poisoned) {
         	System.out.println("A monster was poisoned.");
     	}
     }

     /**
      * Use this method in showdown() to display a tie game.
      */
     public static void printTieGame() {
     	System.out.println("-------GAME OVER-------");
     	System.out.println("TIE: Both monsters died!");
     }

     /**
      * Use this method in showdown() to display the winner.
      * @param side The side of the Monster that won.
      */
     public static void printWinner(String side) {
     	System.out.println("-------GAME OVER-------");
     	System.out.println(side + " monster wins!");
     }
}
