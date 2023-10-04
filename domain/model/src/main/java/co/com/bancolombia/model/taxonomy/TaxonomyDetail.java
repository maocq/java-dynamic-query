package co.com.bancolombia.model.taxonomy;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class TaxonomyDetail {
    public static final Map<String, Map<String, String>> detail;

    static {
        detail = Arrays.stream(Taxonomy.Name.values()).map(taxonomy -> {
            Map<String, String> fields = Arrays.stream(taxonomy.getClazz().getDeclaredFields())
                    .map(field -> Tuples.of(field.getName(), field.getType().getSimpleName()))
                    .collect(Collectors.toMap(Tuple2::getT1, Tuple2::getT2));

            return Tuples.of(taxonomy.name(), fields);
        }).collect(Collectors.toMap(Tuple2::getT1, Tuple2::getT2));
    }

}
