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

                    mourePeça();



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

    private static void mourePeça(){
        Scanner scanner = new Scanner(System.in);
        boolean posicioCorrecte = false;
        String pieza = TaulerEscacs[filaOrigen][columnaOrigen];

        mostrarTauler();


        while (true) {
            System.out.print("Ingresa la fila de la peça a moure(1-8):");
            int filaOrigen = scanner.nextInt() - 1;
            System.out.print("Ingresa la columna de la peça a moure(1-8):");
            int columnaOrigen = scanner.nextInt() - 1;

            if (filaOrigen < 0 || filaOrigen >= 8 || columnaOrigen < 0 || columnaOrigen >= 8) {
                System.out.println("La fila o la columna no són correctes.");
            }else {
                posicioCorrecte = true;
                break;
            }

            if (TaulerEscacs[filaOrigen][columnaOrigen] == "-") {
                System.out.println("Aquesta posició no conté cap peça.");
            } else if (TaulerEscacs[filaOrigen][columnaOrigen].charAt(0)!= jugador) {
                System.out.println("Aquesta peça no pert any dels seus propietaris.");
            }
        }
    }
}