package co.com.bancolombia.model.taxonomy.gateways;

import co.com.bancolombia.model.taxonomy.QueryTaxonomy;
import co.com.bancolombia.model.taxonomy.Taxonomy;
import reactor.core.publisher.Flux;

public interface TaxonomyRepository {

    Flux<? extends Taxonomy> find(QueryTaxonomy query);
}
