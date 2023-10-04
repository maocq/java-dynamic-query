package co.com.bancolombia.model.taxonomy;

import co.com.bancolombia.model.process.Process;
import co.com.bancolombia.model.risk.Risk;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface Taxonomy {

    @Getter
    @AllArgsConstructor
    enum Name {
        RISK(Risk.class),
        PROCESS(Process.class);

        private final Class<? extends Taxonomy> clazz;
    }


    String getName();
    String getDescription();
    String getResponsible();
    String getResponsibleAux();
}
