import java.util.Scanner;
import java.util.Random;
import javax.swing.*;  
import java.awt.event.*;  



public class AmongUs {
	
	static String[] players = {"Red (you)", "Black", "Purple", "Blue", "Lime", "White", "Pink", "Brown", "Cyan", "Yellow"};
	static Boolean playerDead = false;
	static Integer amountDead = 4;
	
	public static void main(String[] args) {
		welcome();
		breaker();
		
		SetupGame newGame = new SetupGame();
		Event newEvent = new Event();
		Task newTask = new Task();

		Boolean impostorDead = false;
		
		breaker();

		String eventText;
		Integer eventNumber;
		Integer taskNumber;
		
		Integer tasksCompleted = 0;
		
		System.out.println(" ");
		System.out.println("                                  C R E W M A T E");
		System.out.println("                           There is 1 impostor among us");
		
		breaker();
		
		amountDead = newGame.returnNumberOfPlayers();
		
		while (!impostorDead && !playerDead && amountDead > 2 && tasksCompleted != 5) {
			
			taskNumber = newTask.randomTask();
			
			if (taskNumber == 1) {
				if (newTask.confirmHuman()) {
					tasksCompleted += 1;
					System.out.println(" ");
					System.out.println("                       Task successful, " + tasksCompleted + "/5 tasks till victory.");
					System.out.println(" ");
				} else {
					System.out.println(" ");
					System.out.println("                       Task failed, " + tasksCompleted + "/5 tasks till victory.");
					System.out.println(" ");
				}		
			} else {
				if (newTask.enterPassword()) {
					tasksCompleted += 1;
					System.out.println(" ");
					System.out.println("                       Task successful, " + tasksCompleted + "/5 tasks till victory.");
					System.out.println(" ");
				} else {
					System.out.println(" ");
					System.out.println("                       Task failed, " + tasksCompleted + "/5 tasks till victory.");
					System.out.println(" ");
				}		
			}
			
			breaker();
			
			eventNumber = newEvent.randomEvent();
			
			if (eventNumber == 2) {
				eventText = newEvent.randomWitnessText(players[newGame.returnImpostor()]);
				System.out.println(eventText);
				
				if (!playerDead && tasksCompleted != 5) {			
					breaker();
					impostorDead = vote(players[newGame.returnImpostor()], newGame.returnNumberOfPlayers());				
				}
				amountDead -= 1;
				breaker();
			} else if (eventNumber == 1) {
				playerDead = true;
				System.out.println("W- wait, who's that behind you- SNAP! You're dead!");
				breaker();
			} else {
				eventText = newEvent.randomNoWitnessText(players, players[newGame.returnImpostor()], newGame.returnNumberOfPlayers());
				System.out.println(eventText);
				if (!playerDead && tasksCompleted != 5) {
					breaker();
					impostorDead = vote(players[newGame.returnImpostor()], newGame.returnNumberOfPlayers());
				}
				amountDead -= 1;
				breaker();
			}
		}
	
		if (impostorDead || tasksCompleted == 5) {
			System.out.println(" ");
			System.out.println("                              Game over... Crewmates win.");
		} else {
			System.out.println(" ");
			System.out.println("                              Game over... Impostor wins.");
		}
		
	}

