import java.io.*;
import java.nio.file.*;
import java.util.*;
public class ReportGenerator {
    private static final Path INPUT = Paths.get("input");
    private static final Path OUTPUT = Paths.get("output");
    private static void prepareFiles() throws IOException {
        Files.createDirectories(INPUT); Files.createDirectories(OUTPUT);
        for (int i = 1; i <= 4; i++)
            Files.writeString(INPUT.resolve("store" + i + ".txt"), "Store " + i + " sales=" + (i * 100) + "\\n");
        Files.writeString(INPUT.resolve("broken.txt"), "deliberately unreadable test represented by missing file");
    }
    static void generateManual(List<Path> paths, Path report) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(report);
        for (Path path : paths) {
            BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
            String line = reader.readLine(); writer.write(line == null ? "" : line);
            writer.newLine(); reader.close();
        }
        writer.close();
    }
    static void generateSafe(List<Path> paths, Path report) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(report)) {
            for (Path path : paths) {
                try (BufferedReader reader = Files.newBufferedReader(path)) {
                    String line = reader.readLine(); writer.write(line == null ? "" : line);
                    writer.newLine();
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        prepareFiles();
        List<Path> valid = List.of(INPUT.resolve("store1.txt"), INPUT.resolve("store2.txt"),
                INPUT.resolve("store3.txt"), INPUT.resolve("store4.txt"));
        generateManual(valid, OUTPUT.resolve("manual-report.txt"));
        generateSafe(valid, OUTPUT.resolve("safe-report.txt"));
        System.out.println("Manual report generated: " + OUTPUT.resolve("manual-report.txt"));
        System.out.println("Try-with-resources report generated: " + OUTPUT.resolve("safe-report.txt"));
        System.out.println("Reports identical: " +
                Files.readString(OUTPUT.resolve("manual-report.txt"))
                .equals(Files.readString(OUTPUT.resolve("safe-report.txt"))));
        List<Path> failing = new ArrayList<>(valid); failing.add(INPUT.resolve("missing-store.txt"));
        try { generateSafe(failing, OUTPUT.resolve("failing-report.txt")); }
        catch (IOException e) { System.out.println("Injected failure logged: " + e.getMessage()); }
        System.out.println("Try-with-resources closes opened resources when failure occurs.");
    }
}