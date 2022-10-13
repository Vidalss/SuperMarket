/*
10.3
Un pequeño supermercado en la salida de 3 cajas de pago. En el local hay 25 carritos de compra. Escribir un
programa que simule el funcionamiento, siguiendo las siguientes reglas:
1. Si cuando llega un cliente no hay ningún carrito disponible, espera a que lo haya
2. Ningún cliente se impacienta yabandona el supermercado sin pasar por alguna de las colas de las cajas.
3. Cuando un cliente finaliza su compra, se coloca en la cola de la caja que hay menos gente y no se cambia
de cola.
4. En el momento en el que un cliente paga en la caja, su carrito de la compra queda disponible.
 */

public class Iniciar {

    public static void main(String... args) {
        Main main = new Main();
        main.start();
    }
}
