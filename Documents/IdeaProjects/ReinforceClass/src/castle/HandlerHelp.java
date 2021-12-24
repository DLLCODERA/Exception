package castle;

public class HandlerHelp extends Handler {

    public HandlerHelp(Game game){
        super(game);
    }

    @Override
    public void doCmd(String word) {
        System.out.println("are your loss? please input help");
        System.out.println("by exp \tgo east");
    }
}
