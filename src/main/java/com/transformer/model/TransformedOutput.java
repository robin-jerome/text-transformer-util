package com.transformer.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TransformedOutput {
    private List<String> horizontalFlipValue = new ArrayList<>();
    private List<String> verticalFlipValue = new ArrayList<>();
    private List<String> linearShiftValue = new ArrayList<>();

    public void clearValueForTransformation(TransformationType type) {
        switch (type) {
            case VERTICAL:
                verticalFlipValue.clear();
                break;
            case HORIZONTAL:
                horizontalFlipValue.clear();
                break;
            case LINEAR:
                linearShiftValue.clear();
                break;
        }
    }

    public List<String> getListToAddTo(TransformationType type) {
        switch (type) {
            case VERTICAL:
                return verticalFlipValue;
            case HORIZONTAL:
                return horizontalFlipValue;
            case LINEAR:
                return linearShiftValue;
        }
        throw new RuntimeException("Unsupported transformation type");
    }
}
