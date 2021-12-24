package castle;

import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private Room currentRoom;
    private HashMap<String, Handler> handlers = new HashMap<String, Handler>();

    public Game(){
        createRooms();
        handlers.put("go", new HandlerGo(this));
        handlers.put("bye", new HandlerBye(this));
        handlers.put("help", new HandlerHelp(this));
    }

    private void createRooms(){
        Room outside, lobby, pub, study, bedroom;

        //create  room
        outside = new Room("城堡外");
        lobby = new Room("客厅");
        pub = new Room("小酒吧");
        study = new Room("书屋");
        bedroom = new Room("卧室");

        //init room exits
        outside.setExits("east", lobby);
        outside.setExits("south", study);
        outside.setExits("west", pub);
        lobby.setExits("west", outside);
        pub.setExits("east", outside);
        study.setExits("north", outside);
        study.setExits("east", bedroom);
        bedroom.setExits("west", study);
        lobby.setExits("up", pub);
        pub.setExits("down", lobby);

        currentRoom = outside; //从城堡外面开始
    }

    private void printWelcome(){
        System.out.println();
        System.out.println("welcome to this castle");
        System.out.println("this is a real boring game");
        System.out.println("if you need help please input help");
        System.out.println();
        showprompt();
    }

    public void showprompt(){
        System.out.println("and now you are in"+currentRoom);
        System.out.println("exits have");
        System.out.println(currentRoom.getExitDesc());  //解开第一个耦合
        System.out.println();
    }

    public void goRoom(String direction){
        Room nextRoom = currentRoom.getExit(direction);  //解决第二个耦合
        if (nextRoom == null){
            System.out.println("there are no door");
        }
        else
        {
            currentRoom = nextRoom;
            showprompt();
        }
    }

    public void play(){
        Scanner in = new Scanner(System.in);
        while (true){
            String line = in.nextLine();
            String[] words = line.split(" ");
            Handler handler = handlers.get(words[0]);
            String value = "";
            if (words.length > 1)
                value = words[1];
            if (handler != null)
            {
                handler.doCmd(value);
                if (handler.isBye())
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.printWelcome();
        game.play();

        System.out.println("thanks, and wish you play again");
    }
}


