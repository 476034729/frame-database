
package com.example.database.frame.builder;

import com.example.database.frame.mapping.SqlSource;
import com.example.database.frame.parse.GenericTokenParser;
import com.example.database.frame.parse.TokenHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SqlSourceBuilder {

    public SqlSource parse(String originalSql) {
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);
        String sql = parser.parse(originalSql);
        return new SqlSource(sql,handler.getParams());
    }
    private static class ParameterMappingTokenHandler implements TokenHandler {
        private List<String> params=new ArrayList<>();
        public String handleToken(String content) {
            params.add(content);
            return "?";
        }

        public List<String> getParams() {
            return params;
        }

        public void setParams(List<String> params) {
            this.params = params;
        }
    }
}
