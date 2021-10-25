package com.elegantbanshee;

import com.google.common.collect.Lists;
import com.google.gson.JsonSyntaxException;
import org.jetbrains.annotations.Nullable;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import static spark.Spark.*;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.BufferedReader;

public class ElegantBansheeComServer {
    
    static void getGeneric(String path, String templatePath) {
        get(path, (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
        });
    }
}
