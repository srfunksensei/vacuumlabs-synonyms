package com.mb.synonyms.range;

import com.mb.synonyms.range.Range;
import com.mb.synonyms.range.RangeInclusive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RangeInclusiveTest {

    @Test
    public void contains_insideBounds() {
        final Range<Long> range = new RangeInclusive(100l, 200l);

        Assertions.assertTrue(range.contains(100l), "Expected to be inside range (inclusive)");
        Assertions.assertTrue(range.contains(200l), "Expected to be inside range (inclusive)");
        Assertions.assertTrue(range.contains(150l), "Expected to be contained inside bounds");
    }

    @Test
    public void contains_outsideBounds() {
        final Range<Long> range = new RangeInclusive(100l, 200l);

        Assertions.assertFalse(range.contains(99l), "Expected to be outside range");
        Assertions.assertFalse(range.contains(201l), "Expected to be outside range");
    }
}
