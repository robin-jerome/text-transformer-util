package com.transformer;

public class Main {

    private static final String INPUT_TEXT =
            "Lorem ipsum dolor sit er elit lamet, consectetaur cillium adipisicing pecu, sed do eiusmod " +
            "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud " +
            "exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
            "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint " +
            "occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." +
            "Nam liber te conscient to factor tum poen legum odioque civiuda.";

    private static final String TRANSFORMATIONS = "H,V,H,15,H,V,-12,V,H,V,V,H,H,H,-100,58,H";

    public static void main(String[] args) {
        TextTransformer transformer = new TextTransformer(INPUT_TEXT, TRANSFORMATIONS);
        String transformedText = transformer.transform();
        System.out.println(INPUT_TEXT);
        System.out.println(transformedText);
    }
}
