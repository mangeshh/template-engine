package com.fd.lg.template.module;

final public class ParamObject {

    final private String name;
    final private String developer;

    public ParamObject(final String name, final String developer) {
        this.name = name;
        this.developer = developer;
    }

    public String getName() {
        return name;
    }

    public String getDeveloper() {
        return developer;
    }

}
