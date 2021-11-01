package com.fd.lg.template.module;

import java.util.Map;

public class Letter {
    final private String templateName;
    final private String templateString;
    final private Map<String, Object> input;

    public Letter(String templateName, String templateString, Map<String, Object> input){
        this.templateName = templateName;
        this.templateString = templateString;
        this.input = input;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getTemplateString() {
        return templateString;
    }

    public Map<String, Object> getInput() {
        return input;
    }
}
