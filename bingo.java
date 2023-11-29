import java.util.*;

public class bingo{
    public static void main(String[] args){
        int[] poolOfNumbers = createBingo();
        int[] carton = createCarton();
        
        Scanner scanner = new Scanner(System.in);
        
        //Se obtiene la cantidad que quieres apostar
        System.out.println("Que cantidad de dinero quiere apostar: ");
        int bet = scanner.nextInt();
        
        //Se obtiene la predicción de cuando se va a acertar bingo
        System.out.println("En cuantos números cree que acertará el bingo: ");
        int prediction = scanner.nextInt();
        
        scanner.close();
        
        //Se inicia la partida de bingo
        playBingo(carton, poolOfNumbers, prediction, bet);
        
    }
    
    
    public static void playBingo(int[] carton, int[] poolOfNumbers, int prediction, int bet){
        /***
         * Método para jugar al bingo, se recorre el array poolOfNumbers y por cada número
         * se comprueba si existe en el carton. Si existe se suma un acierto en hits y se continua
         * recorriendo el array, en caso de que no exista no se suma y se continua.
         * 
         * Siempre se comprueba el número de aciertos, si es 5 se guarda cuando se hizo linea,
         * si es bingo se guarda cuando se hizo linea y se sale del for.
         * 
         * Por último se llama a la función results para que imprima los resultados.
        ***/
        
        int row = 0;
        int bingo = 0;
        int hits = 0;
        
        //Se recorre el array de números del bingo
        for(int i = 0; i < poolOfNumbers.length; i++){
            // Se obtiene el número en posición i del del array poolOfNumbers
            int number = poolOfNumbers[i]; 
            // Se comprueba si existe o no el número en el cartón
            boolean hit = numberExists(carton, number);
            
            if (hit) {
                // Si existe en el cartón se suma un acierto
                hits += 1;
            }
            if (hits == 5){
                // Si hay 5 aciertos se guarda cuando se ha hecho linea
                row = i + 1;
            
            } else if (hits == 10){
                // Si hay 5 aciertos se guarda cuando se ha hecho bingo
                // y se sale del for
                bingo = i + 1;
                break;
            }
        }
        
        // Se muestran los resultados de la partida
        results(row, bingo, prediction, bet);
    }
    
    public static void results(int row, int bingo, int prediction, int bet){
        /***
         * Método para mostrar los resultados.
         * 
         * Se muestra cuando se ha hecho bingo, linea. 
         * Se muestra la predicción hecha por el usuario.
         * 
         * Se comprueba si la predicción y el bingo son iguales, si lo son
         * se multiplica la apuesta por 10 y se devuelve, en caso contrario
         * se le dice al usuario que ha perdido.
        ***/
        
        System.out.println("La linea se ha producido despues de " + row + " números");
        System.out.println("El bingo se ha producido despues de " + bingo + " números");
        System.out.println("Ha predicho que terminaria en " + prediction + " números");
        
        if (prediction == bingo){
            //Si la predicción es la misma que el bingo has ganado
            System.out.println("Enhorabuena!!! Ha ganado " + bet * 10 + " euros");
        } else {
            //Si la predicción no es la misma que el bingo has perdido
            System.out.println("Lo siento, ha perdido " + bet + " euros T_T");
        }
    }
    
    public static int[] createBingo() {
        /***
         * Se crea el bingo, de forma que se meten 99 números en el array
         * no repetidos del 1 al 99 de forma aleatoria.
        ***/
        
        //Se crea un array vacio de tamaño 99
        int[] poolOfNumbers = new int[99];
        for (int i = 0; i < 99; i++) {
            //Se recorre el array poolOfNumbers que se acaba de crear
            boolean exists;
            do {
                //Se obtiene un número aleatorio del 1 al 99
                int random = (int) (Math.random() * 99) + 1;
                //Se comprueba si el número ya existe en poolOfNumbers
                exists = numberExists(poolOfNumbers, random);

                if (!exists) {
                    // Si no existe se guarda el número en
                    // la posición i del array poolOfNumbers
                    poolOfNumbers[i] = random;
                }

            } while (exists); 
            //Se continua hasta que el número obtenido no esté en el array
        }
        return poolOfNumbers;
    }

    public static int[] createCarton() {
         /***
         * Se crea el bingo, de forma que se meten 10 números en el array
         * no repetidos del 1 al 99 de forma aleatoria.
        ***/
        
        //Se crea un array vacio de tamaño 10
        int[] carton = new int[10];
        //Se recorre el array carton que se acaba de crear
        for (int i = 0; i < 10; i++) {
            boolean exists;
            do {
                 //Se obtiene un número aleatorio del 1 al 99
                int random = (int) (Math.random() * 99) + 1;
                 //Se comprueba si el número ya existe en carton
                exists = numberExists(carton, random);

                if (!exists) {
                    // Si no existe se guarda el número en
                    // la posición i del array carton
                    carton[i] = random;
                }

            } while (exists);
            //Se continua hasta que el número obtenido no esté en el array
        }
        return carton;
    }

    public static boolean numberExists(int[] array, int number) {
        /***
         * Método para comprobar si un número ya existe en un array
        ***/
        
        //Se recorre el array de principio a fin
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                // Si algún número de dentro del array coincide con number
                // se devuelve true
                return true;
            }
        }
        
        //en caso de que no haya ningún número repetido se devuelve dalse
        return false;
    }
}
