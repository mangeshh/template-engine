package com.fd.lg.config;

import java.util.List;
import java.util.Objects;

/**
 * project: "letter_generation"
 * templates:
 *   - template: "DISPUTE_LETTER"
 *     sql: "select name, phone from param_tbl where seq_id = ?"
 *   - template: "GENERAL_LETTER"
 *     sql: "select name, phone from param_tbl where seq_id = ?"
 *
 */
public class Properties {
    public String project;
    public List<Template> templates;

    public String getProject() {
        return project;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "project='" + project + '\'' +
                ", templates=" + templates +
                '}';
    }
}

