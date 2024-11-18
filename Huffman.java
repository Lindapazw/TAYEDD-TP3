import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el texto a codificar:");
        String texto = scanner.nextLine();

        // Paso 1: Contar frecuencias
        Map<Character, Integer> frecuencias = new HashMap<>();
        for (char c : texto.toCharArray()) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
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

        NodoHuffman raiz = colaPrioridad.poll();

        // Paso 3: Generar la codificación de Huffman
        Map<Character, String> codigos = new HashMap<>();
        generarCodigos(raiz, "", codigos);

        // Paso 4: Codificar el mensaje
        StringBuilder mensajeCodificado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            mensajeCodificado.append(codigos.get(c));
        }

        // Mostrar resultados
        System.out.println("Codificación de caracteres:");
        for (Map.Entry<Character, String> entrada : codigos.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }
        System.out.println("Mensaje original: " + texto);
        System.out.println("Mensaje codificado: " + mensajeCodificado.toString());
    }

    private static void generarCodigos(NodoHuffman nodo, String codigo, Map<Character, String> codigos) {
        if (nodo == null) return;
        if (nodo.caracter != '\0') { // Es una hoja
            codigos.put(nodo.caracter, codigo);
        }
        generarCodigos(nodo.izquierdo, codigo + "0", codigos);
        generarCodigos(nodo.derecho, codigo + "1", codigos);
    }
}

