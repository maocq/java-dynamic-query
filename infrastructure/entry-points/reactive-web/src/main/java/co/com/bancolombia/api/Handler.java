package co.com.bancolombia.api;

import co.com.bancolombia.model.exceptions.BusinessException;
import co.com.bancolombia.model.taxonomy.QueryTaxonomy;
import co.com.bancolombia.model.taxonomy.TaxonomyDetail;
import co.com.bancolombia.model.taxonomy.gateways.TaxonomyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

import static co.com.bancolombia.model.exceptions.message.BusinessErrorMessage.INVALID_REQUEST;

@Slf4j
@Component
@RequiredArgsConstructor
public class Handler {

    private final TaxonomyRepository repository;

    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue(TaxonomyDetail.detail);
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(QueryTaxonomy.class)
                .flatMap(queryTaxonomy -> Mono.fromSupplier(() -> validate(queryTaxonomy)))
                .flatMap(queryTaxonomy -> repository.find(queryTaxonomy).collectList())
                .flatMap(taxonomy -> ServerResponse.ok().bodyValue(taxonomy));
    }

    // Domain
    private QueryTaxonomy validate(QueryTaxonomy queryTaxonomy) {
        Map<String, String> fields = TaxonomyDetail.detail.get(queryTaxonomy.getTaxonomy().name());
        queryTaxonomy.getFilters().forEach(filter -> {
            if (fields.get(filter.getAtribute()) == null) {
                log.error("Invalid atribute: {}", filter.getAtribute());
                throw new BusinessException(INVALID_REQUEST);
            }
        });
        return queryTaxonomy;
    }
}
