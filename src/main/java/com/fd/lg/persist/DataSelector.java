package com.fd.lg.persist;

import com.fd.lg.template.module.Letter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.fd.lg.config.Constants.*;

public class DataSelector {

    private JdbcTemplate selectQuery;

    public void setJdbcTemplate(final JdbcTemplate jdbcTemplate) {
        selectQuery = jdbcTemplate;
    }

    public Letter getLetter(final String template, final String sql, final String templateSql, final String seqId) {
        final List<Map<String, Object>> parameters = selectQuery.queryForList(sql, seqId);
        final Map<String, Object> templateStr = selectQuery.queryForMap(templateSql, template);
        final Map<String, Object> inputMap = new HashMap<>();
        parameters.forEach(parameter -> inputMap.put((String)parameter.get(NAME),parameter.get(VALUE)));
        final Letter letter = new Letter(template, (String)templateStr.get(TEMPLATE), inputMap);
        return letter;
    }

}
