package com.fd.lg.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import java.util.Optional;

import static com.fd.lg.config.Constants.*;

final public class Configurator {

    private volatile static Configurator configurator;
    private static Properties props;

    private Configurator() {
        Yaml yaml = new Yaml(new Constructor(Properties.class));
        props = yaml.load(Configurator.class.getClassLoader().getResourceAsStream(PROPERTIES));
    }

    public static Properties getProperties() {
        if (configurator == null) {
            configurator = new Configurator();
        }
        return props;
    }

    public static Configurator getInstance() {
        if (configurator == null) {
            configurator = new Configurator();
        }
        return configurator;
    }

    public Optional<Template> getMatchedTemplate(String template) {
        return props.templates
                .stream()
                .filter(t -> t.template.equals(template))
                .findFirst();
    }

}
