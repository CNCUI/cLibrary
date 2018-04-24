package thread.volitleTest;

public class VolitleTest {
	
	public static void main(String[] args) throws InterruptedException {
		 GoalNotifier goalNotifier = new GoalNotifier();
	        Thread goalNotifierThread = new Thread(goalNotifier);
	        goalNotifierThread.start();

	        // After 3s
	        Thread.sleep(3000);
	        // Goal !!!
	        goalNotifier.setGoal(true);
	}
}
