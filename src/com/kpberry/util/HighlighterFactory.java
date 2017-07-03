package com.kpberry.util;

import com.kpberry.spirals.highlighters.Highlighter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

/**
 * Created by Kevin on 6/29/2017 for Spirals for Spirals for Spirals for Spirals.
 *
 */
@SuppressWarnings("unchecked")
public class HighlighterFactory {
    private final String expr;
    private final Highlighter instance;
    private Class<?> compiledClass;
    private Method compiledMethod;
    private String compileErrors;

    public HighlighterFactory(String expr) throws NoSuchMethodException,
            IOException, ClassNotFoundException, IllegalAccessException,
            InstantiationException {
        this.expr = expr;
        this.compile();
        this.instance = new Highlighter() {
            private final Function<Integer, Double> instance
                    = (Function<Integer, Double>) compiledClass.newInstance();

            @Override
            public Double apply(Integer integer) {
                return this.instance.apply(integer);
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
                "import java.util.function.Function;\n"
                        + "public class " + className
                        + " implements Function<Integer, Double> {\n"
                        + "\tpublic Double apply(Integer n) {\n\t\t"
                        + " return " + expr + "\n\t}"
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
        compiledMethod = compiledClass.getDeclaredMethod("apply", Integer.class);
        compiledMethod.setAccessible(true);
    }

    public double invoke(int n) throws InvocationTargetException,
            IllegalAccessException {
        return (double) this.compiledMethod.invoke(this.instance, n);
    }

    public Highlighter getInstance() {
        return instance;
    }

    public boolean hasCompileErrors() {
        return !compileErrors.isEmpty();
    }
}
