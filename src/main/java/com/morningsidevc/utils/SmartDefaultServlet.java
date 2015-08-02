package com.morningsidevc.utils;


import com.google.javascript.jscomp.*;
import com.google.javascript.jscomp.Compiler;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author float.lu
 */
public class SmartDefaultServlet extends HttpServlet {

    private ConcurrentHashMap<String,String> jsCache = new ConcurrentHashMap<String, String>();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {

        serveResource(request, response);

    }

    protected String getRelativePath(HttpServletRequest request) {
        if (request.getAttribute(
                "javax.servlet.include.request_uri") != null) {
            String result = (String) request.getAttribute(
                    "javax.servlet.include.path_info");
            if (result == null) {
                result = (String) request.getAttribute(
                        "javax.servlet.include.servlet_path");
            } else {
                result = request.getAttribute(
                "javax.servlet.include.servlet_path") + result;
            }
            if ((result == null) || (result.equals(""))) {
                result = "/";
            }
            return (result);
        }

        String result = request.getPathInfo();
        if (result == null) {
            result = request.getServletPath();
        } else {
            result = request.getServletPath() + result;
        }
        if ((result == null) || (result.equals(""))) {
            result = "/";
        }
        return (result);

    }

    protected void serveResource(HttpServletRequest request,
                                 HttpServletResponse response)
            throws IOException, ServletException {

        String path = getRelativePath(request);
        if(jsCache.get(path) != null){
            String result = jsCache.get(path);
            writeResponse(result, response);
            return;
        }else{
            String realPath = this.getServletContext().getRealPath(path);
            String compiledFile = compileJavaScript(realPath);
            jsCache.put(path, compiledFile);
            writeResponse(jsCache.get(path), response);
        }
    }

    private String compileJavaScript(String realPath){
        SourceFile sourceFile = SourceFile.fromFile(new File(realPath));
        Compiler compiler = createCompiler();
        CompilerOptions options = new CompilerOptions();
        CompilationLevel level = CompilationLevel.SIMPLE_OPTIMIZATIONS;
        level.setOptionsForCompilationLevel(options);
        Result result = compiler.compile(SourceFile.fromCode("extern", ""), sourceFile, options);
        if(result.success){
            return compiler.toSource();
        }else{
            return null;
        }
    }


    private Compiler createCompiler() {
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        PrintStream errWrapper = new PrintStream(err);
        Compiler compiler = new Compiler(errWrapper);
        return compiler;
    }


    private void writeResponse(String result, HttpServletResponse response) throws IOException{
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(result.getBytes());
        outputStream.flush();
    }
}
