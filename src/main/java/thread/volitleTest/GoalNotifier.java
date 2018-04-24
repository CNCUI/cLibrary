package thread.volitleTest;

public class GoalNotifier implements Runnable{
	public volatile  boolean goal = false;

    public boolean isGoal() {
        return goal;
    }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    @Override
    public void run() {
        while (true) {
            if (isGoal()) {
                System.out.println("Goal !!!!!!");

                // Tell the referee the ball is in.
                // ...

                // reset goal flag
                setGoal(false);
            }
        }
    }
}
