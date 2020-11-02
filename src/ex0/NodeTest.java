package ex0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testKeys() {
        Node n0 = new Node();
        Node n1 = new Node();
        Node n2 = new Node();
        assertEquals(0, n0.getKey());
        assertEquals(1, n1.getKey());
        assertEquals(2, n2.getKey());
        Node n3 = new Node();
        assertEquals(3, n3.getKey());
    }

    @Test void testAddNi(){
        Node n0 = new Node();
        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();
        Node n4 = new Node();
        n0.addNi(n1);
        assertTrue(n0.hasNi(1));
        assertTrue(n0.hasNi(0));
        assertFalse(n0.hasNi(2));
        n0.addNi(n2);
        assertTrue(n0.hasNi(2));
        n0.addNi(n4);
        assertTrue(n0.hasNi(4));
        assertFalse(n0.hasNi(3));

    }
}