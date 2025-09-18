package RuayRuayMain;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.File;
import java.io.FileInputStream;

public class SFTPUploadImage {
	public static void uploadFile(String localFilePath, String remoteDir, String sftpHost, String sftpUser, String sftpPassword) {
	    JSch jsch = new JSch();
	    Session session = null;
	    ChannelSftp channelSftp = null;

	    try {
	        session = jsch.getSession(sftpUser, sftpHost, 22);
	        session.setPassword(sftpPassword);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();

	        channelSftp = (ChannelSftp) session.openChannel("sftp");
	        channelSftp.connect();

	        File file = new File(localFilePath);
	        if (!file.exists()) {
	            throw new RuntimeException("Local file does not exist: " + localFilePath);
	        }

	        channelSftp.put(new FileInputStream(file), remoteDir + "/" + file.getName());
	        System.out.println("File uploaded successfully!");
	    } catch (Exception e) {
	        System.err.println("SFTP upload failed: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        if (channelSftp != null) channelSftp.disconnect();
	        if (session != null) session.disconnect();
	    }
	}

}
