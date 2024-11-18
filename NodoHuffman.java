class NodoHuffman implements Comparable<NodoHuffman> {
    char caracter;
    int frecuencia;
    NodoHuffman izquierdo;
    NodoHuffman derecho;

    // Constructor para nodos hoja
    public NodoHuffman(char caracter, int frecuencia) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
    }

    // Constructor para nodos internos
    public NodoHuffman(int frecuencia, NodoHuffman izquierdo, NodoHuffman derecho) {
        this.caracter = '\0'; // Nodo interno
        this.frecuencia = frecuencia;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    @Override
    public int compareTo(NodoHuffman otro) {
        return Integer.compare(this.frecuencia, otro.frecuencia);
    }
}
