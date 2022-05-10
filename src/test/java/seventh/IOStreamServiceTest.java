package seventh;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IOStreamServiceTest {
    private File tempFile;
    private int[] array;

    @BeforeTest
    public void setUpArray() {
        array = new int[200];
        for (int i = 0; i <= 100; i++) {
            array[i] = i * i;
        }
        for (int i = 101; i < 200; i++) {
            array[i] = -i * i;
        }
    }

    @BeforeMethod
    public void setUpFile() throws IOException {
        tempFile = Files.createTempFile("IOStreamServiceTest", ".txt").toFile();
    }

    @AfterMethod
    public void deleteFile() {
        tempFile.delete();
    }

    @Test
    public void testWriteByteArray() throws IOException {
        DataOutputStream output = new DataOutputStream(new FileOutputStream(tempFile));
        IOStreamService.writeArray(array, output);
        DataInputStream input = new DataInputStream(new FileInputStream(tempFile));
        int[] arr = IOStreamService.readArray(input);
        Assert.assertArrayEquals(array, arr);
    }

    @Test
    public void testWriteCharArray() throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(tempFile), StandardCharsets.UTF_8);
        IOStreamService.writeArray(array, writer);
        FileReader reader = new FileReader(tempFile);
        int[] arr = IOStreamService.readArray(reader);
        Assert.assertArrayEquals(array, arr);
    }

    @Test
    public void testReadArrayRandom() throws IOException {
        RandomAccessFile raf = new RandomAccessFile(tempFile, "r");
        DataOutputStream output = new DataOutputStream(new FileOutputStream(tempFile));
        IOStreamService.writeArray(array, output);
        int[] arr = IOStreamService.readArray(raf, 101);
        int[] expected = new int[99];
        for (int i = 0; i < 99; i++) {
            expected[i] = - (i + 101) * (i + 101);
        }
        Assert.assertArrayEquals(expected, arr);
    }

    @Test
    public void testGetListOfFiles() {
        File file1 = mock(File.class);
        when(file1.isDirectory()).thenReturn(false);
        when(file1.isFile()).thenReturn(true);
        when(file1.getName()).thenReturn("IOServiceTest1.txt");

        File file2 = mock(File.class);
        when(file2.isDirectory()).thenReturn(false);
        when(file2.isFile()).thenReturn(true);
        when(file2.getName()).thenReturn("IOServiceTest2.txt");

        File dir1 = mock(File.class);
        when(dir1.isDirectory()).thenReturn(true);
        when(dir1.getName()).thenReturn("dir.txt");

        File dir = mock(File.class);
        when(dir.isDirectory()).thenReturn(true);
        when(dir.listFiles()).thenReturn(new File[]{file1, file2, dir1});

        ArrayList<File> expectedList = new ArrayList<>(List.of(file1, file2, dir1));
        assertEquals(IOStreamService.getListOfFiles(dir, ".txt"), expectedList);
    }

    @Test
    public void testGetListOfRegexFiles() {
        Pattern regex = Pattern.compile("^data[a-zA-Z | 0-9 | .]*", Pattern.CASE_INSENSITIVE);
        File file0 = mock(File.class);
        when(file0.isDirectory()).thenReturn(false);
        when(file0.getName()).thenReturn("datalog.txt");

        File file1 = mock(File.class);
        when(file1.getName()).thenReturn("dataConst.txt");
        when(file1.isDirectory()).thenReturn(false);

        File file2 = mock(File.class);
        when(file2.getName()).thenReturn("dataVariables.txt");
        when(file2.isDirectory()).thenReturn(false);

        File file3 = mock(File.class);
        when(file3.isDirectory()).thenReturn(false);
        when(file3.getName()).thenReturn("gitignore.txt");

        File dir1 = mock(File.class);
        when(dir1.getName()).thenReturn("DataArgs");
        when(dir1.isDirectory()).thenReturn(true);
        when(dir1.listFiles()).thenReturn(new File[]{file1, file2});

        File dir = mock(File.class);
        when(dir.isDirectory()).thenReturn(true);
        when(dir.getName()).thenReturn("DataCenter");
        when(dir.listFiles()).thenReturn(new File[]{dir1, file0, file3});

        List<File> expected = new ArrayList<>(List.of(dir1, file1, file2, file0));
        assertEquals(IOStreamService.getListOfFile(dir, regex), expected);
    }
}