package com.moderndrummer.rest.converters;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 import com.fasterxml.jackson.core.JsonProcessingException;
 import com.fasterxml.jackson.core.Version;
 import com.fasterxml.jackson.databind.JavaType;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import com.fasterxml.jackson.databind.ObjectWriter;
 import com.fasterxml.jackson.databind.module.SimpleModule;
 import com.fasterxml.jackson.databind.type.TypeFactory;
 */
/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class JSONConverterHandler {

    @ResponseBody
    public static Response writeToJSON(final List<Map<String, BigDecimal>> memberSchoolMapList,
            final List<Map<String, String>> list) throws IOException {
        final SimpleModule module = new SimpleModule("MyMapKeySerializerModule", new Version(1, 0, 0, null));
        TypeFactory.defaultInstance().constructParametricType(ArrayList.class, HashMap.class);

        final MapType myMapType = TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class,
                String.class);

        final ObjectWriter writer = new ObjectMapper().withModule(module).typedWriter(myMapType);

        return Response.ok(writer.writeValueAsString(list)).build();
    }

    public static Response writeToJSON(final List<Map<String, Object>> list) throws IOException {

        return Response.ok(new ObjectMapper().writeValueAsString(list)).build();
    }
}
