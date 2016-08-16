package com.orgname.skeleton.domain;

import java.util.Optional;

public class ComplexDoohickey {

    private static boolean poo = false;

    private String name;

    private Optional<String> details;

    public ComplexDoohickey(String name, String details) {
        this.name = name;
        this.details = Optional.ofNullable(details);
    }

    public String getName() {
        return name;
    }

    public Optional<String> getDetails() {
        return details;
    }

    public static void main(String[] args) {
        Optional<ComplexDoohickey> one = getOne();
        Optional<String> details = one.flatMap(ComplexDoohickey::getDetails);
        Optional<String> name = one.map(ComplexDoohickey::getName);
    }

    public static Optional<ComplexDoohickey> getOne() {
        if (poo) {
            return Optional.empty();
        } else {
            return Optional.of(new ComplexDoohickey("fart", "butt"));
        }
    }
}
