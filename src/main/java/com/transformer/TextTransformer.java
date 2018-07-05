package com.transformer;

import com.transformer.model.TransformationType;
import com.transformer.model.TransformedOutput;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.transformer.model.Keyboard.*;
import static com.transformer.model.TransformationType.HORIZONTAL;
import static com.transformer.model.TransformationType.LINEAR;
import static com.transformer.model.TransformationType.VERTICAL;
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

    private TransformedOutput flip(List<String> text, TransformationType type) {
        transformedOutput.clearValueForTransformation(type);
        int arrayLen = HORIZONTAL.equals(type) ? ROW_COUNT : COLUMN_COUNT;
        boolean found;
        for (String letter : text) {
            found = false;
            for (int j = 0; j < arrayLen; j++) {
                String[] arr = HORIZONTAL.equals(type) ? getRow(j) : getColumn(j);
                if (contains(arr, letter)) {
                    found = true;
                    int index = indexOf(arr, letter);
                    transformedOutput.getListToAddTo(type).add(arr[(arr.length - 1) - index]);
                }
            }
            if (!found) {
                transformedOutput.getListToAddTo(type).add(letter);
            }
        }
        return transformedOutput;
    }

    private TransformedOutput linearShift(List<String> text, int offset) {
        transformedOutput.clearValueForTransformation(LINEAR);
        List<String> listToAddTo = transformedOutput.getListToAddTo(LINEAR);

        for (String letter : text) {
            if (contains(ALL_KEYS, letter)) {
                int index = indexOf(ALL_KEYS, letter);
                if (offset <= 0) {  // Left shift
                    if (offset % KEY_COUNT == 0) { // No effective shift
                        listToAddTo.add(ALL_KEYS[index]);
                    } else if ((index + (offset % 40)) < 0) {
                        listToAddTo.add(ALL_KEYS[KEY_COUNT + (index + (offset % 40))]);
                    } else {
                        listToAddTo.add(ALL_KEYS[index + (offset % 40)]);
                    }
                } else { // Right shift
                    listToAddTo.add(ALL_KEYS[(index + (offset % KEY_COUNT)) % KEY_COUNT]);
                }
            } else {
                listToAddTo.add(letter);
            }
        }
        return transformedOutput;
    }

    public String transform() {
        List<String> outputChars = Arrays.asList(inputChars);
        for (String trans: transformations) {
            switch (trans) {
                case "H":
                    outputChars = flip(new ArrayList<>(outputChars), HORIZONTAL).getHorizontalFlipValue();
                    break;
                case "V":
                    outputChars = flip(new ArrayList<>(outputChars), VERTICAL).getVerticalFlipValue();
                    break;
                default:
                    try {
                        outputChars = linearShift(new ArrayList<>(outputChars), Integer.valueOf(trans)).getLinearShiftValue();
                    } catch (NumberFormatException nfe) {
                        // Ignore
                    }
            }
        }
        return String.join("", outputChars);
    }
}
