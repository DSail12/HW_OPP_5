// Реализовать полноценный выстрел у стрелков. Поиск ближайшего противник, расчёт повреждения с учётом 
// расстояния и разницы атаки стрелка и защиты цели. Ну и нанесение повреждения. Боец с нулём жизней 
// считается мёртвым. В дальнейшем не лечится и не наносит повреждений) Мертвые бойцы обозначаются другим цветом.

package OOP_5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class
Main {
    public static final int GANG_SIZE = 5;
    public static ArrayList<BaseUnit> whiteSide;
    public static ArrayList<BaseUnit> darkSide;

    public static void main(String[] args) {
        init();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            ConsoleView.view();
            System.out.print("-----------------Ходят светлые силы-----------------");
            whiteSide.forEach(n -> n.step(darkSide));
            System.out.print("\n-----------------Ходят тёмные силы------------------");
            darkSide.forEach(n -> n.step(whiteSide));
            System.out.println("\n-------------------Press Enter--------------------");
            scanner.nextLine();
        }

    }

    public static String getName() {
        Random random = new Random();
        return EnumeratorSurnames.values()[random.nextInt(EnumeratorSurnames.values().length)] + " "
                + EnumeratorNames.values()[random.nextInt(EnumeratorNames.values().length)];
    }

    private static void init() {
        whiteSide = new ArrayList<>();
        darkSide = new ArrayList<>();

        int x = 1;
        int y = 1;
        for (int i = 0; i < GANG_SIZE; i++) {
            switch (new Random().nextInt(4)) {
                case 0:
                    whiteSide.add(new Farmer(whiteSide, getName(), x, y++));
                    break;
                case 1:
                    whiteSide.add(new Thug(whiteSide, getName(), x, y++));
                    break;
                case 2:
                    whiteSide.add(new Sniper(whiteSide, getName(), x, y++));
                    break;
                default:
                    whiteSide.add(new Monk(whiteSide, getName(), x, y++));
                    break;
            }
        }

        Collections.sort(whiteSide, new ComparatorByType());

        x = GANG_SIZE;
        y = 1;
        for (int i = 0; i < GANG_SIZE; i++) {

            switch (new Random().nextInt(4)) {
                case 0:
                    darkSide.add(new Farmer(darkSide, getName(), x, y++));
                    break;
                case 1:
                    darkSide.add(new Pikeman(darkSide, getName(), x, y++));
                    break;
                case 2:
                    darkSide.add(new Crossbowman(darkSide, getName(), x, y++));
                    break;
                default:
                    darkSide.add(new Wizard(darkSide, getName(), x, y++));
                    break;
            }
        }

        Collections.sort(darkSide, new ComparatorByType());

    }

}