package com.transformer;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class TextTransformerTest {

    private TextTransformer transformer;

    @Test
    public void textTransformerInitializationUnifiesCase() {
        transformer = new TextTransformer("lOrEM", "H,V,1");
        assertArrayEquals(transformer.getInputChars(), new String[] { "l", "o", "r", "e", "m" });
        assertArrayEquals(transformer.getTransformations(), new String[] { "H", "V", "1" });
    }

    @Test
    public void textTransformerInitializationRemovesEmptySpaceInTransformations() {
        transformer = new TextTransformer("lOrEM", "H,     V, 1");
        assertArrayEquals(transformer.getInputChars(), new String[] { "l", "o", "r", "e", "m" });
        assertArrayEquals(transformer.getTransformations(), new String[] { "H", "V", "1" });
    }

    @Test
    public void textTransformerTransformsTextValue() {
        transformer = new TextTransformer("lOrEM", "H,V,1");
        String transformed = transformer.transform();
        assertEquals(transformed, "edkl5");
    }

    @Test
    public void textTransformerTransformsSameTransformationMultipleTimes() {
        transformer = new TextTransformer("lOrEM", "H,H,1");
        String transformed = transformer.transform();
        assertEquals(transformed, ";ptr,");
    }

}
