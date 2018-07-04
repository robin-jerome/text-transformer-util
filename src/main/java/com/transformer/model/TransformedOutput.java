package com.transformer.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TransformedOutput {
    private List<String> horizontalFlipValue = new ArrayList<>();
    private List<String> verticalFlipValue = new ArrayList<>();
    private List<String> linearShiftValue = new ArrayList<>();
}
