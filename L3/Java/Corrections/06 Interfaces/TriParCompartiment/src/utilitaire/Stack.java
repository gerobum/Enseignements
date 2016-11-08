package utilitaire;

/**
 *
 * @author yvan
 */
public class Stack {
    private static class Chainon {
        Object value;
        Chainon next;

        public Chainon(Object value, Chainon next) {
            this.value = value;
            this.next = next;
        }
    }
    
    Chainon top;
    
    public Stack() {
        top = null;
    }
    
    public void push(Object o) {
        top = new Chainon(o, top);
    }
    
    public Object pop() {
        Object o = top.value;
        top = top.next;
        return o;
    }
    
    public boolean isEmpty() {
        return top == null;
    }
}
