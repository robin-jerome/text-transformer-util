package com.transformer;

import com.transformer.model.TransformedOutput;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.transformer.model.Keyboard.*;
import static org.apache.commons.lang3.ArrayUtils.contains;
import static org.apache.commons.lang3.ArrayUtils.indexOf;

@Getter(AccessLevel.PACKAGE)
public class TextTransformer {
    private final String[] inputChars;
    private final String[] transformations;
    private TransformedOutput transformedOutput;

    public TextTransformer(String rawInputChars, String rawTransformations) {
        this.inputChars = rawInputChars.toLowerCase().split("");
        this.transformations = rawTransformations.toUpperCase().replace(" ", "").split(",");
        this.transformedOutput = new TransformedOutput();
    }

    private TransformedOutput horizontalFlip(List<String> text) {
        transformedOutput.getHorizontalFlipValue().clear();
        boolean found;
        for (String letter : text) {
            found = false;
            for (int j = 0; j < ROW_COUNT; j++) {
                String[] row = getRow(j);
                if (contains(row, letter)) {
                    found = true;
                    int index = indexOf(row, letter);
                    transformedOutput.getHorizontalFlipValue().add(row[(row.length - 1) - index]);
                }
            }
            if (!found) {
                transformedOutput.getHorizontalFlipValue().add(letter);
            }
        }
        return transformedOutput;
    }

    private TransformedOutput verticalFlip(List<String> text) {
        transformedOutput.getVerticalFlipValue().clear();
        boolean found;
        for (String letter : text) {
            found = false;
            for (int j = 0; j < COLUMN_COUNT; j++) {
                String[] column = getColumn(j);
                if (contains(column, letter)) {
                    found = true;
                    int index = indexOf(column, letter);
                    transformedOutput.getVerticalFlipValue().add(column[(column.length - 1) - index]);
                }
            }
            if (!found) {
                transformedOutput.getVerticalFlipValue().add(letter);
            }
        }
        return transformedOutput;
    }

    private TransformedOutput linearShift(List<String> text, int offset) {
        transformedOutput.getLinearShiftValue().clear();
        for (String letter : text) {
            if (contains(ALL_KEYS, letter)) {
                int index = indexOf(ALL_KEYS, letter);
                if (offset <= 0) {  // Left shift
                    if (offset % KEY_COUNT == 0) { // No effective shift
                        transformedOutput.getLinearShiftValue().add(ALL_KEYS[index]);
                    } else if ((index + (offset % 40)) < 0) {
                        transformedOutput.getLinearShiftValue().add(ALL_KEYS[KEY_COUNT + (index + (offset % 40))]);
                    } else {
                        transformedOutput.getLinearShiftValue().add(ALL_KEYS[index + (offset % 40)]);
                    }
                } else { // Right shift
                    transformedOutput.getLinearShiftValue().add(ALL_KEYS[(index + (offset % KEY_COUNT)) % KEY_COUNT]);
                }
            } else {
                transformedOutput.getLinearShiftValue().add(letter);
            }
        }
        return transformedOutput;
    }

    public String transform() {
        List<String> outputChars = Arrays.asList(inputChars);
        for (String trans: transformations) {
            switch (trans) {
                case "H":
                    outputChars = horizontalFlip(new ArrayList<>(outputChars)).getHorizontalFlipValue();
                    break;
                case "V":
                    outputChars = verticalFlip(new ArrayList<>(outputChars)).getVerticalFlipValue();
                    break;
                default:
                    try {
                        outputChars = linearShift(new ArrayList<>(outputChars), Integer.valueOf(trans)).getLinearShiftValue();
                    } catch (NumberFormatException nfe) {
                        System.out.println(nfe);
                        // Ignore
                    }
            }
        }
        return String.join("", outputChars);
    }
}