	static boolean vote(String impostorName, Integer numberOfPlayers) {
		Scanner in = new Scanner(System.in);
		System.out.println("Time to vote... type in the colour of the player you wish to vote for. If you wish to abstain write, SKIP.");
		System.out.println("");
		System.out.print("Players: | ");
		for (int x = 0; x < numberOfPlayers; x++) {
			System.out.print(players[x] + " | ");
		}
		System.out.println("");
		String name = in.nextLine();
		breaker();
		in.close();
		
		Random random = new Random();	
		Integer dontBelieve = (random.nextInt(9));
		
		
		if (dontBelieve > 2 || name.equals("SKIP")) {
			if (!name.equals("SKIP")) {
				if (name.equals(impostorName)) {
					System.out.println("                                    You voted for " + name);
					System.out.println("                                  . ,  *         , *    .");
					System.out.println("                                            .");
					System.out.println("                                 .      * ,         * .");
					System.out.println("                                            o   ^");
					System.out.println("                                    ,      -I-        , ");
					System.out.println("                                       *    ^    .      ");
					System.out.println("                                       .            ,   *");
					System.out.println("                                     " + name + " was An Impostor.");
					System.out.println("                                  ,        .          *");
					System.out.println("                                     0 Impostors remain");
					System.out.println("                                  ,        *          .   ");
					System.out.println("");
					return true;
				} else if (name.equals("Red")) {
					System.out.println("Y- you.. you just voted yourself...");
					System.out.println(" ");
					System.out.println("                                  . ,  *         , *    .");
					System.out.println("                                            .");
					System.out.println("                                 .      * ,         * .");
					System.out.println("                                            o   ^");
					System.out.println("                                    ,      -I-        , ");
					System.out.println("                                       *    ^    .      ");
					System.out.println("                                       .            ,   *");
					System.out.println("                                 " + name + " was not An Impostor.");
					System.out.println("                                  ,        .          *");
					System.out.println("                                     1 Impostor remains");
					System.out.println("                                  ,        *          .   ");
					AmongUs.playerDead = true;
					return false;
				} else {
					for (int x = 0; x < numberOfPlayers; x++) {
						if (name.equals(players[x])) {
							players[x] = name + " (dead)";
						}
					}
					System.out.println("                                    You voted for " + name);
					System.out.println("                                  . ,  *         , *    .");
					System.out.println("                                            .");
					System.out.println("                                 .      * ,         * .");
					System.out.println("                                            o   ^");
					System.out.println("                                    ,      -I-        , ");
					System.out.println("                                       *    ^    .      ");
					System.out.println("                                       .            ,   *");
					System.out.println("                                 " + name + " was not An Impostor.");
					System.out.println("                                  ,        .          *");
					System.out.println("                                     1 Impostor remains");
					System.out.println("                                  ,        *          .   ");
					AmongUs.amountDead -= 1;
					return false;
				}
			} else {
				System.out.println("Vote skipped.");
				return false;
			}
		} else if (dontBelieve == 0) {
			System.out.println("You're starting to sound like an impostor! You got voted off!");
			System.out.println(" ");
			System.out.println("                                  . ,  *         , *    .");
			System.out.println("                                            .");
			System.out.println("                                 .      * ,         * .");
			System.out.println("                                            o   ^");
			System.out.println("                                    ,      -I-        , ");
			System.out.println("                                       *    ^    .      ");
			System.out.println("                                       .            ,   *");
			System.out.println("                                 " + name + " was not An Impostor.");
			System.out.println("                                  ,        .          *");
			System.out.println("                                     1 Impostor remains");
			System.out.println("                                  ,        *          .   ");
			AmongUs.playerDead = true;
			return false;
		} else {
			System.out.println("You accussed the wrong person! The crewmates argued and no one voted!");
			System.out.println("");
			System.out.println("Vote skipped.");
			return false;
		}
	}
	
	static void welcome() {
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("  # # #     #       #     # # #     #       #     # # #          #       #    # # #  ");
		System.out.println("#       #   # #   # #   #       #   # #     #   #       #        #       #  #       #");
		System.out.println("#       #   #   #   #   #       #   #   #   #   #                #       #  #        ");
		System.out.println("# # # # #   #       #   #       #   #   #   #   #   # # #        #       #    # # #  ");
		System.out.println("#       #   #       #   #       #   #     # #   #       #        #       #          #");
		System.out.println("#       #   #       #   #       #   #     # #   #       #        #       #  #       #");
		System.out.println("#       #   #       #     # # #     #       #     # # #            # # #      # # #  ");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println(" ");
		System.out.println("                                 Press ENTER to play...");
		
		Scanner in = new Scanner(System.in);
		in.nextLine();
		in.close();
	}
	
	static void breaker() {
		System.out.println(" ");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println(" ");
	}
	
}

class Task {
	Integer randomNum;
	
	Integer getRandom() {
		Random random = new Random();
		return (random.nextInt(2));
	}
	
	Integer randomTask() {
		randomNum = getRandom();
		
		// return 1 means confirm human task.
		// return 2 means enter password task.
			
		return randomNum;
	}
	
	Boolean confirmHuman() {
		/*String name = JOptionPane.showInputDialog("To verify you're a human, click your colour.", QUESTION_MESSAGE);*/
		
		Scanner in = new Scanner(System.in);
		
		Object[] possibleValues1 = { "Red", "Blue", "Green" };
		Object[] possibleValues2 = { "Green", "Blue", "Red" };
		Object[] possibleValues3 = { "Blue", "Red", "Green" };

		Object[] possibleValues;

		Random random = new Random();
		Integer randomPossible = (random.nextInt(2)+1);
		
		Integer redNum;
		
		if (randomPossible == 1) {
			possibleValues = possibleValues1;
		} else if (randomPossible == 2) {
			possibleValues = possibleValues2;
		} else {
			possibleValues = possibleValues3;
		}

		for (int x = 0; x < 3; x++) {
			System.out.println(x+1);
			System.out.print(" " + possibleValues[x]);
			
			if (possibleValues[x] = "Red") {
				redNum = x+1;
			}
		}

		System.out.println("");
		System.out.println("Select the number with option Red to verify you're a human.");
		Integer selectedValue = in.nextInt();
		
		// Object selectedValue = JOptionPane.showInputDialog(null, "Choose Red to verify you're a human.", "Input", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
		// value in if selectedValue.equals("Red")
		
		if (selectedValue == redNum) {
			// JOptionPane.showMessageDialog(null, "Human Verfied", "Task Successful.", 1);
			return true;
		} else {
			// JOptionPane.showMessageDialog(null, "Potential Robot", "Task Not Successful.", 0);
			return false;
		}		
	}

