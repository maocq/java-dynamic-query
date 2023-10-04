package co.com.bancolombia.model.process;

import co.com.bancolombia.model.taxonomy.Taxonomy;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Process implements Taxonomy {
    private final String name;
    private final String description;
    private final String responsible;
    private final String responsibleAux;

    private final String status;
}
