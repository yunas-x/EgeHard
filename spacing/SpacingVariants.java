package org.example.spacing;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum SpacingVariants {

    Hyphen,
    WhiteSpace,
    NoSpace;

}
