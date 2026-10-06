import java.io.*;
import java.util.Scanner;

public class Diario {

    public static void main(String[] args) throws IOException {
//        Variables
        Scanner sc = new Scanner(System.in);
        int op = 0;

        while (op != 6) {
            //      Mostramos las opciones del menu
            System.out.println("---MENÚ DIARIO NIEVES---");
            System.out.println("1. Añadir notas al diario\n" +
                    "2. Leer el diario completo\n" +
                    "3. Leer hasta que líneas se quiere leer\n" +
                    "4. Leer desde que líneas se quiere leer\n" +
                    "5. Leer rango de líneas (desde / hasta)\n" +
                    "6. Salir\nElige una opción: ");

            op = sc.nextInt();

            //      Creamos la ruta del fichero
            String ruta = "src/ficheroDiario/diario.txt";

            // Control de opciones del menu
            try {
                switch (op) {
                    case 1 -> anhadirDiario(ruta, sc);
                    case 2 -> leerDiario(ruta, true);
                    case 3 -> leerHasta(ruta, sc);
                    case 4 -> leerDesde(ruta, sc);
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void leerDesde (String ruta, Scanner sc) throws IOException {
        //      Variables
        int numLinea = 0, maxLineas = leerDiario(ruta,false);

        do {
            System.out.println("Introduzca la linea desde la cual quiera leer su fichero.");
            numLinea = sc.nextInt();

            if ( numLinea < maxLineas ){
                System.out.println("ERROR! Numero de linea superior al que hay.");
            }
        } while(numLinea > maxLineas || numLinea < 1);

        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);
        String linea = "";
        int cont = 0;
        for(int i = 0; i < maxLineas; i ++) {
            while ((linea = br.readLine()) != null && cont < numLinea) {
                System.out.print(linea);
                cont++;
            }
        }
        br.close();
        fr.close();
    }

    private static void leerHasta(String ruta, Scanner sc) throws IOException {
//      Variables
        int numLinea = 0, maxLineas = leerDiario(ruta,false);

        do {
            System.out.println("Introduzca la linea hasta la cual quiera leer su fichero.");
            numLinea = sc.nextInt();

            if ( numLinea > maxLineas ){
                System.out.println("No existen ese numero de lineas vuelva a intentarlo.");
            }

        } while(numLinea > maxLineas || numLinea < 1);

        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);
        String linea = "";
        int cont = 0;

        while ((linea = br.readLine()) != null && cont < numLinea) {
            System.out.println(linea);
            cont++;
        }

        br.close();
        fr.close();
        }


    private static int leerDiario(String ruta, boolean mostrarDatos) throws IOException {
//        variables
        int cont = 0;

        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);

        String linea = "";

        do {
            linea = br.readLine();
            if (linea != null) {
                if (mostrarDatos) {
                    System.out.println(linea);
                }
                cont++;
            }
        } while (linea != null);
        return cont;
    }

    private static void anhadirDiario(String ruta, Scanner sc) throws IOException {
//      Variables
        String frase = "";
        sc.nextLine();

            FileWriter fw = new FileWriter(ruta, true);
            BufferedWriter bw = new BufferedWriter(fw);

//          Preguntamos la frase al usuario hasta que escriba "FIN"
            do {
                System.out.println("Introduzca su frase (FIN para salir): ");
                frase = sc.nextLine();

                if (frase.equalsIgnoreCase("fin")) {
                    System.out.println("Fin fichero");
                    break;
                }
                bw.write(frase + "\n");

            } while (!frase.equalsIgnoreCase("fin"));

            bw.close();
            fw.close();

    }
}
