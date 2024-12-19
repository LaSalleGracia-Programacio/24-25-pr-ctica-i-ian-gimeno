import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        menu();


    }

    private static void menu() {
        int torn = 0;
        Scanner scanner = new Scanner(System.in);
        int opcio = 0;

        do {

            char jugador = (torn == 0) ? 'M' : 'm';

            System.out.println("Menu:");
            System.out.println("Torn de moure de: "+ (torn == 0 ? "1 (Majúscules)" : "2 (Minúscules)"));
            System.out.println("1. Moure peça");
            System.out.println("2. Salir");
            System.out.print("Opción: ");
            try {
                opcio = scanner.nextInt();
                if (opcio != 1 && opcio != 2) {
                    System.out.println("Opció incorrecta. Torneu a provar.");
                }
            }catch (InputMismatchException e) {
                System.out.println("Error: Entrada no vàlida. Si us plau, introdueix un número entre 1 i 2.");
                scanner.next();
            }


            switch (opcio) {
                case 1 -> {
                    System.out.println("Has seleccionat moure una peça.");
                    torn = (torn + 1) % 2;
                    mourePeça(jugador, torn);
                }
                case 2 -> System.out.println("Has sortit del programa.");
            }
        } while (opcio != 2);
    }

    private static String[][] TaulerEscacs = {
            {"t", "c", "a", "d", "r", "a", "c", "t"},
            {"p", "p", "p", "p", "p", "p", "p", "p"},
            {"-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-"},
            {"P", "P", "P", "P", "P", "P", "P", "P"},
            {"T", "C", "A", "D", "R", "A", "C", "T"},
    };
    private static void mostrarTauler() {
        for (String[] fila : TaulerEscacs) {
            for (String celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }

    private static void mourePeça(char jugador, int torn ){
        Scanner scanner = new Scanner(System.in);
        boolean posicioCorrecte = false;
        int filaOrigen=0;
        int columnaOrigen=0;


        mostrarTauler();


        while (!posicioCorrecte) {
            System.out.print("Ingresa la fila de la peça a moure(1-8):");
            filaOrigen = scanner.nextInt() - 1;
            System.out.print("Ingresa la columna de la peça a moure(1-8):");
            columnaOrigen = scanner.nextInt() - 1;

            if (filaOrigen < 0 || filaOrigen >= 8 || columnaOrigen < 0 || columnaOrigen >= 8) {
                System.out.println("La fila o la columna no són correctes.");
            } else {
                posicioCorrecte = true;
            }

        }

        String peça = TaulerEscacs[filaOrigen][columnaOrigen];

        System.out.println("Has seleccionat la peça '" + peça + "' en la fila " + (filaOrigen + 1) + " y la columna " + (columnaOrigen + 1) + ".");


        if (TaulerEscacs[filaOrigen][columnaOrigen].equals("-")) {
                System.out.println("Aquesta posició no conté cap peça.");
        } else if ((Character.isUpperCase((peça.charAt(0)))&& Character.isLowerCase(jugador)) ||(Character.isLowerCase((peça.charAt(0)))&& Character.isUpperCase(jugador))) {
                System.out.println("Aquesta peça no pertany al jugador actual.");
        }else {
            menuPerPeça(peça, torn, filaOrigen, columnaOrigen);
        }
    }

    private static void menuPerPeça(String peça, int torn, int filaOrigen, int ColumnaOrigen){
        Scanner scanner = new Scanner(System.in);

        switch (peça.toLowerCase()) {
            case "p" -> {
                System.out.println("Has seleccionat el peó.");
                peo(torn, filaOrigen, ColumnaOrigen);
            }
            case "t" -> {
                System.out.println("Has seleccionat la torre.");
                torre(torn, filaOrigen, ColumnaOrigen);
            }
            case "c" -> {
                System.out.println("Has seleccionat el cavall.");
                cavall(torn);
            }
            case "a" -> {
                System.out.println("Has seleccionat l'alfil.");
                alfil(torn);
            }
            case "d" -> {
                System.out.println("Has seleccionat la reina.");
                reina(torn);
            }
            case "r" -> {
                System.out.println("Has seleccionat el rei.");
                rei(torn);
            }
        }

    }

    private static void peo (int torn, int filaOrigen, int columnaOrigen){
        Scanner scanner = new Scanner(System.in);
        int direccio = (torn == 0) ? -1 : 1;
        if (direccio == -1) {

            System.out.println("El peó es mou cap avall.");

            if (filaOrigen > 0 && columnaOrigen > 0 && columnaOrigen < TaulerEscacs[0].length - 1) {
                boolean movPossible = false;

                if (TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].equals("-") && TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].equals("-")) {
                    TaulerEscacs[filaOrigen + 1][columnaOrigen] = "P";
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }
                else if (TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].equals("-") && !TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].equals("-")) {
                    TaulerEscacs[filaOrigen + 1][columnaOrigen + 1] = "P";
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }
                else if (!TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].equals("-") && TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].equals("-")) {
                    TaulerEscacs[filaOrigen + 1][columnaOrigen - 1] = "P";
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }
                else if (!TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].equals("-") && !TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].equals("-")) {
                    mostrarTauler();
                    System.out.print("Quina peça vols capturar?(1. Esquerra, 2. Dreta): ");
                    int opcio = scanner.nextInt();

                    if (opcio == 1) {
                        TaulerEscacs[filaOrigen + 1][columnaOrigen - 1] = "P";
                        TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                        movPossible = true;
                        mostrarTauler();
                    } else if (opcio == 2) {
                        TaulerEscacs[filaOrigen + 1][columnaOrigen + 1] = "P";
                        TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                        movPossible = true;
                        mostrarTauler();
                    }
                }

                if (!movPossible) {
                    System.out.println("No es pot moure el peó en cap direcció.");
                }
            } else {
                System.out.println("Coordenades no vàlides per al moviment del peó.");
            }

        } else {
            System.out.println("El peó es mou cap amunt.");

            if (filaOrigen > 0 && columnaOrigen > 0 && columnaOrigen < TaulerEscacs[0].length - 1) {
                boolean movPossible = false;

                if (TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].equals("-") && TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].equals("-")) {
                    TaulerEscacs[filaOrigen - 1][columnaOrigen] = "P";
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }
                else if (TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].equals("-") && !TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].equals("-")) {
                    TaulerEscacs[filaOrigen - 1][columnaOrigen + 1] = "P";
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }
                else if (!TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].equals("-") && TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].equals("-")) {
                    TaulerEscacs[filaOrigen - 1][columnaOrigen - 1] = "P";
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }
                else if (!TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].equals("-") && !TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].equals("-")) {
                    mostrarTauler();
                    System.out.print("Quina peça vols capturar?(1. Esquerra, 2. Dreta): ");
                    int opcio = scanner.nextInt();

                    if (opcio == 1) {
                        TaulerEscacs[filaOrigen - 1][columnaOrigen - 1] = "P";
                        TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                        movPossible = true;
                        mostrarTauler();
                    } else if (opcio == 2) {
                        TaulerEscacs[filaOrigen - 1][columnaOrigen + 1] = "P";
                        TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                        movPossible = true;
                        mostrarTauler();
                    }
                }

                if (!movPossible) {
                    System.out.println("No es pot moure el peó en cap direcció.");
                }
            } else {
                System.out.println("Coordenades no vàlides per al moviment del peó.");
            }



        }
    }
    private static void torre(int torn, int filaOrigen, int columnaOrigen) {
        Scanner scanner = new Scanner(System.in);
        int direccio = (torn == 0) ? -1 : 1;
        String pesa = (direccio == -1) ? "la torre minuscula" : "la torre majuscula";

        System.out.println("La torre que es mou es " + pesa);

        System.out.print("En quina direcció vols moure la torre?(1. Vertical, 2. Horizontal): ");
        int opcio = scanner.nextInt();

        if (opcio == 1) {
            System.out.print("En quina direcció vols moure la torre?(1. Amunt, 2. Avall): ");
            int opcio2 = scanner.nextInt();

            System.out.print("Cuantes caselles vols moure la torre? ");
            int moviment = scanner.nextInt();

            if (opcio2 == 1) {
                for (int i = 1; i <= moviment; i++) {
                    if (filaOrigen - i >= 0) {
                        if (TaulerEscacs[filaOrigen - i][columnaOrigen].equals("-")) {
                            TaulerEscacs[filaOrigen - i][columnaOrigen] = (direccio == -1) ? "t" : "T";
                            TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                            mostrarTauler();
                        } else if (Character.isUpperCase(TaulerEscacs[filaOrigen - i][columnaOrigen].charAt(0))) {

                            System.out.println("Has capturat una peça a la casella (" + (filaOrigen - i +1) + ", " + (columnaOrigen+1) + ").");
                            TaulerEscacs[filaOrigen - i][columnaOrigen] = (direccio == -1) ? "t" : "T";
                            TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                            mostrarTauler();
                            break;
                        } else {
                            System.out.println("La torre no pot moure's més amunt, obstacle en la casella (" + (filaOrigen - i+1) + ", " + (columnaOrigen+1) + ").");
                            break;
                        }
                    } else {
                        System.out.println("No pots moure la torre més amunt, has arribat al límit del tauler.");
                        break;
                    }
                }
            } else if (opcio2 == 2) {
                for (int i = 1; i <= moviment; i++) {
                    if (filaOrigen + i < TaulerEscacs.length) {
                        if (TaulerEscacs[filaOrigen + i][columnaOrigen].equals("-")) {
                            TaulerEscacs[filaOrigen + i][columnaOrigen] = (direccio == -1) ? "t" : "T";
                            TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                            mostrarTauler();
                        } else if (Character.isUpperCase(TaulerEscacs[filaOrigen + i][columnaOrigen].charAt(0))) {
                            System.out.println("Has capturat una peça a la casella (" + (filaOrigen + i+1) + ", " + (columnaOrigen+1) + ").");
                            TaulerEscacs[filaOrigen + i][columnaOrigen] = (direccio == -1) ? "t" : "T";
                            TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                            mostrarTauler();
                            break;
                        } else {
                            System.out.println("La torre no pot moure's més avall, obstacle en la casella (" + (filaOrigen + i+1) + ", " + (columnaOrigen+1) + ").");
                            break;
                        }
                    } else {
                        System.out.println("No pots moure la torre més avall, has arribat al límit del tauler.");
                        break;
                    }
                }
            }
        } else if (opcio == 2) {
            System.out.print("En quina direcció vols moure la torre?(1. Dreta, 2. Esquerra): ");
            int opcio3 = scanner.nextInt();

            System.out.print("Cuantes caselles vols moure la torre? ");
            int moviment = scanner.nextInt();

            if (opcio3 == 1) {
                for (int i = 1; i <= moviment; i++) {
                    if (columnaOrigen + i < TaulerEscacs[0].length) {
                        if (TaulerEscacs[filaOrigen][columnaOrigen + i].equals("-")) {
                            TaulerEscacs[filaOrigen][columnaOrigen + i] = (direccio == -1) ? "t" : "T";
                            TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                            mostrarTauler();
                        } else if (Character.isUpperCase(TaulerEscacs[filaOrigen][columnaOrigen + i].charAt(0))) {
                            System.out.println("Has capturat una peça a la casella (" + (filaOrigen+1) + ", " + (columnaOrigen + i+1) + ").");
                            TaulerEscacs[filaOrigen][columnaOrigen + i] = (direccio == -1) ? "t" : "T";
                            TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                            mostrarTauler();
                            break;
                        } else {
                            System.out.println("La torre no pot moure's més a la dreta, obstacle en la casella (" + (filaOrigen+1) + ", " + (columnaOrigen + i+1) + ").");
                            break;
                        }
                    } else {
                        System.out.println("No pots moure la torre més a la dreta, has arribat al límit del tauler.");
                        break;
                    }
                }
            } else if (opcio3 == 2) {
                for (int i = 1; i <= moviment; i++) {
                    if (columnaOrigen - i >= 0) {
                        if (TaulerEscacs[filaOrigen][columnaOrigen - i].equals("-")) {
                            TaulerEscacs[filaOrigen][columnaOrigen - i] = (direccio == -1) ? "t" : "T";
                            TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                            mostrarTauler();
                        } else if (Character.isUpperCase(TaulerEscacs[filaOrigen][columnaOrigen - i].charAt(0))) {
                            System.out.println("Has capturat una peça a la casella (" + (filaOrigen+1) + ", " + (columnaOrigen - i+1) + ").");
                            TaulerEscacs[filaOrigen][columnaOrigen - i] = (direccio == -1) ? "t" : "T";
                            TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                            mostrarTauler();
                            break;
                        } else {
                            System.out.println("La torre no pot moure's més a l'esquerra, obstacle en la casella (" + (filaOrigen+1) + ", " + (columnaOrigen - i+1) + ").");
                            break;
                        }
                    } else {
                        System.out.println("No pots moure la torre més a l'esquerra, has arribat al límit del tauler.");
                        break;
                    }
                }
            }
        }
    }

    private static void cavall (int torn){

    }
    private static void alfil (int torn){

    }
    private static void reina (int torn){

    }
    private static void rei (int torn){

    }

}