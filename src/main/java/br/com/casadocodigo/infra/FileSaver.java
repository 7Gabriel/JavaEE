package br.com.casadocodigo.infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;

import javax.servlet.http.Part;


public class FileSaver {
	
	public static final String SERVER_PATH = "C:\\casadocodigo";
	
	public String write(Part arquivo, String path){
		
		String relativePath = path + "/" + arquivo.getSubmittedFileName();
		try {
			arquivo.write(SERVER_PATH + "/" +relativePath);
			return relativePath;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	public static void tranfer(Path source, OutputStream outputStream) {
		try {
			FileInputStream inputStream = new FileInputStream(source.toFile());
			try(ReadableByteChannel channel = Channels.newChannel(inputStream);
					WritableByteChannel byteChannel = Channels.newChannel(outputStream)){
				ByteBuffer buffer = java.nio.ByteBuffer.allocate(1024 * 10);
				
				while(channel.read(buffer) != -1){
					buffer.flip();
					byteChannel.write(buffer);
					buffer.clear();
				}
				
			}catch(IOException e){
				throw new RuntimeException(e);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
