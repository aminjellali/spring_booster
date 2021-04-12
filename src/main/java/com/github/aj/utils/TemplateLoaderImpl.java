package com.github.aj.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

@Service
/**
 * 
 * @author amin.jellali
 *
 */
public class TemplateLoaderImpl {

	private PrintToTerminal pt;

	@Autowired
	public TemplateLoaderImpl(PrintToTerminal pt) {
		this.pt = pt;
	}

	/**
	 * 
	 * @param templatesFilePath
	 * @param data
	 * @param templateName
	 * @return
	 */
	public String generateCode(String templatesFilePath, Map<String, Object> data, String templateName) {
		pt.infos("Generating code");
		Configuration cfg = new Configuration(new Version("2.3.23"));
		try {
			pt.infos(new File(templatesFilePath).getPath());
			cfg.setDirectoryForTemplateLoading(new File(templatesFilePath));
		} catch (IOException e) {
			pt.errors("Failed to configure template file.");
			e.printStackTrace();
		}
		cfg.setDefaultEncoding("UTF-8");
		Template template;
		try {
			template = cfg.getTemplate(templateName);
			StringWriter out = new StringWriter();
			template.process(data, out);
			if (out.toString() == null || out.toString().equals("")) {
				pt.errors("code generation failed");
			}
			pt.infos("Successfully generated code");
			return out.toString();
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}
		pt.errors("Failed to write code.");
		return "";
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public String formatJavaCode(String code) {

		Formatter formatter = new Formatter();
		try {
			return formatter.formatSource(code);
		} catch (FormatterException e) {
			e.printStackTrace();
			pt.warns("Failed to format code.");
		}
		return code;
	}

	/**
	 * 
	 * @param unformattedJson
	 * @return
	 */
	public String formatJson(String unformattedJson) {
		JSONObject json = new JSONObject(unformattedJson);
		return json.toString(2);
	}
}
