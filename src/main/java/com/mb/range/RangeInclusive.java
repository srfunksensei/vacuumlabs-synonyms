package com.mb.synonyms;

import com.mb.range.Range;

public class RangeInclusive<T extends Number & Comparable<? super T>> implements Range<T> {
    private final T min, max;

    public RangeInclusive(final T min, final T max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean contains(final T val) {
        return containsInclusive(min, val) && containsInclusive(val, max);
    }

    public boolean containsInclusive(final T first, final T second) {
        return first.compareTo(second) < 0 || first.compareTo(second) == 0;
    }
}
