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
        char jugador = (torn == 0) ? 'M' : 'm'; // Turno: 0 = mayúsculas, 1 = minúsculas


        do {
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
                case 1:
                    System.out.println("Has seleccionat moure una peça.");
                    torn ++;

                    mourePeça(jugador);



                    break;
                case 2:
                    System.out.println("Has sortit del programa.");
                    break;
            }
        } while (opcio != 2);
    }

    private static String[][] TaulerEscacs = {
            {"t", "c", "a", "d", "R", "a", "c", "t"},
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

    private static void mourePeça(char jugador ){
        Scanner scanner = new Scanner(System.in);
        boolean posicioCorrecte = false;
        int filaOrigen=0;
        int columnaOrigen=0;


        mostrarTauler();


        while (true) {
            System.out.print("Ingresa la fila de la peça a moure(1-8):");
            filaOrigen = scanner.nextInt() - 1;
            System.out.print("Ingresa la columna de la peça a moure(1-8):");
            columnaOrigen = scanner.nextInt() - 1;

            if (filaOrigen < 0 || filaOrigen >= 8 || columnaOrigen < 0 || columnaOrigen >= 8) {
                System.out.println("La fila o la columna no són correctes.");
            } else {
                posicioCorrecte = true;
                break;
            }

        }

        String peça = TaulerEscacs[filaOrigen][columnaOrigen];

        System.out.println("Has seleccionat la peça '" + peça + "' en la fila " + (filaOrigen + 1) + " y la columna " + (columnaOrigen + 1) + ".");

        System.out.println(jugador);

        if (TaulerEscacs[filaOrigen][columnaOrigen] == "-") {
                System.out.println("Aquesta posició no conté cap peça.");
        } else if ((Character.isUpperCase((peça.charAt(0)))&& Character.isLowerCase(jugador)) ||(Character.isLowerCase((peça.charAt(0)))&& Character.isUpperCase(jugador))) {
                System.out.println("Aquesta peça no pertany al jugador actual.");
        }

        menuPerPeça(peça);

    }

    private static void menuPerPeça(String peça){
        Scanner scanner = new Scanner(System.in);

        switch (peça.toLowerCase()){
            case "p":
                System.out.println("Has seleccionat el peó. Opcions:");
                System.out.println("1. Moure endavant");
                System.out.println("2. Capturar en diagonal");
                break;
            case "t":
                System.out.println("Has seleccionat la torre. Opcions:");
                System.out.println("1. Moure horitzontalment");
                System.out.println("2. Moure verticalment");
                break;
            case "c":
                System.out.println("Has seleccionat el cavall. Opcions:");
                System.out.println("1. Moure en L");
                break;
            case "a":
                System.out.println("Has seleccionat l'alfil. Opcions:");
                System.out.println("1. Moure en diagonal");
                break;
            case "d":
                System.out.println("Has seleccionat la reina. Opcions:");
                System.out.println("1. Moure horitzintalment");
                System.out.println("2. Moure verticalment");
                System.out.println("3. Moure en diagonal");
                break;
            case "r":
                System.out.println("Has seleccionat el rei. Opcions:");
                System.out.println("1. Moure horitzintalment");
                System.out.println("2. Moure verticalment");
                System.out.println("3. Moure en diagonal");
                break;
        }
    }

}