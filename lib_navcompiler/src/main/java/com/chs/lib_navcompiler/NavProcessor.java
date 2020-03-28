package com.chs.lib_navcompiler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chs.lib_navannotation.ActivityDestination;
import com.chs.lib_navannotation.FragmentDestination;
import com.google.auto.service.AutoService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;


/**
 * author：chs
 * date：2020/3/26
 * des：
 */
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"com.chs.lib_navannotation.ActivityDestination","com.chs.lib_navannotation.FragmentDestination"})
public class NavProcessor extends AbstractProcessor {
    private static final String OUTPUT_FILE_NAME = "destination.json";
    private Messager messager;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        //日志工具
        messager = processingEnv.getMessager();
        //文件处理工具
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //拿到带有这两个注解的类的集合
        Set<? extends Element> fragmentElement = roundEnv.getElementsAnnotatedWith(FragmentDestination.class);
        Set<? extends Element> activityElement = roundEnv.getElementsAnnotatedWith(ActivityDestination.class);

        if(!fragmentElement.isEmpty()||!activityElement.isEmpty()){
            Map<String, JSONObject> destMap = new HashMap<>();
            handleDestination(fragmentElement,FragmentDestination.class,destMap);
            handleDestination(activityElement,ActivityDestination.class,destMap);
            FileOutputStream fos = null;
            OutputStreamWriter writer = null;
            //将map转换为json文件，保存到app/src/asset中
            try {
                //filer.createResource方法用来生成源文件
                //StandardLocation.CLASS_OUTPUT java文件生成class文件的位置，/build/intermediates/javac/debug/classes/目录下
                //StandardLocation.SOURCE_OUTPUT：java文件的位置，一般在/app/build/generated/source/apt/目录下
                FileObject resource = filer.createResource(StandardLocation.SOURCE_OUTPUT, "", OUTPUT_FILE_NAME);
                String resourcePath = resource.toUri().getPath();
                messager.printMessage(Diagnostic.Kind.NOTE,"resourcePath:"+resourcePath);

                String appPath = resourcePath.substring(0,resourcePath.indexOf("build"));
                String assetPath = appPath + "src/main/assets";

                File assetDir = new File(assetPath);
                if(!assetDir.exists()){
                    assetDir.mkdir();
                }
                File assetFile = new File(assetDir,OUTPUT_FILE_NAME);
                if(assetFile.exists()){
                    assetFile.delete();
                }
                assetFile.createNewFile();
                String content = JSON.toJSONString(destMap);

                fos = new FileOutputStream(assetFile);
                writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                writer.write(content);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(fos!=null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(writer!=null){
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }

    private void handleDestination(Set<? extends Element> elements, Class<? extends Annotation> desAnnotationClazz,
                                   Map<String, JSONObject> destMap) {

        for (Element element : elements) {
            //TypeElement代表类或者接口，因为定义的注解是写在类上面的，所以可以直接转换成TypeElement
            TypeElement typeElement = (TypeElement) element;
            //获取全类名
            String className = typeElement.getQualifiedName().toString();
            int id = Math.abs(className.hashCode());
            String pageUrl = null;
            boolean needLogin = false;
            boolean asStarter = false;
            boolean isFragment = true;
//            messager.printMessage(Diagnostic.Kind.NOTE,"className:"+className);
            Annotation annotation = element.getAnnotation(desAnnotationClazz);
            //根据不同的注解获取注解的参数
            if(annotation instanceof FragmentDestination){
                FragmentDestination destination =  (FragmentDestination) annotation;
                pageUrl = destination.pageUrl();
                needLogin = destination.needLogin();
                asStarter = destination.asStarter();
                isFragment = true;
            }else if(annotation instanceof ActivityDestination){
                ActivityDestination destination =  (ActivityDestination) annotation;
                pageUrl = destination.pageUrl();
                needLogin = destination.needLogin();
                asStarter = destination.asStarter();
                isFragment = false;
            }
            //将参数封装成JsonObject后放到map中保存
            if(destMap.containsKey(pageUrl)){
               messager.printMessage(Diagnostic.Kind.ERROR,"不允许使用相同的pagUrl:"+className);
            }else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",id);
                jsonObject.put("className",className);
                jsonObject.put("pageUrl",pageUrl);
                jsonObject.put("needLogin",needLogin);
                jsonObject.put("asStarter",asStarter);
                jsonObject.put("isFragment",isFragment);
                destMap.put(pageUrl,jsonObject);
            }
        }
    }
}
