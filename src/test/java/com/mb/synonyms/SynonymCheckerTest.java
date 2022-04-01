package com.mb.synonyms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynonymCheckerTest {

    @Test
    public void init_malformedSynonyms() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final List<String> synonyms = new ArrayList<>(Collections.singletonList("word"));
            new SynonymChecker(synonyms);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final List<String> synonyms = new ArrayList<>(Collections.singletonList("word asdas wwesd"));
            new SynonymChecker(synonyms);
        });
    }

    @Test
    public void check_malformedQueries() {
        final List<String> synonyms = new ArrayList<>(Collections.singletonList("word synonym"));
        final SynonymChecker synonymChecker = new SynonymChecker(synonyms);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final List<String> queries = new ArrayList<>(Collections.singletonList("word"));
            synonymChecker.check(queries);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final List<String> queries = new ArrayList<>(Collections.singletonList("word asdas wwesd"));
            synonymChecker.check(queries);
        });
    }

    @Test
    public void check() {
        final List<String> synonyms = new ArrayList<>() {{
            add("magic WaTCH");
            add("uNdeRDog EartH");
            add("EArTh caKE");
            add("UnIforM baLance");
            add("BALancE ABILity");
            add("UnifORM uNIfORM");
            add("maNagER WaTcH");
            add("MaNagER MaNAGeR");
            add("FaKe EaRth");
            add("BAlance CAKe");
            add("AbIliTY uNiFOrm");
            add("UNdErdoG magiC");
        }};
        final SynonymChecker synonymChecker = new SynonymChecker(synonyms);

        final List<String> queries = new ArrayList<>() {{
            add("Magic MagIc");
            add("Cake eArth");
            add("aBIlITy abiLiTY");
            add("watCh UniFoRM");
            add("CAke FaKe");
            add("FAkE watCh");
            add("MagIC abIlitY");
            add("uNIfoRm AbIlITY");
            add("baLAnCe eaRtH");
            add("bAlANCE MANAGER");
        }};
        final List<String> result = synonymChecker.check(queries);
        Assertions.assertEquals(queries.size(), result.size(), "Expected same size as queries");
        result.forEach(e -> Assertions.assertEquals(SynonymChecker.SYNONYMS, e, "Expected synonyms"));
    }
}
