package com.fd.lg.config;

import java.util.Objects;

public class Template {

    public String template;
    public String sql;
    public String templateSql;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.fd.lg.config.Template template1 = (com.fd.lg.config.Template) o;
        return Objects.equals(template, template1.template);
    }

    @Override
    public int hashCode() {
        return Objects.hash(template);
    }

    @Override
    public String toString() {
        return "Template{" +
                "template='" + template + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}