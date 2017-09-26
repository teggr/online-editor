package com.robintegg.editor.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sourceforge.plantuml.SourceStringReader;

@Controller
@RequestMapping("/diagram")
public class DiagramController {

    @GetMapping(path = "/{plantUmlSourceBase64}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody ResponseEntity<InputStreamResource> getDiagram(
            @PathVariable("plantUmlSourceBase64") String plantUmlSourceBase64) throws IOException {

        String plantUmlSource = new String(Base64.getDecoder().decode(plantUmlSourceBase64));

        System.out.println(plantUmlSource);

        SourceStringReader reader = new SourceStringReader(plantUmlSource);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String desc = reader.generateImage(out);

        System.out.println("generated image " + desc);

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

        return ResponseEntity.ok(new InputStreamResource(in));

    }

}
