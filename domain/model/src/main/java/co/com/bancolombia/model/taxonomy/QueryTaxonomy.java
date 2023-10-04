package co.com.bancolombia.model.taxonomy;

import lombok.Getter;

import java.util.List;

@Getter
public class QueryTaxonomy {

    private Taxonomy.Name taxonomy;
    private List<Filter> filters;
}
