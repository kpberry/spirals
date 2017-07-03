package com.kpberry.util;

import com.kpberry.math.inclusion_criteria.InclusionCriterion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * Created by Kevin on 6/29/2017 for Spirals for Spirals for Spirals.
 *
 */
@SuppressWarnings("unchecked")
public class InclusionCriterionFactory {
    private final String expr;
    private final String variable;
    private final InclusionCriterion instance;
    private Class<?> compiledClass;
    private String compileErrors;

    public InclusionCriterionFactory(String expr, String variable)
            throws NoSuchMethodException, IOException, ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        this.expr = expr;
        this.compileErrors = "";
        if ((variable == null) || variable.matches("\\s*")) {
            this.variable = "n";
        } else {
            this.variable = variable;
        }
        this.compile();
        this.instance = new InclusionCriterion() {
            private final Predicate<Integer> instance
                    = (Predicate<Integer>) compiledClass.newInstance();

            @Override
            public boolean test(Integer integer) {
                return instance.test(integer);
            }

            @Override
            public String toString() {
                return variable + ": " + expr;
            }
        };
    }

    public final void compile() throws NoSuchMethodException,
            IOException, ClassNotFoundException {
        String name = "test";
        Path file = Files.createTempFile(name, ".java");
        file.toFile().deleteOnExit();
        String fileName = file.getFileName().toString();
        String className = fileName.substring(0, fileName.lastIndexOf("."));
        String output =
                "import java.util.function.Predicate;\n"
                        + "import static java.lang.Math.*;\n"
                        + "public class " + className
                        + " implements Predicate<Integer> {\n"
                        + "\tpublic boolean test(Integer " + this.variable + ") {\n\t\t"
                        + " return " + expr + ";\n\t}"
                        + "\n}";
        Files.write(file, output.getBytes(StandardCharsets.UTF_8));

        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        PrintWriter error = new PrintWriter(errorStream);
        int errorCode = com.sun.tools.javac.Main.compile(new String[]{
                "-classpath", "bin", "-d", file.getParent().toString(),
                file.toString()
        }, error);
        this.compileErrors = new String(
                errorStream.toByteArray(), StandardCharsets.UTF_8
        ).replace(fileName, name);
        if (!this.compileErrors.isEmpty()) {
            System.err.println(this.compileErrors);
        }

        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{
                file.getParent().toUri().toURL()
        });
        compiledClass = Class.forName(className, true, loader);
        Method compiledMethod = compiledClass.getDeclaredMethod("test", Integer.class);
        compiledMethod.setAccessible(true);
    }

    public InclusionCriterion getInstance() {
        return instance;
    }

    public String getCompileErrors() {
        return compileErrors;
    }

    public boolean hasCompileErrors() {
        return !compileErrors.isEmpty();
    }
}