	Boolean enterPassword() {
		
		Random random = new Random();
		Integer randomPassInt = (random.nextInt(999)+1000);
		Scanner in = new Scanner(System.in);
		
		String randomPass = Integer.toString(randomPassInt);		
		
		// JOptionPane.showMessageDialog(null, "Today's password is " + randomPass, "Today's password...",  1);			
		System.out.println("What is today's password?");
		String inputPass = in.nextLine(); //JOptionPane.showInputDialog("What is today's password?");
		in.close();	

		if (inputPass.equals(randomPass)) {			
			// JOptionPane.showMessageDialog(null, "Correct Password", "Task Successful.", 1);
			System.out.println("Correct Password, Task Successful.");
			return true;
		} else {
			// JOptionPane.showMessageDialog(null, "Incorrect Password", "Task Not Successful.", 0);
			System.out.println("Incorrect Password, Task Not Successful.");
			return false;
		}		
		
	}

}
	

class Event {
	
	Integer randomNum;
	
	Integer getRandom() {
		Random random = new Random();	
		return (random.nextInt(9)+1);
	}
	
	Integer randomEvent() {
		randomNum = getRandom();
		
		// return 1 means the player is killed
		// return 2 means the player witnesses someone being killed
		// return 3 means someone was killed, but no witness.

		if (randomNum == 1) {
			return 1;
		} else if (randomNum == 2 || randomNum == 3 || randomNum == 4) {
			return 2;
		} else {
			return 3;
		}

	}
	
	String randomNoWitnessText(String[] players, String impostor, Integer numberOfPlayers) {
		Random random = new Random();
		Integer randomDeath = random.nextInt(numberOfPlayers);
		String deadPlayer = players[randomDeath];
		
		while ((deadPlayer.contains("dead")) || deadPlayer.equals(impostor)) {
			randomDeath = random.nextInt(numberOfPlayers);
			deadPlayer = players[randomDeath];
		}
		
		if (deadPlayer.contains("Red")) {
			AmongUs.playerDead = true;
		}
		
		for (int x = 0; x < numberOfPlayers; x++) {
			if (deadPlayer.equals(players[x])) {
				players[x] = deadPlayer + " (dead)";
			}
		}
		
		randomNum = getRandom();
		switch(randomNum) {
			case 1:
				return deadPlayer + " is holy... literaly.";	
			case 2:
				return deadPlayer + " must've sat through Faron's lecture... didn't end well for them.";				
			case 3:
				return "Seems like " + deadPlayer + " took a one way trip to hell.";				
			case 4:
				return "The lights went off and when they came back on, " + deadPlayer + " was dead!";				
			case 5:
				return "There was a big bang! Next thing we knew " + deadPlayer + " was dead on the floor!";				
			case 6:
				return deadPlayer + " caught covid and died!";				
			case 7:
				return deadPlayer + " didn't look both ways when crossing the street!";				
			case 8:
				return deadPlayer + " used a 2d array in Lab Session 3... RIP!";				
			case 9:
				return "Tut tut tut... " + deadPlayer + " plagarised and paid the price!";	
			case 10:
				return deadPlayer + " sacrificed his life for Pakistan!";				
			default:
				break;
		}		
		return "null";
		
		
		
		
	}
	
	String randomWitnessText(String impostorName) {
		randomNum = getRandom();
		
		switch(randomNum) {
			case 1:
				return "You see " + impostorName + " jump into a vent!";	
			case 2:
				return "You see " + impostorName + " jump out a vent!";				
			case 3:
				return impostorName + " seems like they're up to no good...";				
			case 4:
				return impostorName + " keeps following you... suspiciously.";				
			case 5:
				return "Who's that... wait, " + impostorName + " completed that task suspiciously fast...";				
			case 6:
				return "You're following " + impostorName + ", but the doors shut infront of you. When you open them, there's an open vent and " + impostorName + " is nowhere to be seen!";				
			case 7:
				return "Ahh! " + impostorName + " is running after you!";				
			case 8:
				return "Wait, why is " + impostorName + " holding a knife?";				
			case 9:
				return "Faron, no! " + impostorName + " just broke Faron's neck whilst he was doing a lecture! A man has died!";	
			case 10:
				return "Mike is sus... but " + impostorName + " is even more sus...";				
			default:
				break;
		}		
		return "null";
	}
	
}

class SetupGame {
	
	Integer numberOfPlayers;
	Integer impostor;
	
	public SetupGame() {		
		setNumberOfPlayers();
		setImpostor();
	}
	
	Integer returnNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	Integer returnImpostor() {
		return impostor;
	}
	
	void setNumberOfPlayers() {
		System.out.println("How many players would you like in your game?");
		Scanner in = new Scanner(System.in);
		numberOfPlayers = in.nextInt();
		in.close();

		if (numberOfPlayers<4) {
			numberOfPlayers = 4;
		} else if (numberOfPlayers>10) {
			numberOfPlayers = 10;
		}
		
		
	}
	
	void setImpostor() {
		Random random = new Random();	
		impostor = (random.nextInt(numberOfPlayers-1)+1);
	}

}
