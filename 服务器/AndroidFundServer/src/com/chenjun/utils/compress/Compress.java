package com.chenjun.utils.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Compress {
	private static final int BUFFER_LENGTH = 400;
	
	
	//压缩字节最小长度，小于这个长度的字节数组不适合压缩，压缩完会更大
	public static final int BYTE_MIN_LENGTH = 50;
	
	
	//字节数组是否压缩标志位
	public static final byte FLAG_GBK_STRING_UNCOMPRESSED_BYTEARRAY = 0;
	public static final byte FLAG_GBK_STRING_COMPRESSED_BYTEARRAY = 1;
	public static final byte FLAG_UTF8_STRING_COMPRESSED_BYTEARRAY = 2;
	public static final byte FLAG_NO_UPDATE_INFO = 3;
	
	/**  
     * 数据压缩  
     *   
     * @param is  
     * @param os  
     * @throws Exception  
     */  
    public static void compress(InputStream is, OutputStream os)   
            throws Exception {   
  
        GZIPOutputStream gos = new GZIPOutputStream(os);   
  
        int count;   
        byte data[] = new byte[BUFFER_LENGTH];   
        while ((count = is.read(data, 0, BUFFER_LENGTH)) != -1) {   
            gos.write(data, 0, count);   
        }   
  
        gos.finish();   
  
        gos.flush();   
        gos.close();   
    }   
    
    
    /**  
     * 数据解压缩  
     *   
     * @param is  
     * @param os  
     * @throws Exception  
     */  
    public static void decompress(InputStream is, OutputStream os)   
            throws Exception {   
  
        GZIPInputStream gis = new GZIPInputStream(is);   
  
        int count;   
        byte data[] = new byte[BUFFER_LENGTH];   
        while ((count = gis.read(data, 0, BUFFER_LENGTH)) != -1) {   
            os.write(data, 0, count);   
        }   
  
        gis.close();   
    } 
    
    /** 
     * 数据压缩 
     *  
     * @param data 
     * @return 
     * @throws Exception 
     */  
    public static byte[] byteCompress(byte[] data) throws Exception {  
        ByteArrayInputStream bais = new ByteArrayInputStream(data);  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
  
        // 压缩  
        compress(bais, baos);  
  
        byte[] output = baos.toByteArray();  
  
        baos.flush();  
        baos.close();  
  
        bais.close();  
  
        return output;  
    } 
    
    
    /** 
     * 数据解压缩 
     *  
     * @param data 
     * @return 
     * @throws Exception 
     */  
    public static byte[] byteDecompress(byte[] data) throws Exception {  
        ByteArrayInputStream bais = new ByteArrayInputStream(data);  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
  
        // 解压缩  
  
        decompress(bais, baos);  
  
        data = baos.toByteArray();  
  
        baos.flush();  
        baos.close();  
  
        bais.close();  
  
        return data;  
    }  
}
