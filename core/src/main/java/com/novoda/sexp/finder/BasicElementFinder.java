package com.novoda.sexp.finder;

import android.sax.Element;

import com.novoda.sexp.parser.Parser;

public class BasicElementFinder<T> implements ElementFinder<T> {

    private final Parser<T> parser;
    private T result;

    public BasicElementFinder(Parser<T> parser) {
        this.parser = parser;
    }

    @Override
    public void find(Element from, String tag) {
        parser.parse(from.getChild(tag), this);
    }

    @Override
    public void onParsed(T body) {
        result = body;
    }

    @Override
    public T getResult() {
        validateResult();
        return result;
    }

    private void validateResult() {
        if (result == null) {
            throw new NullPointerException("Did you call find() with a valid tag and/or wait for the result to be parsed?");
        }
    }
}