package com.voteroid.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;

public class DeployPresentation {
    public static void main(String[] args) {
        try {
            Path sourceFilePath = Path.of(args[0]);
            Path processorPath = Path.of(args[1]);
            Path revealRootPath = Path.of(args[2]);
            Path footerFilePath = Path.of("src/main/resources/static/footer.html");

            deployPresentation(sourceFilePath, processorPath, revealRootPath, footerFilePath);
        } catch (IOException | InterruptedException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void deployPresentation(Path sourceFilePath, Path processorPath, Path revealRootPath, Path footerFilePath) throws IOException, InterruptedException {
        String adocFileName = sourceFilePath.getFileName().toString();

        Path tempDirPath = Files.createTempDirectory("presentation");

        Files.copy(sourceFilePath, tempDirPath.resolve(adocFileName));
        Files.copy(footerFilePath, tempDirPath.resolve(adocFileName.replace(".adoc", "-docinfo-footer.html")));

        ProcessBuilder processBuilder = new ProcessBuilder(processorPath.toString(), tempDirPath.resolve(adocFileName).toString());
        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode == 0) {
            String presentationFileName = adocFileName.replace(".adoc", ".html");
            Path generatedHtmlPath = tempDirPath.resolve(presentationFileName);
            Path targetPath = revealRootPath.resolve(presentationFileName);
            Files.move(generatedHtmlPath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Presentation deployed successfully.");
        } else {
            System.err.println("Command execution failed with exit code: " + exitCode);
        }
    }
}

