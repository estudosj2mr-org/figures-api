package com.fofok.figuresapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FigureBody {

    @JsonProperty
    private String description;

    @JsonProperty
    private String id;

    @JsonProperty
    private String title;

    @JsonProperty("type")
    private FigureType type;

}
