package com.fd.lg.engine;

import com.fd.lg.config.Configurator;
import com.fd.lg.config.SpringJDBCConfiguration;
import com.fd.lg.persist.DataSelector;
import com.fd.lg.template.module.Letter;
import freemarker.template.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.Map;
import java.util.Optional;

public class TemplateProcessor {

    final private Configuration configuration = new Configuration();
    final static AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
            SpringJDBCConfiguration.class);
    final static DataSelector dataSelector = applicationContext.getBean(DataSelector.class);

    public TemplateProcessor() {
        configuration.setClassForTemplateLoading(getClass(), "/");
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setIncompatibleImprovements(new Version(2, 3, 20));
    }

    public void process(final String templateName, final Map<String, Object> input, final Writer writer) {
        try {
            freemarker.template.Template template = configuration.getTemplate(templateName);
            template.process(input, writer);
            try {
                template.process(input, writer);
            } finally {
                writer.close();
            }
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    public void process(final String templateName, final String templateStr, final Map<String, Object> input, final Writer writer) {
        try {
            freemarker.template.Template template = new freemarker.template.Template(templateName, new StringReader(templateStr), configuration);
            try {
                template.process(input, writer);
            } finally {
                writer.close();
            }
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * TODO
     * change database to H2 instead of mysql
     * make a jar and use it - Optional.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //case 1
        /**
         * To: ${name}
         *   The brown fox jumped over ${name} to get
         *   to ${address} on ${date} Today
         */
        /*String templateName = "FOX Header";
        String sequenceId = "1";
        getLetter(templateName, sequenceId);*/

        //case 2
        /**
         * The Lady Header
         *   A lady who's name is ${name} who was born on
         *   ${dob} used her SSN of ${ssn}
         *   and a phone number of ${phone} to verify her identity
         */
        /*String templateName = "The Lady Header";
        String sequenceId = "2";
        getLetter(templateName, sequenceId);*/

        //case 3
        /**
         * ${name} has requested the address of ${address}
         * and phone number of ${phone} to be updated today, ${date}
         */
        /*String templateName = "Change Request Header";
        String sequenceId = "3";
        getLetter(templateName, sequenceId);*/

        //case 4
        /**
         * <#if "NJ" == state>
         *   New Jersey state tax is 6.625%
         * <#elseif "NY" == state>
         *   New York state tax is 10.3%
         * <#elseif "OH" == state>
         *   Ohio state tax is 5.75%
         * </#if>
         */
        /*String templateName = "state tax template";
        String sequenceId = "4";
        getLetter(templateName, sequenceId);*/

        //case 5
        /**
         * <#assign providers = ["transunion", "experian", "amrent", ]>
         * 	<#list providers  as provider>
         * 		name :  ${provider}  \n
         * 	</#list>
         */
       /* String templateName = "provider list";
        String sequenceId = "0";
        getLetter(templateName, sequenceId);*/

        /**
         * <#assign references = ["MICROSOFT", "GOOGLE", "APPL"]>
         * <#assign reference_index = 1>
         * <html>
         * <head><title> ${blogTitle} </title>
         * <body>
         * <h1> ${blogTitle} </h1>
         * <p>
         *   ${message}
         * </p>
         * <h3>References</h3>
         * <#list references as reference>
         *     ${reference_index + 1}. <a href="http://www.${reference}.com"> ${reference} </a> <br/>
         * </#list>
         * </body>
         * </html>
         */
        String templateName = "html basic";
        String sequenceId = "6";
        getLetter(templateName, sequenceId);

    }

    public static void getLetter(String templateName, String seqId){
        Optional<com.fd.lg.config.Template> template = Configurator.getInstance().getMatchedTemplate(templateName);
        com.fd.lg.config.Template t = template.get();
        Letter letter = dataSelector.getLetter(t.template, t.sql, t.templateSql, seqId);
        TemplateProcessor processor = new TemplateProcessor();
        Writer writer = new OutputStreamWriter(System.out);
        processor.process(templateName, letter.getTemplateString(), letter.getInput(), writer);
    }
}

