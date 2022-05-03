public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Scoreboard board = new Scoreboard(10);
        board.add(new GameEntry("Miguel", 300));
        board.add(new GameEntry("Susie", 3000));
        board.add(new GameEntry("Jose", 500));
        board.add(new GameEntry("Tino", 5000));

        System.out.print(board);
    }
}