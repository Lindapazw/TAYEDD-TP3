// Linda Candela Paz
//42.379.854

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Algoritmo de Compresión de Huffman ====");
        System.out.print("Ingrese el texto que desea codificar: ");
        String texto = scanner.nextLine().trim();

        if (texto.isEmpty()) {
            System.out.println("El texto ingresado está vacío. Por favor, inténtelo de nuevo.");
            return;
        }

        // Paso 1: Contar frecuencias de caracteres
        Map<Character, Integer> frecuencias = new HashMap<>();
        for (char c : texto.toCharArray()) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }

        System.out.println("\n=== Frecuencias de caracteres ===");
        for (Map.Entry<Character, Integer> entrada : frecuencias.entrySet()) {
            System.out.printf("'%c': %d veces\n", entrada.getKey(), entrada.getValue());
        }

        // Paso 2: Crear nodos y construir el árbol de Huffman
        PriorityQueue<NodoHuffman> colaPrioridad = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entrada : frecuencias.entrySet()) {
            colaPrioridad.add(new NodoHuffman(entrada.getKey(), entrada.getValue()));
        }

        while (colaPrioridad.size() > 1) {
            NodoHuffman nodo1 = colaPrioridad.poll();
            NodoHuffman nodo2 = colaPrioridad.poll();
            NodoHuffman nuevoNodo = new NodoHuffman(nodo1.frecuencia + nodo2.frecuencia, nodo1, nodo2);
            colaPrioridad.add(nuevoNodo);
        }

        NodoHuffman raiz = colaPrioridad.poll(); // Raíz del árbol de Huffman

        // Paso 3: Generar la codificación de Huffman
        Map<Character, String> codigos = new HashMap<>();
        generarCodigos(raiz, "", codigos);

        System.out.println("\n=== Codificación de caracteres ===");
        for (Map.Entry<Character, String> entrada : codigos.entrySet()) {
            System.out.printf("'%c': %s\n", entrada.getKey(), entrada.getValue());
        }

        // Paso 4: Codificar el mensaje original
        StringBuilder mensajeCodificado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            mensajeCodificado.append(codigos.get(c));
        }

        System.out.println("\n=== Resultados ===");
        System.out.println("Mensaje original: " + texto);
        System.out.println("Mensaje codificado: " + mensajeCodificado.toString());
        System.out.println("¡Completada con éxito!");
    }

    /**
     * Método para generar códigos de Huffman recorriendo el árbol.
     *
     * @param nodo Nodo actual del árbol.
     * @param codigo Código acumulado hasta el nodo actual.
     * @param codigos Mapa para almacenar los códigos generados.
     */
    private static void generarCodigos(NodoHuffman nodo, String codigo, Map<Character, String> codigos) {
        if (nodo == null) return;

        // Si es un nodo hoja, asociamos el código al carácter
        if (nodo.caracter != '\0') {
            codigos.put(nodo.caracter, codigo);
        }

        // Recursivamente generamos códigos para los hijos izquierdo y derecho
        generarCodigos(nodo.izquierdo, codigo + "0", codigos);
        generarCodigos(nodo.derecho, codigo + "1", codigos);
    }
}
