package Logic;

import javafx.concurrent.Task;

public class SleeperTask extends Task<Void> {
    private static final int SLEEP_TIME = 1200;

    @Override
    protected Void call(){
        try{
            Thread.sleep(SLEEP_TIME);
        } catch(Exception e){
            System.out.println("Cant interrupt the computer");
        }

        return null;
    }
}
