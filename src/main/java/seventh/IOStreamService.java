package seventh;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class IOStreamService {

    private IOStreamService () {}

    public static void writeArray(int[] array, OutputStream output) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(output)) {
            for (int data: array) {
                dos.writeInt(data);
            }
        }
    }

    public static void writeArray(int[] array, Writer writer) {
        try (PrintWriter osw = new PrintWriter(writer)) {
            for (int data: array) {
                final String string = Integer.toString(data);
                osw.write(string);
                osw.write(" ");
            }
        }
    }

    public static int[] readArray(InputStream input) throws IOException {
        int[] numbers;
        try (DataInputStream dis = new DataInputStream(input)) {
            LinkedList<Integer> list = new LinkedList<>();
            while (input.available() > 0) {
                list.add(dis.readInt());
            }
            numbers = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                numbers[i] = list.get(i);
            }
        }
        return numbers;
    }

    public static int[] readArray(Reader reader) throws IOException {
        int[] numbers;
        try (BufferedReader br = new BufferedReader(reader)) {
            LinkedList<Integer> listOfNumbers = new LinkedList<>();
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String[] numbersString = line.split(" ");
                for (String string : numbersString) {
                    listOfNumbers.add(Integer.parseInt(string));
                }
            }
            numbers = new int[listOfNumbers.size()];
            for (int i = 0; i < listOfNumbers.size(); i++) {
                numbers[i] = listOfNumbers.get(i);
            }
        }
        return numbers;
    }

    public static int[] readArray(RandomAccessFile raf, long pos) throws IOException {
        LinkedList<Integer> list = new LinkedList<>();
        raf.seek(pos * Integer.BYTES);
        while (true) {
            try {
                list.add(raf.readInt());
            } catch (EOFException e) {
                break;
            }
        }
        int[] numbers = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            numbers[i] = list.get(i);
        }
        return numbers;
    }

    public static List<File> getListOfFiles (File dir, String extension) {
        LinkedList<File> list = new LinkedList<>();
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("The file isn't a directory: " + dir.getName());
        }
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.getName().endsWith(extension)) {
                list.add(file);
            }
        }
        return list;
    }

    public static List<File> getListOfFile(File dir, Pattern regex) {
        LinkedList<File> list = new LinkedList<>();
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("The file isn't a directory: " + dir.getName());
        }
        File[] files = dir.listFiles();
        assert files != null;
        for (File file: files) {
            Matcher matcher = regex.matcher(file.getName());
            if (matcher.matches()) {
                list.add(file);
            }
            if (file.isDirectory()) {
                list.addAll(getListOfFile(file, regex));
            }
        }
        return list;
    }
}
