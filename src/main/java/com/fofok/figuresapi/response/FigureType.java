package com.fofok.figuresapi.response;

public enum FigureType {

    ACTION_FIGURE("action figure"), FUNKO("funko"), NENDOROID("nendoroid"), GASHAPON("gashapon");

    private final String stringFigureType;

    FigureType(String str) {
        this.stringFigureType = str;
    }

    public String getStringFigureType() {
        return this.stringFigureType;
    }

    public static FigureType fromString(String str) {
        for (FigureType type : FigureType.values()) {
            if (type.stringFigureType.equals(str))
                return type;
        }
        return ACTION_FIGURE;
    }

}
