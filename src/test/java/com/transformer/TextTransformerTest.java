package com.transformer;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class TextTransformerTest {

    private TextTransformer transformer;

    @Test
    public void initializationConvertsInputToLowerCase() {
        transformer = new TextTransformer("lOrEM", "H,V,1");
        assertArrayEquals(new String[] { "l", "o", "r", "e", "m" }, transformer.getInputChars());
        assertArrayEquals(new String[] { "H", "V", "1" }, transformer.getTransformations());
    }

    @Test
    public void initializationIgnoresWhiteSpaceInTransformations() {
        transformer = new TextTransformer("lOrEM", "H,     V, 1");
        assertArrayEquals(new String[] { "l", "o", "r", "e", "m" }, transformer.getInputChars());
        assertArrayEquals(new String[] { "H", "V", "1" }, transformer.getTransformations());
    }

    @Test
    public void singleHorizontalFlipTransformation() {
        transformer = new TextTransformer("lOrEM", "H");
        String transformed = transformer.transform();
        assertEquals("swuiv", transformed);
    }

    @Test
    public void singleVerticalFlipTransformation() {
        transformer = new TextTransformer("lOrEM", "V");
        String transformed = transformer.transform();
        assertEquals("olfd7", transformed);
    }

    @Test
    public void singleLinearShiftWithPositiveOffset() {
        transformer = new TextTransformer("lOrEM", "1");
        String transformed = transformer.transform();
        assertEquals(";ptr,", transformed);
    }

    @Test
    public void singleLinearShiftWithNegativeOffset() {
        transformer = new TextTransformer("lOrEM", "-1");
        String transformed = transformer.transform();
        assertEquals("kiewn", transformed);
    }

    @Test
    public void singleLinearShiftWithZeroOffset() {
        transformer = new TextTransformer("lOrEM", "0");
        String transformed = transformer.transform();
        assertEquals("lorem", transformed);
    }

    @Test
    public void singleLinearShiftWithEffectiveZeroOffset() {
        transformer = new TextTransformer("lOrEM", "40");
        String transformed = transformer.transform();
        assertEquals("lorem", transformed);
    }

    @Test
    public void horizontalFlipFollowedByHorizontalFlipCausesNoTransformation() {
        transformer = new TextTransformer("lOrEM", "H,H");
        String transformed = transformer.transform();
        assertEquals("lorem", transformed);
    }

    @Test
    public void verticalFlipFollowedByVerticalFlipCausesNoTransformation() {
        transformer = new TextTransformer("lOrEM", "V,V");
        String transformed = transformer.transform();
        assertEquals("lorem", transformed);
    }

    @Test
    public void horizontalVerticalAndLinearTransformations() {
        transformer = new TextTransformer("lOrEM", "H,V,1");
        String transformed = transformer.transform();
        assertEquals("edkl5", transformed);
    }

}
