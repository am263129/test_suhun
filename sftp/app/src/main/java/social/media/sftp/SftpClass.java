package social.media.sftp;

import android.util.Log;

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


    public static void uploadFile(String path) {


        final String host = "10.3.1.144", username = "dev", password = "password123!";

        File file  =  new File(path);
        Log.e("Test",file.getName());
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        final String localFilePath = path+"/"+fileName;


        final String remoteFilePath = "/home/dev/sftp_test" + fileName;


        final JSch jsch = new JSch();
        Thread thread =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Session session = null;
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
                    Log.e("error_1", e.toString());
                } catch (SftpException e) {
                    e.printStackTrace();
                    Log.e("error_2",e.toString());

                }
            }
        });
        thread.start();

    }
}