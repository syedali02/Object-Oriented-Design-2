import java.io.*;

public class Reversing
{
    public static final int BUFFER = 20000000;
    private String outputPath;

    public Reversing(String OutputFolder){
        outputPath=getOutputPath(OutputFolder);
    }

    private String getOutputPath(String OutputFolder)
    {
        String seperator= File.separator;
        String path= System.getProperty("user.dir") + seperator + OutputFolder;
        File folder= new File(path);
        if(!folder.exists())
        {
            folder.mkdirs();
        }
        return path;
    }
    public void readandwrite(String filename,String outfile) throws IOException
    {
       String inputfilepath= Reversing.class.getResource(filename).getPath();
        RandomAccessFile r= new RandomAccessFile(inputfilepath,"r");
        FileOutputStream fout= new FileOutputStream(outputPath+File.separator+"rev"+outfile);
        BufferedOutputStream bout= new BufferedOutputStream(fout);
        byte [] arr= new byte[BUFFER];
        int pointer = (int)r.length()-BUFFER;
        pointer = pointergreaterthanzero(r, bout, arr, pointer);
        pointerlessthanzero(r, bout, pointer, pointer);
        closefiles(r, fout, bout);
    }

    private int pointergreaterthanzero(RandomAccessFile r, BufferedOutputStream bout, byte[] arr, int pointer) throws IOException {
        while (pointer>=0)
        {
            r.seek(pointer);
            r.read(arr);
            reverseArray(arr);
            bout.write(arr);
            pointer -= BUFFER;
        }
        return pointer;
    }

    private void closefiles(RandomAccessFile r, FileOutputStream fout, BufferedOutputStream bout) throws IOException {
        r.close();
        bout.close();
        fout.close();
    }

    private void pointerlessthanzero(RandomAccessFile r, BufferedOutputStream bout, int byteread, int pointer) throws IOException {
        if(pointer<0)
        {
            r.seek(0);
            long leftbytes=byteread+BUFFER;
            byte[] leftarr= new byte[(int) leftbytes];
            r.read(leftarr);
            reverseArray(leftarr);
            bout.write(leftarr);
        }
    }

    public void reverseArray(byte[] array)
    {
        byte temp;
        int mid= array.length/2;
        int j= array.length-1;
        for (int i=0;i<=mid;i++,j--)
        {
            temp=array[i];
            array[i]=array[j];
            array[j]=temp;
        }
    }
}
