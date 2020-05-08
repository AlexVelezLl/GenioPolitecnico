/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA;

public class Nodo<E> {

    private E Data;
    /**
     * Retorna true si el nodo es tiene dos respuestas o dos preguntas
     */
    private boolean flag;
    private Nodo<E> si; // Izquierdo Respuesta
    private Nodo<E> no; // Derecho Pregunta

    public Nodo(E pregunta, Nodo<E> si, Nodo<E> no, boolean flag) {
        this.Data = pregunta;
        this.si = si;
        this.no = no;
        this.flag = flag;
        this.si = si;
        this.no = no;
    }

    public Nodo() {
        this.Data = null;
        this.si = null;
        this.no = null;
        this.flag = false;
    }

    public Nodo(E Data) {
        this.Data = Data;
        this.flag = false;
    }

    public Nodo(E Data, boolean doble) {
        this.Data = Data;
        this.flag = doble;
    }

    public void setData(E Data) {
        this.Data = Data;
    }

    public void setSi(Nodo<E> si) {
        this.si = si;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean doble) {
        this.flag = doble;
    }

    public void setNo(Nodo<E> no) {
        this.no = no;
    }

    public E getData() {
        return Data;
    }

    public Nodo<E> getSi() {
        return si;
    }

    public Nodo<E> getNo() {
        return no;
    }
}
