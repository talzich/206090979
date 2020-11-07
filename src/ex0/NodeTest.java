package ex0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testKeys() {
        NodeData n0 = new NodeData();
        NodeData n1 = new NodeData();
        NodeData n2 = new NodeData();
        assertEquals(0, n0.getKey());
        assertEquals(1, n1.getKey());
        assertEquals(2, n2.getKey());
        NodeData n3 = new NodeData();
        assertEquals(3, n3.getKey());
    }

    @Test void testAddNi(){
        NodeData n0 = new NodeData();
        NodeData n1 = new NodeData();
        NodeData n2 = new NodeData();
        NodeData n3 = new NodeData();
        NodeData n4 = new NodeData();
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