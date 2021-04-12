package com.github.aj.entry;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.github.aj.utils.PrintToTerminal;
import com.github.aj.utils.TemplateLoaderImpl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

@ShellComponent
class MyShell {

	@Value("${templates_files_location}")
	private String java_files_templates;

	@Autowired
	PrintToTerminal pt;
	@Autowired TemplateLoaderImpl tl;

	@ShellMethod("create")
	public String create(String type, String name, String parentPackageName) throws IOException {
		pt.demo();
		File folder = new File(java_files_templates);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (isTemplateFile(file)) {
				// split
				// System.out.println(file.getName());
				System.out.println(file.getName());

			}
		}
		return type + name + parentPackageName;
	}

	private boolean isTemplateFile(File file) {
		// System.out.println(Stream.of(file.getName().split("\\.")).collect(Collectors.toSet()));
		return file.isFile() && file.getName().split("\\.")[1].equals("spring_cli");
	}

	@ShellMethod("print telmplate")
	public void print() {
		pt.infos("Hello");
		Map<String,Object> data = new HashMap<>();
		data.put("msg", "HelloWorld");
		String code = tl.generateCode(java_files_templates, data, "test.ftl");
		pt.infos(code);
		code = tl.formatJavaCode(code);
		pt.infos(code);
		String unj = "{\"fruit\": \"Apple\",   \"size\": \"Large\",    \"color\": \"Red\"}";
		pt.infos(unj);
		JSONObject json = new JSONObject(unj);
		System.out.println(json.toString(2));
		
	}

}