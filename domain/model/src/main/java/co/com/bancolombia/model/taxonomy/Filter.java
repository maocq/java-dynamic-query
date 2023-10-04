package co.com.bancolombia.model.taxonomy;

import lombok.Getter;

@Getter
public class Filter {

    private String atribute;
    private Condition condition;
    private String value;
}
