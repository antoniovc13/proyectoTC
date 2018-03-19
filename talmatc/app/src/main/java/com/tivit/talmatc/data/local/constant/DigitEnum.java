package com.tivit.talmatc.data.local.constant;

/**
 * Created by Alexzander Guillermo on 12/09/2017.
 */

public enum DigitEnum {

    DIGIT_ONE   (1,"1"),
    DIGIT_TWO   (2,"2"),
    DIGIT_THREE (3,"3"),
    DIGIT_FOUR  (4,"4"),
    DIGIT_FIVE  (5,"5"),
    DIGIT_SIX   (6,"6");


    private int id;
    private String value;

    DigitEnum(int id, String value) {
        this.setId(id);
        this.setValue(value);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
