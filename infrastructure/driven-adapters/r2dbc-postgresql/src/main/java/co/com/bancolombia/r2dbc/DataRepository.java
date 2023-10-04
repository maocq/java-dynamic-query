package co.com.bancolombia.r2dbc;

import co.com.bancolombia.model.taxonomy.Condition;
import co.com.bancolombia.model.taxonomy.Filter;
import co.com.bancolombia.model.taxonomy.QueryTaxonomy;
import co.com.bancolombia.model.taxonomy.Taxonomy;
import co.com.bancolombia.model.taxonomy.gateways.TaxonomyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

import static org.springframework.data.relational.core.query.Criteria.where;

@Repository
@RequiredArgsConstructor
public class DataRepository implements TaxonomyRepository {

    private final R2dbcEntityTemplate template;

    @Override
    public Flux<? extends Taxonomy> find(QueryTaxonomy query) {
        Criteria criteria = query
                .getFilters().stream()
                .collect(Collectors.groupingBy(Filter::getAtribute))
                .values()
                .stream()
                .map(filters -> filters.stream()
                        .map(filter -> extract(filter.getAtribute(), filter.getValue(), filter.getCondition()))
                        .reduce(Criteria.empty(), Criteria::or))
                .reduce(Criteria.empty(), Criteria::and);

        /*
        Criteria criteria = query.getFilters().stream()
                .map(filter -> extract(filter.getAtribute(), filter.getValue(), filter.getCondition()))
                .reduce(Criteria.empty(), Criteria::or);
         */

        var type = query.getTaxonomy().getClazz();

        return template
                .select(type)
                .matching(Query.query(criteria))
                .all();                                         // <-
    }

    private static Criteria extract(String attribute, String value, Condition filter) {
        return switch (filter) {
            case IS -> where(attribute).is(value);
            case NOT -> where(attribute).not(value);
            case IN -> where(attribute).in(value);
            case NOT_IN -> where(attribute).notIn(value);
            case LESS_THAN -> where(attribute).lessThan(value);
            case LESS_THAN_OR_EQUALS -> where(attribute).lessThanOrEquals(value);
            case GREATER_THAN -> where(attribute).greaterThan(value);
            case GREATER_THAN_OR_EQUALS -> where(attribute).greaterThanOrEquals(value);
            case LIKE -> where(attribute).like(value);
            case NOT_LIKE -> where(attribute).notLike(value);
            case IS_NULL -> where(attribute).isNull();
            case IS_NOT_NULL -> where(attribute).isNotNull();
            case IS_TRUE -> where(attribute).isTrue();
            case IS_FALSE -> where(attribute).isFalse();
        };
    }
}
