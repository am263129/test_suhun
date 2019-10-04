package social.media.sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.File;

/**
 * Created by pratap.kesaboyina on 24-05-2016.
 */
public class SftpClass {


    public static void uploadFile(File file) {


        String host = "", username = "", password = "";


        String localFilePath = file.getAbsolutePath();

        String fileName = localFilePath.substring(localFilePath.lastIndexOf("/") + 1);

        String remoteFilePath = "/home/dev/upload_with_sftp" + fileName;


        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(username, host, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.put(localFilePath, remoteFilePath);
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();

        }
    }
}