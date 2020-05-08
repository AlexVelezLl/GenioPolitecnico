/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Comparator;
import java.util.Stack;

public class ArbolDecision<E> implements Comparator<E> {

    private Nodo<E> root;

    public ArbolDecision() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int alturaArbol() {
        return alturaArbol(root);
    }

    private int alturaArbol(Nodo<E> n) {
        if (n == null) {
            return 0;
        }
        return 1 + Math.max(alturaArbol(n.getSi()), alturaArbol(n.getNo())); // getSi Izq
    }

    public E getRoot() {
        return root == null ? null : root.getData();
    }

    public E getAnswer(E parent, boolean afirmative) {
        if (parent == null) {
            return null;
        }
        Nodo<E> p = searchNodo(parent);
        if (p == null) {
            return null;
        }
        Nodo<E> respNode = afirmative ? p.getSi() : p.getNo();
        return respNode == null ? null : respNode.getData();
    }

    public boolean replaceData(E oldData, E newData) {
        if (oldData == null || newData == null) {
            return false;
        }
        Nodo<E> n = searchNodo(oldData);
        if (n == null) {
            return false;
        }
        n.setData(newData);
        return true;
    }

    public static ArbolDecision<String> cargarArbol() {
        ArbolDecision<String> tree = new ArbolDecision<>();
        Stack<Nodo<String>> pila = new Stack<>();
        Nodo<String> nodo = null;
        try (BufferedReader bf = new BufferedReader(new FileReader("src/Resources/datos-1.txt"))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String st = line.substring(3);
                nodo = new Nodo<>(st);
                if (line.startsWith("#P")) {
                    if (!pila.isEmpty()) {
                        nodo.setNo(pila.pop());
                    }
                    if (!pila.isEmpty()) {
                        nodo.setSi(pila.pop());
                    }
                }
                pila.push(nodo);
            }
        } catch (Exception ex) {

        }
        tree.root = nodo;
        return tree;
    }

    public void guardarArbol() {
        String arbol = postOrden(root);
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("src/Resources/datos-1.txt"))) {
            bf.write(arbol);
        } catch (Exception ex) {

        }
    }

    public String postOrden(Nodo<E> n) {
        if (n != null) {
            if (n.getNo() == null && n.getSi() == null) {
                return "#R " + n.getData();
            }
            return postOrden(n.getSi()) + "\n" + postOrden(n.getNo()) + "\n#P " + n.getData();
        }
        return "";
    }
    //////////// ---------------Insertar en el arbol------------------///////////

    public boolean add(E child, E parent, boolean afirmative) {
        Nodo<E> nch = new Nodo<>(child);
        if (child == null) {
            return false;
        }
        if (parent == null && isEmpty()) {
            root = nch;
            return true;
        }
        if (parent != null && searchNodo(child) == null) {
            Nodo<E> np = searchNodo(parent);

            if (afirmative) {
                if (np == null || np.getSi() != null) {
                    return false;
                }
                np.setSi(nch);
            } else {
                if (np == null || np.getNo() != null) {
                    return false;
                }
                np.setNo(nch);
            }
            return true;
        }
        return false;
    }

    public boolean addPregunta(E element) {
        if (element == null) {
            return false;
        }

        return addPregunta(element, root);
    }

    public boolean addPregunta(E pregunta, Nodo<E> raiz) {

        Nodo<E> nodoPregunta = new Nodo<>(pregunta);

        if (pregunta == null) {
            return false;
        } else if (raiz == null) {
            Nodo<E> nodoRaiz = new Nodo<>(pregunta, true);
            this.root = nodoRaiz;
            return true;
        } else {
            if (searchNodo(pregunta) == null) {

                if ((raiz.getSi() != null && raiz.getNo() != null)) {
                    if (raiz.getNo().getNo() != null && raiz.getSi().getNo() == null && raiz.isFlag()) {
                        return addPregunta(pregunta, raiz.getSi());
                    }
                    return addPregunta(pregunta, raiz.getNo());
                } else if (raiz.getNo() == null) {
                    raiz.setNo(nodoPregunta);
                    return true;
                } else if (raiz.getSi() == null && raiz.getNo() != null && raiz.isFlag()) {
                    raiz.setSi(nodoPregunta);
                }
                return true;
            }
            return false;
        }
    }

    public boolean addRespuesta(E element) {
        if (element == null) {
            return false;
        }

        return addRespuesta(element, root);
    }

    public boolean addRespuesta(E respuesta, Nodo<E> raiz) {

        Nodo<E> nodoRespuesta = new Nodo<>(respuesta);

        if (respuesta == null) {
            return false;
        } else if (raiz == null) {
            this.root = nodoRespuesta;
            return true;
        } else {
            if (searchNodo(respuesta) == null) {

                if ((raiz.getSi() != null && raiz.getNo() != null)) {

                    if (raiz.getNo().getSi() != null && raiz.getSi().getSi() == null && raiz.isFlag() == true) {
                        return addRespuesta(respuesta, raiz.getSi());
                    }

                    return addRespuesta(respuesta, raiz.getNo());
                } else if (raiz.getSi() == null) {
                    raiz.setSi(nodoRespuesta);
                    return true;
                } else if (raiz.getNo() == null && raiz.getSi() != null) {
                    raiz.setFlag(true);
                    raiz.setNo(nodoRespuesta);
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Función auxiliar de "add" Busca si el elemento "data" ya se encuentra en el
     * árbol
     *
     * @param data
     * @return Nodo
     */
    private Nodo<E> searchNodo(E data) {
        if (data == null) {
            return null;
        }
        return searchNodo(data, root);
    }

    /**
     * Busca la data en el árbol
     *
     * @param data
     * @param raiz
     * @return Nodo
     */
    private Nodo<E> searchNodo(E data, Nodo<E> raiz) {

        if (raiz == null) {
            return raiz;
        } else if (raiz.getData().equals(data)) {
            return raiz;
        } else {
            Nodo<E> l = searchNodo(data, raiz.getSi());
            Nodo<E> r = searchNodo(data, raiz.getNo());

            if (l != null) {
                return l;
            }
            return r;
        }
    }

    // enOrden-------
    public void enOrden() {
        enOrden(this.root);
        System.out.println();
    }

    private void enOrden(Nodo<E> nodo) {

        if (nodo != null) {
            enOrden(nodo.getNo());
            enOrden(nodo.getSi());

            System.out.print(nodo.getData() + ", ");
        }
    }

    @Override
    public int compare(E t, E t1) {
        if (t.equals(t1)) {
            return 1;
        }
        return 0;
    }
}
