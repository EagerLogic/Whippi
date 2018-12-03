/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.annotation.processors;

import com.google.auto.service.AutoService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 *
 * @author david
 */
@SupportedAnnotationTypes(
        "com.el.whippi.annotations.BeAction")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class WhippiBeActionProcessor extends AbstractProcessor {

    private Map<String, List<BeMethod>> controllers = new HashMap<>();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        System.out.println("============ WHIPPI ANNOTATION PROCESSOR ===========");
        
        if (annotations.size() < 1) {
            return true;
        }

        for (TypeElement annotation : annotations) {
            Set<? extends Element> elements = env.getElementsAnnotatedWith(annotation);
            for (Element elem : elements) {
                if (elem.getKind() != ElementKind.METHOD) {
                    this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "The @BeAction annotation only applicable on methods!", elem);
                    continue;
                }
                processElement((ExecutableElement) elem, controllers);
            }
        }
        
        try {
            generateClasses();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return true;
    }

    private void processElement(ExecutableElement methodElement, Map<String, List<BeMethod>> controllers) {
        System.out.println("Processing method: " + methodElement.getSimpleName());

        TypeElement classElement = (TypeElement) methodElement.getEnclosingElement();
        if (!classElement.getKind().equals(ElementKind.CLASS)) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "@BeAction annotation can be applyed in classes only!", methodElement);
            return;
        }

        if (classElement.getModifiers().contains(Modifier.ABSTRACT)) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "@BeAction annotation can not be applied on abstract classes!", methodElement);
            return;
        }

        DeclaredType superClasstype = (DeclaredType) classElement.getSuperclass();

        TypeElement superClassElement = (TypeElement) this.processingEnv.getTypeUtils().asElement(superClasstype);
        System.out.println("Superclass: " + superClassElement.getQualifiedName());
        if (!superClassElement.getQualifiedName().toString().equals("com.el.whippi.AController")) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "@BeAction annotation must be applied inside a Whippi controller!", methodElement);
            return;
        }

        if (methodElement.getModifiers().contains(Modifier.ABSTRACT)) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "@BeAction annotation can not be applied on abstract!", methodElement);
            return;
        }
        if (methodElement.getModifiers().contains(Modifier.STATIC)) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "@BeAction annotation can not be applied on abstract!", methodElement);
            return;
        }

        String fullClassName = classElement.getQualifiedName().toString();
        String className = classElement.getSimpleName().toString();
        String feFullClassName = fullClassName + "_FE";
        String feClassName = className + "_FE";

        List<BeMethod> methods = controllers.get(feFullClassName);
        if (methods == null) {
            methods = new ArrayList<>();
            controllers.put(feFullClassName, methods);
        }

        String methodName = methodElement.getSimpleName().toString();
        List<BeMethodParam> params = new ArrayList<>();

        for (VariableElement paramElement : methodElement.getParameters()) {
            String paramName = paramElement.getSimpleName().toString();
            String paramType = paramElement.asType().toString();
            if (paramElement.asType().getKind() != TypeKind.DECLARED) {
                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "The @BeAction annotation does not support primitive type parameters yet!", paramElement);
            }

            System.out.println("    param: " + paramName + ": " + paramType);

            BeMethodParam param = new BeMethodParam(paramName, paramType);
            params.add(param);
        }

        BeMethod method = new BeMethod(methodName, params);
        methods.add(method);

    }

    private void generateClasses() throws IOException {
        for (Map.Entry<String, List<BeMethod>> entry : controllers.entrySet()) {
            generateClass(entry.getKey(), entry.getValue());
        }
    }

    private void generateClass(String className, List<BeMethod> methods) throws IOException {
        JavaFileObject builderFile = processingEnv.getFiler()
                .createSourceFile(className);
        
        String packageName = className.substring(0, className.lastIndexOf("."));
        String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
        String controllerClassName = className.substring(0, className.length() - 3);

        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
            out.println("package " + packageName + ";");
            out.println();
            out.println("public final class " + simpleClassName + " {");
            out.println();
            
            for (BeMethod method : methods) {
                out.print("    public com.el.whippi.feactions.AFeAction " + method.getName() + "(");
                
                boolean isFirst = true;
                for (BeMethodParam param : method.getParams()) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        out.print(", ");
                    }
                    
                    out.print("com.el.whippi.feactions.AFeValue<" + param.getType() + ">" + " " + param.getName());
                }
                out.println(") {");
                
                out.println("        java.util.List<com.el.whippi.feactions.AFeValue<?>> values "
                        + "= new java.util.ArrayList<com.el.whippi.feactions.AFeValue<?>>();");
                for (BeMethodParam param : method.getParams()) {
                    out.println("        values.add(" + param.getName() + ");");
                }
                
                out.println("        return new com.el.whippi.feactions.CallBeMethod(\"" + method.getName() 
                        + "\", values);");
                
                out.println("    }");
                
                out.println();
            }
            
            out.println("}");
        }
    }

}
