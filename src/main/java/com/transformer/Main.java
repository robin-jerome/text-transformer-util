package com.transformer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main {

    private static final String INPUT_TEXT =
            "Lorem ipsum dolor sit er elit lamet, consectetaur cillium adipisicing pecu, sed do eiusmod " +
                    "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud " +
                    "exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
                    "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint " +
                    "occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." +
                    "Nam liber te conscient to factor tum poen legum odioque civiuda.";

    private static final String TRANSFORMATIONS = "H,V,H,15,H,V,-12,V,H,V,V,H,H,H,-100,58,H";

    public static void main(String[] args) throws IOException {
        String inputFileContent;
        String transformationFileContent;
        if (args.length == 2) {
            System.out.println("Input and transformation files will be picked up from command line arguments");
            inputFileContent = Files.lines(Paths.get(args[0])).collect(Collectors.joining(""));
            transformationFileContent = Files.lines(Paths.get(args[1])).collect(Collectors.joining(""));
        } else {
            System.err.println("Not enough arguments default values will be used for input & transformation files");
            inputFileContent = INPUT_TEXT;
            transformationFileContent = TRANSFORMATIONS;
        }

        TextTransformer transformer = new TextTransformer(inputFileContent, transformationFileContent);
        String transformedText = transformer.transform();
        System.out.println("--------------------------------------------");
        System.out.println("Original Text          : " + inputFileContent);
        System.out.println("Transformation applied : " + transformationFileContent);
        System.out.println("Transformed Text       : " + transformedText);
        System.out.println("--------------------------------------------");
    }
}
