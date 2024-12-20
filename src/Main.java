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
        boolean ReiMajus= false;
        boolean ReiMinus= false;

        do {

            for (String[] taulerEscac : TaulerEscacs) {
                for (String pesa : taulerEscac) {
                    if (pesa.equals("r")) {
                        ReiMinus = true;
                    }
                    if (pesa.equals("R")) {
                        ReiMajus = true;
                    }
                    if (!pesa.equals("r")) {
                        ReiMinus = false;
                    }
                    if (!pesa.equals("R")) {
                        ReiMajus = false;
                    }

                    }
                }


            if (!ReiMinus && ReiMajus) {
                System.out.println("GUANYADOR MAJUSCULA");
                break;
            }
            if (ReiMinus && !ReiMajus) {
                System.out.println("GUANYADOR MINUSCULA");
                break;
            }

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
                    mourePesa(jugador, torn);
                }
                case 2 -> System.out.println("Has sortit del programa.");
            }
        } while (opcio != 2);
    }

    private static final String[][] TaulerEscacs = {
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

    private static void mourePesa(char jugador, int torn ){
        Scanner scanner = new Scanner(System.in);
        boolean posicioCorrecte = false;
        int filaOrigen=0;
        int columnaOrigen=0;


        mostrarTauler();


        while (!posicioCorrecte) {
            try {
                System.out.print("Ingresa la fila de la peça a moure(1-8):");
                filaOrigen = scanner.nextInt() - 1;
                System.out.print("Ingresa la columna de la peça a moure(1-8):");
                columnaOrigen = scanner.nextInt() - 1;

                if (filaOrigen < 0 || filaOrigen >= 8 || columnaOrigen < 0 || columnaOrigen >= 8) {
                    System.out.println("La fila o la columna no són correctes.");
                } else {
                    posicioCorrecte = true;
                }
            }catch (InputMismatchException e){
                System.out.println("Error: Entrada no vàlida. Si us plau, introdueix números entre 1 i 8.");
                scanner.next();
            }

        }

        String pesa = TaulerEscacs[filaOrigen][columnaOrigen];

        System.out.println("Has seleccionat la peça '" + pesa + "' en la fila " + (filaOrigen + 1) + " y la columna " + (columnaOrigen + 1) + ".");


        if (TaulerEscacs[filaOrigen][columnaOrigen].equals("-")) {
                System.out.println("Aquesta posició no conté cap peça.");

        } else if ((Character.isUpperCase((pesa.charAt(0)))&& Character.isLowerCase(jugador)) ||(Character.isLowerCase((pesa.charAt(0)))&& Character.isUpperCase(jugador))) {
                System.out.println("Aquesta peça no pertany al jugador actual.");
        }else {
            menuPerPesa(pesa, torn, filaOrigen, columnaOrigen);
        }
    }

    private static void menuPerPesa(String pesa, int torn, int filaOrigen, int ColumnaOrigen){

        switch (pesa.toLowerCase()) {
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
                cavall(torn, filaOrigen, ColumnaOrigen);
            }
            case "a" -> {
                System.out.println("Has seleccionat l'alfil.");
                alfil(torn, filaOrigen, ColumnaOrigen);
            }
            case "d" -> {
                System.out.println("Has seleccionat la reina.");
                reina(torn, filaOrigen, ColumnaOrigen);
            }
            case "r" -> {
                System.out.println("Has seleccionat el rei.");
                rei(torn, filaOrigen, ColumnaOrigen);
            }
        }

    }

    private static void peo(int torn, int filaOrigen, int columnaOrigen) {
        Scanner scanner = new Scanner(System.in);
        boolean movPossible = false;
        int direccio = (torn == 0) ? -1 : 1;
        String peo = (torn == 0) ? "p" : "P";

        if (direccio == -1) {
            System.out.println("El peó es mou cap avall.");


            if (columnaOrigen != 0 && columnaOrigen != 7 && filaOrigen != 0) {
                if (
                        (Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen].charAt(0)) &&
                                Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].charAt(0)) &&
                                Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].charAt(0)))
                ) {
                    movPossible = false;
                }if (
                        (TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].equals("-") && TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].equals("-")) ||
                                (Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].charAt(0)) || Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].charAt(0))) ||
                                (Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].charAt(0)) && Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].charAt(0)))
                                        && TaulerEscacs[filaOrigen + 1][columnaOrigen].equals("-")
                ) {
                    TaulerEscacs[filaOrigen + 1][columnaOrigen] = peo;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                } else if (TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].equals("-") && !TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].equals("-") &&
                        !TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].equals(peo)) {
                    TaulerEscacs[filaOrigen + 1][columnaOrigen - 1] = peo;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                } else if (!TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].equals("-") && TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].equals("-") &&
                        !TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].equals(peo)) {
                    TaulerEscacs[filaOrigen + 1][columnaOrigen + 1] = peo;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                } else if (!TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].equals("-") && !TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].equals("-")) {
                    mostrarTauler();
                    System.out.print("Quina peça vols capturar?(1. Esquerra, 2. Dreta): ");
                    try {
                        int opcio = scanner.nextInt();

                        if (opcio != 1 && opcio != 2) {
                            System.out.println("Opció incorrecta. Torneu a provar.");
                        } else {
                            if (opcio == 1 && !TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].equals(peo)) {
                                TaulerEscacs[filaOrigen + 1][columnaOrigen + 1] = peo;
                                TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                movPossible = true;
                                mostrarTauler();
                            } else if (opcio == 2 && !TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].equals(peo)) {
                                TaulerEscacs[filaOrigen + 1][columnaOrigen - 1] = peo;
                                TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                movPossible = true;
                                mostrarTauler();
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Entrada no vàlida. Si us plau, introdueix un número entre 1 i 2.");
                        scanner.next();
                    }
                }

                if (!movPossible) {
                    System.out.println("No es pot moure el peó en cap direcció.");
                }
            }
            else if (columnaOrigen == 0 && filaOrigen != 0) {
                if (
                        Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen].charAt(0)) &&
                                (Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].charAt(0)))
                ) {
                    movPossible = false;
                }if (
                        (TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].equals("-")) ||
                                (Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].charAt(0)))
                ) {
                    TaulerEscacs[filaOrigen + 1][columnaOrigen ] = peo;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }else if (Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen + 1].charAt(0))) {
                    TaulerEscacs[filaOrigen + 1][columnaOrigen+1] = peo;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }
            }
            else if (columnaOrigen == 7 && filaOrigen != 0) {
                if (
                        Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen].charAt(0)) &&
                                (Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].charAt(0)))
                ) {
                    movPossible = false;
                }if (
                        (TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].equals("-")) ||
                                (Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].charAt(0)))
                ) {
                    TaulerEscacs[filaOrigen + 1][columnaOrigen] = peo;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }else if (Character.isUpperCase(TaulerEscacs[filaOrigen + 1][columnaOrigen - 1].charAt(0))) {
                    TaulerEscacs[filaOrigen - 1][columnaOrigen -1 ] = peo;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }
            }
            else {
                System.out.println("En quina peça et vols transformar?");
                System.out.println("1.Reina");
                System.out.println("2.Alfil");
                System.out.println("3.Cavall");
                System.out.println("4.Torre");
                System.out.print("Escull:");

                try {
                    int pesa = scanner.nextInt();
                    if (pesa != 1 && pesa != 2 && pesa != 3 && pesa != 4) {
                        System.out.println("Opció incorrecta. Torneu a provar.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Entrada no vàlida. Si us plau, introdueix un número entre 1 i 4.");
                    scanner.next();
                }
            }


        }
        else {
            System.out.println("El peó es mou cap amunt.");

            if (columnaOrigen !=0 && columnaOrigen != 7 && filaOrigen != 0){

                if (
                        (Character.isUpperCase(TaulerEscacs[filaOrigen - 1][columnaOrigen].charAt(0)) &&
                                Character.isUpperCase(TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].charAt(0)) &&
                                Character.isUpperCase(TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].charAt(0)))
                ) {
                    movPossible = false;
                } else if (
                        (TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].equals("-") && TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].equals("-")) ||
                                (Character.isUpperCase(TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].charAt(0)) || Character.isUpperCase(TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].charAt(0))) ||
                                (Character.isUpperCase(TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].charAt(0)) && Character.isUpperCase(TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].charAt(0)))
                                        && TaulerEscacs[filaOrigen -1][columnaOrigen].equals("-")
                ) {
                    TaulerEscacs[filaOrigen - 1][columnaOrigen] = peo;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }
                else if (TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].equals("-") && !TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].equals("-") &&
                        !TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].equals(peo)) {
                    TaulerEscacs[filaOrigen - 1][columnaOrigen + 1] = peo;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }
                else if (!TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].equals("-") && TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].equals("-") &&
                        !TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].equals(peo)) {
                    TaulerEscacs[filaOrigen - 1][columnaOrigen - 1] = peo;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }
                else if (!TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].equals("-") && !TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].equals("-")) {
                    mostrarTauler();
                    System.out.print("Quina peça vols capturar?(1. Esquerra, 2. Dreta): ");
                    try {
                        int opcio = scanner.nextInt();

                        if (opcio != 1 && opcio != 2) {
                            System.out.println("Opció incorrecta. Torneu a provar.");
                        } else {
                            if (opcio == 1 && !TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].equals(peo)) {
                                TaulerEscacs[filaOrigen - 1][columnaOrigen - 1] = peo;
                                TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                movPossible = true;
                                mostrarTauler();
                            } else if (opcio == 2 && !TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].equals(peo)) {
                                TaulerEscacs[filaOrigen - 1][columnaOrigen + 1] = peo;
                                TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                movPossible = true;
                                mostrarTauler();
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Entrada no vàlida. Si us plau, introdueix un número entre 1 i 2.");
                        scanner.next();
                    }
                }

            }
            else if (columnaOrigen == 0 && filaOrigen != 0){
                if (
                        Character.isUpperCase(TaulerEscacs[filaOrigen - 1][columnaOrigen].charAt(0)) &&
                                (Character.isUpperCase(TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].charAt(0)))
                ) {
                    movPossible = false;
                } else if (
                        (TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].equals("-")) ||
                                (Character.isUpperCase(TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].charAt(0))))
                {
                    TaulerEscacs[filaOrigen - 1][columnaOrigen] = peo;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                } else if (Character.isLowerCase(TaulerEscacs[filaOrigen - 1][columnaOrigen + 1].charAt(0))) {
                    TaulerEscacs[filaOrigen - 1][columnaOrigen+1] = peo;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }
            }
            else if (columnaOrigen == 7 && filaOrigen != 0) {
                if (
                        Character.isUpperCase(TaulerEscacs[filaOrigen - 1][columnaOrigen].charAt(0)) &&
                                (Character.isUpperCase(TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].charAt(0)))
                ) {
                    movPossible = false;
                }else if (
                        (TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].equals("-")) ||
                                (Character.isUpperCase(TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].charAt(0)))
                ) {
                    TaulerEscacs[filaOrigen - 1][columnaOrigen] = peo;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }else if (Character.isLowerCase(TaulerEscacs[filaOrigen - 1][columnaOrigen - 1].charAt(0))) {
                    TaulerEscacs[filaOrigen - 1][columnaOrigen-1] = peo;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    movPossible = true;
                    mostrarTauler();
                }
            }
            else {
                System.out.println("En quina peça et vols transformar?");
                System.out.println("1.Reina");
                System.out.println("2.Alfil");
                System.out.println("3.Cavall");
                System.out.println("4.Torre");
                System.out.print("Escull:");

                try {
                    int pesa = scanner.nextInt();
                    if (pesa != 1 && pesa != 2 && pesa != 3 && pesa != 4) {
                        System.out.println("Opció incorrecta. Torneu a provar.");
                    }
                }catch (InputMismatchException e) {
                    System.out.println("Error: Entrada no vàlida. Si us plau, introdueix un número entre 1 i 4.");
                    scanner.next();
                }



            }

        }

        if (!movPossible) {
            System.out.println("No es pot moure el peó en cap direcció.");
            mostrarTauler();
        }

    }

    private static void torre(int torn, int filaOrigen, int columnaOrigen) {
        Scanner scanner = new Scanner(System.in);
        int direccio = (torn == 0) ? -1 : 1;
        String pesa = (direccio == -1) ? "la torre minuscula" : "la torre majuscula";

        System.out.println("La torre que es mou es " + pesa);

        System.out.print("En quina direcció vols moure la torre?(1. Vertical, 2. Horizontal): ");
        try {
            int opcio = scanner.nextInt();
            if (opcio != 1 && opcio != 2) {
                System.out.println("Opció incorrecta. Torneu a provar.");
            } else {
                if (opcio == 1) {
                    System.out.print("En quina direcció vols moure la torre?(1. Amunt, 2. Avall): ");
                    try {
                        int opcio2 = scanner.nextInt();
                        if (opcio2 != 1 && opcio2 != 2) {
                            System.out.println("Opció incorrecta. Torneu a provar.");
                        } else {
                            System.out.print("Cuantes caselles vols moure la torre? ");
                            try {
                                int moviment = scanner.nextInt();
                                if (moviment <= 0) {
                                    System.out.println("El número de caselles ha de ser positiu.");
                                } else {
                                    if (opcio2 == 1) {
                                        for (int i = 1; i <= moviment; i++) {
                                            if (filaOrigen - i >= 0) {
                                                String casellaDesti = TaulerEscacs[filaOrigen - i][columnaOrigen];
                                                if (casellaDesti.equals("-")) {
                                                    TaulerEscacs[filaOrigen - i][columnaOrigen] = (direccio == -1) ? "t" : "T";
                                                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                                } else if ((Character.isUpperCase(casellaDesti.charAt(0)) && direccio == -1) ||
                                                        (Character.isLowerCase(casellaDesti.charAt(0)) && direccio == 1)) {
                                                    System.out.println("Has capturat una peça a la casella (" + (filaOrigen - i + 1) + ", " + (columnaOrigen + 1) + ").");
                                                    TaulerEscacs[filaOrigen - i][columnaOrigen] = (direccio == -1) ? "t" : "T";
                                                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                                    break;
                                                } else {
                                                    System.out.println("La torre no pot moure's més amunt, obstacle en la casella (" + (filaOrigen - i + 1) + ", " + (columnaOrigen + 1) + ").");
                                                    break;
                                                }
                                            } else {
                                                System.out.println("No pots moure la torre més amunt, has arribat al límit del tauler.");
                                                TaulerEscacs[0][columnaOrigen] = (direccio == -1) ? "t" : "T";

                                                break;
                                            }
                                            if (i < moviment) {
                                                TaulerEscacs[filaOrigen - i][columnaOrigen] = "-";
                                            }
                                        }
                                        mostrarTauler();
                                    } else {
                                        for (int i = 1; i <= moviment; i++) {
                                            if (filaOrigen + i < TaulerEscacs.length) {
                                                String casellaDesti = TaulerEscacs[filaOrigen + i][columnaOrigen];
                                                if (casellaDesti.equals("-")) {
                                                    TaulerEscacs[filaOrigen + i][columnaOrigen] = (direccio == -1) ? "t" : "T";
                                                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                                } else if ((Character.isUpperCase(casellaDesti.charAt(0)) && direccio == -1) ||
                                                        (Character.isLowerCase(casellaDesti.charAt(0)) && direccio == 1)) {
                                                    System.out.println("Has capturat una peça a la casella (" + (filaOrigen + i + 1) + ", " + (columnaOrigen + 1) + ").");
                                                    TaulerEscacs[filaOrigen + i][columnaOrigen] = (direccio == -1) ? "t" : "T";
                                                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                                    break;
                                                } else {
                                                    System.out.println("La torre no pot moure's més avall, obstacle en la casella (" + (filaOrigen + i + 1) + ", " + (columnaOrigen + 1) + ").");
                                                    break;
                                                }
                                            } else {
                                                System.out.println("No pots moure la torre més avall, has arribat al límit del tauler.");
                                                TaulerEscacs[7][columnaOrigen] = (direccio == -1) ? "t" : "T";
                                                break;
                                            }
                                            if (i < moviment) {
                                                TaulerEscacs[filaOrigen + i][columnaOrigen] = "-";
                                            }
                                        }
                                        mostrarTauler();
                                    }
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Entrada no vàlida. Si us plau, introdueix un número positiu.");
                                scanner.next();
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Entrada no vàlida. Si us plau, introdueix 1 o 2.");
                        scanner.next();
                    }
                } else {
                    System.out.print("En quina direcció vols moure la torre?(1. Dreta, 2. Esquerra): ");
                    try {
                        int opcio3 = scanner.nextInt();
                        if (opcio3 != 1 && opcio3 != 2) {
                            System.out.println("Opció incorrecta. Torneu a provar.");
                        } else {
                            System.out.print("Cuantes caselles vols moure la torre? ");
                            try {
                                int moviment = scanner.nextInt();
                                if (moviment <= 0) {
                                    System.out.println("El número de caselles ha de ser positiu.");
                                } else {
                                    if (opcio3 == 1) {
                                        for (int i = 1; i <= moviment; i++) {
                                            if (columnaOrigen + i < TaulerEscacs[0].length) {
                                                String casellaDesti = TaulerEscacs[filaOrigen][columnaOrigen + i];
                                                if (casellaDesti.equals("-")) {
                                                    TaulerEscacs[filaOrigen][columnaOrigen + i] = (direccio == -1) ? "t" : "T";
                                                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                                } else if ((Character.isUpperCase(casellaDesti.charAt(0)) && direccio == -1) ||
                                                        (Character.isLowerCase(casellaDesti.charAt(0)) && direccio == 1)) {
                                                    System.out.println("Has capturat una peça a la casella (" + (filaOrigen + 1) + ", " + (columnaOrigen + i + 1) + ").");
                                                    TaulerEscacs[filaOrigen][columnaOrigen + i] = (direccio == -1) ? "t" : "T";
                                                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                                    break;
                                                } else {
                                                    System.out.println("La torre no pot moure's més a la dreta, obstacle en la casella (" + (filaOrigen + 1) + ", " + (columnaOrigen + i + 1) + ").");
                                                    break;
                                                }
                                            } else {
                                                System.out.println("No pots moure la torre més a la dreta, has arribat al límit del tauler.");
                                                TaulerEscacs[filaOrigen][7] = (direccio == -1) ? "t" : "T";
                                                break;
                                            }
                                            if (i < moviment) {
                                                TaulerEscacs[filaOrigen][columnaOrigen + i] = "-";
                                            }
                                        }
                                        mostrarTauler();
                                    } else {
                                        for (int i = 1; i <= moviment; i++) {
                                            if (columnaOrigen - i >= 0) {
                                                String casellaDesti = TaulerEscacs[filaOrigen][columnaOrigen - i];
                                                if (casellaDesti.equals("-")) {
                                                    TaulerEscacs[filaOrigen][columnaOrigen - i] = (direccio == -1) ? "t" : "T";
                                                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                                } else if ((Character.isUpperCase(casellaDesti.charAt(0)) && direccio == -1) ||
                                                        (Character.isLowerCase(casellaDesti.charAt(0)) && direccio == 1)) {
                                                    System.out.println("Has capturat una peça a la casella (" + (filaOrigen + 1) + ", " + (columnaOrigen - i + 1) + ").");
                                                    TaulerEscacs[filaOrigen][columnaOrigen - i] = (direccio == -1) ? "t" : "T";
                                                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                                    break;
                                                } else {
                                                    System.out.println("La torre no pot moure's més a l'esquerra, obstacle en la casella (" + (filaOrigen + 1) + ", " + (columnaOrigen - i + 1) + ").");
                                                    break;
                                                }
                                            } else {
                                                System.out.println("No pots moure la torre més a l'esquerra, has arribat al límit del tauler.");
                                                TaulerEscacs[filaOrigen][0] = (direccio == -1) ? "t" : "T";
                                                break;
                                            }
                                            if (i < moviment) {
                                                TaulerEscacs[filaOrigen][columnaOrigen - i] = "-";
                                            }
                                        }
                                        mostrarTauler();
                                    }
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Entrada no vàlida. Si us plau, introdueix un número positiu.");
                                scanner.next();
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Entrada no vàlida. Si us plau, introdueix 1 o 2.");
                        scanner.next();
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no vàlida. Si us plau, introdueix 1 o 2.");
            scanner.next();
        }
    }

    private static void cavall(int torn, int filaOrigen, int columnaOrigen) {
        Scanner scanner = new Scanner(System.in);
        int direccio = (torn == 0) ? -1 : 1;
        String pesa = (direccio == -1) ? "el cavall minúscul" : "el cavall majúscul";
        System.out.println("El cavall que es mou és " + pesa);

        System.out.print("En quina direcció vols fer els 2 salts? (1. Vertical, 2. Horitzontal): ");
        try {
            int opcio = scanner.nextInt();
            if (opcio != 1 && opcio != 2) {
                System.out.println("Opció incorrecta. Torneu a provar.");
            } else {
                int deltaFila1 = 0, deltaColumna1 = 0, deltaFila2 = 0, deltaColumna2 = 0;
                if (opcio == 1) {
                    System.out.print("En quina direcció vols fer el primer salt? (1. Amunt, 2. Avall): ");
                    int opcio2 = scanner.nextInt();
                    if (opcio2 == 1) deltaFila1 = -2;
                    else if (opcio2 == 2) deltaFila1 = 2;
                    else {
                        System.out.println("Opció incorrecta. Torneu a provar.");
                        return;
                    }

                    System.out.print("On vols fer el segon salt? (1. Dreta, 2. Esquerra): ");
                    int opcio3 = scanner.nextInt();
                    if (opcio3 == 1) deltaColumna2 = 1;
                    else if (opcio3 == 2) deltaColumna2 = -1;
                    else {
                        System.out.println("Opció incorrecta. Torneu a provar.");
                        return;
                    }
                } else {
                    System.out.print("En quina direcció vols fer el primer salt? (1. Dreta, 2. Esquerra): ");
                    int opcio2 = scanner.nextInt();
                    if (opcio2 == 1) deltaColumna1 = 2;
                    else if (opcio2 == 2) deltaColumna1 = -2;
                    else {
                        System.out.println("Opció incorrecta. Torneu a provar.");
                        return;
                    }

                    System.out.print("On vols fer el segon salt? (1. Amunt, 2. Avall): ");
                    int opcio3 = scanner.nextInt();
                    if (opcio3 == 1) deltaFila2 = -1;
                    else if (opcio3 == 2) deltaFila2 = 1;
                    else {
                        System.out.println("Opció incorrecta. Torneu a provar.");
                        return;
                    }
                }

                int filaDesti = filaOrigen + deltaFila1 + deltaFila2;
                int columnaDesti = columnaOrigen + deltaColumna1 + deltaColumna2;

                if (filaDesti >= 0 && filaDesti < TaulerEscacs.length && columnaDesti >= 0 && columnaDesti < TaulerEscacs[0].length) {
                    String casellaDesti = TaulerEscacs[filaDesti][columnaDesti];
                    char cavall = (torn == 0) ? 'c' : 'C';

                    if (casellaDesti.equals("-") ||
                            (Character.isLowerCase(casellaDesti.charAt(0)) && Character.isUpperCase(cavall)) ||
                            (Character.isUpperCase(casellaDesti.charAt(0)) && Character.isLowerCase(cavall))) {
                        TaulerEscacs[filaDesti][columnaDesti] = String.valueOf(cavall);
                        TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                        mostrarTauler();
                    } else {
                        System.out.println("No pots menjar la teva pròpia peça.");
                    }
                } else {
                    System.out.println("El cavall no pot moure's a aquesta casella.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no vàlida. Si us plau, introdueix 1 o 2.");
            scanner.next();
        }
    }

    private static void alfil(int torn, int filaOrigen, int columnaOrigen) {
        Scanner scanner = new Scanner(System.in);
        String alfil = (torn == 0) ? "a" : "A";

        System.out.println("Escull la direccio per moure l'alfil:");
        System.out.println("1. Dalt Dreta");
        System.out.println("2. Dalt Esquerra");
        System.out.println("3. Baix Dreta");
        System.out.println("4. Baix Esquerra");
        System.out.print("Escull una opcio: ");

        try {
            int opcio = scanner.nextInt();

            if (opcio < 1 || opcio > 4) {
                System.out.println("Opcio incorrecta. Intenta-ho de nou.");
                return;
            }

            System.out.print("Quantes caselles vols moure't? ");
            int passos = scanner.nextInt();

            int filaActual = filaOrigen;
            int columnaActual = columnaOrigen;

            for (int i = 0; i < passos; i++) {
                int filaTemp = filaActual;
                int columnaTemp = columnaActual;

                switch (opcio) {
                    case 1 -> { filaTemp--; columnaTemp++; }
                    case 2 -> { filaTemp--; columnaTemp--; }
                    case 3 -> { filaTemp++; columnaTemp++; }
                    case 4 -> { filaTemp++; columnaTemp--; }
                }

                if (filaTemp < 0 || filaTemp >= TaulerEscacs.length || columnaTemp < 0 || columnaTemp >= TaulerEscacs[0].length) {
                    break;
                }

                String casellaActual = TaulerEscacs[filaTemp][columnaTemp];

                if (!casellaActual.equals("-")) {
                    if ((Character.isLowerCase(casellaActual.charAt(0)) && torn == 0) ||
                            (Character.isUpperCase(casellaActual.charAt(0)) && torn == 1)) {
                        System.out.println("La teva peça talla el pas a la casella (" + (filaTemp + 1) + ", " + (columnaTemp + 1) + "). L'alfil no es pot moure.");
                        mostrarTauler();
                        return;
                    } else {
                        TaulerEscacs[filaTemp][columnaTemp] = alfil;
                        TaulerEscacs[filaActual][columnaActual] = "-";
                        mostrarTauler();
                        return;
                    }
                }

                filaActual = filaTemp;
                columnaActual = columnaTemp;
            }

            String casellaDesti = TaulerEscacs[filaActual][columnaActual];
            if (casellaDesti.equals("-") ||
                    (Character.isLowerCase(casellaDesti.charAt(0)) && torn == 1) ||
                    (Character.isUpperCase(casellaDesti.charAt(0)) && torn == 0)) {
                TaulerEscacs[filaActual][columnaActual] = alfil;
                TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                mostrarTauler();
            } else {
                System.out.println("No pots menjar la teva propria peça.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no valida. Si us plau, introdueix un numero valid.");
            scanner.next();
        }
    }

    private static void reina(int torn, int filaOrigen, int columnaOrigen) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Vols moure la reina en una de les quatre direccions? (1. Horitzontal, 2. Vertical, 3. Diagonal): ");
        try {
            int opcio = scanner.nextInt();

            if (opcio != 1 && opcio != 2 && opcio != 3) {
                System.out.println("Opció incorrecta. Torneu a provar.");
            } else {
                if (opcio == 1) {
                    moureReinaHorizontal(torn, filaOrigen, columnaOrigen);
                }
                else if (opcio == 2) {moureReinaVertical(torn,filaOrigen,columnaOrigen);
                }
                else {moureReinaDiagonal(torn, filaOrigen, columnaOrigen);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no vàlida. Si us plau, introdueix una opció vàlida.");
            scanner.next();
        }
    }

    private static void moureReinaVertical(int torn, int filaOrigen, int columnaOrigen){
        Scanner scanner = new Scanner(System.in);
        int direccio = (torn == 0) ? -1 : 1;
        String pesa = (direccio == -1) ? "la torre minuscula" : "la torre majuscula";

        System.out.println("La torre que es mou es " + pesa);

                System.out.print("En quina direcció vols moure la torre?(1. Amunt, 2. Avall): ");
                try {
                    int opcio2 = scanner.nextInt();
                    if (opcio2 != 1 && opcio2 != 2) {
                        System.out.println("Opció incorrecta. Torneu a provar.");
                    } else {
                        System.out.print("Cuantes caselles vols moure la torre? ");
                        try {
                            int moviment = scanner.nextInt();
                            if (moviment <= 0) {
                                System.out.println("El número de caselles ha de ser positiu.");
                            } else {
                                if (opcio2 == 1) {
                                    for (int i = 1; i <= moviment; i++) {
                                        if (filaOrigen - i >= 0) {
                                            String casellaDesti = TaulerEscacs[filaOrigen - i][columnaOrigen];
                                            if (casellaDesti.equals("-")) {
                                                TaulerEscacs[filaOrigen - i][columnaOrigen] = (direccio == -1) ? "d" : "D";
                                                TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                            } else if ((Character.isUpperCase(casellaDesti.charAt(0)) && direccio == -1) ||
                                                    (Character.isLowerCase(casellaDesti.charAt(0)) && direccio == 1)) {
                                                System.out.println("Has capturat una peça a la casella (" + (filaOrigen - i + 1) + ", " + (columnaOrigen + 1) + ").");
                                                TaulerEscacs[filaOrigen - i][columnaOrigen] = (direccio == -1) ? "d" : "D";
                                                TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                                break;
                                            } else {
                                                System.out.println("La torre no pot moure's més amunt, obstacle en la casella (" + (filaOrigen - i + 1) + ", " + (columnaOrigen + 1) + ").");
                                                break;
                                            }
                                        } else {
                                            System.out.println("No pots moure la torre més amunt, has arribat al límit del tauler.");
                                            TaulerEscacs[0][columnaOrigen] = (direccio == -1) ? "d" : "D";
                                            break;
                                        }
                                        if (i < moviment) {
                                            TaulerEscacs[filaOrigen - i][columnaOrigen] = "-";
                                        }
                                    }
                                    mostrarTauler();
                                } else {
                                    for (int i = 1; i <= moviment; i++) {
                                        if (filaOrigen + i < TaulerEscacs.length) {
                                            String casellaDesti = TaulerEscacs[filaOrigen + i][columnaOrigen];
                                            if (casellaDesti.equals("-")) {
                                                TaulerEscacs[filaOrigen + i][columnaOrigen] = (direccio == -1) ? "d" : "D";
                                                TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                            } else if ((Character.isUpperCase(casellaDesti.charAt(0)) && direccio == -1) ||
                                                    (Character.isLowerCase(casellaDesti.charAt(0)) && direccio == 1)) {
                                                System.out.println("Has capturat una peça a la casella (" + (filaOrigen + i + 1) + ", " + (columnaOrigen + 1) + ").");
                                                TaulerEscacs[filaOrigen + i][columnaOrigen] = (direccio == -1) ? "d" : "D";
                                                TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                                break;
                                            } else {
                                                System.out.println("La torre no pot moure's més avall, obstacle en la casella (" + (filaOrigen + i + 1) + ", " + (columnaOrigen + 1) + ").");
                                                break;
                                            }
                                        } else {
                                            System.out.println("No pots moure la torre més avall, has arribat al límit del tauler.");
                                            TaulerEscacs[7][columnaOrigen] = (direccio == -1) ? "d" : "D";
                                            break;
                                        }
                                        if (i < moviment) {
                                            TaulerEscacs[filaOrigen + i][columnaOrigen] = "-";
                                        }
                                    }
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Entrada no vàlida. Si us plau, introdueix un número positiu.");
                            scanner.next();
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Entrada no vàlida. Si us plau, introdueix 1 o 2.");
                    scanner.next();
                }
            }

    private static void moureReinaHorizontal(int torn, int filaOrigen, int columnaOrigen){
        Scanner scanner = new Scanner(System.in);
        int direccio = (torn == 0) ? -1 : 1;
        String pesa = (direccio == -1) ? "la torre minuscula" : "la torre majuscula";

        System.out.println("La torre que es mou es " + pesa);


                System.out.print("En quina direcció vols moure la torre?(1. Dreta, 2. Esquerre): ");
                try {
                    int opcio3 = scanner.nextInt();
                    if (opcio3 != 1 && opcio3 != 2) {
                        System.out.println("Opció incorrecta. Torneu a provar.");
                    } else {
                        System.out.print("Quantes caselles vols moure la torre? ");
                        try {
                            int moviment = scanner.nextInt();
                            if (moviment <= 0) {
                                System.out.println("El número de caselles ha de ser positiu.");
                            } else {
                                if (opcio3 == 1) {
                                    for (int i = 1; i <= moviment; i++) {
                                        if (columnaOrigen + i < TaulerEscacs[0].length) {
                                            String casellaDesti = TaulerEscacs[filaOrigen][columnaOrigen + i];
                                            if (casellaDesti.equals("-")) {
                                                TaulerEscacs[filaOrigen][columnaOrigen + i] = (direccio == -1) ? "d" : "D";
                                                TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                            } else if ((Character.isUpperCase(casellaDesti.charAt(0)) && direccio == -1) ||
                                                    (Character.isLowerCase(casellaDesti.charAt(0)) && direccio == 1)) {
                                                System.out.println("Has capturat una peça a la casella (" + (filaOrigen + 1) + ", " + (columnaOrigen + i + 1) + ").");
                                                TaulerEscacs[filaOrigen][columnaOrigen + i] = (direccio == -1) ? "d" : "D";
                                                TaulerEscacs[filaOrigen][columnaOrigen] = "-";

                                                break;
                                            } else {
                                                System.out.println("La torre no pot moure's més a la dreta, obstacle en la casella (" + (filaOrigen + 1) + ", " + (columnaOrigen + i + 1) + ").");

                                                break;
                                            }
                                        } else {
                                            System.out.println("No pots moure la torre més a la dreta, has arribat al límit del tauler.");
                                            TaulerEscacs[filaOrigen][7] = (direccio == -1) ? "d" : "D";
                                            break;
                                        }
                                        if (i < moviment) {
                                            TaulerEscacs[filaOrigen][columnaOrigen + i] = "-";
                                        }
                                    }
                                    mostrarTauler();
                                } else {
                                    for (int i = 1; i <= moviment; i++) {
                                        if (columnaOrigen - i >= 0) {
                                            String casellaDesti = TaulerEscacs[filaOrigen][columnaOrigen - i];
                                            if (casellaDesti.equals("-")) {
                                                TaulerEscacs[filaOrigen][columnaOrigen - i] = (direccio == -1) ? "d" : "D";
                                                TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                            } else if ((Character.isUpperCase(casellaDesti.charAt(0)) && direccio == -1) ||
                                                    (Character.isLowerCase(casellaDesti.charAt(0)) && direccio == 1)) {
                                                System.out.println("Has capturat una peça a la casella (" + (filaOrigen + 1) + ", " + (columnaOrigen - i + 1) + ").");
                                                TaulerEscacs[filaOrigen][columnaOrigen - i] = (direccio == -1) ? "d" : "D";
                                                TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                                                break;
                                            } else {
                                                System.out.println("La torre no pot moure's més a l'esquerra, obstacle en la casella (" + (filaOrigen + 1) + ", " + (columnaOrigen - i + 1) + ").");
                                                break;
                                            }
                                        } else {
                                            System.out.println("No pots moure la torre més a l'esquerra, has arribat al límit del tauler.");
                                            TaulerEscacs[filaOrigen][0] = (direccio == -1) ? "d" : "D";
                                            break;
                                        }
                                        if (i < moviment) {
                                            TaulerEscacs[filaOrigen][columnaOrigen - i] = "-";
                                        }
                                    }
                                    mostrarTauler();
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Entrada no vàlida. Si us plau, introdueix un número positiu.");
                            scanner.next();
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Entrada no vàlida. Si us plau, introdueix 1 o 2.");
                    scanner.next();
                }
    }

    private static void moureReinaDiagonal(int torn, int filaOrigen, int columnaOrigen) {
        Scanner scanner = new Scanner(System.in);
        String reina = (torn == 0) ? "d" : "D";

        System.out.println("Escull la direccio per moure la reina:");
        System.out.println("1. Dalt Dreta");
        System.out.println("2. Dalt Esquerra");
        System.out.println("3. Baix Dreta");
        System.out.println("4. Baix Esquerra");
        System.out.print("Escull una opcio: ");

        try {
            int opcio = scanner.nextInt();

            if (opcio < 1 || opcio > 4) {
                System.out.println("Opcio incorrecta. Intenta-ho de nou.");
                return;
            }

            System.out.print("Quantes caselles vols moure't? ");
            int passos = scanner.nextInt();

            int filaActual = filaOrigen;
            int columnaActual = columnaOrigen;

            for (int i = 0; i < passos; i++) {
                int filaTemp = filaActual;
                int columnaTemp = columnaActual;

                switch (opcio) {
                    case 1 -> { filaTemp--; columnaTemp++; }
                    case 2 -> { filaTemp--; columnaTemp--; }
                    case 3 -> { filaTemp++; columnaTemp++; }
                    case 4 -> { filaTemp++; columnaTemp--; }
                }

                if (filaTemp < 0 || filaTemp >= TaulerEscacs.length || columnaTemp < 0 || columnaTemp >= TaulerEscacs[0].length) {
                    break;
                }

                String casellaActual = TaulerEscacs[filaTemp][columnaTemp];

                if (!casellaActual.equals("-")) {
                    if ((Character.isLowerCase(casellaActual.charAt(0)) && torn == 0) ||
                            (Character.isUpperCase(casellaActual.charAt(0)) && torn == 1)) {
                        System.out.println("La reina no es pot moure, obstacle a la casella (" + (filaTemp + 1) + ", " + (columnaTemp + 1) + "). Peça de color propi.");
                        mostrarTauler();
                        return;
                    } else {
                        TaulerEscacs[filaTemp][columnaTemp] = reina;
                        TaulerEscacs[filaActual][columnaActual] = "-";
                        mostrarTauler();
                        return;
                    }
                }

                filaActual = filaTemp;
                columnaActual = columnaTemp;
            }

            String casellaDesti = TaulerEscacs[filaActual][columnaActual];
            if (casellaDesti.equals("-") ||
                    (Character.isLowerCase(casellaDesti.charAt(0)) && torn == 1) ||
                    (Character.isUpperCase(casellaDesti.charAt(0)) && torn == 0)) {
                TaulerEscacs[filaActual][columnaActual] = reina;
                TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                mostrarTauler();
            } else {
                System.out.println("No pots menjar la teva propria peça.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no valida. Si us plau, introdueix un numero valid.");
            scanner.next();
        }
    }

    private static void rei(int torn, int filaOrigen, int columnaOrigen) {
        Scanner scanner = new Scanner(System.in);
        String rei = (torn == 0) ? "r" : "R";

        System.out.println("Escull la direccio per moure el rei:");
        System.out.println("1. Dalt Dreta");
        System.out.println("2. Dalt Esquerra");
        System.out.println("3. Baix Dreta");
        System.out.println("4. Baix Esquerra");
        System.out.println("5. Dalt");
        System.out.println("6. Baix");
        System.out.println("7. Dreta");
        System.out.println("8. Esquerra");
        System.out.print("Escull una opcio: ");

        try {
            int opcio = scanner.nextInt();

            if (opcio < 1 || opcio > 8) {
                System.out.println("Opcio incorrecta. Intenta-ho de nou.");
                return;
            }

            String direccio = "";
            int filaDesti = filaOrigen;
            int columnaDesti = columnaOrigen;

            switch (opcio) {
                case 1 -> { filaDesti--; columnaDesti++; direccio = "Dalt Dreta"; }
                case 2 -> { filaDesti--; columnaDesti--; direccio = "Dalt Esquerra"; }
                case 3 -> { filaDesti++; columnaDesti++; direccio = "Baix Dreta"; }
                case 4 -> { filaDesti++; columnaDesti--; direccio = "Baix Esquerra"; }
                case 5 -> { filaDesti--; direccio = "Dalt"; }
                case 6 -> { filaDesti++; direccio = "Baix"; }
                case 7 -> { columnaDesti++; direccio = "Dreta"; }
                case 8 -> { columnaDesti--; direccio = "Esquerra"; }
            }

            if (filaDesti < 0 || filaDesti >= TaulerEscacs.length || columnaDesti < 0 || columnaDesti >= TaulerEscacs[0].length) {
                System.out.println("Moviment fora del tauler.");
                return;
            }

            String casellaDesti = TaulerEscacs[filaDesti][columnaDesti];

            if (!casellaDesti.equals("-")) {
                if ((Character.isLowerCase(casellaDesti.charAt(0)) && torn == 0) || (Character.isUpperCase(casellaDesti.charAt(0)) && torn == 1)) {
                    System.out.println("El rei no es pot moure, obstacle a la casella (" + (filaDesti + 1) + ", " + (columnaDesti + 1) + "). Peça de color propi.");
                    mostrarTauler();
                    return;
                } else {
                    TaulerEscacs[filaDesti][columnaDesti] = rei;
                    TaulerEscacs[filaOrigen][columnaOrigen] = "-";
                    System.out.println("El rei s'ha mogut " + direccio);
                    mostrarTauler();
                    return;
                }
            }

            TaulerEscacs[filaDesti][columnaDesti] = rei;
            TaulerEscacs[filaOrigen][columnaOrigen] = "-";
            System.out.println("El rei s'ha mogut " + direccio);
            mostrarTauler();
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no vàlida. Si us plau, introdueix un número vàlid.");
            scanner.next();
        }
    }

}