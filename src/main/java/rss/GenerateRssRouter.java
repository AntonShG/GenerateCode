package rss;

import com.squareup.javapoet.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.rss.RssEndpoint;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Modifier;
import java.io.File;

public class GenerateRssRouter {

    public void generateRssRouter(Object o) {
        TypeSpec rssRouter = TypeSpec
                .classBuilder("RssRouter")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Component.class)
                .superclass(RouteBuilder.class)
                .addField(FieldSpec.builder(RssEndpoint.class,"rssEndpoint")
                        .addModifiers(Modifier.PRIVATE)
                        .build())
                .addMethod(getMethodSpec(o))
                .build();
        try {
            JavaFile javaFile = JavaFile
                    .builder("rss", rssRouter)
                    .build();
            javaFile.writeTo(new File("src/main/java"));
        } catch (Exception e) {

        }
    }

    private MethodSpec getMethodSpec(Object o) {
        return MethodSpec
                .methodBuilder("configure")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addCode(getCode(o))
                .build();
    }

    public CodeBlock getCode(Object o) {
        String rssURI = null;
        String filter = null;
        String process = null;
        String to = null;
        if (o instanceof EntityRss) {
            rssURI = ((EntityRss) o).getFrom();
            filter = ((EntityRss) o).getFilter();
            process = ((EntityRss) o).getProcces();
            to = ((EntityRss) o).getProcces();
        }
        String statement = "";
        statement += "from(\"rssEndpoint\").streamCaching().threads(12, 24).marshal().rss()\n";
        if (filter != null) statement += ".filter().method(RssFilter.class,\"" + filter + "\")\n";
        if (process != null) statement += ".process(\"" + process + "\")\n";
        if (to != null) statement += ".to(\"" + to + "\")";

        return CodeBlock.builder()
                .addStatement("rssEndpoint = endpoint(\"" + rssURI + "\", RssEndpoint.class)")
                .addStatement(statement)
                .build();
    }

}
